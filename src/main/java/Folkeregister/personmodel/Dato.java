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
    //constructor
    public Dato(DateTimeFormatter dateFormater) {
        this.dateFormater = dateFormater;
    }
    @Override
    public boolean isValidDate(String innFodselsdato) {
        try {
            this.dateFormater.parse(innFodselsdato);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException("Ugyldig fødselsdato! ");
        }
        return true;
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
        fodselsdato = fodselsdatoFormater(innFodselsdato);
        calender.setTime(fodselsdato);
        int year = calender.get(Calendar.YEAR);
        if (year < 1900 || year > 2020) {
            throw new InvalidAgeException("fødselsår må være mellom 1900 og 2020!");
        }
        return year;
    } //end getYeat()

    public static int getMonth(String innFodselsdato) {
        fodselsdato = fodselsdatoFormater(innFodselsdato);
        calender.setTime(fodselsdato);
        int month = calender.get(Calendar.MONTH) + 1;
        return month;
    } // end getMonth()

    public static int getDay(String innFodselsdato) {
        fodselsdato = fodselsdatoFormater(innFodselsdato);
        calender.setTime(fodselsdato);
        int day = calender.get(Calendar.DATE);
        return day;
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