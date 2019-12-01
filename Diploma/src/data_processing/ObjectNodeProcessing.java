package data_processing;

//import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.databind.JsonNode;

public class ObjectNodeProcessing {
	
	private JsonNode node;
	private JsonNodeType jsonNodeType = new JsonNodeType();
	private HashMap<Integer, HashMap<String, String>> schema = new HashMap<Integer, HashMap<String, String>>();
	private int id = 0;
	
	public void setObjectNode(JsonNode node) {
		this.node = node;
	}
	
	public void processObject(String parent) {
		Iterator<Map.Entry<String, JsonNode>> objectIterator = node.fields();
		HashMap<Integer, HashMap<String, String>> schema_kid = new HashMap<Integer, HashMap<String, String>>();
		HashMap<Integer, HashMap<String, String>> schema_list = new HashMap<Integer, HashMap<String, String>>();
		@SuppressWarnings("unused")
		Map.Entry<String, JsonNode> nextField;
		ObjectNode objectNode = new ObjectNode();
		HashMap<String, String> inside;
		objectNode.setParent(parent);
		while (objectIterator.hasNext()) {
			nextField = objectIterator.next();
			jsonNodeType.setJsonNode(nextField.getValue());
			if (jsonNodeType.getTypeAsString().equals("ArrayNode")) {
				inside = new HashMap<String, String>();
				inside.put(nextField.getKey(), jsonNodeType.getTypeAsString());
				schema.put(id, inside);
				id++;
				inside = new HashMap<String, String>();
				inside.put("START LIST", jsonNodeType.getTypeAsString());
				objectNode.addList(nextField.getKey(), jsonNodeType.getArrayAsString());
				schema.put(id, inside);
				id++;
				schema_list = processList(nextField.getKey(), nextField.getValue());
				for (Map.Entry<Integer, HashMap<String, String>> entry : schema_list.entrySet()) {
					for (Map.Entry<String, String> entry_inside : entry.getValue().entrySet()) {
						inside = new HashMap<String, String>();
						inside.put(entry_inside.getKey(), entry_inside.getValue());
						schema.put(id, inside);
						id++;
					}
				}
				//schema.put(Integer.toString(id), schema_list);
				//id++;
				inside = new HashMap<String, String>();
				inside.put("END LIST", "Array Node");
				//objectNode.addList(nextField.getKey(), jsonNodeType.getArrayAsString());
				schema.put(id, inside);
				id++;
			} else if (jsonNodeType.getTypeAsString().equals("ObjectNode")) {
				objectNode.addKid(nextField.getKey(), jsonNodeType.getTypeAsString());
				//schema_kid = processKidObject(nextField.getValue(), objectNode.getParent() + "/" + nextField.getKey());
				//schema.put(nextField.getKey(), schema_kid);
				inside = new HashMap<String, String>();
				inside.put(nextField.getKey(), jsonNodeType.getTypeAsString());
				schema.put(id, inside);
				id++;
				inside = new HashMap<String, String>();
				inside.put("START OBJECT", "Object Node");
				schema.put(id, inside);
				id++;
				schema_kid = processKidObject(nextField.getValue(), objectNode.getParent() + "/" + nextField.getKey());
				//System.out.println(schema_kid);
				for (Map.Entry<Integer, HashMap<String, String>> entry : schema_kid.entrySet()) {
					for (Map.Entry<String, String> entry_inside : entry.getValue().entrySet()) {
						inside = new HashMap<String, String>();
						//System.out.println("aaa");
						inside.put(entry_inside.getKey(), entry_inside.getValue());
						schema.put(id, inside);
						id++;
					}
				}
				inside = new HashMap<String, String>();
				inside.put("END OBJECT", "Object Node");
				//objectNode.addList(nextField.getKey(), jsonNodeType.getArrayAsString());
				schema.put(id, inside);
				//id++;
				id++;
			} else {
				inside = new HashMap<String, String>();
				objectNode.addPrimitive(nextField.getKey(), jsonNodeType.getTypeAsString());
				inside.put(nextField.getKey(), jsonNodeType.getTypeAsString());
				schema.put(id, inside);
				//System.out.println(inside);
				//System.out.println(schema);
				//inside.remove(Integer.toString(id));
				id++;
			}
			//inside.clear();
		}
		//schema.put(nextField.getKey(), inside);
		//addSchema(parent: objectNode);
		//System.out.println(schema);
		for (Map.Entry<Integer, HashMap<String, String>> entry : schema.entrySet()) {
			for (Map.Entry<String, String> entry_inside : entry.getValue().entrySet()) {
				if (entry_inside.getKey().equals("START OBJECT")) {
					System.out.println("{");
				} else if (entry_inside.getKey().equals("END OBJECT")) {
					System.out.println("}");
				} else if (entry_inside.getKey().equals("START LIST")) {
					System.out.println("[");
				} else if (entry_inside.getKey().equals("END LIST")) {
					System.out.println("]");
				} else {
					System.out.println(entry_inside);
				}
				//inside = new HashMap<String, String>();
				//System.out.println("aaa");
				//inside.put(entry_inside.getKey(), entry_inside.getValue());
				//schema.put(id, inside);
				//id++;
			}
		}
		System.out.println("\n--------\n");
		//System.out.println(schema);
	}
	
