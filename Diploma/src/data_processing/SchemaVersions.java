package data_processing;

import java.util.ArrayList;

public class SchemaVersions {
	
	private ArrayList<ObjectNode> schemaVersions =
			new ArrayList<ObjectNode>();
	
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

}
