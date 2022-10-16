package com.wgcisotto.bitcoin.transaction.batch.service;

import com.wgcisotto.bitcoin.transaction.batch.domain.Customer;
import com.wgcisotto.bitcoin.transaction.batch.exception.CustomerNotFoundException;

public interface CustomerService {

    void setupCustomers();

    Customer findKnowCustomer(String address) throws CustomerNotFoundException;

}
