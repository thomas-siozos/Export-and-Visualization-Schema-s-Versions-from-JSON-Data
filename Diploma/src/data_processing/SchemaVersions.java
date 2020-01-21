package data_processing;

import java.util.ArrayList;

public class SchemaVersions {
	
	private ArrayList<ObjectNode> schemaVersions =
			new ArrayList<ObjectNode>();
	
	public void addSchema(ObjectNode objectNode) {
		schemaVersions.add(objectNode);
	}
	
	public ObjectNode getVersion(int version) {
		return schemaVersions.get(version);
	}

}
