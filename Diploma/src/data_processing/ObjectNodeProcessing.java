package data_processing;

import java.util.Iterator;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

public class ObjectNodeProcessing {
	
	private JsonNode objectNode;
	private JsonNodeType jsonNodeType = new JsonNodeType();
	
	public void setObjectNode(JsonNode objectNode) {
		this.objectNode = objectNode;
	}
	
	public void processObject(String parent) {
		Iterator<Map.Entry<String, JsonNode>> objectIterator = objectNode.fields();
		@SuppressWarnings("unused")
		Map.Entry<String, JsonNode> nextField;
		ObjectNode objectNode = new ObjectNode();
		objectNode.setParent(parent);
		while (objectIterator.hasNext()) {
			nextField = objectIterator.next();
			jsonNodeType.setJsonNode(nextField.getValue());
			if (jsonNodeType.getTypeAsString().equals("ArrayNode")) {
				//System.out.println("List");
				objectNode.addList(nextField.getKey(), jsonNodeType.getArrayAsString());
				processList(nextField.getKey(), nextField.getValue());
			} else if (jsonNodeType.getTypeAsString().equals("ObjectNode")) {
				//System.out.println("Object");
				objectNode.addKid(nextField.getKey(), jsonNodeType.getTypeAsString());
				ObjectNodeProcessing kidProcessing = new ObjectNodeProcessing();
				kidProcessing.setObjectNode(nextField.getValue());
				kidProcessing.processObject(objectNode.getParent() + "/" + nextField.getKey());
			} else {
				objectNode.addPrimitive(nextField.getKey(), jsonNodeType.getTypeAsString());
			}
		}
		if (!objectNode.getPrimitives().isEmpty()) System.out.println(objectNode.getPrimitives());
		System.out.println(objectNode.getParent());
		if (!objectNode.getLists().isEmpty()) System.out.println(objectNode.getLists());
	}
	
	private void processList(String key, JsonNode list) {
		Iterator<JsonNode> element = list.elements();
		JsonNodeType jsonNodeType = new JsonNodeType();
		JsonNode next;
		while (element.hasNext()) {
			next = element.next();
			jsonNodeType.setJsonNode(next);
			if (jsonNodeType.getTypeAsString().equals("ObjectNode")) {
				setObjectNode(next);
				processObject(key + "_list");
			}
		}
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