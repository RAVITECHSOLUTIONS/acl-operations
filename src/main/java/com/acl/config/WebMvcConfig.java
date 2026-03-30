package com.acl.config;

import com.acl.util.ACLConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	private static final Logger logger = LoggerFactory.getLogger(WebMvcConfig.class);

	@Value("${cors.allowed.origins}")
	private String allowedOrigins;
	@Value("${cors.allowed.headers}")
	private String allowedHeaders;
	@Value("${cors.allowed.methods}")
	private String getAllowedMethods;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins(allowedOrigins.split(ACLConstants.COMMA))
				.allowedHeaders(allowedHeaders.split(ACLConstants.COMMA))
				.allowedMethods(getAllowedMethods.split(ACLConstants.COMMA));

		// Allow GraphQL endpoint from Angular (localhost:4200)
		// and Postman (any origin)
		registry.addMapping("/graphql")
				.allowedOriginPatterns("*")          // or "http://localhost:4200"
				.allowedMethods("GET", "POST", "OPTIONS")
				.allowedHeaders("*")
				.allowCredentials(false)
				.maxAge(3600);

		// Allow GraphiQL UI
		registry.addMapping("/graphiql")
				.allowedOriginPatterns("*")
				.allowedMethods("GET", "OPTIONS")
				.allowedHeaders("*");
	}
}
