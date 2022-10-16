package com.wgcisotto.bitcoin.transaction.batch.mapper;

import com.wgcisotto.bitcoin.transaction.batch.domain.Deposit;
import com.wgcisotto.bitcoin.transaction.batch.domain.enums.Category;
import com.wgcisotto.bitcoin.transaction.batch.model.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepositMapper {

    Deposit map(Transaction transaction);

    default Category toCategory(String category){
        return Category.fromName(category);
    }

}
