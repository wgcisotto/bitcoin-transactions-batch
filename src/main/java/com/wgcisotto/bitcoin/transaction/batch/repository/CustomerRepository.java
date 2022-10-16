package com.wgcisotto.bitcoin.transaction.batch.repository;

import com.wgcisotto.bitcoin.transaction.batch.domain.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

    Optional<Customer> findCustomerByAddress(String address);

}
