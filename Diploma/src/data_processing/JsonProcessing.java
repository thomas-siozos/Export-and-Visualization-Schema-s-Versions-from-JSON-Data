package data_processing;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import schema.SchemaHistory;

public class JsonProcessing {
	
	private SchemaHistory schemaHistory;
	private VersionComparison versionComparison;
	private ObjectNodeProcessing objectNodeProcessing;
	//private JsonOutputFile jsonOutputFile;
	private int id;
	
	public JsonProcessing() {
		schemaHistory = new SchemaHistory();
		versionComparison = new VersionComparison();
		id = 1;
	}
	
	public void processingJsonFile(String file) {
		JsonFactory factory = new JsonFactory();
		JsonParser parser = null;
		boolean json_array = true;
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
				json_array = false;
				process(parser);
			}
			if (json_array == true) {
				JsonToken token = null;
				while(true) {
					try {
						token = parser.nextToken();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (!JsonToken.START_OBJECT.equals(token)) break;
					if (token == null) break;
					process(parser);
				}
			}
		}
		System.out.println("Total json objects : " + id);
		schemaHistory.printVersionsNumber();
		if (schemaHistory.createOutputFiles()) {
			System.out.println("All Version Files Created Successfully...");
		} else {
			System.out.println("An error occurred while "
					+ "creating version files...");
		}
	}
	
	private void process(JsonParser parser) {
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
		versionComparison.compareVersions(schemaHistory, currentObject);
		id++;
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
