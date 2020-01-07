package com.invillia.springsecurityjwtrest.resource;

import com.invillia.springsecurityjwtrest.model.request.CustomerRequest;
import com.invillia.springsecurityjwtrest.model.response.CustomerResponse;
import com.invillia.springsecurityjwtrest.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerResouce {

    final private CustomerService customerService;

    @Autowired
    public CustomerResouce(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping
    public ResponseEntity<?> findAll() {

        List<CustomerResponse> customerResponses = customerService.findAll();

        return ResponseEntity.ok(customerResponses);
    }


    @PostMapping
    public ResponseEntity<?> create(@RequestBody final CustomerRequest customerRequest) {

        customerService.save(customerRequest);

        return ResponseEntity.ok().build();
    }
}
