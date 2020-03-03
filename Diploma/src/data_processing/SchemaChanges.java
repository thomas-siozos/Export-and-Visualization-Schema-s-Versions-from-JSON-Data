package data_processing;

import java.util.ArrayList;

import javafx.util.Pair;

public class SchemaChanges {
	
	private ObjectNode prevVersion;
	private ObjectNode currentVersion;
	private final String ADDED_FIELD = "+";
	private final String REMOVED_FIELD = "-";
	
	public SchemaChanges(ObjectNode prevVersion, ObjectNode currentVersion) {
		this.prevVersion = prevVersion;
		this.currentVersion = currentVersion;
	}
	
	public ArrayList<Field> getChanges() {
		return getListWithChanges(prevVersion, currentVersion);
	}

	public ArrayList<String> getKeys(ArrayList<Pair<String, String>> fields) {
		ArrayList<String> onlyKeys = new ArrayList<String>();
		for (Pair<String, String> field : fields) {
			onlyKeys.add(field.getKey());
		}
		return onlyKeys;
	}
	
	private ArrayList<Field> getListWithChanges(ObjectNode prevVersion,
			ObjectNode currentVersion) {
		ArrayList<Field> changes = new ArrayList<Field>();
		for (Pair<String, String> currentVersionField :
				currentVersion.getAllFields()) {
			if (prevVersion.getAllFields().contains(currentVersionField)) {
				if (currentVersionField.getValue().equals("ObjectNode")) {
					try {
						ArrayList<Field> nestedOb = new ArrayList<Field>();
						nestedOb = getListWithChanges
								(prevVersion.searchObjectNode
								(currentVersionField.getKey()),
								currentVersion.searchObjectNode
								(currentVersionField.getKey()));
						changes.addAll(nestedOb);
					} catch(NullPointerException e) {
						System.out.println("Can't find object " +
									"with this name...");
					}
				} 
			} else {
					Field field = new Field();
					field.setKey(currentVersionField.getKey());
					field.setValue(currentVersionField.getValue());
					field.setParent(currentVersion.getObjectName());
					field.setAct(ADDED_FIELD);
					changes.add(field);
				}
		}
		
		for (Pair<String, String> prevVersionField :
				prevVersion.getAllFields()) {
			if (!currentVersion.getAllFields().contains(prevVersionField)) {
				Field field = new Field();
				field.setKey(prevVersionField.getKey());
				field.setValue(prevVersionField.getValue());
				field.setParent(currentVersion.getObjectName());
				field.setAct(REMOVED_FIELD);
				changes.add(field);
			}
		}
		return changes;
	}
}
