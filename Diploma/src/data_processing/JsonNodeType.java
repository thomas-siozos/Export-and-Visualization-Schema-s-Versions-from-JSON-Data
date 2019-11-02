package data_processing;

import java.util.Iterator;
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
			default:
				System.out.println("Not valid Json Node Type");
				break;
			
		}
		return null;
	}
	
	public String getArrayAsString() {
		StringBuilder arrayTabDelimited = new StringBuilder();
		Iterator<JsonNode> arrayIterator = jsonNode.elements();
		JsonNode next;
		while (arrayIterator.hasNext() ) {
			next = arrayIterator.next();
			arrayTabDelimited.append(next.getClass().getSimpleName() + "\t");
			//System.out.println(next.getClass().getSimpleName());
		}
		//System.out.println(arrayTabDelimited);
		return arrayTabDelimited.toString();
	}

}
