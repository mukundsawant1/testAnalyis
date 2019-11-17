package co.analysis;

import java.io.IOException;
import java.util.List;
import co.analysis.exceptions.ArgumentsNotPassedException;
import co.analysis.exceptions.BlankDateException;
import co.analysis.exceptions.DateInvalidFormatException;
import co.analysis.exceptions.ExtensionDoesNotExist;
import co.analysis.exceptions.FileDoesNotExistException;
import co.analysis.exceptions.FileNotAllowedException;
import co.analysis.input.CSVReader;
import co.analysis.input.InputReader;
import co.analysis.exceptions.ExceptionMessages;
import co.analysis.model.FilterParam;
import co.analysis.output.OutputFormatter;
import co.analysis.transaction.processor.TransactionProcessor;
import co.analysis.model.TransactionStatistics;

public class Main {

	public static void main(String[] args) throws IOException, FileDoesNotExistException, ExtensionDoesNotExist,
			FileNotAllowedException, ArgumentsNotPassedException, BlankDateException, DateInvalidFormatException {

		if (args.length < 1) {
			throw new ArgumentsNotPassedException(ExceptionMessages.CSV_FILES_MISSING);
		}

		FilterParam filterParam = InputReader.readFilterParamsFromUser();
		List<String> unformattedTransactions = CSVReader.readCSVs(args);

		TransactionProcessor transactionProcessor = new TransactionProcessor();
		TransactionStatistics transactionStatistics = transactionProcessor.getStatistics(unformattedTransactions, filterParam);
		OutputFormatter.printStats(transactionStatistics);
	}
}
