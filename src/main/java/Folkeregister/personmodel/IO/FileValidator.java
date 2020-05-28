package Folkeregister.personmodel.IO;

import Folkeregister.personmodel.exceptions.InvalidDateException;
import Folkeregister.personmodel.exceptions.InvalidFodselsnummerException;
import Folkeregister.personmodel.exceptions.InvalidNameException;
import Folkeregister.personmodel.exceptions.InvalidPersonException;
import Folkeregister.personmodel.gui.Dialogs;

public class FileValidator {
    public static int parseDay(String s) {
        if (isValidDay(s)){
            return Integer.parseInt(s);
        }else {
            throw new InvalidDateException("No File is loaded!!");
        }
    }
    static boolean isValidDay(String s){
        try {
            if(s.isEmpty() || s.isBlank()){
                throw new InvalidDateException("Enter a valid day?");
            }
           int  number = Integer.parseInt(s);
            if(number < 1 ){
                throw new InvalidDateException("There is no negative day in a month!");
            }else if( number > 31){
                throw new InvalidDateException("No month has more than 31 days!");
            }else {
                return true;
            }
        } catch (NumberFormatException e) {
          throw new InvalidDateException("Invalid Month!\n Only a positive number is allowed! ");
        }

    }

    public static int parseMonth(String s) {
        if (isValidMonth(s)){
            return Integer.parseInt(s);
        }else {
            throw new InvalidDateException("No File is loaded!!");
        }
    }
    static boolean isValidMonth(String s){
        try {
            if(s.isEmpty() || s.isBlank()){
                throw new InvalidDateException("Enter a valid month?");
            }
            int  number = Integer.parseInt(s);
            if(number < 1  | number >12){
                throw new InvalidDateException("A valid month is between 1 and 12. ");
            }else{
                return true;
            }
        } catch (NumberFormatException e) {
            throw new InvalidDateException("Invalid Day!\n Only a positive number 1 and 12 is allowed! ");
        }

    }
    public static int parseYear(String s) {
        if (isValidYear(s)){
            return Integer.parseInt(s);
        }else {
            throw new InvalidDateException("No File is loaded!!");
        }
    }
    static boolean isValidYear(String s){
        try {
            if(s.isEmpty() || s.isBlank()){
                throw new InvalidDateException("Enter a valid birth Year?");
            }
            int  number = Integer.parseInt(s);
            if(number < 1900 || number > 2020 ){
                throw new InvalidDateException("Year must be 1900 and 2020.");
            }else{
                return true;
            }
        } catch (NumberFormatException e) {
            throw new InvalidDateException("Invalid Year!\nYear is a number between: 1900 and 2020");
        }
    }

    public static int parseAge(String s) {
        if (isValidAge(s)){
            return Integer.parseInt(s);
        }else {
            throw new InvalidDateException("No File is loaded!!");
        }
    }
    static boolean isValidAge(String s){
        try {
            if(s.isEmpty() || s.isBlank()){
                throw new InvalidDateException("Enter a valid Age?");
            }
            int  number = Integer.parseInt(s);
            if(number < 0 || number > 120 ){
                throw new InvalidDateException("Age must be between 0 and 120.");
            }else{
                return true;
            }
        } catch (NumberFormatException e) {
            throw new InvalidDateException("Invalid Age!\n Only a positive number 0 and 120 is allowed! ");
        }

    }


    public static boolean isValidFodselsnummer(String s) {
        if (!isValidFodselsnummerRegex(s)) {
            throw new InvalidFodselsnummerException("Fodselsnummer is 11 sifir!");
        }
        return true;
    }
    public static boolean isValidFodselsnummerRegex(String navn) {
        String regex = "\\d{11}$";
        return navn.matches(regex);
    }
}
