package pl.paweln.mjtdd.mock;

public class StockManager {

    private ExternalISBNDataService webService;
    private ExternalISBNDataService databaseService;

    public void setDatabaseService(ExternalISBNDataService databaseService) {
        this.databaseService = databaseService;
    }

    public void setWebService(ExternalISBNDataService webService) {
        this.webService = webService;
    }



    public String getLocatorCode(String isbn) {
        Book book = databaseService.lookup(isbn);

        if (book == null) {
            book = webService.lookup(isbn);
        }

        return isbn.substring(isbn.length() - 4) +
                book.getAuthor().charAt(0) +
                book.getTitle().split(" ").length;
    }
}
