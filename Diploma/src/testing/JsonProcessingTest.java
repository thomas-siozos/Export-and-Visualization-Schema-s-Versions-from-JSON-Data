package testing;

import java.io.IOException;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import com.fasterxml.jackson.core.JsonParseException;
import data_processing.JsonProcessing;

class JsonProcessingTest {
	
	private JsonProcessing jsonProcessing = new JsonProcessing();
	private String file;
	
	@Rule
    public ExpectedException thrown = ExpectedException.none();
	
	@Test
	void testParserNullPointerException() {
		file = "abc";
		thrown.expect(IOException.class);
//		Assertions.assertThrows(IOException.class, () -> {
//			jsonProcessing.processingJsonFile(file);
//		  });
	}
	
//	@Test
//	void testJsonParseException() {
//		String file = "testing_files/JsonParseExceptionTestFile.json";
//		Assertions.assertThrows(JsonParseException.class, () -> {
//			jsonProcessing.processingJsonFile(file);
//		  });
//	}
	
}