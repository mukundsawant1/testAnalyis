package co.analysis.processor.file;


import static org.junit.Assert.assertTrue;
import java.io.IOException;

import co.analysis.input.processor.CSVProcessor;
import org.junit.Test;
import co.analysis.exceptions.ExtensionDoesNotExist;
import co.analysis.exceptions.FileDoesNotExistException;
import co.analysis.exceptions.FileNotAllowedException;

public class FileProcessorTest {

	@Test
	public void isAbleToReadDataFromFile() throws ExtensionDoesNotExist, FileNotAllowedException, IOException, FileDoesNotExistException {
		CSVProcessor fp= new CSVProcessor("./source/csv1.csv");
		assertTrue(fp.readData().size() > 0 );
	}
	
}
