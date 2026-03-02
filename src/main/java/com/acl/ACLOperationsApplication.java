package com.acl;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.acl"})
@OpenAPIDefinition(info = @Info(title = "Account Calculator API"))
public class ACLOperationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ACLOperationsApplication.class, args);
	}
}
