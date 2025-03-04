//package com.example.Apigateway.filter;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.http.MediaType;
//import org.springframework.core.io.buffer.DataBuffer;
//import com.example.Apigateway.util.JwtUtil;
//import com.google.common.net.HttpHeaders;
//
//import reactor.core.publisher.Mono;
//
//@Component
//public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
//
//    @Autowired
//    private RouteValidator validator;
//
//    @Autowired
//    private JwtUtil util;
//
//    public static class Config {
//    }
//
//    public AuthenticationFilter() {
//        super(Config.class);
//    }
//
//    @Override
//    public GatewayFilter apply(Config config) {
//        return (exchange, chain) -> {
//            ServerHttpRequest request = exchange.getRequest();
//            ServerHttpResponse response = exchange.getResponse();
//
//            if (validator.isSecured.test(request)) {
//                if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
//                    return handleUnauthorized(response, "Missing authorization header");
//                }
//
//                String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
//                if (authHeader != null && authHeader.startsWith("Bearer ")) {
//                    authHeader = authHeader.substring(7);
//                }
//
//                try {
//                    String role = util.extractRolesFromToken(authHeader);
//                    String path = request.getPath().toString();
//                    String method = request.getMethodValue();
//
//                    if (!isAuthorized(role, path, method)) {
//                        return handleUnauthorized(response, "Unauthorized access");
//                    }
//
//                } catch (io.jsonwebtoken.ExpiredJwtException e) {
//                    return handleUnauthorized(response, "Token expired");
//                } catch (io.jsonwebtoken.SignatureException e) {
//                    return handleUnauthorized(response, "Invalid token signature");
//                } catch (Exception e) {
//                    return handleUnauthorized(response, "Invalid token");
//                }
//            }
//            return chain.filter(exchange);
//        };
//    }
//
//    private boolean isAuthorized(String role, String path, String method) {
//        if ("ADMIN".equalsIgnoreCase(role)) {
//            // Admin: Full access to manager, employee, and project
//            return path.startsWith("/manager/") || path.startsWith("/employee/") || path.startsWith("/project/");
//        } else if ("MANAGER".equalsIgnoreCase(role)) {
//            // Manager: Full access to manager and employee, GET for project
//            if (path.startsWith("/manager/") || path.startsWith("/employee/")) {
//                return true; // Full access
//            } else if (path.startsWith("/project/")) {
//                return method.equalsIgnoreCase("GET"); // GET only
//            }
//            return false;
//        } else if ("EMPLOYEE".equalsIgnoreCase(role)) {
//            // Employee: GET for manager, full access to Employee, GET for project
//            if (path.startsWith("/manager/")) {
//                return method.equalsIgnoreCase("GET"); // GET only for manager
//            } else if(path.startsWith("/employee/")){
//                return true; // Full access to employee
//            } else if (path.startsWith("/project/")) {
//                return method.equalsIgnoreCase("GET"); // GET only for project
//            }
//            return false;
//        }
//        return false; // Default: deny access
//    }
//    
//
//    private Mono<Void> handleUnauthorized(ServerHttpResponse response, String message) {
//    	response.setStatusCode(HttpStatus.FORBIDDEN);
//        response.getHeaders().setContentType(MediaType.APPLICATION_JSON); // Set content type to JSON
//        String responseBody = "error:" + " " + message;
//        DataBuffer buffer = response.bufferFactory().wrap(responseBody.getBytes());
//        return response.writeWith(Mono.just(buffer));
//    }
//}


package com.example.Apigateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.http.MediaType;
import org.springframework.core.io.buffer.DataBuffer;
import com.example.Apigateway.util.JwtUtil;
import com.google.common.net.HttpHeaders;

import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator validator;

    @Autowired
    private JwtUtil util;

    public static class Config {
    }

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            // Allow OPTIONS requests to pass through
            if (request.getMethod() == HttpMethod.OPTIONS) {
                return chain.filter(exchange);
            }

            if (validator.isSecured.test(request)) {
                if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    return handleUnauthorized(response, "Missing authorization header");
                }

                String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }

                try {
                    String role = util.extractRolesFromToken(authHeader);
                    String path = request.getPath().toString();
                    String method = request.getMethodValue();

                    if (!isAuthorized(role, path, method)) {
                        return handleUnauthorized(response, "Unauthorized access");
                    }

                } catch (io.jsonwebtoken.ExpiredJwtException e) {
                    return handleUnauthorized(response, "Token expired");
                } catch (io.jsonwebtoken.SignatureException e) {
                    return handleUnauthorized(response, "Invalid token signature");
                } catch (Exception e) {
                    return handleUnauthorized(response, "Invalid token");
                }
            }
            return chain.filter(exchange);
        };
    }

    private boolean isAuthorized(String role, String path, String method) {
        // ... (your existing authorization logic)
        if ("ADMIN".equalsIgnoreCase(role)) {
            // Admin: Full access to manager, employee, and project
            return path.startsWith("/manager/") || path.startsWith("/employee/") || path.startsWith("/project/");
        } else if ("MANAGER".equalsIgnoreCase(role)) {
            // Manager: Full access to manager and employee, GET for project
            if (path.startsWith("/manager/") || path.startsWith("/employee/")) {
                return true; // Full access
            } else if (path.startsWith("/project/")) {
                return method.equalsIgnoreCase("GET"); // GET only
            }
            return false;
        } else if ("EMPLOYEE".equalsIgnoreCase(role)) {
            // Employee: GET for manager, full access to Employee, GET for project
            if (path.startsWith("/manager/")) {
                return method.equalsIgnoreCase("GET"); // GET only for manager
            } else if(path.startsWith("/employee/")){
                return true; // Full access to employee
            } else if (path.startsWith("/project/")) {
                return method.equalsIgnoreCase("GET"); // GET only for project
            }
            return false;
        }
        return false; // Default: deny access
    }

    private Mono<Void> handleUnauthorized(ServerHttpResponse response, String message) {
        response.setStatusCode(HttpStatus.FORBIDDEN);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        String responseBody = "error:" + " " + message;
        DataBuffer buffer = response.bufferFactory().wrap(responseBody.getBytes());
        return response.writeWith(Mono.just(buffer));
    }
}