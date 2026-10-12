package io.github.tthn0.demo.withSwaggerAndGql;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class GqlController {
    @QueryMapping
    public List<Customer> customers() {
        return CustomerService.getCustomers();
    }

    @QueryMapping
    public Customer customerById(@Argument Long id) {
        return CustomerService.getCustomerById(id);
    }

    @MutationMapping
    public Customer createCustomer(
            @Argument String name,
            @Argument String email) {
        return CustomerService.createCustomer(name, email);
    }
}