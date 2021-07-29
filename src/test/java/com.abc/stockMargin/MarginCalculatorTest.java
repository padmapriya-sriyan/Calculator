package com.abc.stockMargin;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class MarginCalculatorTest {

    /*
    load the Stock Loss Profit Margin, with the details in separate line
    calculate Total Profit
    calculate Total Loss
    Top Loss Share
     */

    MarginCalculator marginCalculator;

    @Test
    public void testTotalProfit_when_passed_single_profitShare() {

        List<Stocks> stocks = new ArrayList<>();
        stocks.add(new Stocks(new Date(),new BigDecimal("20000.00"), "MNM"));
        marginCalculator = new MarginCalculator(stocks);
        Assert.assertEquals("Returns 20000.00", new BigDecimal("20000.00"), marginCalculator.calculateTotalProfit());
    }

    @Test
    public void testTotalLoss_when_passed_single_profit_and_lose_shares() {

        List<Stocks> stocks = new ArrayList<>();
        stocks.add(new Stocks(new Date(),new BigDecimal("20000.00"), "MNM"));
        stocks.add(new Stocks(new Date(),new BigDecimal("3200.00").negate(), "MNM"));
        marginCalculator = new MarginCalculator(stocks);
        Assert.assertEquals("Returns 20000.00", new BigDecimal("20000.00"), marginCalculator.calculateTotalProfit() );
    }

    @Test
    public void testTotalLoss_when_passed_single_loss_share() {
        List<Stocks> stocks = new ArrayList<>();
        stocks.add(new Stocks(new Date(),new BigDecimal("3200.00").negate(), "Abacus"));
        marginCalculator = new MarginCalculator(stocks);
        Assert.assertEquals("Returns 3200.00", new BigDecimal("3200.00"), marginCalculator.calculateTotalLoss() );
    }

    @Test
    public void testTotalSavings_when_passed_single_profit_and_loss_share() {
        List<Stocks> stocks = new ArrayList<>();
        stocks.add(new Stocks(new Date(),new BigDecimal("20000.00"), "Abacus"));
        stocks.add(new Stocks(new Date(),new BigDecimal("3200.00").negate(), "MNM"));
        marginCalculator = new MarginCalculator(stocks);
        Assert.assertEquals("Returns 16800.00", new BigDecimal("16800.00"), marginCalculator.calculateTotalMargin() );
    }

    @Test
    public void testTopExpenseCategory_when_passed_two_loss_shares() {
            List<Stocks> stocks = new ArrayList<>();
        stocks.add(new Stocks(new Date(),new BigDecimal("1740.00").negate(), "MNM"));
        stocks.add(new Stocks(new Date(),new BigDecimal("3200.00").negate(), "Abacus"));
        stocks.add(new Stocks(new Date(),new BigDecimal("1000.00").negate(), "MNM"));
        marginCalculator = new MarginCalculator(stocks);
        Assert.assertEquals("Returns Top Lose share", "Abacus @ 3200.00", marginCalculator.calculateTopLossShare() );
    }
}
