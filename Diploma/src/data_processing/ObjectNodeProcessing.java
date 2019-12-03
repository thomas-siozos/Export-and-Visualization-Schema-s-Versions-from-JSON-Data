package data_processing;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

public class ObjectNodeProcessing {
	
	private JsonNode node;
	private JsonNodeType jsonNodeType = new JsonNodeType();
	private HashMap<Integer, HashMap<String, String>> schema
				= new HashMap<Integer, HashMap<String, String>>();
	private int id = 0;
	
	public void setObjectNode(JsonNode node) {
		this.node = node;
	}
	
	public void processObject(String parent) {
		Iterator<Map.Entry<String, JsonNode>> objectIterator = node.fields();
		Map.Entry<String, JsonNode> nextField;
		while (objectIterator.hasNext()) {
			nextField = objectIterator.next();
			jsonNodeType.setJsonNode(nextField.getValue());
			if (jsonNodeType.getTypeAsString().equals("ArrayNode")) {
				createListOnSchema(nextField);
			} else if (jsonNodeType.getTypeAsString().equals("ObjectNode")) {
				createObjectOnSchema(nextField);
			} else {
				schema.put(id, fillNestedMap(nextField.getKey(),
						jsonNodeType.getTypeAsString()));
				id++;
			}
		}
		System.out.println(schema);
		printSchema();
	}
	

	private void createListOnSchema(Map.Entry<String, JsonNode> nextField) {
		schema.put(id, fillNestedMap(nextField.getKey(),
				jsonNodeType.getTypeAsString()));
		id++;
		schema.put(id, fillNestedMap("START LIST",
				jsonNodeType.getTypeAsString()));
		id++;
		processList(nextField.getKey(), nextField.getValue());
		schema.put(id, fillNestedMap("END LIST", "Array Node"));
		id++;
	}
	
	private HashMap<String, String> fillNestedMap(String key, String value) {
		HashMap<String, String> nestedMap = new HashMap<String, String>();
		nestedMap.put(key, value);
		return nestedMap;
	}
	
	private void processList(String key, JsonNode list) {
		JsonNodeType jsonNodeType = new JsonNodeType();
		for (int i = 0; i < list.size(); i++) {
			jsonNodeType.setJsonNode(list.get(i));
			if (jsonNodeType.getTypeAsString().equals("ObjectNode")) {
				schema.put(id, fillNestedMap("START OBJECT", "Object Node"));
				id++;
				processKidObject(list.get(i));
				schema.put(id, fillNestedMap("END OBJECT", "Object Node"));
				id++;
			} else {
				schema.put(id, fillNestedMap(Integer.toString(id),
						jsonNodeType.getTypeAsString()));
				id++;
			}
		}
	}
	
	private void processKidObject(JsonNode node) {
		Iterator<Map.Entry<String, JsonNode>> objectIterator = node.fields();
		Map.Entry<String, JsonNode> nextField;
		while (objectIterator.hasNext()) {
			nextField = objectIterator.next();
			jsonNodeType.setJsonNode(nextField.getValue());
			if (jsonNodeType.getTypeAsString().equals("ArrayNode")) {
				createListOnSchema(nextField);
			} else if (jsonNodeType.getTypeAsString().equals("ObjectNode")) {
				createObjectOnSchema(nextField);
			} else {
				schema.put(id, fillNestedMap(nextField.getKey(),
						jsonNodeType.getTypeAsString()));
				id++;
			}
		}
	}
	
	private void createObjectOnSchema(Map.Entry<String, JsonNode> nextField) {
		schema.put(id, fillNestedMap(nextField.getKey(),
				jsonNodeType.getTypeAsString()));
		id++;
		schema.put(id, fillNestedMap("START OBJECT", "Object Node"));
		id++;
		processKidObject(nextField.getValue());
		schema.put(id, fillNestedMap("END OBJECT", "Object Node"));
		id++;
	}

	private void printSchema() {
		for (Map.Entry<Integer, HashMap<String, String>> entry :
											schema.entrySet()) {
			for (Map.Entry<String, String> entryInNestedMap :
								entry.getValue().entrySet()) {
				if (entryInNestedMap.getKey().equals("START OBJECT")) {
					System.out.println("{");
				} else if (entryInNestedMap.getKey().equals("END OBJECT")) {
					System.out.println("}");
				} else if (entryInNestedMap.getKey().equals("START LIST")) {
					System.out.println("[");
				} else if (entryInNestedMap.getKey().equals("END LIST")) {
					System.out.println("]");
				} else {
					System.out.println(entryInNestedMap);
				}
			}
		}
		System.out.println("\n--------\n");
	}
}
