package data_processing;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import output.JSOutputFile;
import output.OutputTemplate;
import output.VersionDirectory;
import output.VersionFile;

public class SchemaHistory {
	
	private ArrayList<Schema> schemaVersions;
	private ArrayList<ArrayList<AtomicFieldChange>> changes;
	private final int ROOT_OBJECT_DEPTH = 0;
	private final String ADDED_FIELD = "+";
	private final String REMOVED_FIELD = "-";
	private OutputTemplate versionDirectory;
	private OutputTemplate versionFile;
	private OutputTemplate jsOutputFile;
	
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
		versionDirectory = new VersionDirectory();
		versionFile = new VersionFile();
		jsOutputFile = new JSOutputFile();
		versionDirectory.createDirOrFile(null, null);
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(jsOutputFile
					.getDirectoryName() + "versions.js", "UTF-8");
		} catch (FileNotFoundException e) {
			System.out.println("File: version" + jsOutputFile.getId() 
					+ "not found...");
		} catch (UnsupportedEncodingException e) {
			System.out.println("File: version" + jsOutputFile.getId() 
					+ " unsupported encoding...");
		}
		for (Schema version : schemaVersions) {
			contents = new StringBuilder();
			contentsForJS = new StringBuilder();
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
			if (!versionFile.createDirOrFile(contents.toString(), null)) {
				System.out.println("File: version_"
					+ Integer.toString(count_versions) + ", wasn't created...");
				return false;
			}
			if (!jsOutputFile.createDirOrFile(contentsForJS.toString(), writer)) {
				System.out.println("File: version_"
					+ Integer.toString(count_versions) + ", wasn't created...");
				return false;
			}
			count_versions++;
		}
		writer.write("var data_counter = " + jsOutputFile.getId() + ";");
		writer.close();
		return true;
	}
}
