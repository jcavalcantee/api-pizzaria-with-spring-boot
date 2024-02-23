package com.management.pizzaria.controllers;

import com.management.pizzaria.dtos.CustomerDTO;
import com.management.pizzaria.exceptions.CustomerNotFoundException;
import com.management.pizzaria.models.Customer;
import com.management.pizzaria.models.Drink;
import com.management.pizzaria.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<Customer> saveCustomer(@RequestBody CustomerDTO customerDTO) throws Exception {
        Customer newCustomer = customerService.createCustomer(customerDTO);
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity <List<Customer>> getAllCustomers() {
        var customerList = this.customerService.allCustomers();
        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Customer> findCustomerById(@PathVariable (value = "id") Long id) throws Exception{
        var customer = this.customerService.getCustomerById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Customer> getCustomerByName(@PathVariable (value = "name") String name) throws Exception {
        var customer = this.customerService.getCustomerByName(name);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable (value = "id") Long id, @RequestBody Customer customer) throws Exception {
        var customerUpdate = this.customerService.updateCustomer(id, customer);
        return new ResponseEntity<>(customerUpdate, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable (value = "id") Long id) {
        return this.customerService.deleteCustomer(id);
    }
}
