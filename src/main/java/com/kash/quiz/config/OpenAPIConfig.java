package com.kash.quiz.config;

import com.kash.quiz.constant.ApiInfoSwaggerConstants;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;


@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = ApiInfoSwaggerConstants.TITLE,
                version = ApiInfoSwaggerConstants.VERSION,
                description = ApiInfoSwaggerConstants.DESCRIPTION,
                termsOfService = ApiInfoSwaggerConstants.TERMS_OF_SERVICE_URL,
                license = @License(
                        name = ApiInfoSwaggerConstants.LICENSE,
                        url = ApiInfoSwaggerConstants.LICENSE_URL
                ),
                contact = @Contact(
                        name = ApiInfoSwaggerConstants.NAME,
                        url = ApiInfoSwaggerConstants.URL,
                        email = ApiInfoSwaggerConstants.EMAIL)
        ),
        security = {
                @SecurityRequirement(
                        name = ApiInfoSwaggerConstants.SECURITY_REFERENCE,
                        scopes = ApiInfoSwaggerConstants.AUTHORIZATION_SCOPE
                )
        },
        servers = {
                @Server(url = "/quiz-app/v1", description = "Default Server URL")
        }

)

@SecurityScheme(
        name = ApiInfoSwaggerConstants.API_KEY_NAME,
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = ApiInfoSwaggerConstants.SECURITY_REFERENCE


)
public class OpenAPIConfig {

    @Bean
    public OpenAPI baseOpenAPI() {
        ApiResponse badRequestAPI = new io.swagger.v3.oas.models.responses.ApiResponse().content(
                new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE,
                        new io.swagger.v3.oas.models.media.MediaType().addExamples("default",
                                new Example().value("{\"code\" : 400, \"status\": \"Bad Request!\", \"Message\": \"Bad Request!\" }")))
        ).description("Bad Request!!");


        ApiResponse internalServerErrorAPI = new ApiResponse().content(
                new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE,
                        new io.swagger.v3.oas.models.media.MediaType().addExamples("default",
                                new Example().value("{\"code\" : 500, \"status\": \"Internal Server Error!\", \"Message\": \"Internal Server Error!\" }")))
        ).description("Internal Server Error!!");

        Components components = new Components();
        components.addResponses("badRequest", badRequestAPI);
        components.addResponses("internalServerError", internalServerErrorAPI);

        return new OpenAPI().components(components);

    }
}
