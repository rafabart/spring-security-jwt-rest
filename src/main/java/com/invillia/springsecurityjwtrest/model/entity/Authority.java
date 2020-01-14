package com.invillia.springsecurityjwtrest.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
/*
    A classe 'Authority', nome padrão do Spring, é responsável por definir
    os métodos do CRUD permitido a determinado usuário.

    Ela implementa a interface 'GrantedAuthority' da lib Spring Security.

    SequenceGenerator -> customiza a forma que é gerado o ID no banco,
    o valor inicial e de quanto será o incremento.
*/
@SequenceGenerator(name = "seq_authority", sequenceName = "seq_authority", allocationSize = 1, initialValue = 1)
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_authority")
    private Long id;

    @Column(nullable = false)
    private String authority; /* Opções do CRUD com autorização: ROLE_SAVE, ROLE_UPDATE, etc... */


}
