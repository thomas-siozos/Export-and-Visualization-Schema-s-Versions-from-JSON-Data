package data_processing;

import java.util.ArrayList;

public class SchemaVersions {
	
	private ArrayList<ObjectNode> schemaVersions =
			new ArrayList<ObjectNode>();
	private ArrayList<ArrayList<Field>> changes =
			new ArrayList<ArrayList<Field>>();
	
	public void addSchema(ObjectNode objectNode) {
		schemaVersions.add(objectNode);
	}
	
	public ArrayList<ObjectNode> getSchemaVersions() {
		return schemaVersions;
	}
	
	public ObjectNode getVersion(int version) {
		return schemaVersions.get(version);
	}

	public boolean isEmpty() {
		if (schemaVersions != null && schemaVersions.isEmpty()) return true;
		return false;
	}
	
	public ObjectNode getLastVersion() {
		return schemaVersions.get(schemaVersions.size() - 1);
	}
	
	public int getSchemaVersionsSize() {
		return schemaVersions.size();
	}
	
	public void addChanges(ArrayList<Field> changes) {
		this.changes.add(changes);
	}
	
	public ArrayList<Field> getVersionChanges(int version) {
		return changes.get(version);
	}

}
