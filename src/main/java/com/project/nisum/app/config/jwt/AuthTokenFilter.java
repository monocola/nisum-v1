package com.project.nisum.app.config.jwt;

import com.project.nisum.app.services.impl.UserDetailsServiceImpl;
import com.project.nisum.app.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

public class AuthTokenFilter extends AuthenticationFilter {

    @Autowired
    private JwtUtil jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected String parseToken(HttpServletRequest request) {
        return jwtUtils.getJwtFromCookies(request);
    }

    @Override
    protected boolean validateToken(String token) {
        return jwtUtils.validateJwtToken(token);
    }

    @Override
    protected UserDetails loadUserDetails(String token) {
        String email = jwtUtils.getUserNameFromJwtToken(token);
        return userDetailsService.loadUserByUsername(email);
    }

    @Override
    protected void authenticateUser(HttpServletRequest request, UserDetails userDetails) {
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}