package test;

import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import data_processing.JsonNodeType;

class JsonNodeTypeTest {
	
	private String file;
	private JsonFactory factory;
	private JsonParser parser;
	
	@BeforeAll
	public static void setUp() {
		System.out.println("Testing JsonNodeType class...\n");
	}

	@BeforeEach
	public void init() {
		file = "tests/json_node_type_test.json";
		factory = new JsonFactory();
		parser = null;
	}
	
	@Test
	void testGetTypeAsString() {
		try {
			parser = factory.
					createParser(new File(file));
		} catch (IOException e) {
			System.out.println("IO Exception");
		} catch(NullPointerException e) {
			System.out.println("NullPointerException");
		}
		if (parser != null) {
			try {
				while(parser.nextToken()
						== JsonToken.START_OBJECT) {
					parser.setCodec(new ObjectMapper());
					JsonNode jsonNode = null;
					jsonNode = parser.readValueAsTree();
					JsonNodeType jsonNodeType = new JsonNodeType();
					Iterator<Map.Entry<String, JsonNode>> objectIterator =
							jsonNode.fields();
					Map.Entry<String, JsonNode> nextField;
					while (objectIterator.hasNext()) {
						nextField = objectIterator.next();
						jsonNodeType.setJsonNode(nextField.getValue());
						System.out.println(nextField.getKey() + " : " +
								jsonNodeType.getTypeAsString());
						if (nextField.getKey().equals("name") ||
								nextField.getKey().equals("lastname")) {
							assertEquals("TextNode", jsonNodeType.getTypeAsString());
						}
						if (nextField.getKey().equals("age"))
							assertEquals("IntNode",jsonNodeType.getTypeAsString());
						if (nextField.getKey().equals("height"))
							assertEquals("DoubleNode", jsonNodeType.getTypeAsString());
						if (nextField.getKey().equals("wife"))
							assertEquals("ObjectNode", jsonNodeType.getTypeAsString());
						if (nextField.getKey().equals("kids"))
							assertEquals("ArrayNode", jsonNodeType.getTypeAsString());
						if (nextField.getKey().equals("married"))
							assertEquals("NullNode", jsonNodeType.getTypeAsString());
						if (nextField.getKey().equals("student"))
							assertEquals("BooleanNode", jsonNodeType.getTypeAsString());
					}
				}
			} catch (IOException e) {
				System.out.println("IOException");
			}
		}
	}
}
