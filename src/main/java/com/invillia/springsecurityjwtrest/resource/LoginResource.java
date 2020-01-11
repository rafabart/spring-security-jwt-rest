package com.invillia.springsecurityjwtrest.resource;

import com.invillia.springsecurityjwtrest.model.request.LoginRequest;
import com.invillia.springsecurityjwtrest.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginResource {

    final private LoginService loginService;

    @Autowired
    public LoginResource(final LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody final LoginRequest loginRequest) throws Exception {

        String token = loginService.login(loginRequest);

        return ResponseEntity.ok()
                .header("Authorization", token)
                .body("Authorization " + token);
    }
}
