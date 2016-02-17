package es.beni.testing.example3;

import org.mockito.ArgumentMatcher;

/**
 * Created by beni on 12/12/15.
 */
public class BlueChipStockMatcher implements ArgumentMatcher<String> {
    @Override
    public boolean matches(Object argument) {
        return "FB".equals(argument) || "AAPL".equals(argument);
    }
}
