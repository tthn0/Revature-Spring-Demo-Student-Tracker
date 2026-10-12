package io.github.tthn0.demo.withSwaggerAndGql;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @GetMapping
    @Operation(summary = "Get customers.", description = "Returns an array of all customers with all fields")
    @ApiResponse(responseCode = "200", description = "Success")
    public List<Customer> customers() {
        return CustomerService.getCustomers();
    }

    @Operation(summary = "Get a specific customer by ID.", description = "Returns a Customer if found and null if not found")
    @ApiResponse(responseCode = "200", description = "Success")
    @GetMapping("/{id}")
    public Customer customerById(
            @Parameter(description = "Customer's ID") @PathVariable Long id) {
        return CustomerService.getCustomerById(id);
    }

    @Operation(summary = "Add a customer", description = "Inserts a new customer and returns the newly created customer")
    @ApiResponse(responseCode = "201", description = "Success")
    @PostMapping
    public ResponseEntity<Customer> createCustomer(
            @Parameter(description = "Customer Write DTO") @RequestBody CustomerWriteDto customer) {
        Customer newCustomer = CustomerService.createCustomer(customer.getName(), customer.getEmail());
        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(newCustomer.getId())
                        .toUri())
                .body(newCustomer);
    }
}