	private HashMap<Integer, HashMap<String, String>> processList(String key, JsonNode list) {
		HashMap<Integer, HashMap<String, String>> schema_list = new HashMap<Integer, HashMap<String, String>>();
		HashMap<Integer, HashMap<String, String>> schema_kid = new HashMap<Integer, HashMap<String, String>>();
		HashMap<String, String> inside;
		int id = 0;
		//Iterator<Map.Entry<String, JsonNode>> listIterator = list.fields();
		//Map.Entry<String, JsonNode> nextField;
		//int id = 0;
		JsonNodeType jsonNodeType = new JsonNodeType();
		//JsonNode node;
		//JsonNode next;
		//System.out.println(list.get(0).getClass().getSimpleName());
		//inside = new HashMap<String, String>();
		//inside
		//schema_list.put(key, list.getNodeType().toString());
		//@SuppressWarnings("unused")
		//Map.Entry<String, JsonNode> nextField;
		for (int i = 0; i < list.size(); i++) {
			jsonNodeType.setJsonNode(list.get(i));
			//node = list.get(i);
			//System.out.println(jsonNodeType.getTypeAsString());
			if (jsonNodeType.getTypeAsString().equals("ObjectNode")) {
//				nextField = node.fie;
//				inside = new HashMap<String, String>();
//				inside.put(nextField.getKey(), jsonNodeType.getTypeAsString());
//				schema_list.put(id, inside);
//				id++;
				inside = new HashMap<String, String>();
				inside.put("START OBJECT", "Object Node");
				schema_list.put(id, inside);
				id++;
				schema_kid = processKidObject(list.get(i), key);
				//System.out.println(schema_kid);
				for (Map.Entry<Integer, HashMap<String, String>> entry : schema_kid.entrySet()) {
					for (Map.Entry<String, String> entry_inside : entry.getValue().entrySet()) {
						inside = new HashMap<String, String>();
						inside.put(entry_inside.getKey(), entry_inside.getValue());
						schema_list.put(id, inside);
						id++;
					}
				}
				inside = new HashMap<String, String>();
				inside.put("END OBJECT", "Object Node");
				//objectNode.addList(nextField.getKey(), jsonNodeType.getArrayAsString());
				schema_list.put(id, inside);
				id++;
				//schema.put(Integer.toString(id), schema_kid);
				//id++;
			} else {
				inside = new HashMap<String, String>();
				inside.put(Integer.toString(id), jsonNodeType.getTypeAsString());
				schema_list.put(id, inside);
				id++;
			}
		}
		return schema_list;
//		while (listIterator.hasNext()) {
//			nextField = listIterator.next();
//			jsonNodeType.setJsonNode(nextField.getValue());
//			System.out.println(nextField.getKey());
//			schema_list.put(nextField.getKey(), jsonNodeType.getTypeAsString());
//			//System.out.println(next.);
//			//schema_list.put(next)
//			if (jsonNodeType.getTypeAsString().equals("ObjectNode")) {
//				processKidObject(nextField.getValue(), key + "_list");
//			}
//		}
//		return schema_list;
	}
	
