package com.invillia.springsecurityjwtrest;

import com.invillia.springsecurityjwtrest.model.entity.Role;
import com.invillia.springsecurityjwtrest.model.request.CustomerRequest;
import com.invillia.springsecurityjwtrest.repository.RoleRepository;
import com.invillia.springsecurityjwtrest.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class DataInitializer implements CommandLineRunner {

    final private CustomerService customerService;

    final private RoleRepository roleRepository;

    @Autowired
    public DataInitializer(CustomerService customerService, RoleRepository roleRepository) {
        this.customerService = customerService;
        this.roleRepository = roleRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        if (customerService.findAll().isEmpty()) {


            Role roleAdmin = roleRepository.save(Role.builder().nameRole("ROLE_ADMIN").build());
            Role roleUser = roleRepository.save(Role.builder().nameRole("ROLE_USER").build());

            roleRepository.findAll().forEach(role -> System.out.println(role));

            customerService.save(
                    CustomerRequest.builder()
                            .name("Administrador")
                            .username("admin")
                            .password("admin")
                            .roles(Collections.singleton(roleAdmin))
                            .build()
            );

            customerService.save(
                    CustomerRequest.builder()
                            .name("usuario")
                            .username("user")
                            .password("user")
                            .roles(Collections.singleton(roleUser))
                            .build()
            );

            customerService.findAll().forEach(usuario -> System.out.println(usuario));
        }
    }
}
