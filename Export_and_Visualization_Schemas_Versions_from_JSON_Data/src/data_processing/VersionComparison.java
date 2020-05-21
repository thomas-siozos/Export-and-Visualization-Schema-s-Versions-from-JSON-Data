package data_processing;

import javafx.util.Pair;
import schema.Schema;
import schema.SchemaDifference;
import schema.SchemaHistory;

public class VersionComparison {
	
	private SchemaDifference schemaDifference;
	
	public VersionComparison() {
		schemaDifference = null;
	}

	public boolean compareVersions(SchemaHistory schemaHistory,
					ObjectNode currentVersion) {
		int count_dif_versions = 0;
		if (schemaHistory.isEmpty()) {
			Schema schema = new Schema(currentVersion);
			schemaHistory.addSchema(schema);
			return true;
		}
		for (Schema version : schemaHistory.getSchemaVersions()) {
			if (compareFields(version.getObjectNode(), currentVersion) == false) {
				count_dif_versions++;
			}
		}
		if (count_dif_versions == schemaHistory.getSchemaVersions().size()) {
			if (schemaHistory.getSchemaVersionsSize() >= 1) {
				schemaDifference =
						new SchemaDifference(schemaHistory.getLastVersion()
								.getObjectNode(),currentVersion);
				Schema schema = new Schema(currentVersion);
				schema.addChanges(schemaDifference.getChanges());
				schemaHistory.addSchema(schema);
			}
			return true;
		}
		return false;
	}
	
	private boolean compareFields(ObjectNode version,
			ObjectNode currentVersion) {
		int count_same_fields = 0;
		for (Pair<String, String> currentVersionField :
				currentVersion.getAllFields()) {
			if (version.getAllFields().contains(currentVersionField)) {
				count_same_fields++;
				if (currentVersionField.getValue().equals("ObjectNode")) {
					try {
						if (compareFields(version.searchObjectNode
								(currentVersionField.getKey()),
								currentVersion.searchObjectNode
								(currentVersionField.getKey())) == false ) {
							return false;
						}
					} catch(NullPointerException e) {
						System.out.println("Class: VersionComparison, " +
									"can't find object " +
									"with this name...");
					}
				}
			} else {
				return false;
			}
		}
		if (count_same_fields == version.getAllFields().size()) return true;
		return false;
	}
}
