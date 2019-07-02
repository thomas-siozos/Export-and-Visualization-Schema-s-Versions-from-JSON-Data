import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

public class GSonDemo {

	public static void main(String[] args) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		Type mapType = new TypeToken<Map<String, Object>>(){}.getType();
		Gson gson = new Gson();
		JsonReader jsonReader = new JsonReader(new FileReader("test.json"));
		//jsonReader.beginObject();
		jsonReader.setLenient(true);
//		System.out.println(jsonReader.peek());
//		System.out.println(jsonReader.hasNext());
//		map = gson.fromJson(jsonReader, mapType);
//		System.out.println(map.keySet());
//		System.out.println(jsonReader.peek());
//		System.out.println(jsonReader.hasNext());
//		map = gson.fromJson(jsonReader, mapType);
//		System.out.println(map.keySet());
//		System.out.println(jsonReader.peek());
//		System.out.println(jsonReader.hasNext());
//		map = gson.fromJson(jsonReader, mapType);
		System.out.println(map.keySet());
		while (jsonReader.hasNext() && jsonReader.peek() != JsonToken.END_DOCUMENT) {
			System.out.println(jsonReader.hasNext());
			map = gson.fromJson(jsonReader, mapType);
			System.out.println(map);
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				if (entry.getValue() != null) {
					System.out.println("key: " + entry.getKey() + " type: " + entry.getValue().getClass().getSimpleName());
				}
			}
			if(jsonReader.peek() == JsonToken.END_DOCUMENT) {
				jsonReader.close();
				break;
			}
		}
//		System.out.println(jsonReader.hasNext());
//		if(jsonReader.peek() == JsonToken.END_DOCUMENT) {
//			jsonReader.close();
//		}
	}

}
