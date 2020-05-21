package output;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class JSOutputFile extends VersionDirectory {
	
	private int version_number;
	private PrintWriter writer;
	
	public void setId(int version_number) {
		this.version_number = version_number;
	}
	
	public boolean openWriter() {
		try {
			writer = new PrintWriter(this.getDirectoryName() + "versions.js", "UTF-8");
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
	
	public void closeWriter() {
		writer.write("var data_counter = " + version_number + ";");
		writer.close();
	}

	@Override
	public boolean createVersionFile(String contents) {
		writer.write("var data_" + version_number + " =\n");
		writer.write(contents);
		return true;
	}

}
