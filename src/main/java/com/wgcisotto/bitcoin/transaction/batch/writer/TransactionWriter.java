package com.wgcisotto.bitcoin.transaction.batch.writer;

import com.wgcisotto.bitcoin.transaction.batch.domain.Deposit;
import com.wgcisotto.bitcoin.transaction.batch.repository.DepositRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class TransactionWriter implements ItemWriter<Deposit> {

    @Autowired
    private DepositRepository repository;

    @Override
    public void write(List<? extends Deposit> deposits) {
        log.info("Persisting {} deposits", deposits.size());
        repository.saveAll(deposits);
    }

}
