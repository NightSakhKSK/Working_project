package com.utarex.youngone.config.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);
	@Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException e) throws IOException, ServletException {
		log.debug("JwtAuthenticationEntryPoint");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "UnAuthorized");
    }
}
