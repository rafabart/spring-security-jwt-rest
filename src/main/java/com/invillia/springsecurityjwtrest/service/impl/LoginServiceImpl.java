package com.invillia.springsecurityjwtrest.service.impl;

import com.invillia.springsecurityjwtrest.model.request.LoginRequest;
import com.invillia.springsecurityjwtrest.security.JwtTokenUtil;
import com.invillia.springsecurityjwtrest.service.CustomerService;
import com.invillia.springsecurityjwtrest.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    final private JwtTokenUtil jwtTokenUtil;

    final private CustomerService customerService;

    final private AuthenticationManager authenticationManager;

    @Autowired
    public LoginServiceImpl(final JwtTokenUtil jwtTokenUtil, CustomerService customerService, final AuthenticationManager authenticationManager) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.customerService = customerService;
        this.authenticationManager = authenticationManager;
    }


    public String login(final LoginRequest loginRequest) throws Exception {

        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );

        } catch (BadCredentialsException e) {
            throw new Exception("Username e/ou senha inválidos!");
        }


        final UserDetails userDetails = customerService.loadUserByUsername(loginRequest.getUsername());

        return jwtTokenUtil.generateToken(userDetails);
    }
}
