package com.example.demo.security;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.service.CustomAuthenticationManager;
import com.example.demo.util.JwtTokenUtil;

public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    @Autowired
    private final JwtTokenUtil jwtTokenUtil;

    public JwtAuthenticationFilter(CustomAuthenticationManager customAuthenticationManager, JwtTokenUtil jwtTokenUtil) {
        super(new AntPathRequestMatcher("/**"));
        setAuthenticationManager(customAuthenticationManager);
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String token = jwtTokenUtil.resolveToken(request);
        if (token != null && jwtTokenUtil.validateToken(token)) {
            String username = jwtTokenUtil.getUsername(token);
            Authentication auth = new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
            return getAuthenticationManager().authenticate(auth);
        }
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authResult);
        chain.doFilter(request, response);
    }
}

