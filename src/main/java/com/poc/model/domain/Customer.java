package com.poc.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Customer {
    @Id
    private String id;
    private String fullName;
    private String phoneNumber;
    private Instant createdAt;
    private Instant updatedAt;
    private Address address;
    private Loyalty loyalty;
    private List<FinancialInfo> financials;
    private List<PurchaseTransaction> purchaseTransactions;
}
