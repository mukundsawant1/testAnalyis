package co.analysis.transaction;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.analysis.model.TransactionStatistics;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import co.analysis.model.Transaction;
import co.analysis.utils.CommonUtils;

public class TransAnalyzerTest {

	List<Transaction> transactions = new ArrayList<Transaction>();

	@Before
	public void initiateTransactions() throws Exception {
		
		Transaction t1 = new Transaction();

		t1.setId("ABCDXF");
		t1.setDate(CommonUtils.stringToDateConvert("20/08/2018 00:01:00"));
		t1.setAmount(Double.parseDouble("12.00"));
		t1.setTransactionType("PAYMENT");
		t1.setMerchant("MUKASHI");
		t1.setRelatedTransaction("");
		transactions.add(t1);

		Transaction t2 = new Transaction();

		t2.setId("AABDEF");
		t2.setDate(CommonUtils.stringToDateConvert("20/08/2018 10:23:01"));
		t2.setAmount(Double.parseDouble("24.00"));
		t2.setTransactionType("PAYMENT");
		t2.setMerchant("MUKASHI");
		t2.setRelatedTransaction("");
		transactions.add(t2);

		Transaction t3 = new Transaction();

		t3.setId("ABCDEF");
		t3.setDate(CommonUtils.stringToDateConvert("20/08/2018 11:22:01"));
		t3.setAmount(Double.parseDouble("36.00"));
		t3.setTransactionType("PAYMENT");
		t3.setMerchant("MUKASHI");
		t3.setRelatedTransaction("");
		
		Transaction t4 = new Transaction();

		t4.setId("ADEDEF");
		t4.setDate(CommonUtils.stringToDateConvert("20/08/2018 11:23:41"));
		t4.setAmount(Double.parseDouble("36.00"));
		t4.setTransactionType("REVERSAL");
		t4.setMerchant("MUKASHI");
		t4.setRelatedTransaction("ABCDEF");
		
		transactions.add(t4);
		
	}

	@Test
	public void testNumberOfTransaction() throws Exception {
		
		TransactionStatistics transactionStats = new TransactionStatistics();
		Date fromDate = CommonUtils.stringToDateConvert("20/08/2018 00:01:00");
		Date toDate = CommonUtils.stringToDateConvert("20/08/2018 23:59:00");
		String merchant = "Mukashi";
		Analyzer analyzerA = new TransactionAnalyzer(transactions);
		transactionStats = analyzerA.getStatistics(fromDate, toDate, merchant);
		assertTrue(transactionStats.getNumOfTransactions() == 2.0);

	}
	
	@Test
	public void ifFilterCriteriaDoesNotMatch() throws Exception {
		
		TransactionStatistics transactionStats = new TransactionStatistics();
		Date fromDate = CommonUtils.stringToDateConvert("11/08/2018 00:01:00");
		Date toDate = CommonUtils.stringToDateConvert("11/08/2018 23:59:00");
		String merchant = "Mukashi";
		Analyzer analyzerA = new TransactionAnalyzer(transactions);
		transactionStats = analyzerA.getStatistics(fromDate, toDate, merchant);
		assertTrue(transactionStats.getNumOfTransactions() == 0.0);

	}

	@Test
	public void testAverageValue() throws Exception {
		
		TransactionStatistics transactionStats = new TransactionStatistics();
		Date fromDate = CommonUtils.stringToDateConvert("20/08/2018 00:01:00");
		Date toDate = CommonUtils.stringToDateConvert("20/08/2018 23:59:00");
		String merchant = "Mukashi";
		Analyzer analyzerA = new TransactionAnalyzer(transactions);
		transactionStats = analyzerA.getStatistics(fromDate, toDate, merchant);
		assertTrue(transactionStats.getAvaerageVal() == 18.0);

	}
	
	@After
	public void onceTestScenariosAreCompleted() {
		
		transactions.clear();
		
	}

}
