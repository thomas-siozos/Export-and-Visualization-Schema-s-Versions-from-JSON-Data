package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import data_processing.JsonNodeType;

class JsonNodeTypeTest {
	
	JsonNodeType jsonNodeType = new JsonNodeType();

	@Test
	void testGetTypeAsString() throws JsonParseException, IOException {
		String jsonIntExample = "{ \"int\" : 8, \"double\" : 8.0, \"array\" : [ 1, \"8\", 3 ]}";
		JsonFactory factory = new JsonFactory();
		JsonParser parser = factory.createParser(jsonIntExample);
		parser.setCodec(new ObjectMapper());
		JsonNode jsonNode = parser.readValueAsTree();
		Iterator<Map.Entry<String, JsonNode>> ite = jsonNode.fields();
		Map.Entry<String, JsonNode> entry = ite.next();
		testIntNode(entry.getValue());
		entry = ite.next();
		testDoubleNode(entry.getValue());
		entry = ite.next();
		System.out.println(entry.getValue());
		testArrayNode(entry.getValue());
	}
	
	void testIntNode(JsonNode jsonNode) {
		jsonNodeType.setJsonNode(jsonNode);
		assertEquals("IntNode", jsonNodeType.getTypeAsString());
	}
	
	void testDoubleNode(JsonNode jsonNode) {
		jsonNodeType.setJsonNode(jsonNode);
		assertEquals("DoubleNode", jsonNodeType.getTypeAsString());
	}
	
	void testArrayNode(JsonNode jsonNode) {
		jsonNodeType.setJsonNode(jsonNode);
		assertEquals("ArrayNode", jsonNodeType.getTypeAsString());
	}

}
