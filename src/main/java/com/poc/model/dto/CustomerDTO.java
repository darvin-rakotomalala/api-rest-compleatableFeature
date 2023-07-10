package com.poc.model.dto;

import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class CustomerDTO {
    private String id;
    private String fullName;
    private String phoneNumber;
    private Instant createdAt;
    private Instant updatedAt;
    private AddressDTO address;
    private LoyaltyDTO loyalty;
    private List<FinancialInfoDTO> financials;
    private List<PurchaseTransactionDTO> purchaseTransactions;
}
