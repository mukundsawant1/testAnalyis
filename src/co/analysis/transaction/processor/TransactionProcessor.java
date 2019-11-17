package co.analysis.transaction.processor;

import java.util.List;

import co.analysis.transaction.TransactionAnalyzer;
import co.analysis.transaction.formatter.TransactionDataFormatter;
import co.analysis.model.FilterParam;
import co.analysis.model.Transaction;
import co.analysis.transaction.Analyzer;
import co.analysis.model.TransactionStatistics;

public class TransactionProcessor {

	public TransactionStatistics getStatistics(List<String> unformattedTransactions, FilterParam filterParam) {

		List<Transaction> formattedTransactions  = TransactionDataFormatter.getFormattedTransactions(unformattedTransactions);

		Analyzer analyzer = new TransactionAnalyzer(formattedTransactions);
		return analyzer.getStatistics(filterParam);
	}

}
