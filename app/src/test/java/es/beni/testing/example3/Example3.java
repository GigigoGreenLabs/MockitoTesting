package es.beni.testing.example3;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import es.beni.testing.MarketWatcher;
import es.beni.testing.Portfolio;
import es.beni.testing.StockBroker;
import es.beni.testing.dto.Stock;

import static org.mockito.Mockito.argThat;
import static org.mockito.Mockito.isA;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by beni on 12/12/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class Example3 {

    @Mock
    Portfolio portfolio;

    @Mock
    MarketWatcher marketWatcher;

    private StockBroker stockBroker;


    @Before
    public void setUp() throws Exception {
        stockBroker = new StockBroker(marketWatcher);
    }

    @Test
    public void testArgumentMatcher() throws Exception {
        when(portfolio.getAvgPrice(isA(Stock.class))).thenReturn(new BigDecimal("10.00"));

        Stock stockBlueChip = new Stock("FB", "FB Cop.", new BigDecimal("1000.00"));

        Stock otherStock = new Stock("XY", "XY Cop.", new BigDecimal("5.00"));

        when(marketWatcher.getQuote(argThat(new BlueChipStockMatcher()))).thenReturn(stockBlueChip);

        when(marketWatcher.getQuote(argThat(new OtherStockMatcher()))).thenReturn(otherStock);

        stockBroker.perform(portfolio, stockBlueChip);

        verify(portfolio).sell(stockBlueChip, 10);

        stockBroker.perform(portfolio, otherStock);

        verify(portfolio, never()).sell(otherStock, 10);

    }
}
