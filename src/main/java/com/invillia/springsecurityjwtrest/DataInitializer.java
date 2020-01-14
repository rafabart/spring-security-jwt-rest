package com.invillia.springsecurityjwtrest;

import com.invillia.springsecurityjwtrest.model.entity.Authority;
import com.invillia.springsecurityjwtrest.model.request.CustomerRequest;
import com.invillia.springsecurityjwtrest.repository.AuthorityRepository;
import com.invillia.springsecurityjwtrest.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@Component
public class DataInitializer implements CommandLineRunner {

    final private CustomerService customerService;

    final private AuthorityRepository authorityRepository;

    @Autowired
    public DataInitializer(CustomerService customerService, AuthorityRepository authorityRepository) {
        this.customerService = customerService;
        this.authorityRepository = authorityRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        if (customerService.findAll().isEmpty()) {

            Authority roleSave = authorityRepository.save(Authority.builder().authority("ROLE_SAVE").build());
            Authority roleUpdate = authorityRepository.save(Authority.builder().authority("ROLE_UPDATE").build());
            Authority roleDelete = authorityRepository.save(Authority.builder().authority("ROLE_DELETE").build());
            Authority roleFind = authorityRepository.save(Authority.builder().authority("ROLE_FIND").build());

            authorityRepository.findAll().forEach(role -> System.out.println(role));

            customerService.save(
                    CustomerRequest.builder()
                            .name("Administrador")
                            .username("admin")
                            .password("admin")
                            .enabled(true)
                            .authorities(new HashSet<Authority>(Arrays.asList(roleSave, roleUpdate, roleDelete, roleFind)))
                            .build()
            );

            customerService.save(
                    CustomerRequest.builder()
                            .name("usuario")
                            .username("user")
                            .password("user")
                            .enabled(true)
                            .authorities(Collections.singleton(roleFind))
                            .build()
            );

            customerService.findAll().forEach(usuario -> System.out.println(usuario));
        }
    }
}
