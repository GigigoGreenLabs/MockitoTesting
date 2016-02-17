package es.beni.testing;

import org.mockito.Mockito;

import java.math.BigDecimal;

import es.beni.testing.dto.Stock;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

/**
 * Created by beni on 13/12/15.
 */
public class Example8 {

//    @Test
    public void testChangingDefault() throws Exception {
        Stock stock = new Stock("A", "A Corp.", new BigDecimal(11.20));

        Portfolio portfolio = Mockito.mock(Portfolio.class);
        assertNull(portfolio.getAvgPrice(stock));

        Portfolio portfolio1 = Mockito.mock(Portfolio.class, Mockito.RETURNS_SMART_NULLS);
        System.out.println("#1: " + portfolio1.getAvgPrice(stock));
        assertNotNull(portfolio1.getAvgPrice(stock));

        Portfolio portfolio2 = Mockito.mock(Portfolio.class, Mockito.RETURNS_MOCKS);
        System.out.println("#2: " + portfolio2.getAvgPrice(stock));
        assertNotNull(portfolio2.getAvgPrice(stock));

        Portfolio portfolio3 = Mockito.mock(Portfolio.class, Mockito.RETURNS_DEEP_STUBS);
        System.out.println("#3: " + portfolio3.getAvgPrice(stock));
        assertNotNull(portfolio3.getAvgPrice(stock));
    }

//    @Test
    public void testReset() throws Exception {
        Stock stock = new Stock("A", "A Corp.", new BigDecimal(11.20));

        Portfolio portfolio = Mockito.mock(Portfolio.class);

        when(portfolio.getAvgPrice(stock)).thenReturn(BigDecimal.TEN);

        assertNotNull(portfolio.getAvgPrice(stock));

        Mockito.reset(portfolio);

        assertNull(portfolio.getAvgPrice(stock));
    }
}
