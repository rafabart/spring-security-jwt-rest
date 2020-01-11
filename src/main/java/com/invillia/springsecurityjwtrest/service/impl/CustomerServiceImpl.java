package com.invillia.springsecurityjwtrest.service.impl;

import com.invillia.springsecurityjwtrest.exception.CustomerNotFoundException;
import com.invillia.springsecurityjwtrest.exception.UsernameException;
import com.invillia.springsecurityjwtrest.mapper.CustomerMapper;
import com.invillia.springsecurityjwtrest.model.response.CustomerResponse;
import com.invillia.springsecurityjwtrest.model.entity.Customer;
import com.invillia.springsecurityjwtrest.model.request.CustomerRequest;
import com.invillia.springsecurityjwtrest.repository.CustomerRepository;
import com.invillia.springsecurityjwtrest.repository.RoleRepository;
import com.invillia.springsecurityjwtrest.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService, UserDetailsService {

    final private CustomerRepository customerRepository;

    final private RoleRepository roleRepository;

    final private CustomerMapper customerMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, RoleRepository roleRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.roleRepository = roleRepository;
        this.customerMapper = customerMapper;
    }


    public void save(final CustomerRequest customerRequest) {

        final Optional<Customer> customerByUsername = customerRepository.findByUsername(customerRequest.getUsername());

        if (customerByUsername.isEmpty()) {

            final Customer customer = customerMapper.CustomerRequestToCustomer(customerRequest);

            customerRepository.save(customer);

        } else {
            throw new UsernameException(customerByUsername.get().getUsername());
        }

    }


    public void update(final Long id, final CustomerRequest customerRequest) {

        final Customer customer = customerRepository.findById(id).orElseThrow(
                () -> new CustomerNotFoundException(id)
        );

        customerMapper.UpdateCustomerWithCustomerRequest(customer, customerRequest);

        customerRepository.save(customer);
    }


    public void delete(final Long id) {

        final Customer customer = customerRepository.findById(id).orElseThrow(
                () -> new CustomerNotFoundException(id)
        );

        customerRepository.delete(customer);
    }


    public CustomerResponse findById(final Long id) {

        final Customer customer = customerRepository.findById(id).orElseThrow(
                () -> new CustomerNotFoundException(id)
        );

        return customerMapper.CustomerToCustomerResponse(customer);
    }


    public List<CustomerResponse> findAll() {

        List<Customer> customers = customerRepository.findAll();

        return customerMapper.CustomerListToCustomerResponseList(customers);
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

        final Customer customer = customerRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("Username não encontrado: " + username)
        );

        return new User(customer.getUsername(), customer.getPassword(), customer.getAuthorities());
    }
}
