package output;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class VersionFile extends OutputTemplate {

	public boolean createDirOrFile(String contents, PrintWriter writer) {
		PrintWriter localWriter;
		try {
			localWriter = new PrintWriter(this.getDirectoryName() + "version_"
						+ this.getId() + ".csv", "UTF-8");
			localWriter.write(contents);
			localWriter.close();
			return true;
		} catch (FileNotFoundException e) {
			System.out.println("File: version" + this.getId()
					+ "not found...");
		} catch (UnsupportedEncodingException e) {
			System.out.println("File: version" + this.getId()
					+ " unsupported encoding...");
		}
		return false;
	}
}
