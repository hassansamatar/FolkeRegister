package Folkeregister.personmodel;

import Folkeregister.personmodel.exceptions.InvalidEmailException;
import Folkeregister.personmodel.exceptions.InvalidGenderException;
import Folkeregister.personmodel.exceptions.InvalidNameException;
import Folkeregister.personmodel.exceptions.InvalidTelephoneException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class PersonValidator {
    public static boolean isValidNavn(String s) {
        if (!isValidNavnRegex(s)) {
            throw new InvalidNameException("Navn Må være riktig format");
        }
        return true;
    }

    public static boolean isValidGender(String s) {
        if (!isValidGenderInput(s)) {
            throw new InvalidGenderException("Skriv inn m: for mann eller f: for kvinne");
        }
        return true;
    }

    public static boolean isValidEpost(String s) {
        if (!isValidEpostRegex(s)) {
            throw new InvalidEmailException("Epost må være riktig format");
        }
        return true;
    }

    public static boolean isValidTelefon(String s) {
        if (!isValidTelefonRegex(s)) {
            throw new InvalidTelephoneException("Telefon Må være riktig format");
        }
        return true;
    }
    public static boolean isValidNavnRegex(String navn) {
        String regex = "^[ÆØÅæøåA-Za-z+\\-?+\\'+\\s?]{2,30}$";
        return navn.matches(regex);
    }

    /*
     * Gender
     */
    public static boolean isValidGenderInput(String s) {
        return s.equals("m") || s.equals("f");
    }
    public static boolean isValidEpostRegex(String epost) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";  //Norwegian
        return epost.matches(regex);
    }
    public static boolean isValidTelefonRegex(String telefon) {
        Pattern patternNorsk = Pattern.compile("(\b?[(]?(0047|\\+47|47)?[ )]??[ ]?(\\d{2}u?[ ]?){4})");
        Pattern patternUk = Pattern.compile("^(?:(?:\\(?(?:0(?:0|11)\\)?[\\s-]?\\(?|\\+)44\\)?[\\s-]?(?:\\(?0\\)?[\\s-]" +
                "?)?)|(?:\\(?0))(?:(?:\\d{5}\\)?[\\s-]?\\d{4,5})|(?:\\d{4}\\)?[\\s-]?(?:\\d{5}|\\d{3}[\\s-]?\\d{3}))|(?:" +
                "\\d{3}\\)?[\\s-]?\\d{3}[\\s-]?\\d{3,4})|(?:\\d{2}\\)?[\\s-]?\\d{4}[\\s-]?\\d{4}))(?:[\\s-]?(?:x|ext\\.?" +
                "|\\#)\\d{3,4})?$");
        Pattern patternUsa = Pattern.compile("^(?:(?:\\+?1\\s*(?:[.-]\\s*)?)?(?:\\(\\s*([2-9]1[02-9]|[2-9][02-8]1|[2-9]" +
                "[02-8][02-9])\\s*\\)|([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9]))\\s*(?:[.-]\\s*)?)?([2-9]1[02-9]|[" +
                "2-9][02-9]1|[2-9][02-9]{2})\\s*(?:[.-]\\s*)?([0-9]{4})(?:\\s*(?:#|x\\.?|ext\\.?|extension)\\s*(\\d+))?$");
        Matcher matcher = patternNorsk.matcher(telefon);
        Matcher matcher1 = patternUk.matcher(telefon);
        Matcher matcher2 = patternUsa.matcher(telefon);
        if (matcher.matches()) {
            return true;
        } else if (matcher1.matches()) {
            return true;
        } else if (matcher2.matches()) {
            return true;
        } else {
            return false;
        }
    }

}


