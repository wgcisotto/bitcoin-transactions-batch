package com.wgcisotto.bitcoin.transaction.batch;

import com.wgcisotto.bitcoin.transaction.batch.repository.CustomerRepository;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Map;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableBatchProcessing
@EnableMongoRepositories(basePackages = "com.kraken.batch")
public class BatchApplication {

	private static final Map<String, String> customers = Map.ofEntries(
			Map.entry("mvd6qFeVkqH6MNAS2Y2cLifbdaX5XUkbZJ", "Wesley Crusher"),
			Map.entry("mmFFG4jqAtw9MoCC88hw5FNfreQWuEHADp", "Leonard McCoy"),
			Map.entry("mzzg8fvHXydKs8j9D2a8t7KpSXpGgAnk4n", "Jonathan Archer"),
			Map.entry("2N1SP7r92ZZJvYKG2oNtzPwYnzw62up7mTo", "Jadzia Dax"),
			Map.entry("mutrAf4usv3HKNdpLwVD4ow2oLArL6Rez8", "Montgomery Scott"),
			Map.entry("miTHhiX3iFhVnAEecLjybxvV5g8mKYTtnM", "James T. Kirk"),
			Map.entry("mvcyJMiAcSXKAEsQxbW9TYZ369rsMG6rVV", "Spock")
	);

	private static CustomerRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(BatchApplication.class, args);
	}

}
