package client;

import data_processing.JsonProcessing;

public class Client {

	public static void main(String[] args) {
		JsonProcessing jsonProcessing = new JsonProcessing();
		//jsonProcessing.processingJsonFile("data/photo.json");
		//jsonProcessing.processingJsonFile("data/tip.json");
		//jsonProcessing.processingJsonFile("test_countries_2_entries.json");
		//jsonProcessing.processingJsonFile("data/profiles.json");
		//jsonProcessing.processingJsonFile("data/business.json");
		jsonProcessing.processingJsonFile("data/review.json");
	}
}
