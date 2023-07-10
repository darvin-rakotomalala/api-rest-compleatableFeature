package com.poc.service.applicatif;

import com.poc.model.dto.CustomerDTO;
import com.poc.utils.HelpPage;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface CustomerSA {
    CustomerDTO getCustomerById(String customerId);
    CompletableFuture<Void> updateCustomer(String customerId, String phoneNumber);
    CompletableFuture<CustomerDTO> cFGetCustomerById(String customerId);
    CompletableFuture<CustomerDTO> cFGetCustomerByIdUsingAllOf(String customerId);
    List<CustomerDTO> cFGetAllCustomers();
    HelpPage<CustomerDTO> getAllCustomersPaged(int page, int size);
}
