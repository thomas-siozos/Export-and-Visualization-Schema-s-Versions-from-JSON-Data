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
import data_processing.ObjectNode;
import data_processing.ObjectNodeProcessing;
import data_processing.VersionComparison;
import schema.SchemaHistory;

class VersionComparisonNotChangesTest {
	
	private SchemaHistory schemaHistory;
	private VersionComparison versionComparison;
	private String file;
	private JsonFactory factory;
	private JsonParser parser;
	private ObjectNodeProcessing objectNodeProcessing;
	private int id;

	@BeforeAll
	static void setUpBeforeClass() {
		System.out.println("Testing VersionComparison class...");
	}

	@BeforeEach
	void setUp() {
		file = "tests/version_comparison_same_object.json";
		factory = new JsonFactory();
		parser = null;
		objectNodeProcessing = new ObjectNodeProcessing();
		schemaHistory = new SchemaHistory();
		versionComparison = new VersionComparison();
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
				ObjectNode currentObject = objectNodeProcessing
						.processObject("root");
				if (id == 1) {
					assertEquals(true, versionComparison.
							compareVersions(schemaHistory, currentObject));
					System.out.println("First object always returns true...");
				} else {
					assertEquals(false, versionComparison.
							compareVersions(schemaHistory, currentObject));
					System.out.println("Schema didn't change...");
				}
				id++;
			}
		}
		System.out.println("Schema: ");
		System.out.println(schemaHistory.getLastVersion().printObject(0, true));
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
