package com.wgcisotto.bitcoin.transaction.batch.service;

import com.wgcisotto.bitcoin.transaction.batch.domain.Customer;
import com.wgcisotto.bitcoin.transaction.batch.exception.CustomerNotFoundException;
import com.wgcisotto.bitcoin.transaction.batch.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Map<String, String> customers = Map.ofEntries(
            Map.entry("mvd6qFeVkqH6MNAS2Y2cLifbdaX5XUkbZJ", "Wesley Crusher"),
            Map.entry("mmFFG4jqAtw9MoCC88hw5FNfreQWuEHADp", "Leonard McCoy"),
            Map.entry("mzzg8fvHXydKs8j9D2a8t7KpSXpGgAnk4n", "Jonathan Archer"),
            Map.entry("2N1SP7r92ZZJvYKG2oNtzPwYnzw62up7mTo", "Jadzia Dax"),
            Map.entry("mutrAf4usv3HKNdpLwVD4ow2oLArL6Rez8", "Montgomery Scott"),
            Map.entry("miTHhiX3iFhVnAEecLjybxvV5g8mKYTtnM", "James T. Kirk"),
            Map.entry("mvcyJMiAcSXKAEsQxbW9TYZ369rsMG6rVV", "Spock")
    );

    @Autowired
    private CustomerRepository repository;


    @Override
    public void setupCustomers() {
        log.info("Persisting known users");
        customers.forEach((address, name) -> repository.save(Customer.builder().name(name).address(address).build()));
    }

    @Override
    public Customer findKnowCustomer(String address) throws CustomerNotFoundException {
        return repository.findCustomerByAddress(address)
                .orElseThrow(CustomerNotFoundException::new);
    }
}
