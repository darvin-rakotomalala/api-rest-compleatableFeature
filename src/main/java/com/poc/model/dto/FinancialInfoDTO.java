package com.poc.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FinancialInfoDTO {
    private String id;
    private String creditCardNumber;
    private String iban;
}
