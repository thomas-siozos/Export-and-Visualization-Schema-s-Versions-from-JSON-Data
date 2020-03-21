package schema;

import java.util.ArrayList;
import data_processing.AtomicFieldChange;
import data_processing.ObjectNode;

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
