package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import data_processing.ObjectNode;
import data_processing.ObjectNodeProcessing;
import javafx.util.Pair;

class ObjectNodeProcessingTest {

	private String file;
	private JsonFactory factory;
	private JsonParser parser;
	private ObjectNodeProcessing objectNodeProcessing;
	private ArrayList<Pair<String, String>> allFields;
	
	@BeforeAll
	public static void setUpBeforeClass() {
		System.out.println("Testing JsonNodeType class...\n");
	}

	@BeforeEach
	public void setUp() {
		file = "tests/object_node_test.json";
		factory = new JsonFactory();
		parser = null;
		objectNodeProcessing = new ObjectNodeProcessing();
		allFields = new ArrayList<Pair<String, String>>();
		fillAllFields();
	}
	
	@AfterEach
	public void clear() {
		allFields.clear();
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
	void testProcessObject() {
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
					objectNodeProcessing.setObjectNode(jsonNode);
					objectNodeProcessing.setId(1);
				}
			} catch (IOException e) {
				System.out.println("IOException");
			}
		}
		assertEquals(allFields, objectNodeProcessing.
				processObject("root").getAllFields());
		for (Pair<String, String> field : allFields) {
			System.out.println(field.getKey() + " : " + field.getValue());
		}
	}
	
	@Test
	void testSearchObject() {
		System.out.println("\nTesting searchObject method...");
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
					objectNodeProcessing.setObjectNode(jsonNode);
					objectNodeProcessing.setId(1);
				}
			} catch (IOException e) {
				System.out.println("IOException");
			}
		}
		ObjectNode currentObjectNode = new ObjectNode();
		currentObjectNode = objectNodeProcessing.processObject("root");
		assertEquals(currentObjectNode.searchObjectNode("address"),
				currentObjectNode.getObjects().get("address"));
		for (Pair<String, String> field : currentObjectNode.
				searchObjectNode("address").getAllFields()) {
			System.out.println(field.getKey() + " : " + field.getValue());
		}
	}

}
