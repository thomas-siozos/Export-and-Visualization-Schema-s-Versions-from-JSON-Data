package schema;

import java.io.File;
import java.util.ArrayList;
import data_processing.AtomicFieldChange;
import output.JSOutputFile;
import output.VersionFile;

public class SchemaHistory {
	
	private ArrayList<Schema> schemaVersions;
	private ArrayList<ArrayList<AtomicFieldChange>> changes;
	private final int ROOT_OBJECT_DEPTH = 0;
	private final String ADDED_FIELD = "+";
	private final String REMOVED_FIELD = "-";
	private VersionFile versionFile;
	private JSOutputFile jsOutputFile;
	
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
		System.out.println("Total versions = " +
				schemaVersions.size());
	}
	
	public boolean createOutputFiles() {
		int count_versions = 0;
		StringBuilder contents;
		StringBuilder contentsForJS;
		versionFile = new VersionFile();
		jsOutputFile = new JSOutputFile();
		versionFile.createDirectory();
		File directory = versionFile.getDirectory();
		jsOutputFile.setDirectory(directory);
		jsOutputFile.openWriter();
		for (Schema version : schemaVersions) {
			contents = new StringBuilder();
			contentsForJS = new StringBuilder();
			versionFile.setDirectory(directory);
			versionFile.setId(count_versions);
			jsOutputFile.setId(count_versions);
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
			contents.append(version.printObject(ROOT_OBJECT_DEPTH, true));
			contentsForJS.append(version.printObject(ROOT_OBJECT_DEPTH, true));
			if (!versionFile.createVersionFile(contents.toString())) {
				System.out.println("File: version_"
					+ Integer.toString(count_versions) + ", wasn't created...");
				return false;
			}
			if (!jsOutputFile.createVersionFile(contentsForJS.toString())) {
				System.out.println("File: version_"
					+ Integer.toString(count_versions) + ", wasn't created...");
				return false;
			}
			count_versions++;
		}
		jsOutputFile.closeWriter();
		return true;
	}
}