	private HashMap<Integer, HashMap<String, String>> processKidObject(JsonNode node, String parent) {
		Iterator<Map.Entry<String, JsonNode>> objectIterator = node.fields();
		HashMap<Integer, HashMap<String, String>> schema_kid = new HashMap<Integer, HashMap<String, String>>();
		HashMap<Integer, HashMap<String, String>> schema_grandson = new HashMap<Integer, HashMap<String, String>>();
		HashMap<Integer, HashMap<String, String>> schema_list = new HashMap<Integer, HashMap<String, String>>();
		@SuppressWarnings("unused")
		Map.Entry<String, JsonNode> nextField;
		ObjectNode objectNode = new ObjectNode();
		int id = 0;
		HashMap<String, String> inside;
		objectNode.setParent(parent);
		while (objectIterator.hasNext()) {
			nextField = objectIterator.next();
			jsonNodeType.setJsonNode(nextField.getValue());
			if (jsonNodeType.getTypeAsString().equals("ArrayNode")) {
				//objectNode.addList(nextField.getKey(), jsonNodeType.getArrayAsString());
				//processList(nextField.getKey(), nextField.getValue());
				inside = new HashMap<String, String>();
				inside.put(nextField.getKey(), jsonNodeType.getTypeAsString());
				schema_kid.put(id, inside);
				id++;
				inside = new HashMap<String, String>();
				inside.put("START LIST", jsonNodeType.getTypeAsString());
				objectNode.addList(nextField.getKey(), jsonNodeType.getArrayAsString());
				schema_kid.put(id, inside);
				id++;
				schema_list = processList(nextField.getKey(), nextField.getValue());
				for (Map.Entry<Integer, HashMap<String, String>> entry : schema_list.entrySet()) {
					for (Map.Entry<String, String> entry_inside : entry.getValue().entrySet()) {
						inside = new HashMap<String, String>();
						inside.put(entry_inside.getKey(), entry_inside.getValue());
						schema.put(id, inside);
						id++;
					}
				}
				//schema.put(Integer.toString(id), schema_list);
				id++;
				inside = new HashMap<String, String>();
				inside.put("END LIST", "Array Node");
				//objectNode.addList(nextField.getKey(), jsonNodeType.getArrayAsString());
				schema_kid.put(id, inside);
				id++;
			} else if (jsonNodeType.getTypeAsString().equals("ObjectNode")) {
				objectNode.addKid(nextField.getKey(), jsonNodeType.getTypeAsString());
				//processKidObject(nextField.getValue(), objectNode.getParent() + "/" + nextField.getKey());
				inside = new HashMap<String, String>();
				inside.put(nextField.getKey(), jsonNodeType.getTypeAsString());
				schema_kid.put(id, inside);
				id++;
				inside = new HashMap<String, String>();
				inside.put("START OBJECT", "Object Node");
				schema_kid.put(id, inside);
				id++;
				schema_grandson = processKidObject(nextField.getValue(), objectNode.getParent() + "/" + nextField.getKey());
				//System.out.println(schema_kid);
				for (Map.Entry<Integer, HashMap<String, String>> entry : schema_grandson.entrySet()) {
					for (Map.Entry<String, String> entry_inside : entry.getValue().entrySet()) {
						inside = new HashMap<String, String>();
						inside.put(entry_inside.getKey(), entry_inside.getValue());
						schema_kid.put(id, inside);
						id++;
					}
				}
				inside = new HashMap<String, String>();
				inside.put("END OBJECT", "Object Node");
				//objectNode.addList(nextField.getKey(), jsonNodeType.getArrayAsString());
				schema_kid.put(id, inside);
				//id++;
				id++;
			} else {
				objectNode.addPrimitive(nextField.getKey(), jsonNodeType.getTypeAsString());
				inside = new HashMap<String, String>();
				//objectNode.addPrimitive(nextField.getKey(), jsonNodeType.getTypeAsString());
				inside.put(nextField.getKey(), jsonNodeType.getTypeAsString());
				schema_kid.put(id, inside);
				//System.out.println(inside);
				//System.out.println(schema);
				//inside.remove(Integer.toString(id));
				id++;
				//System.out.println(inside);
				//System.out.println(schema);
				//inside.remove(Integer.toString(id));
				//id++;
			}
		}
		return schema_kid;
		//addSchema(objectNode);
	}
	
//	private void addSchema(ObjectNode objectNode) {
//		HashMap<String, String> parentMap = new HashMap<String, String>();
//		parentMap.put("parent", objectNode.getParent());
//		schema.add(parentMap);
//		if (!objectNode.getPrimitives().isEmpty()) schema.add(objectNode.getPrimitives());
//		if (!objectNode.getLists().isEmpty()) schema.add(objectNode.getLists());
//		if (!objectNode.getKids().isEmpty()) schema.add(objectNode.getKids());
//	}
	
//	public HashMap<String, String> getObjectSchema() {
//		Iterator<Map.Entry<String, JsonNode>> objectIterator = objectNode.fields();
//		@SuppressWarnings("unused")
//		Map.Entry<String, JsonNode> nextField;
//		while (objectIterator.hasNext()) {
//			nextField = objectIterator.next();
//			jsonNodeType.setJsonNode(nextField.getValue());
//			if (jsonNodeType.getTypeAsString().equals("ObjectNode")) {
//				System.out.println(nextField.getKey());
//			}
//			schema.put(nextField.getKey(), jsonNodeType.getTypeAsString());
//		}
//		return schema;
//	}

}