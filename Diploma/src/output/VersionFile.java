package output;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class VersionFile extends VersionDirectory {
	
	private int version_number;

	public VersionFile(String file) {
		super(file);
	}
	
	public void setId(int version_number) {
		this.version_number = version_number;
	}

	@Override
	public boolean createVersionFile(String contents) {
		PrintWriter writer;
		try {
			writer = new PrintWriter(this.getDirectoryName() + "version_"
						+ version_number + ".csv", "UTF-8");
			writer.write(contents);
			writer.close();
			return true;
		} catch (FileNotFoundException e) {
			System.out.println("File: version" + version_number
					+ "not found...");
		} catch (UnsupportedEncodingException e) {
			System.out.println("File: version" + version_number
					+ " unsupported encoding...");
		}
		return false;
	}

}
