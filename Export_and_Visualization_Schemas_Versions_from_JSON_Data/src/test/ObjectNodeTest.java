package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import data_processing.JsonNodeType;
import data_processing.ObjectNode;
import javafx.util.Pair;

class ObjectNodeTest {

	private String file;
	private JsonFactory factory;
	private JsonParser parser;
	private ObjectNode objectNode;
	private ArrayList<Pair<String, String>> allFields;
	
	@BeforeAll
	public static void setUp() {
		System.out.println("Testing JsonNodeType class...\n");
	}

	@BeforeEach
	public void init() {
		file = "tests/object_node_test.json";
		factory = new JsonFactory();
		parser = null;
		objectNode = new ObjectNode();
		allFields = new ArrayList<Pair<String, String>>();
		fillAllFields();
	}
	
	private void fillAllFields() {
		allFields.add(new Pair<String, String>("name", "TextNode"));
		allFields.add(new Pair<String, String>("lastname", "TextNode"));
		allFields.add(new Pair<String, String>("age", "IntNode"));
		allFields.add(new Pair<String, String>("languages", "ArrayNode"));
		allFields.add(new Pair<String, String>("email", "TextNode"));
		allFields.add(new Pair<String, String>("address", "ObjectNode"));
		allFields.add(new Pair<String, String>("married", "BooleanNode"));
		allFields.add(new Pair<String, String>("kids", "NullNode"));
	}
	
	@Test
	void testObjectNode() {
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
						objectNode.addField(nextField.getKey(),
								jsonNodeType.getTypeAsString());
					}
				}
			} catch (IOException e) {
				System.out.println("IOException");
			}
		}
		assertEquals(allFields, objectNode.getAllFields());
		for (Pair<String, String> field : allFields) {
			System.out.println(field.getKey() + " : " + field.getValue());
		}
	}
	
	@AfterEach
	public void clear() {
		allFields.clear();
	}
}
