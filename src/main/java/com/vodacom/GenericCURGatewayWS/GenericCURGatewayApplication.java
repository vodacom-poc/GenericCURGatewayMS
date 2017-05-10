package com.vodacom.GenericCURGatewayWS;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableCircuitBreaker
@EnableDiscoveryClient
public class GenericCURGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GenericCURGatewayApplication.class, args);
	}
	@Bean
	public Docket newAPIChargeCode() {
	        return new Docket(DocumentationType.SWAGGER_2)
	        .groupName("processCURQuery").apiInfo(apiInfo()).select().paths(regex("/curportalbalances.*")).build();
	                 
	   }
	@Component
	@Primary
	public class CustomObjectMapper extends ObjectMapper {
	    public CustomObjectMapper() {
	        setSerializationInclusion(JsonInclude.Include.NON_NULL);
	        configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
	        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	        enable(SerializationFeature.INDENT_OUTPUT);
	    }
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Vodacom CUR MS")
				.description("This API is for fetching the Details of a Subscriber.")
				.version("1.0")
				.contact(new Contact("ATA Lean Architecture Team", "", "ATA.Lean.Arch.Group@accenture.com"))
				// .contact("ATA Lean Architecture Team")
				.license("Accenture License Version")
				// .licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE")
				.build();
	}
}
