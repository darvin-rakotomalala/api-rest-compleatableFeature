package com.poc.service.metier;

import com.poc.model.domain.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerSM {
    Customer getCustomerById(String customerId);
    void saveCustomer(Customer customer);
    List<Customer> getAllCustomersById();
}
