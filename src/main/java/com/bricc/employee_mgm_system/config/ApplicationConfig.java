package com.bricc.employee_mgm_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class ApplicationConfig {

	@Bean
	public OpenAPI openApiInformation() {
		Server localServer = new Server().url("http://localhost:8080").description("Localhost Server URL");

		Contact contact = new Contact().email("contact@bricc.com").name("Bricc inc");

		Info info = new Info().contact(contact).description("Spring Boot Employee Management System")
				.title("Employee Management System").version("V1.0.0")
				.license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0.txt"));

		return new OpenAPI().info(info).addServersItem(localServer);
	}
}
