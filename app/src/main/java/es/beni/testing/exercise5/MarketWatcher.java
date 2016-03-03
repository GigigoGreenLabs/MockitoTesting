package es.beni.testing.exercise5;

import es.beni.testing.exercise5.dto.Stock;

public interface MarketWatcher {
   Stock getQuote(String symbol);
}
