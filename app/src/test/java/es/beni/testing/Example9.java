package es.beni.testing;

import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import es.beni.testing.exercise5.Portfolio;
import es.beni.testing.exercise5.dto.Stock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.RETURNS_MOCKS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 * Created by beni on 13/12/15.
 */
public class Example9 {

    Stock globalStock = when(mock(Stock.class).getPrice()).thenReturn(BigDecimal.TEN).getMock();

    @Test
    public void testGlobalMock() throws Exception {
        assertEquals(BigDecimal.TEN, globalStock.getPrice());

        globalStock.updatePrice(BigDecimal.ONE);
    }

    public void testMockingDetail() throws Exception {
        Portfolio portfolio = mock(Portfolio.class, RETURNS_MOCKS);

        BigDecimal result = portfolio.getAvgPrice(globalStock);
        assertNotNull(result);
        assertTrue(Mockito.mockingDetails(portfolio).isMock());

        Stock stock = new Stock(null, null, null);
        Stock spy = spy(stock);
        assertTrue(Mockito.mockingDetails(spy).isSpy());
    }
}
