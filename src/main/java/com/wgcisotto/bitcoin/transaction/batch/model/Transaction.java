package com.wgcisotto.bitcoin.transaction.batch.model;

import java.util.List;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Transaction {

    @JsonProperty("involvesWatchonly")
    private Boolean involvesWatchonly;
    @JsonProperty("account")
    private String account;
    @JsonProperty("address")
    private String address;
    @JsonProperty("category")
    private String category;
    @JsonProperty("amount")
    private Double amount;
    @JsonProperty("label")
    private String label;
    @JsonProperty("confirmations")
    private Integer confirmations;
    @JsonProperty("blockhash")
    private String blockhash;
    @JsonProperty("blockindex")
    private Integer blockindex;
    @JsonProperty("blocktime")
    private Long blocktime;
    @JsonProperty("txid")
    private String txid;
    @JsonProperty("vout")
    private Integer vout;
    @JsonProperty("walletconflicts")
    private List<Object> walletconflicts;
    @JsonProperty("time")
    private Long time;
    @JsonProperty("timereceived")
    private Long timereceived;
    @JsonProperty("bip125-replaceable")
    private String bip125Replaceable;

}