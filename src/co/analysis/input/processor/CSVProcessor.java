package co.analysis.input.processor;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import co.analysis.exceptions.ExtensionDoesNotExist;
import co.analysis.exceptions.FileNotAllowedException;
import co.analysis.utils.CommonUtils;

public class CSVProcessor implements FileProcessor {

	@Override
	public List<String> process(String fileName) throws ExtensionDoesNotExist, FileNotAllowedException {
		List<String> transactions = new ArrayList<String>();
		final File transactionFile = new File(fileName);
		Optional<String> fileExtension = CommonUtils.getExtensionFromFileName(fileName);

		if (transactionFile.isFile() && CommonUtils.isAllowedExtension(fileExtension) && CommonUtils.isFileNotEmpty(transactionFile)) {
			try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
				transactions = br.lines().skip(1).filter(line -> !line.isEmpty()).collect(Collectors.toList());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return transactions;
	}
}
