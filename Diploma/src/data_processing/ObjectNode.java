package data_processing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.databind.node.ArrayNode;
import javafx.util.Pair;

public class ObjectNode {
	
	private String objectName;
	private int id;
	private ArrayList<Pair<String, String>> allFields =
			new ArrayList<Pair<String, String>>();
	private HashMap<String, String> primitives =
			new HashMap<String, String>();
	private HashMap<String, ObjectNode> objects =
			new HashMap<String, ObjectNode>();
	private HashMap<String, ArrayNode> arrays =
			new HashMap<String, ArrayNode>();
	
	
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	
	public String getObjectName() {
		return objectName;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void addField(String key, String type) {
		allFields.add(new Pair<>(key, type));
		
	}
	
	public void addPrimitive(String key, String type) {
		primitives.put(key, type);
	}
	
	public void addObject(String key, ObjectNode object) {
		objects.put(key, object);
	}
	
	public void addArray(String key, ArrayNode array) {
		arrays.put(key, array);
	}
	
	public ArrayList<Pair<String, String>> getAllFields() {
		return allFields;
	}
	
	public ArrayList<String> getAllFieldsKeys() {
		ArrayList<String> keys = new ArrayList<String>();
		for (Pair<String, String> field : allFields) {
			keys.add(field.getKey());
		}
		return keys;
	}
	
	public ArrayList<String> getAllFieldsValues() {
		ArrayList<String> values = new ArrayList<String>();
		for (Pair<String, String> field : allFields) {
			values.add(field.getValue());
		}
		return values;
	}
	
	public HashMap<String, String> getPrimitives() {
		return primitives;
	}
	
	public HashMap<String, ObjectNode> getObjects() {
		return objects;
	}
	
	public void printObject(int object_depth) {
		for (Pair<String, String> pair : allFields) {
			if (pair.getValue().equals("ObjectNode")) {
				System.out.println(getTabs(object_depth) + pair.getKey() +
							" : " + pair.getValue());
				object_depth++;
				try {
					searchObjectNode(pair.getKey()).printObject(object_depth);
				} catch(NullPointerException e) {
					System.out.println("Can't find object with this name...");
				}
				object_depth--;
			} else {
				System.out.println(getTabs(object_depth) + pair.getKey() +
							" : " + pair.getValue());
			}
		}
	}
	
	public ObjectNode searchObjectNode(String key) {
		for (Map.Entry<String, ObjectNode> entry : objects.entrySet()) {
			if (key.equals(entry.getKey())) return entry.getValue();
		}
		return null;
	}
	
	private String getTabs(int object_depth) {
		StringBuilder tabs = new StringBuilder();
		tabs.append("");
		for (int i = 0; i < object_depth; i++) tabs.append("\t");
		return tabs.toString();
	}
	
}
