package data_processing;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonProcessing {
	
	
	private VersionComparison versionComparison = new VersionComparison();
	private int id = 1;
	
	public void processingJsonFile(String file) {
		JsonFactory factory = new JsonFactory();
		JsonParser parser = null;
		try {
			parser = factory.createParser(new File(file));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("IO Exception in JsonParser...");
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
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
					// TODO Auto-generated catch block
					System.out.println("IO Exception in JsonNode...");
					e.printStackTrace();
				}
				ObjectNodeProcessing objectNodeProcessing =
						new ObjectNodeProcessing();
				objectNodeProcessing.setObjectNode(jsonNode);
				objectNodeProcessing.setId(id);
				//objectNodeProcessing.processObject("root");
//				versionComparison.setCurrentVersion(objectNodeProcessing
//								.processObject("root"));
				versionComparison.compareVersions(objectNodeProcessing
						.processObject("root"));
				id++;
			}
		}
		versionComparison.printAllVersions();
		versionComparison.printVersionsNumber();
	}
	
	private boolean hasJsonObject(JsonParser parser) {
		try {
			try {
				if (parser.nextToken() == JsonToken.START_OBJECT) return true;
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				System.out.println("Not valid Json Format...");
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
