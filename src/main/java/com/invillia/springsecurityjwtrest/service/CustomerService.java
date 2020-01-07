package com.invillia.springsecurityjwtrest.service;

import com.invillia.springsecurityjwtrest.model.response.CustomerResponse;
import com.invillia.springsecurityjwtrest.model.request.CustomerRequest;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface CustomerService {

    void save(final CustomerRequest customerRequest);

    void update(final Long id, final CustomerRequest customerRequest);

    void delete(final Long id);

    CustomerResponse findById(final Long id);

    List<CustomerResponse> findAll();

    UserDetails loadUserByUsername(final String username);
}
