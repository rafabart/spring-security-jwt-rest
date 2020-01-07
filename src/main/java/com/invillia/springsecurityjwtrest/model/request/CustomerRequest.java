package com.invillia.springsecurityjwtrest.model.request;

import com.invillia.springsecurityjwtrest.model.entity.Role;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {

    private Long id;

    @NonNull
    @NotBlank
    private String name;

    @NonNull
    @NotBlank
    private String username;

    @NonNull
    @NotBlank
    private String password;

    @NonNull
    @NotBlank
    private Set<Role> roles;


    public String getPassword() {
        return new BCryptPasswordEncoder().encode(this.password);
    }
}
