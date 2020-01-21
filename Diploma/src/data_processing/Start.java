package data_processing;

public class Start {

	public static void main(String[] args) {
		JsonProcessing jsonProcessing = new JsonProcessing();
		jsonProcessing.processingJsonFile("test_countries_2_entries.json");
	}
}
