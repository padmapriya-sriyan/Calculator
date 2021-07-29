package com.zuhlke.stockMargin;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class MarginCalculator {

    private List<Stocks> stocks;

    public MarginCalculator(List<Stocks> stocks) {
        this.stocks = stocks;
    }

    public List<Stocks> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stocks> stocks) {
        this.stocks = stocks;
    }

    public BigDecimal calculateTotalProfit() {
        return stocks.stream().filter(stocks -> stocks.getProfitLossMargin()
                .compareTo(new BigDecimal("0")) > 0).map(stocks -> stocks.getProfitLossMargin()).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calculateTotalLoss() {
        return stocks.stream().filter(stocks -> stocks.getProfitLossMargin()
                .compareTo(new BigDecimal("0")) < 0).map(stocks -> stocks.getProfitLossMargin()).reduce(BigDecimal.ZERO, BigDecimal::add).negate();
    }

    public BigDecimal calculateTotalMargin() {
        return stocks.stream().map(stocks -> stocks.getProfitLossMargin()).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public String calculateTopLossShare() {
        Map<String, BigDecimal> expenseMap = new HashMap();
        expenseMap = stocks.stream().collect(
                Collectors.groupingBy(Stocks::getShareName, Collectors.reducing(BigDecimal.ZERO, Stocks::getProfitLossMargin, BigDecimal::add)));
        Map.Entry<String, BigDecimal> transaction = (expenseMap.entrySet().stream()
                .sorted(Map.Entry.<String, BigDecimal>comparingByValue()).findFirst().get());
        return transaction.getKey().concat(" @ ").concat(String.valueOf(transaction.getValue().negate()));
    }
}
