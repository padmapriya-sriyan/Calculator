package com.zuhlke.stockMargin;

import java.math.BigDecimal;
import java.util.Date;

public class Stocks {

    private Date soldDate;

    private BigDecimal profitLossMargin;

    private String shareName;

    public Stocks(Date soldDate, BigDecimal profitLossMargin, String shareName) {
        this.soldDate = soldDate;
        this.profitLossMargin = profitLossMargin;
        this.shareName = shareName;
    }

    public Date getSoldDate() {
        return soldDate;
    }

    public void setSoldDate(Date soldDate) {
        this.soldDate = soldDate;
    }

    public BigDecimal getProfitLossMargin() {
        return profitLossMargin;
    }

    public void setProfitLossMargin(BigDecimal profitLossMargin) {
        this.profitLossMargin = profitLossMargin;
    }

    public String getShareName() {
        return shareName;
    }

    public void setShareName(String shareName) {
        this.shareName = shareName;
    }
}
