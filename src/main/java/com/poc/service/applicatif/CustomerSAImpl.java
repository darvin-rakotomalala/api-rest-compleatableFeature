package com.poc.service.applicatif;

import com.poc.mapper.CustomerMapper;
import com.poc.model.dto.*;
import com.poc.service.bdl.CustomerBDL;
import com.poc.service.metier.CustomerSM;
import com.poc.utils.HelpPage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerSAImpl implements CustomerSA {

    private final CustomerSM customerSM;
    private final CustomerBDL customerBDL;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerDTO getCustomerById(String customerId) {
        return customerMapper.toDTO(customerSM.getCustomerById(customerId));
    }

    @Override
    public CompletableFuture<Void> updateCustomer(String customerId, String phoneNumber) {
        log.info("### Updating customer", customerId);

        CustomerDTO custFound = getCustomerById(customerId);

        CompletableFuture<Void> updateCustomerCF = null;
        if (phoneNumber != null) {
            log.info("--- Received a phone number, updating customer");
            updateCustomerCF = CompletableFuture.runAsync(() -> {
                custFound.setPhoneNumber(phoneNumber);
                custFound.setUpdatedAt(Instant.now());
                customerSM.saveCustomer(customerMapper.toDO(custFound));
            });
        }

        CompletableFuture<Void> updateFinancialInfoCF = null;
        List<FinancialInfoDTO> financialInfoBdl = customerBDL.getCustomerFinancialInfo();
        if (!CollectionUtils.isEmpty(financialInfoBdl)) {
            log.info("--- Received a financial info, updating it");
            updateFinancialInfoCF = CompletableFuture.runAsync(() -> {
                custFound.setFinancials(financialInfoBdl);
                customerSM.saveCustomer(customerMapper.toDO(custFound));
            });
        }

        CompletableFuture<Void> updateAddressCF = null;
        AddressDTO addressBdl = customerBDL.getCustomerAddress();
        if (addressBdl != null) {
            log.info("--- Received a address, updating it");
            updateAddressCF = CompletableFuture.runAsync(() -> {
                custFound.setAddress(addressBdl);
                customerSM.saveCustomer(customerMapper.toDO(custFound));
            });
        }

        CompletableFuture<Void> updateLoyaltyCF = null;
        LoyaltyDTO loyaltyBdl = customerBDL.getCustomerLoyalty();
        if (loyaltyBdl != null) {
            log.info("--- Received a loyalty, updating it");
            updateLoyaltyCF = CompletableFuture.runAsync(() -> {
                custFound.setLoyalty(loyaltyBdl);
                customerSM.saveCustomer(customerMapper.toDO(custFound));
            });
        }

        CompletableFuture<Void> updatePurchaseTransactionCF = null;
        List<PurchaseTransactionDTO> purchaseTransactionBdl = customerBDL.getCustomerPurchaseTransaction();
        if (!CollectionUtils.isEmpty(purchaseTransactionBdl)) {
            log.info("--- Received a address, updating it");
            updatePurchaseTransactionCF = CompletableFuture.runAsync(() -> {
                custFound.setPurchaseTransactions(purchaseTransactionBdl);
                customerSM.saveCustomer(customerMapper.toDO(custFound));
            });
        }

        List<CompletableFuture<Void>> completableFutures =
                Stream.of(updateCustomerCF, updateFinancialInfoCF, updateAddressCF, updateLoyaltyCF, updatePurchaseTransactionCF)
                        .filter(Objects::nonNull)
                        .toList();

        log.info("### Customer updated successfully!");
        return CompletableFuture.allOf(completableFutures.toArray(
                new CompletableFuture[completableFutures.size()]));
    }

    @Override
    public CompletableFuture<CustomerDTO> cFGetCustomerById(String customerId) {
        log.info("--- Getting customer by id {} ", customerId);
        CompletableFuture<CustomerDTO> customerResponseCF = CompletableFuture.supplyAsync(
                () -> getCustomerById(customerId));

        CompletableFuture<AddressDTO> addressResponseCF = CompletableFuture.supplyAsync(
                customerBDL::getCustomerAddress);

        CompletableFuture<List<PurchaseTransactionDTO>> purchaseTransactionResponsesCF = CompletableFuture.supplyAsync(
                customerBDL::getCustomerPurchaseTransaction);

        CompletableFuture<List<FinancialInfoDTO>> financialResponsesCF = CompletableFuture.supplyAsync(
                customerBDL::getCustomerFinancialInfo);

        CompletableFuture<LoyaltyDTO> loyaltyResponseCF = CompletableFuture.supplyAsync(
                customerBDL::getCustomerLoyalty);

        return customerResponseCF
                .thenCombine(addressResponseCF, (customerResponse, addressResponse) -> {
                    customerResponse.setAddress(addressResponse);
                    return customerResponse;
                })
                .thenCombine(purchaseTransactionResponsesCF, (customerResponse, purchaseTransactionResponses) -> {
                    customerResponse.setPurchaseTransactions(purchaseTransactionResponses);
                    return customerResponse;
                })
                .thenCombine(financialResponsesCF, (customerResponse, financialResponses) -> {
                    customerResponse.setFinancials(financialResponses);
                    return customerResponse;
                })
                .thenCombine(loyaltyResponseCF, (customerResponse, loyaltyResponse) -> {
                    customerResponse.setLoyalty(loyaltyResponse);
                    return customerResponse;
                });
    }

    @Override
    public CompletableFuture<CustomerDTO> cFGetCustomerByIdUsingAllOf(String customerId) {

        log.info("--- Getting customer by id {} using allOf(...)", customerId);
        CompletableFuture<CustomerDTO> customerResponseCF = CompletableFuture.supplyAsync(
                () -> getCustomerById(customerId));

        CompletableFuture<AddressDTO> addressResponseCF = CompletableFuture.supplyAsync(
                customerBDL::getCustomerAddress);

        CompletableFuture<List<PurchaseTransactionDTO>> purchaseTransactionResponsesCF = CompletableFuture.supplyAsync(
                customerBDL::getCustomerPurchaseTransaction);

        CompletableFuture<List<FinancialInfoDTO>> financialResponsesCF = CompletableFuture.supplyAsync(
                customerBDL::getCustomerFinancialInfo);

        CompletableFuture<LoyaltyDTO> loyaltyResponseCF = CompletableFuture.supplyAsync(
                customerBDL::getCustomerLoyalty);

        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(
                customerResponseCF, addressResponseCF,
                purchaseTransactionResponsesCF, financialResponsesCF, loyaltyResponseCF);

        Optional<CustomerDTO> customerResponseOptional = Optional.ofNullable(customerResponseCF.join());

        return voidCompletableFuture
                .thenApply(unusedVariable -> {
                            customerResponseOptional.ifPresent(cr -> {
                                AddressDTO addressResponse = addressResponseCF.join();
                                cr.setAddress(addressResponse);

                                List<PurchaseTransactionDTO> purchaseTransactionResponses = purchaseTransactionResponsesCF.join();
                                cr.setPurchaseTransactions(purchaseTransactionResponses);

                                List<FinancialInfoDTO> financialResponses = financialResponsesCF.join();
                                cr.setFinancials(financialResponses);

                                LoyaltyDTO loyaltyResponse = loyaltyResponseCF.join();
                                cr.setLoyalty(loyaltyResponse);
                            });
                            return customerResponseOptional
                                    .orElse(null);
                        }
                );
    }

    @Override
    public List<CustomerDTO> cFGetAllCustomers() {

        // LIST OF FUTURE TO EXCUTE IN PARALLEL FOR LIST INPUT PARAM
        List<CompletableFuture<CustomerDTO>> cfCustomers = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            cfCustomers.add(cFGetCustomerByIdUsingAllOf("CUST-1"));
        }

        if (!cfCustomers.isEmpty()) {

            // LIST OF FUTURE TO EXCUTE IN PARALLEL FOR LIST INPUT PARAM
            List<CompletableFuture<CustomerDTO>> pageContentFutures = cfCustomers.stream().toList();

            // RUN ALL FUTURES , Create a combined Future using allOf()
            CompletableFuture<Void> allFutures = CompletableFuture
                    .allOf(pageContentFutures.toArray(new CompletableFuture[pageContentFutures.size()]));

            // When all the Futures are completed, call `future.join()` to get their results
            // and collect the results in a list -
            CompletableFuture<List<CustomerDTO>> allPageContentsFuture = allFutures.thenApply(v -> {
                return pageContentFutures.stream()
                        .map(pageContentFuture -> {
                            return pageContentFuture.join();
                        })
                        .collect(Collectors.toList());
            });

            // GET ONLY  List<DepannageUsineDTO>
            List<CustomerDTO> finalResult = allPageContentsFuture.join();
            return finalResult;
        }

        return new ArrayList<>();
    }

    @Override
    public HelpPage<CustomerDTO> getAllCustomersPaged(int page, int size) {
        int skipCount = (page - 1) * size;

        List<CustomerDTO> customerDTOList = cFGetAllCustomers();

        // System.out.println(customerDTOList.toString());

        List<CustomerDTO> customersPage = customerDTOList
                .stream()
                .skip(skipCount)
                .limit(size)
                .toList();

        return new HelpPage<>(page, customerDTOList.size(), customersPage);
    }

}
