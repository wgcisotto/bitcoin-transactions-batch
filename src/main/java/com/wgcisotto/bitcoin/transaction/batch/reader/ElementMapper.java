package com.wgcisotto.bitcoin.transaction.batch.reader;

import java.util.HashMap;

public interface ElementMapper<T> {
    T mapElement(HashMap<String, Object> elementMap);
}