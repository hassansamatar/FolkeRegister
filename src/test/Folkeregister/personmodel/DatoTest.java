package Folkeregister.personmodel;

import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class DatoTest {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.BASIC_ISO_DATE;
    Dato dato = new Dato(dateTimeFormatter);
    @Test
    void isValidDate() {
        assertTrue(dato.isValidDate("20200501"));
        assertTrue(dato.isValidDate("19991231"));
        assertTrue(dato.isValidDate("18110330"));
        //Feb. 29, year, 2020. It is a valid date (leap year)
        assertTrue(dato.isValidDate("20200229"));
        assertTrue(dato.isValidDate("20000228"));
        assertTrue(dato.isValidDate("20300101"));
    }
    @Test
    void isInvalidDate() {
        assertFalse(dato.isValidDate("20201301"));
        // Dec. 32
        assertFalse(dato.isValidDate("19991232"));
        // Feb. 29 . 2011 was not a leap year.
        assertFalse(dato.isValidDate("20110229"));
        assertFalse(dato.isValidDate("2020229"));
        assertFalse(dato.isValidDate("19990631"));
        assertFalse(dato.isValidDate(" "));
        assertFalse(dato.isValidDate("kk"));
        assertFalse(dato.isValidDate("1999 Jan 6"));
    }

}