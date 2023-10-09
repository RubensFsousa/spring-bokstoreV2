package com.study.spring.base.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    public OpenAPI openAPI() {
        var openAPI = new OpenAPI();
        addInfo(openAPI);
        return openAPI;
    }

    private void addInfo(OpenAPI openAPI) {
        openAPI.info(
                new Info()
                        .title("Base Project API")
                        .version("0.1")
                        .description("This API exposes endpoints to Base Project API.")
        );
    }

}
