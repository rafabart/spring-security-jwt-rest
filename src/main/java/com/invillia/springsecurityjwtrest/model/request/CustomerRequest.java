package com.invillia.springsecurityjwtrest.model.request;

import com.invillia.springsecurityjwtrest.model.entity.Authority;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {

    private Long id;

    @NotNull(message = "O campo 'Nome' não pode estar vazio!")
    @NotBlank(message = "O campo 'Nome' não pode estar em branco!")
    private String name;

    @NotNull(message = "O campo 'Username' não pode estar vazio!")
    @NotBlank(message = "O campo 'Username' não pode estar em branco!")
    private String username;

    @NotNull(message = "O campo 'Senha' não pode estar vazio!")
    @NotBlank(message = "O campo 'Senha' não pode estar em branco!")
    private String password;

    @NotNull(message = "O campo 'Ativa' não pode estar vazio!")
    private boolean enabled;


    private Set<Authority> authorities;


    public String getPassword() {
        return new BCryptPasswordEncoder().encode(this.password);
    }
}
