package io.github.tthn0.demo.withSwaggerAndGql;

import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    private static List<Customer> customers = new ArrayList<>(List.of(
            new Customer(1L, "Alice", "alice@example.com"),
            new Customer(2L, "Bob", "bob@example.com")));

    public static List<Customer> getCustomers() {
        return CustomerService.customers;
    }

    public static Customer getCustomerById(Long id) {
        return customers.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public static Customer createCustomer(String name, String email) {
        Long newId = (long) (customers.size() + 1);
        Customer newCustomer = new Customer(newId, name, email);
        customers.add(newCustomer);
        return newCustomer;
    }
}
