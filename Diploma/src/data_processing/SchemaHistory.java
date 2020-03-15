package data_processing;

import java.util.ArrayList;

public class SchemaHistory {
	
	private ArrayList<Schema> schemaVersions;
	private ArrayList<ArrayList<AtomicFieldChange>> changes;
	private final int ROOT_OBJECT_DEPTH = 0;
	private final String ADDED_FIELD = "+";
	private final String REMOVED_FIELD = "-";
	
	public SchemaHistory() {
		schemaVersions = new ArrayList<Schema>();
		changes = new ArrayList<ArrayList<AtomicFieldChange>>();
	}

	public void addSchema(Schema schema) {
		schemaVersions.add(schema);
	}
	
	public ArrayList<Schema> getSchemaVersions() {
		return schemaVersions;
	}
	
	public Schema getVersion(int version) {
		return schemaVersions.get(version);
	}

	public boolean isEmpty() {
		if (schemaVersions != null && schemaVersions.isEmpty()) return true;
		return false;
	}
	
	public Schema getLastVersion() {
		return schemaVersions.get(schemaVersions.size() - 1);
	}
	
	public int getSchemaVersionsSize() {
		return schemaVersions.size();
	}
	
	public void addChanges(ArrayList<AtomicFieldChange> changes) {
		this.changes.add(changes);
	}
	
	public ArrayList<AtomicFieldChange> getVersionChanges(int version) {
		return changes.get(version);
	}
	
	public void printVersionsNumber() {
		System.out.println("\nTotal versions = " +
				schemaVersions.size());
	}
	
	public void printAllVersions() {
		int count_versions = 0;
		for (Schema version : schemaVersions) {
			System.out.println("\n- - - - - - - -\n");
			System.out.println("\nVersion added at: " +
					version.getId() + "\n");
			if (count_versions >= 1) {
				System.out.println("\nChanges:\n");
				for (AtomicFieldChange atomicFieldChange :
						version.getChanges()) {
					if (atomicFieldChange.getAct().equals(ADDED_FIELD)) {
						System.out.println(atomicFieldChange.getParent() + "/" +
								atomicFieldChange.getKey() + " : " + atomicFieldChange.getValue() +
								"\tAdded");
					} else if (atomicFieldChange.getAct().equals(REMOVED_FIELD)) {
						System.out.println(atomicFieldChange.getParent() + "/" +
								atomicFieldChange.getKey() + " : " + atomicFieldChange.getValue() +
								"\tRemoved");
					}
				}
				System.out.println("\n");
			}
			System.out.println(version.printObject(ROOT_OBJECT_DEPTH));
			count_versions++;
		}
	}
}
