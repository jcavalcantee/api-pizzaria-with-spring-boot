package com.management.pizzaria.controllers;

import com.management.pizzaria.dtos.CustomerDTO;
import com.management.pizzaria.exceptions.CustomerNotFoundException;
import com.management.pizzaria.models.Customer;
import com.management.pizzaria.models.Drink;
import com.management.pizzaria.services.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@Tag(name = "Customer", description = "Endpoints for Managing Customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    @Operation(summary = "Adds a new Customer", description = "Adds a new Customer by passing in a JSON representation of customer",
            tags = {"Customer"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Customer.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<Customer> saveCustomer(@RequestBody CustomerDTO customerDTO) throws Exception {
        Customer newCustomer = customerService.createCustomer(customerDTO);
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    @Operation(summary = "Finds a Customer", description = "Finds a Customer",
            tags = {"Customer"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Customer.class))
                            ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<Customer> findCustomerById(@PathVariable (value = "id") Long id) throws Exception{
        var customer = this.customerService.getCustomerById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Finds all Customers", description = "Finds all Customers",
            tags = {"Customer"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = Customer.class))
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity <List<Customer>> getAllCustomers() {
        var customerList = this.customerService.allCustomers();
        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "Finds a Customer by Name", description = "Finds a Customer by Name",
            tags = {"Customer"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Customer.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<Customer> getCustomerByName(@PathVariable (value = "name") String name) throws Exception {
        var customer = this.customerService.getCustomerByName(name);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    @Operation(summary = "Updates a Customer", description = "Updates a Customer by passing in a JSON representation of customer",
            tags = {"Customer"},
            responses = {
                    @ApiResponse(description = "Updated", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Customer.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<Customer> updateCustomer(@PathVariable (value = "id") Long id, @RequestBody Customer customer) throws Exception {
        var customerUpdate = this.customerService.updateCustomer(id, customer);
        return new ResponseEntity<>(customerUpdate, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a Customer", description = "Delete a Customer",
            tags = {"Customer"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<Customer> deleteCustomer(@PathVariable (value = "id") Long id) {
        return this.customerService.deleteCustomer(id);
    }
}
