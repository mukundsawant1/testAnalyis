package co.analysis.utils;

import static org.junit.Assert.assertTrue;
import java.util.Optional;
import org.junit.Test;
import co.analysis.utils.CommonUtils;

public class CommonUtilsTests {

	@Test
	public void ifFileExtensionIsAllowed() throws Exception {

		Optional<String> fileExtension = CommonUtils.getExtensionFromFileName("xyz.csv");
		assertTrue(CommonUtils.isAllowedExtension(fileExtension));

	}

	@Test
	public void checkIfDoubleValueIsConvertedUptoTwoDecimalPlace() {
		assertTrue(CommonUtils.roundValue(12.1234) == 12.12);
	}

}
