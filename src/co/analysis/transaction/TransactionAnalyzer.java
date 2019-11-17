package co.analysis.transaction;

import java.util.Date;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import co.analysis.model.FilterParam;
import co.analysis.model.Transaction;
import co.analysis.utils.CommonUtils;
import co.analysis.utils.TransactionTypes;
import co.analysis.model.TransactionStatistics;

public class TransactionAnalyzer implements Analyzer {

	private List<Transaction> transactionList = null;
	private TransactionStatistics transactionStats = null;

	public TransactionAnalyzer(List<Transaction> transactionListA) {

		this.transactionList = transactionListA;
		transactionStats = new TransactionStatistics();

	}

	private Set<String> getIdsFromTransactionType(String transactionType) {

		return transactionList.parallelStream()
				.filter(transaction -> isTransactionTypeMacthed(transaction.getTransactionType(), transactionType))
				.map(Transaction::getRelatedTransaction).collect(Collectors.toSet());

	}

	public TransactionStatistics getStatistics(FilterParam filterParam) {

		Set<String> reversalIds = getIdsFromTransactionType(TransactionTypes.REVERSAL.toString());

		DoubleSummaryStatistics stats = transactionList.parallelStream()
				.filter(transaction -> isTransactionTypeMacthed(transaction.getTransactionType(),
						TransactionTypes.PAYMENT.toString()))
				.filter(transaction -> isDateEqualOrAfter(transaction.getDate(), filterParam.getFromDate()))
				.filter(transaction -> isDateEqualOrBefore(transaction.getDate(), filterParam.getToDate()))
				.filter(transaction -> isMerchantNameMatched(transaction.getMerchant(), filterParam.getMerchantName()))
				.filter(transaction -> !isTransactionIsReversal(reversalIds,transaction.getId())).mapToDouble(Transaction::getAmount)
				.summaryStatistics();

		return toTransactionStatistics(stats);

	}

	private boolean isTransactionIsReversal(Set<String> reversalIds , String transactionId) {
		
		return reversalIds.contains(transactionId);
	}
	
	private boolean areDatesEqual(Date date1, Date date2) {

		return (CommonUtils.areDatesEqual(date1, date2) == 0) ? (true) : (false);

	}

	private boolean isTransactionTypeMacthed(String type1, String type2) {

		return (type1.equals(type2)) ? (true) : (false);

	}

	private boolean isDateEqualOrBefore(Date date1, Date date2) {

		return (areDatesEqual(date1, date2) || date1.before(date2)) ? (true) : (false);

	}

	private boolean isDateEqualOrAfter(Date date1, Date date2) {

		return (areDatesEqual(date1, date2) || date1.after(date2)) ? (true) : (false);
	}

	private boolean isMerchantNameMatched(String name1, String name2) {

		return name1.equalsIgnoreCase(name2);
	}

	private TransactionStatistics toTransactionStatistics(DoubleSummaryStatistics stats) {

		transactionStats.setNumOfTransactions(stats.getCount());
		transactionStats.setAvaerageVal(CommonUtils.roundValue(stats.getAverage()));

		return transactionStats;

	}
}
