package edu.hw3.Task6;

import org.jetbrains.annotations.NotNull;

public class Stock implements Comparable<Stock> {
    private final String titleStock;
    private final int priceStock;

    public Stock(String titleStock, int priceStock) {
        this.titleStock = titleStock;
        this.priceStock = priceStock;
    }

    public String getTitleStock() {
        return titleStock;
    }

    public int getPriceStock() {
        return priceStock;
    }

    @Override
    public String toString() {
        return "Title: " + this.getTitleStock() + "  Price: " + this.getPriceStock();
    }

    @Override
    public int compareTo(@NotNull Stock o) {
        return (this.getPriceStock() - o.getPriceStock()) * -1;
    }
}
