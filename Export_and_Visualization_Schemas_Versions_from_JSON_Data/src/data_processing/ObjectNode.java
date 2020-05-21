package data_processing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.util.Pair;

public class ObjectNode {
	
	private String objectName;
	private int id;
	private ArrayList<Pair<String, String>> allFields;
	private HashMap<String, ObjectNode> objects;
	
	public ObjectNode() {
		objectName = null;
		id = -1;
		allFields = new ArrayList<Pair<String, String>>();
		objects = new HashMap<String, ObjectNode>();
	}
	
	public ObjectNode(ObjectNode objectNode) {
		objectName = objectNode.getObjectName();
		id = objectNode.getId();
		allFields = objectNode.getAllFields();
		objects = objectNode.getObjects();
	}
	
	public ObjectNode getObjectNode() {
		return this;
	}

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
	
	public void addObject(String key, ObjectNode object) {
		objects.put(key, object);
	}
	
	public ArrayList<Pair<String, String>> getAllFields() {
		return allFields;
	}
	
	public HashMap<String, ObjectNode> getObjects() {
		return objects;
	}
	
	public String printObject(int object_depth, boolean root) {
		StringBuilder output = new StringBuilder();
		output.append("");
		output.append("{\n");
		object_depth++;
		for (Pair<String, String> pair : allFields) {
			if (pair.getValue().equals("ObjectNode")) {
				output.append(getTabs(object_depth) + '"' + pair.getKey() + '"'
						+ " : ");
				object_depth++;
				try {
					output.append(searchObjectNode(pair.getKey())
							.printObject(object_depth, false));
				} catch(NullPointerException e) {
					System.out.println("Can't find object with this name...");
				}
				object_depth--;
				output.append(getTabs(object_depth) + "},\n");
			} else {
				output.append(getTabs(object_depth) + '"' + pair.getKey() + '"'
						+ " : " + '"' + pair.getValue() + '"' + "," + "\n");
			}
		}
		if (root == true) output.append(getTabs(object_depth-2) + "};\n");
		return output.toString();
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
