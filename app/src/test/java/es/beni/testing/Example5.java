package es.beni.testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import es.beni.testing.exercise5.Portfolio;
import es.beni.testing.exercise5.dto.Stock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 * Created by beni on 12/12/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class Example5 {

//    @Test
    public void testSpying() throws Exception {
        Stock stock = new Stock("A", "A", BigDecimal.ONE);
        Stock spyStock = spy(stock);

        assertEquals("A", spyStock.getSymbol());

        spyStock.updatePrice(BigDecimal.ZERO);

        assertEquals(BigDecimal.ZERO, spyStock.getPrice());

        when(spyStock.getPrice()).thenReturn(BigDecimal.TEN);

        spyStock.updatePrice(BigDecimal.ONE);

        assertNotEquals(BigDecimal.ONE, spyStock.getPrice());

        assertEquals(BigDecimal.TEN, spyStock.getPrice());
    }

    @Mock
    Portfolio portfolio;

//    @Test
    public void testVoidStubbing() throws Exception {
        final Stock stock = new Stock("A", "A", BigDecimal.TEN);

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                System.out.println("called with arguments: " + ((Stock) args[0]).getName());
                return null;
            }
        })
                .doNothing()
//        .doCallRealMethod()
//        .doThrow(new StackOverflowError())
        .when(portfolio).buy(stock);


        portfolio.buy(stock);
        portfolio.buy(stock);
        portfolio.buy(stock);
        portfolio.buy(stock);
    }

//    @Test
    public void testDoReturnIsNotTYpeSafe() throws Exception {
        when(portfolio.getCurrentValue()).thenReturn(BigDecimal.ONE);

        portfolio.getCurrentValue();

        doReturn(BigDecimal.ONE).when(portfolio).getCurrentValue();

        portfolio.getCurrentValue();
    }

    @Test
    public void testDoReturnUsage() throws Exception {
        List<String> list = new ArrayList<>();
        List<String> spyList = spy(list);

        spyList.add("0");

//        when(spyList.get(0)).thenReturn("not reachable");
        doReturn("not reachable").when(spyList).get(0);

        assertEquals("not reachable", spyList.get(0));
    }
}
