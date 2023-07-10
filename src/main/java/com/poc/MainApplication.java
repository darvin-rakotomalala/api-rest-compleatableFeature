package com.poc;

import com.poc.model.domain.Customer;
import com.poc.model.dto.AddressDTO;
import com.poc.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Instant;
import java.util.UUID;

@Slf4j
@SpringBootApplication
public class MainApplication implements ApplicationRunner {

    @Autowired
    private CustomerRepository customerRepository;

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    public void run(ApplicationArguments args) throws Exception {
        log.info("############################   RUN   ############################");

        customerRepository.deleteAll();
        customerRepository.save(Customer.builder()
                .id("CUST-1")
                .fullName("Tojo Darvin")
                .createdAt(Instant.now())
                .build());
    }
}
