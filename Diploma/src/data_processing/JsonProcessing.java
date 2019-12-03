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
	
	
	public static void main(String[] args) throws JsonParseException, IOException {
		JsonFactory factory = new JsonFactory();
		JsonParser parser = factory.createParser(new File("test.json"));
		while(hasJsonObject(parser)) {
			parser.setCodec(new ObjectMapper());
			JsonNode jsonNode = parser.readValueAsTree();
			ObjectNodeProcessing objectNodeProcessing = new ObjectNodeProcessing();
			objectNodeProcessing.setObjectNode(jsonNode);
			objectNodeProcessing.processObject("root");
		}
	}
	
	public static boolean hasJsonObject(JsonParser parser) {
		try {
			if (parser.nextToken() == JsonToken.START_OBJECT) return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
