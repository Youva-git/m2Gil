package com.fullstack.backend.controller;

import com.fullstack.backend.dto.auth.AuthenticationRequest;
import com.fullstack.backend.dto.auth.AuthenticationResponse;
import com.fullstack.backend.service.auth.AppUserDetailsService;
import com.fullstack.backend.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.fullstack.backend.utils.Constants.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT+"/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager vAuthenticationManager;
    @Autowired
    private AppUserDetailsService vAppUserDetailsService;
    @Autowired
    private JwtUtil vJwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        vAuthenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getPassword()
                )
        );
        final UserDetails vUserDetails = vAppUserDetailsService.loadUserByUsername(request.getLogin());
        final String vJwt = vJwtUtil.generateToken(vUserDetails);
        return ResponseEntity.ok(AuthenticationResponse.builder().accesToken(vJwt).build());
    }
}
