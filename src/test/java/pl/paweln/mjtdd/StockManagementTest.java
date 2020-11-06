package pl.paweln.mjtdd;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class StockManagementTest {
    @Test
    public void testCanGetACorrectLocatorCode() {

        // create a stub, a replacement for an object that our tested class has dependency on
        ExternalISBNDataService testWebService = isbn -> new Book(isbn, "Of Mice and Man", "J. Steinbeck");

        ExternalISBNDataService testDataBaseService = isbn -> null;

        StockManager stockManager = new StockManager();
        stockManager.setWebService(testWebService);
        stockManager.setDatabaseService(testDataBaseService);

        String isbn = "0140177396";
        String locatorCode = stockManager.getLocatorCode(isbn);

        assertEquals("7396J4", locatorCode);
    }

    @Test
    public void databaseIsUsedWhenDataIsPresent() {
        // testing is particular method have been called using mockito

        ExternalISBNDataService testDataBaseService = mock(ExternalISBNDataService.class);
        ExternalISBNDataService testWebService = mock(ExternalISBNDataService.class);

        when(testDataBaseService.lookup("0140177396"))
                .thenReturn(new Book("0140177396", "abc", "xyz" ));

        StockManager stockManager = new StockManager();
        stockManager.setWebService(testWebService);
        stockManager.setDatabaseService(testDataBaseService);

        String isbn = "0140177396";
        String locatorCode = stockManager.getLocatorCode(isbn);

        verify(testDataBaseService, times(1)).lookup("0140177396");

        verify(testWebService, times(0)).lookup(anyString());
    }

    @Test
    public void webServiceIsUsedWhenDataIsNotPresent() {
        ExternalISBNDataService testDataBaseService = mock(ExternalISBNDataService.class);
        ExternalISBNDataService testWebService = mock(ExternalISBNDataService.class);

        when(testDataBaseService.lookup("0140177396"))
                .thenReturn(null);

        when(testWebService.lookup("0140177396"))
                .thenReturn(new Book("0140177396", "abc", "xyz" ));

        StockManager stockManager = new StockManager();
        stockManager.setWebService(testWebService);
        stockManager.setDatabaseService(testDataBaseService);

        String isbn = "0140177396";
        String locatorCode = stockManager.getLocatorCode(isbn);

        verify(testDataBaseService, times(1)).lookup("0140177396");
        verify(testWebService, times(1)).lookup("0140177396");


    }
}
