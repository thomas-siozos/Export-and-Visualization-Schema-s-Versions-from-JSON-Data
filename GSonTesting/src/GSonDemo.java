import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

public class GSonDemo {

	public static void main(String[] args) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		Type mapType = new TypeToken<Map<String, Object>>(){}.getType();
		ArrayList<Map<String, Object>> l = new ArrayList<Map<String, Object>>();
		Gson gson = new Gson();
		JsonReader jsonReader = new JsonReader(new FileReader("test.json"));
		//jsonReader.beginObject();
		jsonReader.setLenient(true);
		HashMap<String, Object> hash_map = new HashMap<String, Object>();
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
//		System.out.println(map.keySet());
//		while (jsonReader.hasNext() && jsonReader.peek() != JsonToken.END_DOCUMENT) {
//			System.out.println(jsonReader.hasNext());
//			map = gson.fromJson(jsonReader, mapType);
//			l.add(map);
//			System.out.println(map);
//			for (Map.Entry<String, Object> entry : map.entrySet()) {
//				if (entry.getValue() != null) {
//					System.out.println("key: " + entry.getKey() + " type: " + entry.getValue().getClass().getSimpleName());
//				}
//				if (entry.getValue() instanceof LinkedTreeMap) {
//					@SuppressWarnings("unchecked")
//					Map<String, Object> ap = (Map<String, Object>) entry.getValue();
//					for (Map.Entry<String, Object> entry1 : ap.entrySet()) {
//						System.out.println(entry1.getValue().getClass().getSimpleName());
//					}
//					System.out.println("Bingo!");
//				}
//			}
//			if(jsonReader.peek() == JsonToken.END_DOCUMENT) {
//				jsonReader.close();
//				break;
//			}
//		}
//		System.out.println(l);
//		System.out.println(jsonReader.hasNext());
//		if(jsonReader.peek() == JsonToken.END_DOCUMENT) {
//			jsonReader.close();
//		}
		JsonReader jsonReader1 = new JsonReader(new FileReader("test.json"));
		jsonReader1.setLenient(true);
		JsonElement jsonElement;
		JsonObject jsonObject;
		Set<Entry<String, JsonElement>> entrySet;
		while (jsonReader1.hasNext() && jsonReader1.peek() != JsonToken.END_DOCUMENT) {
			jsonElement = gson.fromJson(jsonReader1, JsonElement.class);
			//jsonElement = parser.parse(el);
			System.out.println("As JsonElement: " + jsonElement);
			jsonObject = jsonElement.getAsJsonObject();
			System.out.println("As JsonObject: " + jsonObject);
			entrySet = jsonObject.entrySet();
			System.out.println("As entrySet: " + entrySet);
			for (Map.Entry<String, JsonElement> entry : entrySet) {
				System.out.println(entry.getKey());
			}
		}
	}

}
