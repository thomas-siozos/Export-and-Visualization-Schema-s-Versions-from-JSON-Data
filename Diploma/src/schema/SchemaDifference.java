package schema;

import java.util.ArrayList;
import data_processing.AtomicFieldChange;
import data_processing.ObjectNode;
import javafx.util.Pair;

public class SchemaDifference {
	
	private ObjectNode prevVersion;
	private ObjectNode currentVersion;
	private final String ADDED_FIELD = "+";
	private final String REMOVED_FIELD = "-";
	
	public SchemaDifference(ObjectNode prevVersion, ObjectNode currentVersion) {
		this.prevVersion = prevVersion;
		this.currentVersion = currentVersion;
	}
	
	public ArrayList<AtomicFieldChange> getChanges() {
		return getListWithChanges(prevVersion, currentVersion);
	}
	
	private ArrayList<AtomicFieldChange> getListWithChanges
			(ObjectNode prevVersion, ObjectNode currentVersion) {
		ArrayList<AtomicFieldChange> changes =
					new ArrayList<AtomicFieldChange>();
		for (Pair<String, String> currentVersionField :
				currentVersion.getAllFields()) {
			if (prevVersion.getAllFields().contains(currentVersionField)) {
				if (currentVersionField.getValue().equals("ObjectNode")) {
					try {
						ArrayList<AtomicFieldChange> nestedOb = new
								ArrayList<AtomicFieldChange>();
						nestedOb = getListWithChanges
								(prevVersion.searchObjectNode
								(currentVersionField.getKey()),
								currentVersion.searchObjectNode
								(currentVersionField.getKey()));
						changes.addAll(nestedOb);
					} catch(NullPointerException e) {
						System.out.println("Class: SchemaChanges, " +
									"Can't find object " +
									"with this name...");
					}
				} 
			} else {
				AtomicFieldChange atomicFieldChange = new AtomicFieldChange();
				atomicFieldChange.setKey(currentVersionField.getKey());
				atomicFieldChange.setValue(currentVersionField.getValue());
				atomicFieldChange.setParent(currentVersion.getObjectName());
				atomicFieldChange.setAct(ADDED_FIELD);
				changes.add(atomicFieldChange);
				}
		}
		
		for (Pair<String, String> prevVersionField :
				prevVersion.getAllFields()) {
			if (!currentVersion.getAllFields().contains(prevVersionField)) {
				AtomicFieldChange atomicFieldChange = new AtomicFieldChange();
				atomicFieldChange.setKey(prevVersionField.getKey());
				atomicFieldChange.setValue(prevVersionField.getValue());
				atomicFieldChange.setParent(currentVersion.getObjectName());
				atomicFieldChange.setAct(REMOVED_FIELD);
				changes.add(atomicFieldChange);
			}
		}
		return changes;
	}
}
