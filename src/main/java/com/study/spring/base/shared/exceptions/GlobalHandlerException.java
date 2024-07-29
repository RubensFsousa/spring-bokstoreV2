package com.study.spring.base.shared.exceptions;

import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.study.spring.base.shared.models.DTOs.ProblemDTO;
import com.study.spring.base.shared.models.DTOs.ProblemObjectDTO;
import com.study.spring.base.shared.models.enums.ProblemType;
import com.study.spring.base.shared.utils.DateTimeUtil;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundHandlerException(EntityNotFoundException ex) {
        try {
            var status = HttpStatus.BAD_REQUEST;
            var problemType = ProblemType.RESOURCE_NOT_FOUND;
            var detail = ex.getMessage();

            var problemDTO = problemBuilder(problemType)
                    .status(status.value())
                    .detail(detail)
                    .userMessage(detail)
                    .build();
            return new ResponseEntity<>(problemDTO, status);

        } catch (Exception exception) {
            return handleUncaught(exception);
        }
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handleBusiness(BusinessException ex) {
        try {
            var status = HttpStatus.BAD_REQUEST;
            var problemType = ProblemType.BUSINESS_ERROR;
            var detail = ex.getMessage();

            var problem = problemBuilder(problemType)
                    .status(status.value())
                    .detail(detail)
                    .userMessage(detail)
                    .build();

            return new ResponseEntity<>(problem, status);
        } catch (Exception exception) {
            return handleUncaught(exception);
        }
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        try {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            ProblemType problemType = ProblemType.INVALID_PARAMETER;

//            String detail = internalization.getMessage(
//                    "URLReceivedInvalidTypeParameter",
//                    ex.getName(), ex.getValue(),
//                    Objects.requireNonNull(ex.getRequiredType()).getSimpleName()
//            );

            ProblemDTO problem = problemBuilder(problemType)
                    .status(status.value())
                    .detail("calma la patrao")
                    .userMessage("GenericErrorToUser")
                    .build();

            return new ResponseEntity<>(problem, status);
        } catch (Exception exception) {
            return handleUncaught(exception);
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        var status = HttpStatus.BAD_REQUEST;
        var problemType = ProblemType.INVALID_DATA;
        var detail = "FieldsAreInvalid";

        var problemObjects = extractProblems(ex);

        var problem = problemBuilder(problemType)
                .status(status.value())
                .detail(detail)
                .userMessage(detail)
                .objects(problemObjects)
                .build();

        return new ResponseEntity<>(problem, status);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
        var status = HttpStatus.BAD_REQUEST;
        var problemType = ProblemType.INVALID_DATA;
        var detail = "FieldsAreInvalid";

        var problemObjects = extractProblemsFromConstraintViolations(ex.getConstraintViolations());

        var problem = problemBuilder(problemType)
                .status(status.value())
                .detail(detail)
                .userMessage(detail)
                .objects(problemObjects)
                .build();

        return new ResponseEntity<>(problem, status);
    }

    @ExceptionHandler(BindException.class)
    private ResponseEntity<Object> handleBindException(BindException ex) {
        try {
            var status = HttpStatus.BAD_REQUEST;
            var problemType = ProblemType.INVALID_DATA;
            var detail = "FieldsAreInvalid";

            var problemObjects = extractProblems(ex.getBindingResult());

            var problem = problemBuilder(problemType)
                    .status(status.value())
                    .detail(detail)
                    .userMessage(detail)
                    .objects(problemObjects)
                    .build();

            return new ResponseEntity<>(problem, status);
        } catch (Exception exception) {
            return handleUncaught(exception);
        }
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex) {
        try {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            String path = joinPath(ex.getPath());
            ProblemType problemType = ProblemType.INCOMPREHENSIBLE_MESSAGE;
//            String detail = internalization.getMessage(
//                    "PropertyReceivedInvalidType",
//                    path,
//                    ex.getValue(),
//                    ex.getTargetType().getSimpleName()
//            );

            ProblemDTO problem = problemBuilder(problemType)
                    .status(status.value())
                    .detail("")
                    .userMessage("GenericErrorToUser")
                    .build();

            return new ResponseEntity<>(problem, status);
        } catch (Exception exception) {
            return handleUncaught(exception);
        }
    }

    @ExceptionHandler(PropertyBindingException.class)
    private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException ex) {
        try {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            String path = joinPath(ex.getPath());
            ProblemType problemType = ProblemType.INCOMPREHENSIBLE_MESSAGE;
            String detail = "PropertyDoesNotExist";

            ProblemDTO problem = problemBuilder(problemType)
                    .status(status.value())
                    .detail(detail)
                    .userMessage("GenericErrorToUser")
                    .build();

            return new ResponseEntity<>(problem, status);
        } catch (Exception exception) {
            return handleUncaught(exception);
        }
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException() {
        try {
            var status = HttpStatus.FORBIDDEN;
            var problemType = ProblemType.ACCESS_DENIED;
            var detail = "UserDontHavePermission";

            var problem = problemBuilder(problemType)
                    .status(status.value())
                    .detail(detail)
                    .userMessage(detail)
                    .build();

            return new ResponseEntity<>(problem, status);
        } catch (Exception exception) {
            return handleUncaught(exception);
        }
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentialsException() {
        try {
            var status = HttpStatus.BAD_REQUEST;
            var problemType = ProblemType.INVALID_DATA;
            var detail = "InvalidUsernamePassword";

            var problem = problemBuilder(problemType)
                    .status(status.value())
                    .detail(detail)
                    .userMessage(detail)
                    .build();

            return new ResponseEntity<>(problem, status);
        } catch (Exception exception) {
            return handleUncaught(exception);
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUncaught(Exception ex) {
        var status = HttpStatus.INTERNAL_SERVER_ERROR;
        var problemType = ProblemType.SYSTEM_ERROR;
        var detail = "GenericErrorToUser";

        ex.printStackTrace();

        var problem = problemBuilder(problemType)
                .status(status.value())
                .detail(detail)
                .userMessage(detail)
                .build();

        return new ResponseEntity<>(problem, status);
    }

    private ProblemDTO.ProblemDTOBuilder problemBuilder(ProblemType problemType) {
        return ProblemDTO.builder()
                .timestamp(DateTimeUtil.nowZoneUTC())
                .type(problemType.getUri())
                .title(problemType.getTitle());
    }

    private String joinPath(List<Reference> references) {
        return references.stream()
                .map(Reference::getFieldName)
                .collect(Collectors.joining("."));
    }

    private List<ProblemObjectDTO> extractProblems(BindingResult result) {
        return result.getAllErrors().stream().map(objectError -> {
            var name = objectError.getObjectName();

            if (objectError instanceof FieldError) {
                name = ((FieldError) objectError).getField();
            }

            return new ProblemObjectDTO(name, objectError.getDefaultMessage());
        }).toList();
    }

    private List<ProblemObjectDTO> extractProblemsFromConstraintViolations(Set<ConstraintViolation<?>> constraintViolations) {
        return constraintViolations.stream().map(constraintViolation -> {
            var name = constraintViolation.getPropertyPath() != null ? constraintViolation.getPropertyPath().toString() : constraintViolation.getRootBeanClass().getSimpleName();
            var message = constraintViolation.getMessage();

            return new ProblemObjectDTO(name, message);
        }).toList();
    }


}

