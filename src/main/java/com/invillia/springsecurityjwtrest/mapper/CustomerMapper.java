package com.invillia.springsecurityjwtrest.mapper;

import com.invillia.springsecurityjwtrest.model.response.CustomerResponse;
import com.invillia.springsecurityjwtrest.model.entity.Customer;
import com.invillia.springsecurityjwtrest.model.request.CustomerRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerMapper {

    public Customer CustomerRequestToCustomer(final CustomerRequest customerRequest) {
        return Customer.builder()
                .name(customerRequest.getName())
                .username(customerRequest.getUsername())
                .password(customerRequest.getPassword())
                .enabled(customerRequest.isEnabled())
                .authorities(customerRequest.getAuthorities())
                .build();
    }


    public void UpdateCustomerWithCustomerRequest(Customer customer, final CustomerRequest customerRequest) {
        customer.setId(customerRequest.getId());
        customer.setName(customerRequest.getName());
        customer.setUsername(customerRequest.getUsername());
        customer.setPassword(customerRequest.getPassword());
        customer.setEnabled(customerRequest.isEnabled());
        customer.setAuthorities(customerRequest.getAuthorities());
    }


    public CustomerResponse CustomerToCustomerResponse(final Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .username(customer.getUsername())
                .password(customer.getPassword())
                .enabled(customer.isEnabled())
                .authorities(customer.getAuthorities())
                .build();
    }

    public List<CustomerResponse> CustomerListToCustomerResponseList(final List<Customer> customers) {

        List<CustomerResponse> customerResponses = new ArrayList<>();

        customers.forEach(customer -> customerResponses.add(CustomerToCustomerResponse(customer)));

        return customerResponses;
    }
}
