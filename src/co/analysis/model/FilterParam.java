package co.analysis.model;

import java.util.Date;

public class FilterParam {

    private Date fromDate;
    private Date toDate;
    private String merchantName;

    public FilterParam(Date fromDate, Date toDate, String merchantName) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.merchantName = merchantName;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public String getMerchantName() {
        return merchantName;
    }
}
