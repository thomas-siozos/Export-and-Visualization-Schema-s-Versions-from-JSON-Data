package testing;

import data_processing.JsonNodeType;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

class JsonNodeTypeTest {
	
	private JsonFactory factory;
	private JsonParser parser;
	private JsonNodeType jsonNodeType;
	private JsonNode jsonNode;
	
	@BeforeEach
	public void setUp() {
		factory = new JsonFactory();
		parser = null;
		jsonNodeType = new JsonNodeType();
		jsonNode = null;
	}

	@Test
	void testIntNode() {
		String file = "testing_files/json_node_type_test_file.json";
		int count = 0;
		Iterator<Map.Entry<String, JsonNode>> objectIterator =
										openJsonFile(file).fields();
		Map.Entry<String, JsonNode> nextField;
		while (objectIterator.hasNext()) {
			nextField = objectIterator.next();
			jsonNodeType.setJsonNode(nextField.getValue());
			switch(count) {
				case 0:
					checkIntNode();
					break;
				case 1:
					checkDoubleNode();
					break;
				case 2:
					checkTextNode();
					break;
				case 3:
					checkNullNode();
					break;
				case 4:
					checkArrayNode();
					break;
				case 5:
					checkObjectNode();
					break;
				case 6:
					checkBooleanNode();
					break;
				case 7:
					checkBooleanNode();
					break;
				default:
					System.out.println("Not valid number...");
					break;
			}
			count++;
		}
	}
	
	private void checkIntNode() {
		Assert.assertEquals("IntNode", jsonNodeType.getTypeAsString());
	}
	
	private void checkDoubleNode() {
		Assert.assertEquals("DoubleNode", jsonNodeType.getTypeAsString());
	}
	
	private void checkTextNode() {
		Assert.assertEquals("TextNode", jsonNodeType.getTypeAsString());
	}
	
	private void checkNullNode() {
		Assert.assertEquals("NullNode", jsonNodeType.getTypeAsString());
	}
	
	private void checkArrayNode() {
		Assert.assertEquals("ArrayNode", jsonNodeType.getTypeAsString());
	}
	
	private void checkObjectNode() {
		Assert.assertEquals("ObjectNode", jsonNodeType.getTypeAsString());
	}
	
	private void checkBooleanNode() {
		Assert.assertEquals("BooleanNode", jsonNodeType.getTypeAsString());
	}
	
	private JsonNode openJsonFile(String file) {
		try {
			parser = factory.createParser(new File(file));
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		parser.setCodec(new ObjectMapper());
		try {
			jsonNode = parser.readValueAsTree();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonNode;
	}

}
