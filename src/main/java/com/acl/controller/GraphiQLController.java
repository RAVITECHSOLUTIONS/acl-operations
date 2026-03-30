package com.acl.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GraphiQLController {

	@GetMapping(value = "/graphiql", produces = MediaType.TEXT_HTML_VALUE)
	public String graphiql(HttpServletRequest request) {
		return "graphiql";  // resolves → src/main/resources/templates/graphiql.html
	}
}