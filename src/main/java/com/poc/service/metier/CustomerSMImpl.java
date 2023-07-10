package com.poc.service.metier;

import com.poc.exception.ErrorsEnum;
import com.poc.exception.FunctionalException;
import com.poc.model.domain.Customer;
import com.poc.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerSMImpl implements CustomerSM {

    private final CustomerRepository customerRepository;

    @Override
    public Customer getCustomerById(String customerId) {
        Optional<Customer> customerFound = Optional.ofNullable(customerRepository.findById(
                customerId).orElseThrow(() -> new FunctionalException(ErrorsEnum.ERR_MCS_CF_NOT_FOUND.getErrorMessage())));
        return customerFound.get();
    }

    @Override
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomersById() {
        return customerRepository.findAll();
    }
}
