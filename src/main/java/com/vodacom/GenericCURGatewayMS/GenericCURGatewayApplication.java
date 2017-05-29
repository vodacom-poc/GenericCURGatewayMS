package com.vodacom.GenericCURGatewayMS;

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
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

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
		ObjectMapper mapper = new ObjectMapper(); 
		mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, Boolean.FALSE);
		mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, Boolean.TRUE);
		XmlMapper xmlMapper = new XmlMapper();
		xmlMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, Boolean.TRUE);
	    xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, Boolean.FALSE);
	    xmlMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, Boolean.TRUE);
		SpringApplication.run(GenericCURGatewayApplication.class, args);
	}
	@Bean
	public Docket newAPICURGateway() {
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
