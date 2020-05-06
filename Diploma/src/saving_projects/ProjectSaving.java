package saving_projects;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

public class ProjectSaving {
	
	private String projectName;
	
	public ProjectSaving(){
		String path = "Saving Projects";
		File directory = new File(path);
		System.out.println("Creating directory: " + directory.getName());
		try {
			if (!directory.exists()) {
				directory.mkdir();
				System.out.println("Saving Project directory created"
						+ " successfully...");
			}
		} catch (SecurityException e) {
			System.out.println("Directory: " + directory.getName()
						+ ", didn't create...");
		}
	}
	
	public boolean createProject(String name) {
		projectName = name;
		File directory = new File("Saving Projects/" + projectName);
		System.out.println("Creating directory: Saving Projects/"
							+ directory.getName());
		boolean result = false;
		try {
			if (!directory.exists()) {
				result = directory.mkdir();
				System.out.println(directory.getName() +
						" directory created" + " successfully...");
			}
		} catch (SecurityException e) {
			System.out.println("Directory: " + directory.getName()
						+ ", didn't create...");
		}
		directory = new File("Saving Projects/" + projectName + "/output");
		try {
			if (!directory.exists()) {
				result = directory.mkdir();
				System.out.println(directory.getName() + " directory created"
						+ " successfully...");
			}
		} catch (SecurityException e) {
			System.out.println("Directory: " + directory.getName()
						+ ", didn't create...");
		}
		result = copyOutputFiles("output", "Saving Projects/" + projectName +
				"/output/");
		result = copyOutputFiles("index.html", "Saving Projects/" + projectName +
				"/index.html");
		result = copyOutputFiles("view.js", "Saving Projects/" + projectName +
				"/view.js");
		return result;
	}

	private boolean copyOutputFiles(String source, String destination) {
		File srcFile = new File(source);
		File destFile = new File(destination);
		try {
			if (srcFile.isDirectory()) {
				FileUtils.copyDirectory(srcFile, destFile);
			} else {
				FileUtils.copyFile(srcFile, destFile);
			}
			return true;
		} catch (IOException e) {
		    e.printStackTrace();
		}
		return false;
	}
}
