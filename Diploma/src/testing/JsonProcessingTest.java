package testing;

import java.io.File;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import data_processing.JsonProcessing;

class JsonProcessingTest {
	
	private JsonProcessing jsonProcessing;
	private String file = "abc";
	@Mock
	private JsonProcessing json;
	
//	@Test
//	void testFileExistance() {
//		Assertions.assertThrows(NullPointerException.class, () -> {
//			jsonProcessing.processingJsonFile(file);
//		  });
//	}
	
	@Test
	void testIOException() {
		String file = "test_countries_2_entries.json";
		File tempFile = new File("test_countries_2_entries.json");
		boolean exists = tempFile.exists();
		System.out.println(exists);
		Assertions.assertThrows(NullPointerException.class, () -> {
			json.processingJsonFile(file);
		  });
	}

}