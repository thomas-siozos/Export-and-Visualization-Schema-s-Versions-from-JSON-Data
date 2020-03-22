package json_output;

import java.util.HashMap;
import data_processing.ObjectNode;
import javafx.util.Pair;
import schema.Schema;

public class JsonObject {
	
	private HashMap<String, Object> object;
	
	public JsonObject() {
		object = new HashMap<String, Object>();
	}
	
	public HashMap<String, Object> createJsonObject(Schema schema) {
		for (Pair<String, String> field : schema.getAllFields()) {
			if (field.getValue().equals("ObjectNode")) {
				object.put(field.getKey(),
						createNestedObject(schema.
								searchObjectNode(field.getKey())));
			} else {
				object.put(field.getKey(), field.getValue());
			}
		}
		return object;
	}
	
	public HashMap<String, Object> createNestedObject(ObjectNode objectNode) {
		HashMap<String, Object> nestedObject = new HashMap<String, Object>();
		for (Pair<String, String> field : objectNode.getAllFields()) {
			if (field.getValue().equals("ObjectNode")) {
				nestedObject.put(field.getKey(),
							createNestedObject(objectNode.
									searchObjectNode(field.getKey())));
			} else {
				nestedObject.put(field.getKey(), field.getValue());
			}
		}
		return nestedObject;
	}
}
