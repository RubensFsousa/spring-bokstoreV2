package com.study.spring.bookstore.renters.domain.services.impl;

import com.study.spring.base.shared.exceptions.BusinessException;
import com.study.spring.base.shared.exceptions.EntityNotFoundException;
import com.study.spring.base.shared.models.PageResponse;
import com.study.spring.bookstore.renters.api.controller.models.DTOs.GetRenterDetailsResponseDTO;
import com.study.spring.bookstore.renters.api.controller.models.DTOs.GetRenterPageResponseDTO;
import com.study.spring.bookstore.renters.api.controller.models.DTOs.RenterCreateRequestDTO;
import com.study.spring.bookstore.renters.api.controller.models.DTOs.RenterUpdateRequestDTO;
import com.study.spring.bookstore.renters.domain.entities.RenterEntity;
import com.study.spring.bookstore.renters.domain.mapper.RenterMapper;
import com.study.spring.bookstore.renters.domain.repositories.RenterRepository;
import com.study.spring.bookstore.renters.domain.services.RenterService;
import com.study.spring.bookstore.renters.domain.specs.RenterSpecs;
import com.study.spring.bookstore.rents.domain.enums.RentStatus;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RenterServiceImpl implements RenterService {

    private final RenterRepository renterRepository;
    private final RenterMapper renterMapper;

    @Override
    public void create(RenterCreateRequestDTO request) {
        var renter = renterMapper.toRenterEntity(request);
        validateRenterName(renter);
        validateRenterCPF(renter);
        validateRenterTelephone(renter);

        renterRepository.save(renter);
    }

    @Override
    public GetRenterDetailsResponseDTO getById(Integer id) {
        var renter = getRenterByIdOrElseThrow(id);
        validateIsDeleted(renter);
        return renterMapper.toRenterDetailsResponseDTO(renter);
    }

    @Override
    public PageResponse<GetRenterPageResponseDTO> getRenterPage(String search, PageRequest pageable) {
        Specification<RenterEntity> spec = Specification
                .where(RenterSpecs.containsTextInAllColumns(search))
                .and(RenterSpecs.isDeleted(false));

        var rentersPage = renterRepository.findAll(spec, pageable);
        return renterMapper.toRenterPageResponseDTO(rentersPage);
    }

    @Override
    public void update(RenterUpdateRequestDTO request) {
        var renter = getRenterByIdOrElseThrow(request.id());
        validateIsDeleted(renter);

        renter = renter.toBuilder()
                .name(request.name())
                .email(renter.getEmail())
                .telephone(request.telephone())
                .address(request.address())
                .cpf(request.cpf())
                .build();

        validateRenterName(renter);
        validateRenterCPF(renter);

        renterRepository.save(renter);
    }

    @Override
    public void delete(Integer id) {
        var renter = getRenterByIdOrElseThrow(id);
        validateIsDeleted(renter);
        var rentsWithRenter = renter.getRents().stream().filter(rent -> !rent.getStatus().equals(RentStatus.DELIVERED)).toList();
        if (!rentsWithRenter.isEmpty()) throw new BusinessException("this renter have a pendency");
        renterRepository.save(renter.toBuilder().isDeleted(true).build());
    }

    private RenterEntity getRenterByIdOrElseThrow(Integer id) {
        return renterRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Renter not found"));
    }

    private void validateIsDeleted(RenterEntity renter) {
        if(renter.isDeleted()) throw new BusinessException("this Renter was deleted");
    }

    private void validateRenterTelephone(RenterEntity renter) {
        var savedRenter = renterRepository.findByTelephone(renter.getTelephone()).orElse(null);
        if (savedRenter != null && !savedRenter.getId().equals(renter.getId())){
            throw new BusinessException("RenterTelephoneAlreadyExists");
        }
    }

    private void validateRenterName(RenterEntity renter) {
        var savedRenter = renterRepository.findByName(renter.getName()).orElse(null);
        if (savedRenter != null && !savedRenter.getId().equals(renter.getId())) {
            throw new BusinessException("RenterNameAlreadyExists");
        }
    }

    private void validateRenterCPF(RenterEntity renter) {
        var savedRenter = renterRepository.findByCpf(renter.getCpf()).orElse(null);
        if (savedRenter != null && !savedRenter.getId().equals(renter.getId())) {
            throw new BusinessException("RenterCpfAlreadyExists");
        }
    }
    
}
