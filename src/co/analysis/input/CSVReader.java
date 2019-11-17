package co.analysis.input;

import co.analysis.exceptions.ExtensionDoesNotExist;
import co.analysis.exceptions.FileDoesNotExistException;
import co.analysis.exceptions.FileNotAllowedException;
import co.analysis.input.processor.CSVProcessor;
import co.analysis.input.processor.FileProcessor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    public static List<String> readCSVs(String[] args) throws ExtensionDoesNotExist, FileNotAllowedException, FileDoesNotExistException, IOException {
        List<String> transactions = new ArrayList<>();
        FileProcessor csvProcessor = new CSVProcessor();

        for (int i = 0; i < args.length; i++) {
            transactions.addAll(csvProcessor.process(args[i]));
        }

        return transactions;
    }
}
