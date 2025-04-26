package com.examly.springapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI()
				.info(new Info()
				.title("EstateConnect")
				.version("1.0.0")
				.contact(new Contact()
						.name("EstateConnect support")
						.email("mr.suesigfu@gmail")
						.url("#"))
				.description("EstateConnect"))
				.addSecurityItem(new SecurityRequirement()
				         .addList("Bearer Authentication"))
				.components(new Components()
						.addSecuritySchemes("Bearer Authentication",
								new SecurityScheme().type(SecurityScheme.Type.HTTP)
								.bearerFormat("JWT")
								.scheme("bearer")));
						
	}
}
