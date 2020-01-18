package com.invillia.springsecurityjwtrest.resource;

import com.invillia.springsecurityjwtrest.model.request.CustomerRequest;
import com.invillia.springsecurityjwtrest.model.response.CustomerResponse;
import com.invillia.springsecurityjwtrest.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
    @Secured("ROLE_FIND")
    public ResponseEntity<?> findAll() {

        List<CustomerResponse> customerResponses = customerService.findAll();

        return ResponseEntity.ok(customerResponses);
    }


    @GetMapping("{id}")
    @Secured("ROLE_FIND")
    public ResponseEntity<?> findByid(@PathVariable("id") final Long id) {

        CustomerResponse customerResponse = customerService.findById(id);

        return ResponseEntity.ok(customerResponse);
    }


    @DeleteMapping("{id}")
    @Secured("ROLE_DELETE")
    public ResponseEntity<?> delete(@PathVariable("id") final Long id) {

        customerService.delete(id);

        return ResponseEntity.ok().build();
    }


    @PutMapping("{id}")
    @Secured("ROLE_UPDATE")
    public ResponseEntity<?> update(@Valid @PathVariable("id") final Long id,
                                    @RequestBody final CustomerRequest customerRequest) {

        customerService.update(id, customerRequest);

        return ResponseEntity.ok().build();
    }


    @PostMapping
    @Secured("ROLE_SAVE")
    public ResponseEntity<?> create(@Valid @RequestBody final CustomerRequest customerRequest) {

        customerService.save(customerRequest);

        return ResponseEntity.ok().build();
    }
}
