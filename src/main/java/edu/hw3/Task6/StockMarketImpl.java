package edu.hw3.Task6;

import java.util.PriorityQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StockMarketImpl implements StockMarket {
    private static final Logger LOGGER = LogManager.getLogger();
    private final PriorityQueue<Stock> stocks = new PriorityQueue<>();

    @Override
    public void add(Stock stock) {
        if (!doesStockHasDuplicate(stock.getTitleStock())) {
            LOGGER.info("Add stock: " + stock.toString());
        } else {
            LOGGER.info("Update stock: " + stock.toString());
        }
        stocks.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        if (stocks.contains(stock)) {
            stocks.remove(stock);
        } else {
            throw new IllegalArgumentException("This stock is not on the list!");
        }
    }

    @Override
    public Stock mostValuableStock() {
        if (stocks.size() == 0) {
            throw new RuntimeException("List of stock is empty!");
        }

        return stocks.peek();
    }

    private boolean doesStockHasDuplicate(String str) {
        for (Stock st : stocks) {
            if (st.getTitleStock().equals(str)) {
                stocks.remove(st);
                return true;
            }
        }
        return false;
    }
}
