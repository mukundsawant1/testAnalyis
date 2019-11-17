package co.analysis.transaction;

import co.analysis.model.FilterParam;
import co.analysis.model.TransactionStatistics;

public interface Analyzer {

	TransactionStatistics getStatistics(FilterParam filterParam);
	
}
