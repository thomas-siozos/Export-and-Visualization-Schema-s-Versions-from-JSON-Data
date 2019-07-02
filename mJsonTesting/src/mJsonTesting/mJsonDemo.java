package mJsonTesting;

import java.util.List;
import java.util.Map;

import mjson.Json;

public class mJsonDemo {
	public static void main(String[] args) {
		Json jsonAddress = 
			Json.object("streetAddress", "21 2nd Street",
					    "city", "New York",
					    "state", "NY",
					    "postalCode", "10021-3100");
		Json jsonPhone1 =
			Json.object("typee", "home",
						"number", "212 555-1234");
		Json jsonPhone2 =
			Json.object("type", "office",
						"number", "646 555-4567");
		Json jsonPerson =
			Json.object("firstName", "John",
						"lastName", "Smith",
						"isAlive", true,
						"age", 25,
						"address", jsonAddress,
						"phoneNumbers", Json.array(jsonPhone1, jsonPhone2),
						"children", Json.array(),
						"spouse", Json.nil());
		if(jsonPerson.isObject()) {
			Map<String, Object> props = jsonPerson.asMap();
			for(Map.Entry<String, Object> propEntry: props.entrySet()) {
				System.out.println(propEntry.getKey() + ": " + propEntry.getValue());
				if (propEntry.getValue() instanceof List<?>) {
					System.out.println("----");
				}
			}
		}
	}
	
	private void stringInputExample() {
		String jsonStr = "{" +
				"\"firstName\": \"John\"," +
				"\"lastName\": \"Smith\"," +
				"\"isAlive\": true," +
				"\"age\": 25," +
				"\"address\":" +
				"{" +
				"\"streetAddress\": \"21 2nd Street\"," +
				"\"city\": \"New York\"," +
				"\"state\": \"NY\"," +
				"\"postalCode\": \"10021-3100\"" +
				"}," +
				"\"phoneNumbers\":" +
				"[" +
				"{" +
				"\"type\": \"home\"," +
				"\"number\": \"212 555-1234\"" +
				"}," +
				"{" +
				"\"type\": \"office\"," +
				"\"number\": \"646 555-4567\"" +
				"}" +
				"]," +
				"\"children\": []," +
				"\"spouse\": null" +
				"}" +
				"{" +
				"\"firstName\": \"John\"," +
				"\"lastName\": \"Smith\"," +
				"\"isAlive\": true," +
				"\"age\": 25," +
				"\"address\":" +
				"{" +
				"\"streetAddress\": \"21 2nd Street\"," +
				"\"city\": \"New York\"," +
				"\"state\": \"NY\"," +
				"\"postalCode\": \"10021-3100\"" +
				"}," +
				"\"phoneNumbers\":" +
				"[" +
				"{" +
				"\"type\": \"home\"," +
				"\"number\": \"212 555-1234\"" +
				"}," +
				"{" +
				"\"type\": \"office\"," +
				"\"number\": \"646 555-4567\"" +
				"}" +
				"]," +
				"\"children\": []," +
				"\"spouse\": null" +
				"}";
		Json json = Json.read(jsonStr);
		System.out.println(json);
	}
	
	private void jsonPerson() {
		Json jsonAddress = 
				Json.object("streetAddress", "21 2nd Street",
						    "city", "New York",
						    "state", "NY",
						    "postalCode", "10021-3100");
			Json jsonPhone1 =
				Json.object("typee", "home",
							"number", "212 555-1234");
			Json jsonPhone2 =
				Json.object("type", "office",
							"number", "646 555-4567");
			Json jsonPerson =
				Json.object("firstName", "John",
							"lastName", "Smith",
							"isAlive", true,
							"age", 25,
							"address", jsonAddress,
							"phoneNumbers", Json.array(jsonPhone1, jsonPhone2),
							"children", Json.array(),
							"spouse", Json.nil());
			System.out.println(jsonPerson);
	}
}
