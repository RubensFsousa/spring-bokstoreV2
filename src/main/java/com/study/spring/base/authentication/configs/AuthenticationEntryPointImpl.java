package com.study.spring.base.authentication.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.spring.base.shared.models.DTOs.ProblemDTO;
import com.study.spring.base.shared.models.enums.ProblemType;
import com.study.spring.base.shared.utils.DateTimeUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@AllArgsConstructor
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    //TODO: add i18n

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException)
            throws IOException {
        int status = HttpStatus.UNAUTHORIZED.value();

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(status);

        var out = response.getOutputStream();
        objectMapper.writeValue(out, buildProblem(status));
        out.flush();
    }

    private ProblemDTO buildProblem(int status) {
        String detail = "UnauthenticatedUser";
        return ProblemDTO.builder()
                .status(status)
                .timestamp(DateTimeUtil.nowZoneUTC())
                .type(ProblemType.ACCESS_DENIED.getUri())
                .title(ProblemType.ACCESS_DENIED.getTitle())
                .detail(detail)
                .userMessage(detail)
                .build();
    }


}
