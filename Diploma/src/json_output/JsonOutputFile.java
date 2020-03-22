package json_output;

import schema.SchemaHistory;
import java.nio.file.Paths;
import com.fasterxml.jackson.databind.ObjectMapper;
import schema.Schema;

public class JsonOutputFile {
	
	private SchemaHistory schemaHistory;
	private String path;
	private JsonObject jsonObject;
	
	public JsonOutputFile(SchemaHistory schemaHistory) {
		this.schemaHistory = schemaHistory;
		path = null;
		jsonObject = new JsonObject();
	}
	
	public void setPath(String file) {
		file = file.replace(".", "_");
		file = file.replace("data/", "");
		path = file + "_versions";
	}
	
	public boolean createOutputFiles() {
		int count = 0;
		for (Schema schema : schemaHistory.getSchemaVersions()) {
			try {
				ObjectMapper mapper = new ObjectMapper();
				mapper.writeValue(Paths.get(path + "/version_"
						+ count + ".json").toFile(),
						jsonObject.createJsonObject(schema));
				count++;
			} catch (Exception ex) {
				System.out.println("An error occurred while creating"
						+ " json output files...");
				return false;
			}
		}
		return true;
	}
}
