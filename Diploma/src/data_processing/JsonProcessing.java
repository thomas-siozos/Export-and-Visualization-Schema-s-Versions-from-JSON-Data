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
				ObjectNodeProcessing objectNodeProcessing = new ObjectNodeProcessing();
				objectNodeProcessing.setObjectNode(jsonNode);
				//objectNodeProcessing.processObject("root");
				versions.add(objectNodeProcessing.processObject("root"));
			}
		}
		try {
			VersionComparison v = new VersionComparison(versions.get(0), versions.get(1));
			v.compareVersions();
		} catch (IndexOutOfBoundsException e) {
			// TODO Auto-generated catch block
			System.out.println("List 'versions' out of bounds...");
			e.printStackTrace();
		}
//		System.out.println(versions.get(0).getAllFields());
//		System.out.println("Primitives:");
//		System.out.println(objectNodes.get(0).getPrimitives());
//		System.out.println("Objects:");
//		System.out.println(objectNodes.get(0).getObjects());
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
