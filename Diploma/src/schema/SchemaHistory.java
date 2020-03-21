package schema;

import java.io.File;
import java.util.ArrayList;
import data_processing.AtomicFieldChange;
import output.VersionFile;

public class SchemaHistory {
	
	private ArrayList<Schema> schemaVersions;
	private ArrayList<ArrayList<AtomicFieldChange>> changes;
	private final int ROOT_OBJECT_DEPTH = 0;
	private final String ADDED_FIELD = "+";
	private final String REMOVED_FIELD = "-";
	private String file;
	private VersionFile versionFile;
	
	public SchemaHistory() {
		schemaVersions = new ArrayList<Schema>();
		changes = new ArrayList<ArrayList<AtomicFieldChange>>();
		file = null;
	}
	
	public void setFile(String file) {
		this.file = file;
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
		System.out.println("Total versions = " +
				schemaVersions.size());
	}
	
	public boolean createOutputFiles() {
		int count_versions = 0;
		StringBuilder contents;
		versionFile = new VersionFile(file);
		versionFile.createDirectory();
		File directory = versionFile.getDirectory();
		for (Schema version : schemaVersions) {
			contents = new StringBuilder();
			versionFile = new VersionFile(file);
			versionFile.setDirectory(directory);
			versionFile.setId(count_versions);
			contents.append("Version added at: " +
					version.getId() + "\n");
			if (count_versions >= 1) {
				contents.append("\nChanges:\n");
				for (AtomicFieldChange atomicFieldChange :
						version.getChanges()) {
					if (atomicFieldChange.getAct().equals(ADDED_FIELD)) {
						contents.append(atomicFieldChange.getParent() + "/" +
								atomicFieldChange.getKey() + " : "
								+ atomicFieldChange.getValue() + "\tAdded\n");
					} else if (atomicFieldChange.getAct().equals(REMOVED_FIELD)) {
						contents.append(atomicFieldChange.getParent() + "/" +
								atomicFieldChange.getKey() + " : "
								+ atomicFieldChange.getValue() + "\tRemoved\n");
					}
				}
				contents.append("\n");
			}
			contents.append(version.printObject(ROOT_OBJECT_DEPTH));
			if (!versionFile.createVersionFile(contents.toString())) {
				System.out.println("File: version_"
					+ Integer.toString(count_versions) + ", wasn't created...");
				return false;
			}
			count_versions++;
		}
		return true;
	}
}
