package data_processing;

import com.fasterxml.jackson.databind.JsonNode;

public class JsonNodeType {
	
	private JsonNode jsonNode;
	
	public void setJsonNode(JsonNode jsonNode) {
		this.jsonNode = jsonNode;
	}
	
	public String getTypeAsString() {
		switch(jsonNode.getClass().getSimpleName()) {
			case "IntNode":
				return "IntNode";
			case "DoubleNode":
				return "DoubleNode";
			case "FloatNode":
				return "FloatNode";
			case "LongNode":
				return "LongNode";
			case "TextNode":
				return "TextNode";
			case "NullNode":
				return "NullNode";
			case "ArrayNode":
				return "ArrayNode";
			case "ObjectNode":
				return "ObjectNode";
			case "BooleanNode":
				return "BooleanNode";
			default:
				System.out.println("Not valid Json Node Type");
				break;
		}
		return null;
	}
}
