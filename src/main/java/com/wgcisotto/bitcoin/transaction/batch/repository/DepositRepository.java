package com.wgcisotto.bitcoin.transaction.batch.repository;

import com.wgcisotto.bitcoin.transaction.batch.domain.Deposit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositRepository extends MongoRepository<Deposit, String> {


}
