package client;

import data_processing.JsonProcessing;

public class Client {

	public static void main(String[] args) {
		JsonProcessing jsonProcessing = new JsonProcessing();
		//jsonProcessing.processingJsonFile("data/photo.json");
		//jsonProcessing.processingJsonFile("data/tip.json");
		jsonProcessing.processingJsonFile("data/test_countries_2_entries.json");
		//jsonProcessing.processingJsonFile("data/profiles.json");
		//jsonProcessing.processingJsonFile("data/business.json");
		//jsonProcessing.processingJsonFile("data/review.json");
//		try {
//			Map<String, Object> map = new HashMap<>();
//			Map<String, Object> inside_map = new HashMap<>();
//			map.put("name", "Jhon Deo");
//			map.put("email", "jhon");
//			map.put("roles", new String[] {"Member", "Admin"});
//			map.put("admin", true);
//			inside_map.put("georgia", "i love you");
//			inside_map.put("Your husband", "with love");
//			map.put("inside", inside_map);
//			
//			ObjectMapper mapper = new ObjectMapper();
//			
//			mapper.writeValue(Paths.get("user.json").toFile(), map);
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
	}
}
