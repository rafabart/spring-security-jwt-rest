package com.invillia.springsecurityjwtrest.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
/*
    A classe 'Role', nome padrão do Spring, é responsável por definir
    os niveis de acesso de cada login, exemplo: Admistrador, Gerente,
    Supervisor e etc...

    Ela implementa a interface 'GrantedAuthority' da lib Spring Security.

    SequenceGenerator -> customiza a forma que é gerado o ID no banco,
    o valor inicial e de quanto será o incremento.
*/
@SequenceGenerator(name = "seq_role", sequenceName = "seq_role", allocationSize = 1, initialValue = 1)
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_role")
    private Long id;

    @Column(nullable = false)
    private String nameRole; /* Nivel de acesso: ROLE_ADMIN, ROLE_USER, etc... */

    public String getAuthority() {
        return this.nameRole;
    }
}
