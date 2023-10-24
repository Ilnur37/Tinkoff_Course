package edu.hw3;

import edu.hw3.Task6.Stock;
import edu.hw3.Task6.StockMarketImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task6Test {
    @Test
    @DisplayName("Максимальное число")
    void mostValuableStock() {
        StockMarketImpl stockMarket = new StockMarketImpl();
        Stock stock1 = new Stock("Apple", 200);
        Stock stock2 = new Stock("Bbb", 199);
        Stock stock3 = new Stock("Ccc", 201);
        stockMarket.add(stock1);
        stockMarket.add(stock2);
        stockMarket.add(stock3);
        Stock stock = stockMarket.mostValuableStock();
        Assertions.assertEquals(stock.getTitleStock(), stock3.getTitleStock());
        Assertions.assertEquals(stock.getPriceStock(), stock3.getPriceStock());
    }

    @Test
    @DisplayName("Максимальное число и его удаление")
    void mostValuableStock2() {
        StockMarketImpl stockMarket = new StockMarketImpl();
        Stock stock1 = new Stock("Apple", 200);
        Stock stock2 = new Stock("Bbb", 199);
        Stock stock3 = new Stock("Ccc", 201);
        Stock stock4 = new Stock("Eee", 198);
        Stock stock5 = new Stock("Ddd", 202);
        stockMarket.add(stock1);
        stockMarket.add(stock2);
        stockMarket.add(stock3);
        stockMarket.add(stock4);
        stockMarket.add(stock5);

        stockMarket.remove(stock5);

        Stock stock = stockMarket.mostValuableStock();
        Assertions.assertEquals(stock.getTitleStock(), stock3.getTitleStock());
        Assertions.assertEquals(stock.getPriceStock(), stock3.getPriceStock());
    }

    @Test
    @DisplayName("Максимальное число и добавление акции с тем же названием")
    void mostValuableStock3() {
        StockMarketImpl stockMarket = new StockMarketImpl();
        Stock stock1 = new Stock("Apple", 6);
        Stock stock2 = new Stock("Bbb", 5);
        Stock stock3 = new Stock("Ccc", 4);
        Stock stock4 = new Stock("Eee", 3);
        Stock stock5 = new Stock("Apple", 7);
        stockMarket.add(stock1);
        stockMarket.add(stock2);
        stockMarket.add(stock3);
        stockMarket.add(stock4);
        stockMarket.add(stock5);

        Stock stock = stockMarket.mostValuableStock();
        Assertions.assertEquals(stock.getTitleStock(), stock5.getTitleStock());
        Assertions.assertEquals(stock.getPriceStock(), stock5.getPriceStock());
    }

    @Test
    @DisplayName("Максимальное число и добавление акции с тем же названием и удаления его")
    void mostValuableStock4() {
        StockMarketImpl stockMarket = new StockMarketImpl();
        Stock stock1 = new Stock("Apple", 6);
        Stock stock2 = new Stock("Bbb", 5);
        Stock stock3 = new Stock("Ccc", 4);
        Stock stock4 = new Stock("Eee", 3);
        Stock stock5 = new Stock("Apple", 7);
        stockMarket.add(stock1);
        stockMarket.add(stock2);
        stockMarket.add(stock3);
        stockMarket.add(stock4);
        stockMarket.add(stock5);

        stockMarket.remove(stock5);

        Stock stock = stockMarket.mostValuableStock();
        Assertions.assertEquals(stock.getTitleStock(), stock2.getTitleStock());
        Assertions.assertEquals(stock.getPriceStock(), stock2.getPriceStock());
    }

    @Test
    @DisplayName("Удаление несуществующей акции")
    void removeStock() {
        StockMarketImpl stockMarket = new StockMarketImpl();
        Stock stock = new Stock("Apple", 7);

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            stockMarket.remove(stock);
        }, "Stock in list!");

        Assertions.assertEquals("This stock is not on the list!", thrown.getMessage());
    }

    @Test
    @DisplayName("Поиск максимальной цены в пустом списке")
    void mostValuableStock_whenQueueIsEmpty() {
        StockMarketImpl stockMarket = new StockMarketImpl();

        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> {
            stockMarket.mostValuableStock();
        }, "List is not empty!");

        Assertions.assertEquals("List of stock is empty!", thrown.getMessage());
    }
}
