package data_processing;

import java.util.HashMap;

public class ObjectNode {
	
	private String parent;
	private boolean objectInList;
	private HashMap<String, String> primitives = new HashMap<String, String>();
	private HashMap<String, String> lists = new HashMap<String, String>();
	private HashMap<String, String> kids = new HashMap<String, String>();
	
	public void setParent(String parent) {
		this.parent = parent;
	}
	
	public String getParent() {
		return parent;
	}
	
	public void setObjectInList(boolean objectInList) {
		this.objectInList = objectInList;
	}
	
	public boolean getObjectInList() {
		return objectInList;
	}
	
	public void addPrimitive(String key, String value) {
		primitives.put(key, value);
	}
	
	public HashMap<String, String> getPrimitives() {
		return primitives;
	}
	
	public void addList(String key, String value) {
		lists.put(key, value);
	}
	
	public HashMap<String, String> getLists() {
		return lists;
	}
	
	public void addKid(String key, String value) {
		kids.put(key, value);
	}
	
	public HashMap<String, String> getKids() {
		return kids;
	}
	
}
