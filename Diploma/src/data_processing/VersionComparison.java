package data_processing;

import javafx.util.Pair;

public class VersionComparison {

	private ObjectNode oldVersion;
	private ObjectNode thisVersion;
	
	public VersionComparison(ObjectNode oldVersion, ObjectNode thisVersion) {
		this.oldVersion = oldVersion;
		this.thisVersion = thisVersion;
	}
	
	public void compareVersions() {
		compareObjectVersions(oldVersion, thisVersion);
	}
	
	private void compareObjectVersions(ObjectNode oldVersionObject, ObjectNode thisVersionObject) {
		int count_same_fields = 0;
		int count_same_key_type = 0;
		for (Pair<String, String> oldVersionField : oldVersionObject.getAllFields()) {
			for (Pair<String, String> thisVersionField : thisVersionObject.getAllFields()) {
				if (oldVersionField.getKey().equals(thisVersionField.getKey())) {
					count_same_fields++;
					//break;
					//System.out.println(oldVersionField.getKey());
					if (oldVersionField.getValue().equals(thisVersionField.getValue())) {
						count_same_key_type++;
						//break;
					}
				}
			}
			System.out.print(oldVersionField.getValue() + '\t');
		}
		System.out.println("\n------");
		for(Pair<String, String> thisVersionField : thisVersionObject.getAllFields()) {
			System.out.print(thisVersionField.getValue() + '\t');
		}
		System.out.println("\n------");
		if (count_same_fields == oldVersionObject.getAllFields().size()) {
			System.out.println("Fields: " + count_same_fields + "...Same with previous version...");
		}
		System.out.println(count_same_key_type);
		if (count_same_key_type == oldVersionObject.getAllFields().size()) {
			System.out.println("Same types with previous version");
		}
	}
}
