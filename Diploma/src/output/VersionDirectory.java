package output;

import java.io.File;

public abstract class VersionDirectory {
	
	private String file;
	private File directory;
	
	public VersionDirectory(String file) {
		this.file = file;
	}
	
	public boolean createDirectory() {
		file = file.replace(".", "_");
		file = file.replace("data/", "");
		String path = file + "_versions";
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
