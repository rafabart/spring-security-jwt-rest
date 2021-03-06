package com.invillia.springsecurityjwtrest.resource;

import com.invillia.springsecurityjwtrest.model.request.LoginRequest;
import com.invillia.springsecurityjwtrest.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationResource {

    final private AuthenticationService authenticationService;

    @Autowired
    public AuthenticationResource(final AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody final LoginRequest loginRequest) throws Exception {

        String token = authenticationService.login(loginRequest);

        return ResponseEntity.ok()
                .header("Authorization", token)
                .body("Authorization " + token);
    }
}
