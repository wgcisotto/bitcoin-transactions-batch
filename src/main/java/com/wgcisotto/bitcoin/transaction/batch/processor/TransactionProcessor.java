package com.wgcisotto.bitcoin.transaction.batch.processor;

import com.wgcisotto.bitcoin.transaction.batch.domain.Customer;
import com.wgcisotto.bitcoin.transaction.batch.domain.Deposit;
import com.wgcisotto.bitcoin.transaction.batch.exception.CustomerNotFoundException;
import com.wgcisotto.bitcoin.transaction.batch.mapper.DepositMapper;
import com.wgcisotto.bitcoin.transaction.batch.model.Transaction;
import com.wgcisotto.bitcoin.transaction.batch.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TransactionProcessor implements ItemProcessor<Transaction, Deposit> {

    @Autowired
    private DepositMapper mapper;

    @Autowired
    private CustomerService service;

    @Override
    public Deposit process(Transaction transactions) {
        log.info(transactions.toString());
        Deposit deposit = mapper.map(transactions);
        if(transactions.getConfirmations() >= 6){
            deposit.setValid(true);
        }
        checkForKnowCustomer(deposit);
        log.info(deposit.toString());
        return deposit;
    }

    private void checkForKnowCustomer(Deposit deposit) {
        try {
            Customer customer = service.findKnowCustomer(deposit.getAddress());
            deposit.setKnowCustomer(true);
            deposit.setCustomerIdentification(customer.getId());
        } catch (CustomerNotFoundException e) {
            log.warn("Customer not found for address: {}", deposit.getAddress());
        }
    }
}
