package com.invillia.springsecurityjwtrest.resource;

import com.invillia.springsecurityjwtrest.model.request.CustomerRequest;
import com.invillia.springsecurityjwtrest.model.response.CustomerResponse;
import com.invillia.springsecurityjwtrest.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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


    @GetMapping("{id}")
    public ResponseEntity<?> findByid(@PathVariable("id") final Long id) {

        CustomerResponse customerResponse = customerService.findById(id);

        return ResponseEntity.ok(customerResponse);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") final Long id) {

        customerService.delete(id);

        return ResponseEntity.ok().build();
    }


    @PutMapping("{id}")
    public ResponseEntity<?> update(@Valid @PathVariable("id") final Long id,
                                    @RequestBody final CustomerRequest customerRequest) {

        customerService.update(id, customerRequest);

        return ResponseEntity.ok().build();
    }


    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody final CustomerRequest customerRequest) {

        customerService.save(customerRequest);

        return ResponseEntity.ok().build();
    }
}
