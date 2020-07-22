package output;

import java.io.File;
import java.io.PrintWriter;

public class VersionDirectory extends OutputTemplate {
	
	private File directory;
	
	@Override
	public boolean createDirOrFile(String contents, PrintWriter writer) {
		String path = "output";
		directory = new File(path);
		System.out.println("Creating directory: " + directory.getName());
		boolean result = false;
		try {
			result = directory.mkdir();
			System.out.println("Directory created successfully...");
		} catch (SecurityException e) {
			System.out.println("Directory: " + directory.getName()
						+ ", didn't create...");
		}
		return result;
	}
}
