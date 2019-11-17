package co.analysis.transaction.formatter;

import java.util.List;
import java.util.stream.Collectors;
import co.analysis.exceptions.BlankDateException;
import co.analysis.exceptions.DateInvalidFormatException;
import co.analysis.model.Transaction;
import co.analysis.utils.CommonUtils;
import co.analysis.utils.Constants;
import co.analysis.utils.functions.ThrowException;

public class TransactionDataFormatter {

	public static List<Transaction> getFormattedTransactions(List<String> transactionsDataList) {

		return transactionsDataList.stream().map(line -> line.split(Constants.FILE_SEPERATOR))
				.map(ThrowException.uncheckedThrow(TransactionDataFormatter::toTransaction)).collect(Collectors.toList());

	}

	private static Transaction toTransaction(String[] transactionData) throws BlankDateException, DateInvalidFormatException {

		Transaction transactionDto = new Transaction();
		transactionDto.setId(transactionData[0].trim().toUpperCase());
		transactionDto.setDate(CommonUtils.stringToDateConvert(transactionData[1].trim()));
		transactionDto.setAmount(Double.parseDouble(transactionData[2].trim()));
		transactionDto.setTransactionType(transactionData[4].trim().toUpperCase());
		transactionDto.setMerchant(transactionData[3].trim().toUpperCase());
		
		try {
			
			transactionDto.setRelatedTransaction(transactionData[5].trim().toUpperCase());
		
		} 
		catch (ArrayIndexOutOfBoundsException E) {
		
		}
		
		return transactionDto;
		
	}

}
