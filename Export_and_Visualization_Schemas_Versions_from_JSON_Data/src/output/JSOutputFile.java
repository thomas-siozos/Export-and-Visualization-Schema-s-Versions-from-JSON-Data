package output;

import java.io.PrintWriter;

public class JSOutputFile extends OutputTemplate {
	
	@Override
	public boolean createDirOrFile(String contents, PrintWriter writer) {
		writer.write("var data_" + this.getId() + " =\n");
		writer.write(contents);
		return true;
	}
}
