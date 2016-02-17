package es.beni.testing;

import org.junit.Before;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import java.math.BigDecimal;

import es.beni.testing.dto.Stock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by beni on 13/12/15.
 */
//@RunWith(MockitoJUnitRunner.class)
public class Example6 {

    @Mock Portfolio portfolio;

    @Mock MarketWatcher marketWatcher;

    private StockBroker stockBroker;

    @Before
    public void setUp() throws Exception {
        stockBroker = new StockBroker(marketWatcher);

    }

//    @Test
    public void testArgumentCaptor() throws Exception {
        when(portfolio.getAvgPrice(isA(Stock.class))).thenReturn(new BigDecimal("10.00"));

        Stock stock = new Stock("A", "A Corp.", new BigDecimal("11.20"));

        when(marketWatcher.getQuote(anyString())).thenReturn(stock);

        stockBroker.perform(portfolio, stock);

        ArgumentCaptor<String> stockIdCaptor = ArgumentCaptor.forClass(String.class);

        verify(marketWatcher).getQuote(stockIdCaptor.capture());
        assertEquals("A", stockIdCaptor.getValue());

        ArgumentCaptor<Stock> stockArgumentCaptor = ArgumentCaptor.forClass(Stock.class);

        ArgumentCaptor<Integer> stockSellCaptor = ArgumentCaptor.forClass(Integer.class);

        verify(portfolio).sell(stockArgumentCaptor.capture(), stockSellCaptor.capture());

        assertEquals("A", stockArgumentCaptor.getValue().getSymbol());
        assertEquals(10, stockSellCaptor.getValue().intValue());
    }
}
