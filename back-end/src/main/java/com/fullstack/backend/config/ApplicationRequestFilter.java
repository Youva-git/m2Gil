package com.fullstack.backend.config;

import com.fullstack.backend.service.auth.AppUserDetailsService;
import com.fullstack.backend.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ApplicationRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil vJwtUtil;
    @Autowired
    private AppUserDetailsService vAppUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        String vUserEmail = null;
        String vJwt = null;

        if(StringUtils.hasLength(vJwt) && authHeader.startsWith("Bearer ")){
            vJwt = authHeader.substring(7);
            vUserEmail = vJwtUtil.extractUsername(vJwt);
        }

        if(StringUtils.hasLength(vUserEmail) && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails vUserDetails = vAppUserDetailsService.loadUserByUsername(vUserEmail);
            if(vJwtUtil.validateToken(vJwt, vUserDetails)){
                UsernamePasswordAuthenticationToken vUsernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                  vUserDetails, null, vUserDetails.getAuthorities()
                );
                vUsernamePasswordAuthenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(vUsernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
