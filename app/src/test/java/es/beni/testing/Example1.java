package es.beni.testing;

import org.mockito.Mock;

import es.beni.testing.exercise5.MarketWatcher;
import es.beni.testing.exercise5.Portfolio;
import es.beni.testing.exercise5.dto.Stock;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;


//@RunWith(MockitoJUnitRunner.class)
public class Example1 {

    @Mock
    Portfolio portfolio;
    @Mock
    MarketWatcher marketWatcher;

//    @Test
    public void testVerifyZeroInteractions() throws Exception {
        verifyZeroInteractions(marketWatcher, portfolio);
    }

//@Test
    public void testVerifyZeroInteractions1() throws Exception {
        portfolio.getCurrentValue();
        verifyZeroInteractions(marketWatcher, portfolio);
    }

//    @Test
    public void testVerifyNoMoreInteractions() throws Exception {
        Stock stock = null;
        portfolio.getAvgPrice(stock);
        portfolio.sell(stock, 10);
        verify(portfolio).getAvgPrice(stock);

        verifyNoMoreInteractions(portfolio);
    }

//    @Test
    public void testVerifyNoMoreInteractions1() throws Exception {
        Stock stock = null;

        portfolio.getAvgPrice(stock);
        portfolio.sell(stock, 10);
        portfolio.sell(stock, 10);

        verify(portfolio).getAvgPrice(stock);
        verify(portfolio, times(2)).sell(stock, 10);

        verifyNoMoreInteractions(portfolio);
    }
}
