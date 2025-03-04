package com.example.Apigateway.filter;


import java.util.function.Predicate;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
@Component
public class RouteValidator {


	public static final String[] OPEN_API_ENDPOINTS = { "/auth/register", "/auth/new", "/auth/authenticate" };

	public Predicate<ServerHttpRequest> isSecured = request -> {
        String path = request.getPath().toString();
        for (String endpoint : OPEN_API_ENDPOINTS) {
            if (path.contains(endpoint)) { 
            	return false;
            }
        }
        return true;
    };
}
