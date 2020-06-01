package Folkeregister.personmodel;

import Folkeregister.personmodel.exceptions.InvalidAgeException;
import Folkeregister.personmodel.exceptions.InvalidDateException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;

public class Dato implements DateValidator {
    //Static variable
    private static DateTimeFormatter dateFormater;
    private static Date fodselsdato;
    private static Calendar calender = Calendar.getInstance();
    private static int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    private static int  nextMonth = Calendar.getInstance().get(Calendar.MONTH)+2;
    private static  int today =Calendar.getInstance().get(Calendar.DATE);
    //constructor
    public Dato(DateTimeFormatter dateFormater) {
        this.dateFormater = dateFormater;
    }
    @Override
    public boolean isValidDate(String innFodselsdato) {
        try {
            this.dateFormater.parse(innFodselsdato);
            return true;
        } catch (DateTimeParseException e) {
            throw new InvalidDateException("Invalid Date of Birth! " +
                    "\nEnter a real and a valid date in the form of: dd mm yyyy."
            );
        }
    } //end isValidDate()

    public boolean isInValidDate(String innFodselsdato) {
        try {
            this.dateFormater.parse(innFodselsdato);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }

    } //end isValidDate()

    public static Date fodselsdatoFormater(String innFodselsdato) {
        SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
        try {
            fodselsdato = formater.parse(innFodselsdato);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return fodselsdato;

    } //end fodselsdatoformater

    public static int getYear(String innFodselsdato) {
        int BirthYear;

        fodselsdato = fodselsdatoFormater(innFodselsdato);
        calender.setTime(fodselsdato);
        BirthYear = calender.get(Calendar.YEAR);
        if (BirthYear < 1900 || BirthYear> currentYear) {
            throw new InvalidAgeException("Valid birth year: from 1900 to the current year!");
        }
        return BirthYear;
    } //end getYeat()

    public static int getMonth(String innFodselsdato) {
        int birthYear = getYear(innFodselsdato);
        fodselsdato = fodselsdatoFormater(innFodselsdato);
        calender.setTime(fodselsdato);
        int birthMonth = calender.get(Calendar.MONTH) + 1;
        if(birthMonth >= nextMonth && birthYear >= currentYear){
            throw new InvalidAgeException("You can't be born in the feature! \n" +
                    "A valid month could be either this month or in the past.");
        }
        return birthMonth;
    } // end getMonth()

    public static int getDay(String innFodselsdato) {
        fodselsdato = fodselsdatoFormater(innFodselsdato);
        calender.setTime(fodselsdato);
        int birthDay= calender.get(Calendar.DATE);
        int birthMonth = getMonth(innFodselsdato);
        int birthYear = getYear(innFodselsdato);
        /*
         * if birth month and current month are same,
         * day of birth should be either current day or a day in the past.
         */
        if( birthDay > today && birthMonth == nextMonth -1  && birthYear >= currentYear) {
            throw new InvalidAgeException("You can't be born in the feature! \n" +
                    "A valid birth day is either today or a day in the past.");
        }

        return birthDay;
    } // end getDay()

    public static int getAlder(String innFodselsdato) {
        LocalDate dinFodselsdato = LocalDate.of(
                getYear(innFodselsdato),
                getMonth(innFodselsdato),
                getDay(innFodselsdato)
        );

        Period difference = Period.between(dinFodselsdato, LocalDate.now());
        return difference.getYears();
    } // getAlder()



}