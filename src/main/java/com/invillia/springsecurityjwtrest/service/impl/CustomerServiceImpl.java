package com.invillia.springsecurityjwtrest.service.impl;

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

        final Customer customer = customerMapper.CustomerRequestToCustomer(customerRequest);

        customerRepository.save(customer);
    }


    public void update(final Long id, final CustomerRequest customerRequest) {

        Customer customer = customerRepository.findById(id).get();

        customerMapper.UpdateCustomerWithCustomerRequest(customer, customerRequest);

        customerRepository.save(customer);
    }


    public void delete(final Long id) {

        final Customer customer = customerRepository.findById(id).get();

        customerRepository.delete(customer);
    }


    public CustomerResponse findById(final Long id) {

        final Customer customer = customerRepository.findById(id).get();

        return customerMapper.CustomerToCustomerResponse(customer);
    }


    public List<CustomerResponse> findAll() {

        List<Customer> customers = customerRepository.findAll();

        return customerMapper.CustomerListToCustomerResponseList(customers);
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

        final Customer customer = customerRepository.findByUsername(username).get();

        User user = new User(customer.getUsername(), customer.getPassword(), customer.getAuthorities());

        return user;
    }
}
