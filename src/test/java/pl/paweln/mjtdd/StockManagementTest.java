package pl.paweln.mjtdd;

import org.junit.Test;
import static org.junit.Assert.*;

public class StockManagementTest {
    @Test
    public void testCanGetACorrectLocatorCode() {

        // create a stub, a replacement for an object that our tested class has dependency on
        ExternalISBNDataService testService = isbn -> new Book(isbn, "Of Mice and Man", "J. Steinbeck");

        StockManager stockManager = new StockManager();
        stockManager.setService(testService);

        String isbn = "0140177396";
        String locatorCode = stockManager.getLocatorCode(isbn);

        assertEquals("7396J4", locatorCode);
    }
}
