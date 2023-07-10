package com.poc.service.bdl;

import com.poc.model.dto.AddressDTO;
import com.poc.model.dto.FinancialInfoDTO;
import com.poc.model.dto.LoyaltyDTO;
import com.poc.model.dto.PurchaseTransactionDTO;

import java.util.List;

public interface CustomerBDL {
    AddressDTO getCustomerAddress();
    LoyaltyDTO getCustomerLoyalty();
    List<FinancialInfoDTO> getCustomerFinancialInfo();
    List<PurchaseTransactionDTO> getCustomerPurchaseTransaction();
}
