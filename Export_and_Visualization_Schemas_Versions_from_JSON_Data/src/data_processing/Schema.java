package data_processing;

import java.util.ArrayList;

public class Schema extends ObjectNode {
	
	private ArrayList<AtomicFieldChange> changes;
	
	public Schema(ObjectNode objectNode) {
		super(objectNode);
		changes = new ArrayList<AtomicFieldChange>();
	}
	
	public void addChanges(ArrayList<AtomicFieldChange> changes) {
		this.changes = changes;
	}
	
	public ArrayList<AtomicFieldChange> getChanges() {
		return changes;
	}
}
