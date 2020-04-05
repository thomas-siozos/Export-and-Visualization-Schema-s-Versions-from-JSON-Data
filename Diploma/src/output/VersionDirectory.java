package output;

import java.io.File;

public abstract class VersionDirectory {
	
	private File directory;
	
	public boolean createDirectory() {
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
	
	public void setDirectory(File directory) {
		this.directory = directory;
	}
	
	public File getDirectory() {
		return directory;
	}
	
	public String getDirectoryName() {
		return directory.getName() + "/";
	}
	
	public abstract boolean createVersionFile(String contents);
}
