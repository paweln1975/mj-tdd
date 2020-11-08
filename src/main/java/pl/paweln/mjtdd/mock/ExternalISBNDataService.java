package pl.paweln.mjtdd.mock;

public interface ExternalISBNDataService {
    Book lookup(String isbn);
}
