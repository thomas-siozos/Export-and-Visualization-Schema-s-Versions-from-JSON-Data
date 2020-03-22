package data_processing;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import json_output.OutputFile;
import schema.SchemaHistory;

public class JsonProcessing {
	
	
	private SchemaHistory schemaHistory;
	private VersionComparison versionComparison;
	ObjectNodeProcessing objectNodeProcessing;
	private OutputFile outputFile;
	private int id;
	
	public JsonProcessing() {
		schemaHistory = new SchemaHistory();
		versionComparison = new VersionComparison();
		id = 1;
	}
	
	public void processingJsonFile(String file) {
		JsonFactory factory = new JsonFactory();
		JsonParser parser = null;
		try {
			parser = factory.createParser(new File(file));
		} catch (IOException e) {
			System.out.println("IO Exception in JsonParser...");
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.out.println("Can't open this file...");
			e.printStackTrace();
		}
		if (parser != null)
		{
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
				versionComparison.compareVersions(schemaHistory, currentObject);
				id++;
			}
		}
		schemaHistory.setFile(file);
		schemaHistory.printVersionsNumber();
		if (schemaHistory.createOutputFiles()) {
			System.out.println("All Version Files Created Successfully...");
		} else {
			System.out.println("An error occurred while "
					+ "creating version files...");
		}
		outputFile = new OutputFile(schemaHistory);
		outputFile.setPath(file);
		if (outputFile.createOutputFiles()) {
			System.out.println("All json output files created successfully...");
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
