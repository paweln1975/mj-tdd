package pl.paweln.mjtdd;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class StockManagementTest {

    private ExternalISBNDataService testWebService;
    private ExternalISBNDataService testDataBaseService;
    private StockManager stockManager;

    @Before
    public void setup() {
        // create a stub, a replacement for an object that our tested class has dependency on

        testDataBaseService = mock(ExternalISBNDataService.class);
        testWebService = mock(ExternalISBNDataService.class);
        stockManager = new StockManager();
        stockManager.setWebService(testWebService);
        stockManager.setDatabaseService(testDataBaseService);

    }
    @Test
    public void testCanGetACorrectLocatorCode() {
        // create a stub, a replacement for an object that our tested class has dependency on
        ExternalISBNDataService testWebServiceLocal = isbn -> new Book(isbn, "Of Mice and Man", "J. Steinbeck");
        ExternalISBNDataService testDataBaseServiceLocal = isbn -> null;

        StockManager stockManager = new StockManager();
        stockManager.setWebService(testWebServiceLocal);
        stockManager.setDatabaseService(testDataBaseServiceLocal);

        String isbn = "0140177396";
        String locatorCode = stockManager.getLocatorCode(isbn);

        assertEquals("7396J4", locatorCode);
    }

    @Test
    public void testCanGetACorrectLocatorCodeWithStubUsingMockito() {
        when(testWebService.lookup(anyString())).thenReturn(
                new Book("0140177396", "Of Mice and Man", "J. Steinbeck"));
        when(testDataBaseService.lookup(anyString())).thenReturn(null);

        String isbn = "0140177396";
        String locatorCode = stockManager.getLocatorCode(isbn);

        assertEquals("7396J4", locatorCode);
    }

    @Test
    public void databaseIsUsedWhenDataIsPresent() {
        // testing is particular method have been called using mockito
        when(testDataBaseService.lookup("0140177396"))
                .thenReturn(new Book("0140177396", "abc", "xyz" ));

        String isbn = "0140177396";
        stockManager.getLocatorCode(isbn);

        verify(testDataBaseService, times(1)).lookup("0140177396");
        verify(testWebService, never()).lookup(anyString());
    }

    @Test
    public void webServiceIsUsedWhenDataIsNotPresent() {

        when(testDataBaseService.lookup("0140177396"))
                .thenReturn(null);

        when(testWebService.lookup("0140177396"))
                .thenReturn(new Book("0140177396", "abc", "xyz" ));

        String isbn = "0140177396";
        stockManager.getLocatorCode(isbn);

        verify(testDataBaseService, times(1)).lookup("0140177396");
        verify(testWebService, times(1)).lookup("0140177396");
    }
}
