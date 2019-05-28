package com.happytown.entrypoints.rest;

import org.junit.jupiter.api.Test;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static org.assertj.core.api.Assertions.assertThat;

class SwaggerConfigTest {

    SwaggerConfig swaggerConfig = new SwaggerConfig();
    
    @Test
    public void shouldReturnSwagger2Doc_whenCallApi() {
        // Arrange
        
        // Act
        Docket api = swaggerConfig.api();
        // Assert
        assertThat(api.getDocumentationType()).isEqualToComparingFieldByField(new DocumentationType("swagger", "2.0"));
    }
}