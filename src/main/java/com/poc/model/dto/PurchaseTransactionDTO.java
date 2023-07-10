package com.poc.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseTransactionDTO {
    private String id;
    private String customerId;
    private String paymentType;
    private BigDecimal amount;
    private Instant createdAt;
}
