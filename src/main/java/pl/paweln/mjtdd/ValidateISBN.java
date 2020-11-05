package pl.paweln.mjtdd;

public class ValidateISBN {

    public static final int LONG_ISBN_NUMBER = 13;
    public static final int SHORT_ISBN_NUMBER = 10;
    public static final int SHORT_ISBN_MULTIPLIER = 11;
    public static final int LONG_ISBN_MULTIPLIER = 10;

    public boolean checkISBN (String isbn) {
        if (isbn.length() == SHORT_ISBN_NUMBER) {
            return this.isAValidShortISBN(isbn);
        } else if (isbn.length() == LONG_ISBN_NUMBER) {
            return this.isValidLongISBN(isbn);
        }

        throw new NumberFormatException("ISBN number must be 10 or 13 digits long.");

    }

    private boolean isValidLongISBN(String isbn) {
        int total = 0;

        for (int i = 0; i < LONG_ISBN_NUMBER; i++) {
            if (!Character.isDigit(isbn.charAt(i))) {
                throw new NumberFormatException("ISBN number must contain only digits.");
            }
            if (i  % 2 == 1) {
                total += Character.getNumericValue(isbn.charAt(i)) * 3;
            } else {
                total += Character.getNumericValue(isbn.charAt(i));
            }
        }

        return  (total % LONG_ISBN_MULTIPLIER == 0);
    }

    private boolean isAValidShortISBN(String isbn) {
        int total = 0;

        for (int i = 0; i < SHORT_ISBN_NUMBER; i++) {
            if (!Character.isDigit(isbn.charAt(i))) {
                if (i < 9) {
                    throw new NumberFormatException("ISBN number must contain only digits or X at the end.");
                } else {
                    if(! (Character.isDigit(i) || isbn.charAt(i) == 'X' )) {
                        throw new NumberFormatException("Last item in the ISBN number must be a digit or X.");
                    }
                }
            }
            if (isbn.charAt(i) == 'X') {
                total += 10;
            } else {
                total += Character.getNumericValue(isbn.charAt(i)) * (SHORT_ISBN_NUMBER - i);
            }
        }

        return (total % SHORT_ISBN_MULTIPLIER == 0);
    }
}
