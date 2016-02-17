package es.beni.testing;

import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.math.BigDecimal;

import es.beni.testing.dto.Stock;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.isA;

/**
 * Created by beni on 13/12/15.
 */
//@RunWith(MockitoJUnitRunner.class)
public class Example7 {

    @Mock Portfolio portfolio;

    @Mock MarketWatcher marketWatcher;

//    @Test
    public void testInOrder() throws Exception {
        Stock stock = new Stock("A", "A Corp.", new BigDecimal("10.00"));

        portfolio.getAvgPrice(stock);
        portfolio.getCurrentValue();
        marketWatcher.getQuote("X");
        portfolio.buy(stock);

        InOrder inOrder = Mockito.inOrder(portfolio, marketWatcher);
        
//        inOrder.verify(portfolio).buy(isA(Stock.class));
//        inOrder.verify(portfolio).getAvgPrice(isA(Stock.class));
    }

//    @Test
    public void testInOrderCorrect() throws Exception {
        Stock stock = new Stock("A", "A Corp.", new BigDecimal("10.00"));

        portfolio.getAvgPrice(stock);
        portfolio.getCurrentValue();
        marketWatcher.getQuote("X");
        portfolio.buy(stock);

        InOrder inOrder = Mockito.inOrder(portfolio, marketWatcher);

        inOrder.verify(portfolio).getAvgPrice(isA(Stock.class));
        inOrder.verify(portfolio).getCurrentValue();
        inOrder.verify(marketWatcher).getQuote(anyString());
        inOrder.verify(portfolio).buy(isA(Stock.class));
    }
}
