package com.poc.service.bdl;

import com.poc.model.dto.AddressDTO;
import com.poc.model.dto.FinancialInfoDTO;
import com.poc.model.dto.LoyaltyDTO;
import com.poc.model.dto.PurchaseTransactionDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class CustomerBDLImpl implements CustomerBDL {

    @Override
    public AddressDTO getCustomerAddress() {
        return AddressDTO.builder()
                .id(UUID.randomUUID().toString())
                .street("Cust-streetName")
                .streetNumber("Cust-streetAddressNumber")
                .city("Cust-city")
                .state("Cust-state")
                .zipCode("Cust-zipCode")
                .country("Cust-country")
                .build();
    }

    @Override
    public LoyaltyDTO getCustomerLoyalty() {
        return LoyaltyDTO.builder()
                .points(30)
                .build();
    }

    @Override
    public List<FinancialInfoDTO> getCustomerFinancialInfo() {
        List<FinancialInfoDTO> financialInfoList = new ArrayList<>();
        var financialInfo1 = FinancialInfoDTO.builder()
                .id(UUID.randomUUID().toString())
                .creditCardNumber("Cust1-creditCardNumber")
                .iban("Cust1-iban")
                .build();

        var financialInfo2 = FinancialInfoDTO.builder()
                .id(UUID.randomUUID().toString())
                .creditCardNumber("Cust1.1-creditCardNumber")
                .iban("Cust1.1-iban")
                .build();

        return new ArrayList<>(Arrays.asList(financialInfo1, financialInfo2));
    }

    @Override
    public List<PurchaseTransactionDTO> getCustomerPurchaseTransaction() {
        var purchaseTransaction1 = PurchaseTransactionDTO.builder()
                .id(UUID.randomUUID().toString())
                .customerId("CUST-1")
                .paymentType("DEBIT")
                .amount(new BigDecimal(5000))
                .createdAt(Instant.now())
                .build();

        var purchaseTransaction2 = PurchaseTransactionDTO.builder()
                .id(UUID.randomUUID().toString())
                .customerId("CUST-2")
                .paymentType("CREDIT")
                .amount(new BigDecimal(8000))
                .createdAt(Instant.now())
                .build();

        return new ArrayList<>(Arrays.asList(purchaseTransaction1, purchaseTransaction2));
    }
}
