package com.taskSBv2312.testSBv2312;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
public class TestSBv2312Application {

	public static void main(String[] args) {
		SpringApplication.run(TestSBv2312Application.class, args);
	}

	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/api/*"))
				.apis(RequestHandlerSelectors.basePackage("com.taskSBv2312"))
				.build()
				.apiInfo(apiDetails());
	}

	private ApiInfo apiDetails() {
		return new ApiInfo(
				"Task Mangment System API",
				"Homework for Intern",
				"0.1",
				"Free to Use",
				new springfox.documentation.service.Contact("Arturs Lobanovs", "no url",
						"arturs.lobanovs@cognizant.com"),
				"API Licence",
				"no licence",
				Collections.emptyList());
	}

}
