package com.poc.model.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Address {
    private String id;
    private String street;
    private String streetNumber;
    private String city;
    private String state;
    private String zipCode;
    private String country;
}
