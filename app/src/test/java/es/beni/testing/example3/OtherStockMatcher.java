package es.beni.testing.example3;

/**
 * Created by beni on 12/12/15.
 */
public class OtherStockMatcher extends BlueChipStockMatcher {

    @Override
    public boolean matches(Object argument) {
        return !super.matches(argument);
    }
}
