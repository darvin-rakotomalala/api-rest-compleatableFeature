package com.poc.model.domain;

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
public class PurchaseTransaction {
    private String id;
    private String customerId;
    private String paymentType;
    private BigDecimal amount;
    private Instant createdAt;
}
