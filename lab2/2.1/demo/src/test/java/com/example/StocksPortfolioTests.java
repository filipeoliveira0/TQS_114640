package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;

import static org.assertj.core.api.Assertions.assertThat;


import java.util.List;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT) //Usa-se para evitar warnings e exceptions de misuses
public class StocksPortfolioTests {
    @Mock
    IStockmarketService market;

    @InjectMocks
    StocksPortfolio portfolio;

    @Test
    void testTotalValue() {

        Stock googleStock = new Stock("google", 2);
        Stock appleStock = new Stock("apple", 4);
        Stock teslaStock = new Stock("tesla", 1);
        Stock microsoftStock = new Stock("microsoft", 3);
        Stock amazonStock = new Stock("amazon", 5);

        portfolio.addStock(googleStock);
        portfolio.addStock(appleStock);
        portfolio.addStock(teslaStock);
        portfolio.addStock(microsoftStock);
        portfolio.addStock(amazonStock);


        when(market.lookUpPrice("google")).thenReturn(50.0);
        when(market.lookUpPrice("apple")).thenReturn(100.0);
        when(market.lookUpPrice("tesla")).thenReturn(200.0);
        when(market.lookUpPrice("microsoft")).thenReturn(300.0);
        when(market.lookUpPrice("amazon")).thenReturn(400.0);

        double result = portfolio.totalValue();

        double exepctedvalue = 2*50.0 + 4*100.0 + 1*200.0 + 3*300.0 + 5*400.0;

        assertEquals( exepctedvalue, result);
       
        verify(market).lookUpPrice("google");
        verify(market).lookUpPrice("apple");
        verify(market).lookUpPrice("tesla");
        verify(market).lookUpPrice("microsoft");
        verify(market).lookUpPrice("amazon");
    }

    @Test
    void testTotalValueHamcrest(){

        Stock googleStock = new Stock("google", 2);
        Stock appleStock = new Stock("apple", 4);
        Stock teslaStock = new Stock("tesla", 1);
        Stock microsoftStock = new Stock("microsoft", 3);
        Stock amazonStock = new Stock("amazon", 5);

        portfolio.addStock(googleStock);
        portfolio.addStock(appleStock);
        portfolio.addStock(teslaStock);
        portfolio.addStock(microsoftStock);
        portfolio.addStock(amazonStock);

        when(market.lookUpPrice("google")).thenReturn(50.0);
        when(market.lookUpPrice("apple")).thenReturn(100.0);
        when(market.lookUpPrice("tesla")).thenReturn(200.0);
        when(market.lookUpPrice("microsoft")).thenReturn(300.0);
        when(market.lookUpPrice("amazon")).thenReturn(400.0);

        double result = portfolio.totalValue();

        double expectedvalue = 2*50.0 + 4*100.0 + 1*200.0 + 3*300.0 + 5*400.0;

        assertThat(result, is(closeTo((expectedvalue), 0.01)));

        verify(market).lookUpPrice("google");
        verify(market).lookUpPrice("apple");
        verify(market).lookUpPrice("tesla");
        verify(market).lookUpPrice("microsoft");
        verify(market).lookUpPrice("amazon");
    }
    @Test
    void testMostValuableStocks(){

        Stock googleStock = new Stock("google", 2);
        Stock appleStock = new Stock("apple", 4);
        Stock teslaStock = new Stock("tesla", 1);
        Stock microsoftStock = new Stock("microsoft", 3);
        Stock amazonStock = new Stock("amazon", 5);

        portfolio.addStock(googleStock);
        portfolio.addStock(appleStock);
        portfolio.addStock(teslaStock);
        portfolio.addStock(microsoftStock);
        portfolio.addStock(amazonStock);

        when(market.lookUpPrice("google")).thenReturn(50.0);
        when(market.lookUpPrice("apple")).thenReturn(100.0);
        when(market.lookUpPrice("tesla")).thenReturn(200.0);
        when(market.lookUpPrice("microsoft")).thenReturn(300.0);
        when(market.lookUpPrice("amazon")).thenReturn(400.0);

        List<Stock> mostValuableStocks = portfolio.mostValuableStocks(3);

        assertThat(mostValuableStocks).extracting(Stock::getLabel)
            .containsExactly("amazon", "microsoft", "apple");

        verify(market, atLeast(5)).lookUpPrice(anyString());
    }

}
