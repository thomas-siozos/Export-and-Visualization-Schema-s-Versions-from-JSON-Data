package data_processing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

public class ObjectNodeProcessing {
	
	private JsonNode node;
	private JsonNodeType jsonNodeType = new JsonNodeType();
	private ArrayList<HashMap<String, String>> schema = new ArrayList<HashMap<String, String>>();
	
	public void setObjectNode(JsonNode node) {
		this.node = node;
	}
	
	public void processObject(String parent) {
		Iterator<Map.Entry<String, JsonNode>> objectIterator = node.fields();
		@SuppressWarnings("unused")
		Map.Entry<String, JsonNode> nextField;
		ObjectNode objectNode = new ObjectNode();
		objectNode.setParent(parent);
		while (objectIterator.hasNext()) {
			nextField = objectIterator.next();
			jsonNodeType.setJsonNode(nextField.getValue());
			if (jsonNodeType.getTypeAsString().equals("ArrayNode")) {
				objectNode.addList(nextField.getKey(), jsonNodeType.getArrayAsString());
				processList(nextField.getKey(), nextField.getValue());
			} else if (jsonNodeType.getTypeAsString().equals("ObjectNode")) {
				objectNode.addKid(nextField.getKey(), jsonNodeType.getTypeAsString());
				processKidObject(nextField.getValue(), objectNode.getParent() + "/" + nextField.getKey());
			} else {
				objectNode.addPrimitive(nextField.getKey(), jsonNodeType.getTypeAsString());
			}
		}
		addSchema(objectNode);
		if (parent.equals("root")) System.out.println(schema);
	}
	
	private void processList(String key, JsonNode list) {
		Iterator<JsonNode> element = list.elements();
		JsonNodeType jsonNodeType = new JsonNodeType();
		JsonNode next;
		while (element.hasNext()) {
			next = element.next();
			jsonNodeType.setJsonNode(next);
			if (jsonNodeType.getTypeAsString().equals("ObjectNode")) {
				processKidObject(next, key + "_list");
			}
		}
	}
	
	private void processKidObject(JsonNode node, String parent) {
		Iterator<Map.Entry<String, JsonNode>> objectIterator = node.fields();
		@SuppressWarnings("unused")
		Map.Entry<String, JsonNode> nextField;
		ObjectNode objectNode = new ObjectNode();
		objectNode.setParent(parent);
		while (objectIterator.hasNext()) {
			nextField = objectIterator.next();
			jsonNodeType.setJsonNode(nextField.getValue());
			if (jsonNodeType.getTypeAsString().equals("ArrayNode")) {
				objectNode.addList(nextField.getKey(), jsonNodeType.getArrayAsString());
				processList(nextField.getKey(), nextField.getValue());
			} else if (jsonNodeType.getTypeAsString().equals("ObjectNode")) {
				objectNode.addKid(nextField.getKey(), jsonNodeType.getTypeAsString());
				processKidObject(nextField.getValue(), objectNode.getParent() + "/" + nextField.getKey());
			} else {
				objectNode.addPrimitive(nextField.getKey(), jsonNodeType.getTypeAsString());
			}
		}
		addSchema(objectNode);
	}
	
	private void addSchema(ObjectNode objectNode) {
		HashMap<String, String> parentMap = new HashMap<String, String>();
		parentMap.put("parent", objectNode.getParent());
		schema.add(parentMap);
		if (!objectNode.getPrimitives().isEmpty()) schema.add(objectNode.getPrimitives());
		if (!objectNode.getLists().isEmpty()) schema.add(objectNode.getLists());
		if (!objectNode.getKids().isEmpty()) schema.add(objectNode.getKids());
	}
	
//	public HashMap<String, String> getObjectSchema() {
//		Iterator<Map.Entry<String, JsonNode>> objectIterator = objectNode.fields();
//		@SuppressWarnings("unused")
//		Map.Entry<String, JsonNode> nextField;
//		while (objectIterator.hasNext()) {
//			nextField = objectIterator.next();
//			jsonNodeType.setJsonNode(nextField.getValue());
//			if (jsonNodeType.getTypeAsString().equals("ObjectNode")) {
//				System.out.println(nextField.getKey());
//			}
//			schema.put(nextField.getKey(), jsonNodeType.getTypeAsString());
//		}
//		return schema;
//	}

}