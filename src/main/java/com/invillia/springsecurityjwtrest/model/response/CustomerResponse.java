package com.invillia.springsecurityjwtrest.model.response;

import com.invillia.springsecurityjwtrest.model.entity.Authority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {

    private Long id;

    private String name;

    private String username;

    private String password;

    private boolean enabled;

    private Set<Authority> authorities;
}
