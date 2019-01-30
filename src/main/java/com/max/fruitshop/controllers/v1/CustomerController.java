package com.max.fruitshop.controllers.v1;

import com.max.fruitshop.api.v1.mapper.CustomerMapper;
import com.max.fruitshop.api.v1.model.CustomerDTO;
import com.max.fruitshop.api.v1.model.CustomerListDTO;
import com.max.fruitshop.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private CustomerService customerService;
    private CustomerMapper customerMapper;

    public CustomerController(CustomerService customerService, CustomerMapper customerMapper) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getById(@PathVariable Long id){
        return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<CustomerListDTO> getAll(){
        List<CustomerDTO> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(new CustomerListDTO(customers), HttpStatus.OK);
    }
}
