package test;

import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import data_processing.ObjectNodeProcessing;
import schema.AtomicFieldChange;
import schema.Schema;
import schema.SchemaDifference;
import schema.SchemaHistory;

class SchemaDifferenceTest {
	
	private SchemaHistory schemaHistory;
	private String file;
	private JsonFactory factory;
	private JsonParser parser;
	private ObjectNodeProcessing objectNodeProcessing;
	private int id;
	private SchemaDifference schemaDifference;

	@BeforeAll
	static void setUpBeforeClass() {
		System.out.println("Testing SchemaDifference class...");
	}

	@BeforeEach
	void setUp() {
		file = "tests/version_comparison_different_object.json";
		factory = new JsonFactory();
		parser = null;
		objectNodeProcessing = new ObjectNodeProcessing();
		schemaHistory = new SchemaHistory();
		id = 1;
	}

	@Test
	void test() {
		try {
			parser = factory.createParser(new File(file));
		} catch (IOException e) {
			System.out.println("IO Exception in JsonParser...");
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.out.println("Can't open this file...");
			e.printStackTrace();
		}
		if (parser != null) {
			while(hasJsonObject(parser)) {
				parser.setCodec(new ObjectMapper());
				JsonNode jsonNode = null;
				try {
					jsonNode = parser.readValueAsTree();
				} catch (IOException e) {
					System.out.println("IO Exception in JsonNode...");
					e.printStackTrace();
				}
				objectNodeProcessing = new ObjectNodeProcessing();
				objectNodeProcessing.setObjectNode(jsonNode);
				objectNodeProcessing.setId(id);
				schemaHistory.addSchema(new Schema(objectNodeProcessing
						.processObject("root")));
				id++;
			}
		}
		schemaDifference = new SchemaDifference(schemaHistory.getVersion(0),
					schemaHistory.getVersion(1));
		int count = 0;
		for (AtomicFieldChange atomic : schemaDifference.getChanges()) {
			assertEquals("root", atomic.getParent());
			System.out.println(atomic.getParent());
			System.out.println(atomic.getKey() + " : " + atomic.getValue());
			System.out.println(atomic.getAct());
			assertEquals("kids", atomic.getKey());
			if (count == 0) {
				assertEquals("IntNode", atomic.getValue());
				assertEquals("+", atomic.getAct());
			} else if (count == 1) {
				assertEquals("NullNode", atomic.getValue());
				assertEquals("-", atomic.getAct());
			}
			count++;
		}
	}
	
	private boolean hasJsonObject(JsonParser parser) {
		try {
			try {
				if (parser.nextToken() == JsonToken.START_OBJECT) return true;
			} catch (JsonParseException e) {
				System.out.println("Not valid Json Format...");
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}