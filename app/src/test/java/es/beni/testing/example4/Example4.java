package es.beni.testing.example4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.beni.testing.Portfolio;
import es.beni.testing.dto.Stock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

/**
 * Created by beni on 12/12/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class Example4 {

    Map<String, List<Stock>> stockMap = new HashMap<>();

    public class BuyAnswer implements Answer<Object> {

        @Override
        public Object answer(InvocationOnMock invocation) throws Throwable {
            Stock newStock = (Stock) invocation.getArguments()[0];

            List<Stock> stocks = stockMap.get(newStock.getSymbol());

            if (stocks != null) {
                stocks.add(newStock);
            } else {
                stocks = new ArrayList<>();
                stocks.add(newStock);
                stockMap.put(newStock.getSymbol(), stocks);
            }
            return null;
        }
    }

    public class TotalPriceAnswer implements Answer<BigDecimal> {

        @Override
        public BigDecimal answer(InvocationOnMock invocation) throws Throwable {
            BigDecimal totalPrice = BigDecimal.ZERO;

            for (String stockid : stockMap.keySet()) {
                for (Stock stock : stockMap.get(stockid)) {
                    totalPrice = totalPrice.add(stock.getPrice());
                }
            }
            return totalPrice;
        }
    }

    @Mock
    Portfolio portfolio;

    @Test
    public void testAnswering() throws Exception {
        stockMap.clear();

        doAnswer(new BuyAnswer()).when(portfolio).buy(isA(Stock.class));

        when(portfolio.getCurrentValue()).thenAnswer(new TotalPriceAnswer());

        portfolio.buy(new Stock("A", "A", BigDecimal.TEN));
        portfolio.buy(new Stock("B", "B", BigDecimal.ONE));
        portfolio.buy(new Stock("B", "B", BigDecimal.ONE));

        assertEquals(new BigDecimal("12"), portfolio.getCurrentValue());
    }
}
