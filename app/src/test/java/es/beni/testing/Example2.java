package es.beni.testing;

import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.List;

import es.beni.testing.exercise5.Portfolio;
import es.beni.testing.exercise5.dto.Stock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by beni on 12/12/15.
 */

//@RunWith(MockitoJUnitRunner.class)
public class Example2 {

    @Mock List<String> mockedList;

//    @Test
    public void testVerify1() throws Exception {
        //stubbing using anyInt() argument matcher
        when(mockedList.get(anyInt())).thenReturn("element");

        //following prints "element"
        System.out.println(mockedList.get(999));

        //you can also verify using argument matcher
        verify(mockedList).get(anyInt());
    }

    @Mock
    Portfolio portfolio;

//    @Test(expected = IllegalStateException.class)
    public void testVerifyExceptions() throws Exception {
        when(portfolio.getAvgPrice(isA(Stock.class))).thenThrow(new IllegalStateException("Database is down"));

        portfolio.getAvgPrice(new Stock(null, null, null));
    }


//    @Test(expected = IllegalStateException.class)
    public void testVerifyVoidExceptions() throws Exception {
        doThrow(new IllegalStateException()).when(portfolio).buy(isA(Stock.class));

        portfolio.buy(new Stock(null, null, null));
    }

//    @Test
    public void testConsecutiveCalls() throws Exception {
        when(portfolio.getAvgPrice(any(Stock.class)))
                .thenReturn(BigDecimal.TEN, BigDecimal.ONE, BigDecimal.ZERO);

        assertEquals(BigDecimal.TEN, portfolio.getAvgPrice(any(Stock.class)));
        assertEquals(BigDecimal.ONE, portfolio.getAvgPrice(any(Stock.class)));
        assertEquals(BigDecimal.ZERO, portfolio.getAvgPrice(any(Stock.class)));
        assertEquals(BigDecimal.ZERO, portfolio.getAvgPrice(any(Stock.class)));
    }

//    @Test
    public void testConsecutiveCalls1() throws Exception {
        Stock stock = new Stock(null, null, null);

        when(portfolio.getAvgPrice(stock))
                .thenReturn(BigDecimal.valueOf(1), BigDecimal.valueOf(2), BigDecimal.valueOf(3), BigDecimal.valueOf(4));

        for (int i = 0; i < 3; i++) {
            assertEquals(BigDecimal.valueOf(i+1), portfolio.getAvgPrice(stock));
        }
    }

//    @Test(expected = StackOverflowError.class)
    public void testCOnsecutiveCallsException() throws Exception {
        Stock stock = new Stock(null, null, null);

        when(portfolio.getAvgPrice(stock))
                .thenReturn(BigDecimal.TEN)
                .thenReturn(BigDecimal.ZERO)
                .thenThrow(StackOverflowError.class)
                ;

        assertEquals(BigDecimal.TEN, portfolio.getAvgPrice(stock));
        assertEquals(BigDecimal.ZERO, portfolio.getAvgPrice(stock));
        System.out.println("LLega1");
        assertEquals(BigDecimal.ZERO, portfolio.getAvgPrice(stock));
        System.out.println("LLega2");
    }
}
