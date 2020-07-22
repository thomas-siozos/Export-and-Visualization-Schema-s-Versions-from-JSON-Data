package output;

import java.io.PrintWriter;

public abstract class OutputTemplate {
	
	@SuppressWarnings("unused")
	private final String path = "output";
	private int version_number;
	
	public void setId(int version_number) {
		this.version_number = version_number;
	}
	
	public int getId() {
		return this.version_number;
	}
	
	public String getDirectoryName() {
		return  "output/";
	}
	
	public abstract boolean createDirOrFile(String contents, PrintWriter writer);
}
