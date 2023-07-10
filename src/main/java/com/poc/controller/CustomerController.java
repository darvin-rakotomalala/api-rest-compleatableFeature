package com.poc.controller;

import com.poc.model.dto.CustomerDTO;
import com.poc.service.applicatif.CustomerSA;
import com.poc.utils.HelpPage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "customers")
public class CustomerController {

    private final CustomerSA customerSA;

    @PatchMapping
    public CompletableFuture<Void> updateCustomerUsingCompletableFeature(
            @RequestParam(name = "customerId", required = true) String customerId,
            @RequestParam(name = "phoneNumber", required = true) String phoneNumber) {
        return customerSA.updateCustomer(customerId, phoneNumber);
    }

    @GetMapping("/{customerId}")
    public CompletableFuture<CustomerDTO> getCustomerByIdUsingCompletableFeature(
            @PathVariable(name = "customerId") String customerId) {
        return customerSA.cFGetCustomerById(customerId);
    }

    @GetMapping("/all-of/{customerId}")
    public CompletableFuture<CustomerDTO> getCustomerByIdUsingCompletableFeatureUsingAllOf(
            @PathVariable(name = "customerId") String customerId) {
        return customerSA.cFGetCustomerByIdUsingAllOf(customerId);
    }

    @GetMapping
    public List<CustomerDTO> cFGetAllCustomers() {
        return customerSA.cFGetAllCustomers();
    }

    @GetMapping("/page")
    public HelpPage<CustomerDTO> getAllCustomersPaged(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "5") int size) {
        return customerSA.getAllCustomersPaged(page, size);
    }

}
