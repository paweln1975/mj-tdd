package pl.paweln.mjtdd.junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import pl.paweln.mjtdd.junit.ValidateISBN;


public class ValidateISBNTest {

    private ValidateISBN validator;

    @Before
    public void setUp() {
        validator = new ValidateISBN();
    }

    @Test
    public void checkValid10DigitISBN() {
        // each test should validate one single piece of logic -> here correct ISBN numbers

        boolean result = validator.checkISBN("0385545967");

        assertTrue("First value", result);

        result = validator.checkISBN("0618249060");
        assertTrue("Second value", result);
    }

    @Test
    public void checkInvalid10DigitISBN() {
        boolean result = validator.checkISBN("0385545964");
        assertFalse(result);
    }

    @Test
    public void checkInvalid13DigitISBN() {
        boolean result = validator.checkISBN("9781853260082");
        assertFalse( result);
    }

    @Test
    public void checkValid13DigitISBN() {

        boolean result = validator.checkISBN("9781853260087");
        assertTrue("First value", result);

        result = validator.checkISBN("9780553213508");
        assertTrue("First value", result);
    }


    @Test (expected = NumberFormatException.class)
    public void nineDigitOfISBAreNotAllowed() {
        validator.checkISBN("038554596");
    }

    @Test (expected = NumberFormatException.class)
    public void nonNumericISBNAreNotAllowed() {
        validator.checkISBN("helloworld");
    }

    @Test (expected = NumberFormatException.class)
    public void nonNumeric13LengthISBNAreNotAllowed() {
        validator.checkISBN("helloworld!!!");
    }

    @Test
    public void checkValueISBNNumberEndingWithX() {

        boolean result = validator.checkISBN("012000030X");
        assertTrue("First value", result);
    }
}
