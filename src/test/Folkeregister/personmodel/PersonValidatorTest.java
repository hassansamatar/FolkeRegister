package Folkeregister.personmodel;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PersonValidatorTest {

    @Test
    void isValidName() {
        assertTrue(PersonValidator.isValidNavnRegex("henrik"));
       assertTrue(PersonValidator.isValidNavnRegex("Henrik  Ole"));

        //assertTrue(PersonValidator.isValidNavnRegex("Åse Ødegår"));
       // assertTrue(PersonValidator.isValidNavnRegex("Åse Ødegår Ærlin velgårds"));
    }

    @Test
   void isInvalidName(){
        assertFalse(PersonValidator.isValidNavnRegex("Åse Ødegår Ærlin velgårds Åse Ødegår Ærlin velgårds Åse Ødegår Ærlin velgårds"));
        assertFalse(PersonValidator.isValidNavnRegex("Ole3"));
        assertFalse(PersonValidator.isValidNavnRegex(""));
        assertFalse(PersonValidator.isValidNavnRegex("@oslomet.no"));
        assertFalse(PersonValidator.isValidNavnRegex("henrik1"));
        assertFalse(PersonValidator.isValidNavnRegex(" "));
        assertFalse(PersonValidator.isValidNavnRegex("123"));
        assertFalse(PersonValidator.isValidNavnRegex(";bot@evil.com"));
    }

    @Test
    void isValidGender() {
        assertTrue(PersonValidator.isValidGenderInput("m"));
        assertTrue(PersonValidator.isValidGenderInput("f"));
    }
    @Test
    void isInValidGender() {
        assertFalse(PersonValidator.isValidGenderInput("male"));
        assertFalse(PersonValidator.isValidEpostRegex("female"));
        assertFalse(PersonValidator.isValidGenderInput("mann"));
        assertFalse(PersonValidator.isValidEpostRegex("kvinne"));
        assertFalse(PersonValidator.isValidGenderInput(""));
        assertFalse(PersonValidator.isValidEpostRegex(" "));
        assertFalse(PersonValidator.isValidGenderInput("mannmmmm"));
        assertFalse(PersonValidator.isValidGenderInput("køø"));
    }


    @Test
    void isValidEpostRegex() {
        assertTrue(PersonValidator.isValidEpostRegex("henrik.lieng@oslomet.no"));
        assertTrue(PersonValidator.isValidEpostRegex("example@example.com"));
        assertTrue(PersonValidator.isValidEpostRegex("uk@domain.co.uk"));

    }

    @Test
    void isInvalidEpostRegex() {
        assertFalse(PersonValidator.isValidEpostRegex(""));
        assertFalse(PersonValidator.isValidEpostRegex("henrik.lieng"));
        assertFalse(PersonValidator.isValidEpostRegex("@oslomet.no"));
        assertFalse(PersonValidator.isValidEpostRegex("henrik.lieng@invalid"));
        assertFalse(PersonValidator.isValidEpostRegex("test@"));
        assertFalse(PersonValidator.isValidEpostRegex("test@"));
        assertFalse(PersonValidator.isValidEpostRegex(";bot@evil.com"));
    }
    @Test
    void isValidTelephone() {
        assertTrue(PersonValidator.isValidTelefonRegex("004740300664"));
        assertTrue(PersonValidator.isValidTelefonRegex("+4740300664"));
        assertTrue(PersonValidator.isValidTelefonRegex("4740300664"));
        assertTrue(PersonValidator.isValidTelefonRegex("40300664"));
        assertTrue(PersonValidator.isValidTelefonRegex("4030066")); // 7 digit
    }
    @Test
    void isInvalidTelephone() {
        assertFalse(PersonValidator.isValidTelefonRegex(""));
        assertFalse(PersonValidator.isValidTelefonRegex("004640300664"));
        assertFalse(PersonValidator.isValidTelefonRegex("+4640300664")); //svenisk telefon
        assertFalse(PersonValidator.isValidTelefonRegex("0047403006644")); // 11 digit
        assertFalse(PersonValidator.isValidTelefonRegex("test@"));
        assertFalse(PersonValidator.isValidTelefonRegex("############"));
        assertFalse(PersonValidator.isValidTelefonRegex("abacdefghio"));
    }
}