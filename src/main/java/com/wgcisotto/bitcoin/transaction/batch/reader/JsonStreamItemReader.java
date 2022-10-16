package com.wgcisotto.bitcoin.transaction.batch.reader;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.MappingJsonFactory;
import lombok.Builder;
import lombok.Data;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.batch.item.support.AbstractItemStreamItemReader;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.HashMap;

@Data
@Builder
public class JsonStreamItemReader<T> extends AbstractItemStreamItemReader<T> implements ResourceAwareItemReaderItemStream<T> {

	private Resource resource;
	private JsonParser jsonParser;
	private String arrayName;
	private ElementMapper<T> elementMapper;

	private Class<? extends T> itemType;

	public T read() throws Exception {
		if (jsonParser.nextToken() != JsonToken.END_ARRAY) {
			if (elementMapper != null) {
				HashMap<String, Object> nodeMap = jsonParser.readValueAs(HashMap.class);
				return elementMapper.mapElement(nodeMap);
			} else {
				return jsonParser.readValueAs(itemType);
			}
		} else {
			return null;
		}
	}

	private JsonParser createJsonParser() throws IOException {
		JsonFactory jsonFactory = new MappingJsonFactory();
		return jsonFactory.createParser(resource.getFile());
	}

	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException {
		try {
			jsonParser = createJsonParser();
			if (jsonParser.nextToken() != JsonToken.START_ARRAY) {
				moveParserOnTargetElement();
			}
		} catch (IOException e) {
			throw new ItemStreamException("Exception during JsonParser creation.", e);
		}
	}

	private void moveParserOnTargetElement() throws IOException {
		while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
			String fieldName = jsonParser.getCurrentName();
			if (arrayName.equals(fieldName)) {
				jsonParser.nextToken();
				break;
			}
		}
	}

	@Override
	public void close() throws ItemStreamException {
		try {
			jsonParser.close();
		} catch (IOException e) {
			throw new ItemStreamException("Exception during JsonParser closing.", e);
		}
	}

	@Override
	public void setResource(Resource resource) {
		this.resource = resource;
	}

}