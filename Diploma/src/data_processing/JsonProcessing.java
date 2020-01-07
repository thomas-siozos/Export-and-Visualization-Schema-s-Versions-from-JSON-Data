package data_processing;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonProcessing {
	
	
	public static ArrayList<ObjectNode> versions = new ArrayList<ObjectNode>();
	
	public void processingJsonFile() {
		JsonFactory factory = new JsonFactory();
		JsonParser parser = null;
		try {
			parser = factory.createParser(new File("test_countries_2_entries.json"));
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			System.out.println("Error on parsing this file...");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Can't read this file...");
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
					System.out.println("Error on reading value as Tree...");
					e.printStackTrace();
				}
				ObjectNodeProcessing objectNodeProcessing = new ObjectNodeProcessing();
				objectNodeProcessing.setObjectNode(jsonNode);
				objectNodeProcessing.processObject("root");
				versions.add(objectNodeProcessing.getObjectNode());
			}
		}
		VersionComparison v = new VersionComparison(versions.get(0), versions.get(1));
		v.compareVersions();
//		System.out.println(versions.get(0).getAllFields());
//		System.out.println("Primitives:");
//		System.out.println(objectNodes.get(0).getPrimitives());
//		System.out.println("Objects:");
//		System.out.println(objectNodes.get(0).getObjects());
	}
	
	private boolean hasJsonObject(JsonParser parser) {
		try {
			if (parser.nextToken() == JsonToken.START_OBJECT) return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
