package Folkeregister.personmodel;

import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class DatoTest {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.BASIC_ISO_DATE;
    Dato dato = new Dato(dateTimeFormatter);
    @Test
    void isValidDate() {
        /*
        *Remember BASIC_ISO_DATE format yyyy mm dd
         */
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
        /*
         *Remember BASIC_ISO_DATE format yyyy mm dd
         */
       assertFalse(dato.isInValidDate("20200230"));
        // Dec. 32, 1999.
        assertFalse(dato.isInValidDate("19991232"));
        // Feb. 29 . 2011 was not a leap year.
        assertFalse(dato.isInValidDate("20110229"));
        assertFalse(dato.isInValidDate("2020229"));
        assertFalse(dato.isInValidDate("19990631"));
        assertFalse(dato.isInValidDate(" "));
        assertFalse(dato.isInValidDate("kk"));
        assertFalse(dato.isInValidDate("1999 Jan 6"));
    }

}