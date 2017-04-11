package com.cep.service;

import com.cep.service.Config.MongoConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableAutoConfiguration
@ComponentScan
@EnableSwagger2
@Import(MongoConfig.class)
@EnableMongoRepositories
@SpringBootApplication
public class AddressServiceApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(AddressServiceApplication.class)
				.build(args)
				.run();
	}



	ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("CeService API")
				.description("This system provide services to Cep Service")
				.version("1.0")
				.build();
	}

	@Bean
	public RestTemplate restTemplate() {
		final RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		return restTemplate;
	}



}
