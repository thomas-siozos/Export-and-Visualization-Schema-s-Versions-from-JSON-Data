package data_processing;

import javafx.util.Pair;

public class VersionComparison {

	private SchemaVersions versions = new SchemaVersions();
	private final int ROOT_OBJECT_DEPTH = 0;
	private final String ADDED_FIELD = "+";
	private final String REMOVED_FIELD = "-";
	
	public boolean compareVersions(ObjectNode currentVersion) {
		if (versions.isEmpty()) {
			versions.addSchema(currentVersion);
			return true;
		}
		int count_dif_versions = 0;
		for (ObjectNode version : versions.getSchemaVersions()) {
			if (compareFields(version, currentVersion) == false) {
				count_dif_versions++;
			}
		}
		if (count_dif_versions == versions.getSchemaVersions().size()) {
			if (versions.getSchemaVersionsSize() >= 1) {
				SchemaChanges schemaChanges =
						new SchemaChanges(versions.getLastVersion(),
											currentVersion);
				versions.addChanges(schemaChanges.getChanges());
			}
			versions.addSchema(currentVersion);
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
	
	public void printVersionsNumber() {
		System.out.println("\nTotal versions = " +
					versions.getSchemaVersions().size());
	}
	
	public void printAllVersions() {
		int count_versions = 0;
		for (ObjectNode version : versions.getSchemaVersions()) {
			System.out.println("\n- - - - - - - -\n");
			System.out.println("\nVersion added at: " +
					version.getId() + "\n");
			if (count_versions >= 1) {
				System.out.println("\nChanges:\n");
				for (Field field :
					versions.getVersionChanges(count_versions - 1)) {
					if (field.getAct().equals(ADDED_FIELD)) {
						System.out.println(field.getParent() + "/" +
								field.getKey() + " : " + field.getValue() +
								"\tAdded");
					} else if (field.getAct().equals(REMOVED_FIELD)) {
						System.out.println(field.getParent() + "/" +
								field.getKey() + " : " + field.getValue() +
								"\tRemoved");
					}
				}
				System.out.println("\n");
			}
			version.printObject(ROOT_OBJECT_DEPTH);
			count_versions++;
		}
	}
}
