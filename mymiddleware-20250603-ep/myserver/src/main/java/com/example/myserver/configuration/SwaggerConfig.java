package com.example.myserver.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;


@Configuration
public class SwaggerConfig {

	@Value("${envName}")
	private String envName;

	@Bean
	public OpenAPI carrierCorreosExpressOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("MyServer API")
						.description("MyServer API -- <b>" + envName + "</b>")
						.version("v1.0.0") )
				.externalDocs( new ExternalDocumentation()
						.description("Example")
						.url("https://www.example.com") )
				;
	}

}