package com.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StocksPortfolio {

    private List<Stock> stocks;
    private IStockmarketService stockMarketService;

    public StocksPortfolio(IStockmarketService stockMarketService) {
        this.stockMarketService = stockMarketService;
        this.stocks = new ArrayList<>();
    }

    public void addStock(Stock stock) {
        this.stocks.add(stock);
    }

    public double totalValue() {
        double value = 0;
        for (Stock s : stocks) {
            value += stockMarketService.lookUpPrice(s.getLabel()) * s.getQuantity();
        }
        return value;
    }
        public List<Stock> mostValuableStocks(int topN) {
        return stocks.stream()
            .sorted(Comparator.comparingDouble(stock -> 
                -stock.getQuantity() * stockMarketService.lookUpPrice(stock.getLabel()))) 
            .limit(topN) 
            .collect(Collectors.toList()); 
    }


}
