package pl.paweln.mjtdd;

public class StockManager {

    public void setService(ExternalISBNDataService service) {
        this.service = service;
    }

    private ExternalISBNDataService service;

    public String getLocatorCode(String isbn) {
        Book book = service.lookup(isbn);

        return isbn.substring(isbn.length() - 4) +
                book.getAuthor().charAt(0) +
                book.getTitle().split(" ").length;
    }
}
