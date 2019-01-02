package br.com.maddytec.swagger;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@Profile("dev")
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.maddytec.controllers"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo())
				.securitySchemes(Collections.singletonList(apiKey()));
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Intelligent Point API")
				.description("Document of the Intelligent Point API of access for endpoints with Swagger")
				.termsOfServiceUrl("")
				.contact(new Contact("Madson Silva", "", "maddytec@gmail.com"))
				.license("Maddytec License Version 1.0")
				.licenseUrl("https://www.maddytec.com.br")
				.version("1.0.0")
				.build();
	}

		
	private ApiKey apiKey() {
	    return new ApiKey("Authorization", "Authorization", "header");
	}

}