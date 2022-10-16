package com.wgcisotto.bitcoin.transaction.batch.configuration;

import com.wgcisotto.bitcoin.transaction.batch.domain.Deposit;
import com.wgcisotto.bitcoin.transaction.batch.model.Transaction;
import com.wgcisotto.bitcoin.transaction.batch.processor.TransactionProcessor;
import com.wgcisotto.bitcoin.transaction.batch.reader.JsonStreamItemReader;
import com.wgcisotto.bitcoin.transaction.batch.service.CustomerService;
import com.wgcisotto.bitcoin.transaction.batch.writer.TransactionWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;

@Slf4j
@Configuration
public class JobConfiguration {

    @Autowired
    private Resource[] inputResources;

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    private TransactionProcessor transactionProcessor;

    @Autowired
    private CustomerService customerService;

    @Value("${processing.chunk}")
    private int processingChunk;

    @Bean
    public ItemStreamReader<Transaction> jsonItemReader() {
        return JsonStreamItemReader.<Transaction>builder()
                .itemType(Transaction.class)
                .arrayName("transactions")
                .build();
    }

    @Bean
    public ItemStreamReader<Transaction> multipleJsonItemReader() throws IOException {
        MultiResourceItemReader<Transaction> resourceItemReader = new MultiResourceItemReader<>();
        resourceItemReader.setResources(inputResources);
        resourceItemReader.setDelegate((ResourceAwareItemReaderItemStream<? extends Transaction>) jsonItemReader());
        return resourceItemReader;
    }

    @Bean
    public TransactionWriter transactionWriter() {
        return new TransactionWriter();
    }

    @Bean
    public Step step1() throws Exception {
        return stepBuilderFactory.get("step1")
                .<Transaction, Deposit>chunk(processingChunk)
                .reader(multipleJsonItemReader())
                .processor(transactionProcessor)
                .writer(transactionWriter())
                .build();
    }

    @Bean
    public Job job() throws Exception {
        customerService.setupCustomers();
        return jobBuilderFactory.get("job")
                .start(step1())
                .build();
    }

}