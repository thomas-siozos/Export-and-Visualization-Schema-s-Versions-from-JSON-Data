package data_processing;

import java.util.Iterator;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

public class ObjectNodeProcessing {
	
	private JsonNode node;
	private JsonNodeType jsonNodeType = new JsonNodeType();
	private ObjectNode objectNode = new ObjectNode();
	private final int ROOT_OBJECT_DEPTH = 0;
	
	public void setObjectNode(JsonNode node) {
		this.node = node;
	}
	
	public ObjectNode getObjectNode() {
		return objectNode;
	}
	
	public ObjectNode processObject(String parent) {
		Iterator<Map.Entry<String, JsonNode>> objectIterator = node.fields();
		Map.Entry<String, JsonNode> nextField;
		while (objectIterator.hasNext()) {
			nextField = objectIterator.next();
			jsonNodeType.setJsonNode(nextField.getValue());
			if (jsonNodeType.getTypeAsString().equals("ObjectNode")) {
				objectNode.addField(nextField.getKey(),
						jsonNodeType.getTypeAsString());
				ObjectNodeProcessing object = new ObjectNodeProcessing();
				object.setObjectNode(nextField.getValue());
				object.processObject("kid");
				objectNode.addObject(nextField.getKey(),
						object.getObjectNode());
			} else {
				objectNode.addField(nextField.getKey(),
						jsonNodeType.getTypeAsString());
				objectNode.addPrimitive(nextField.getKey(),
						jsonNodeType.getTypeAsString());
			}
		}
		if (parent.equals("root")) {
			objectNode.printObject(ROOT_OBJECT_DEPTH);
		}
		return objectNode;
	}
}
