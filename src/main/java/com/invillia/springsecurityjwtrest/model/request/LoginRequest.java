package com.invillia.springsecurityjwtrest.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class LoginRequest {

    @NonNull
    @NotBlank
    private String username;

    @NonNull
    @NotBlank
    private String password;
}
