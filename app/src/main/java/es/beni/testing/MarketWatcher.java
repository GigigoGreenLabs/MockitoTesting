package es.beni.testing;

import es.beni.testing.dto.Stock;

public interface MarketWatcher {
   Stock getQuote(String symbol);
}
