package com.invillia.springsecurityjwtrest.service;

import com.invillia.springsecurityjwtrest.model.request.LoginRequest;

public interface AuthenticationService {

    String login(final LoginRequest loginRequest) throws Exception;
}
