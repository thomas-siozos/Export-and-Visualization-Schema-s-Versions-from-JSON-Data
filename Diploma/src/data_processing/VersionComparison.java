package data_processing;

import javafx.util.Pair;

public class VersionComparison {

	private SchemaVersions versions = new SchemaVersions();
	
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
			versions.addSchema(currentVersion);
			return true;
		}
		return false;
	}
	
	private boolean compareFields(ObjectNode version, ObjectNode currentVersion) {
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
						System.out.println("Can't find object with this name...");
					}
				}
			} else {
				//System.out.println(currentVersionField.getKey() + " is missing from " + version.getObjectName());
				return false;
			}
		}
		if (count_same_fields == version.getAllFields().size()) return true;
		return false;
	}
	
	public void printVersionsNumber() {
		System.out.println("Total versions = " + versions.getSchemaVersions().size());
	}
	
	public void printAllVersions() {
		for (ObjectNode version : versions.getSchemaVersions()) {
			System.out.println("---------");
			version.printObject(0);
		}
	}
}
