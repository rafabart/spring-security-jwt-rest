package com.invillia.springsecurityjwtrest.service;

import com.invillia.springsecurityjwtrest.model.request.LoginRequest;

public interface LoginService {

    String login(final LoginRequest loginRequest) throws Exception;
}
