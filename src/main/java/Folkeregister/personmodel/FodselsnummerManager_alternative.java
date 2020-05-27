package Folkeregister.personmodel;

import java.util.ArrayList;
/*
 *Structure of the national identity number.
 *The national identity number consists of 11 digits.
 *The first six digits represent the date of birth in the order date, month, year.
 *The next three digits are an individual number, the third digit of which indicates gender – even numbers for women and odd numbers for men.
 *The last two digits are control digits.
 *The last five digits of the national identity number constitute the personal number.
 *The three digits comprising the individual number are allocated sequentially within the specific date of birth. Individual digits are allocated as follows:
 *born 1900-1999: allocated from series 499-000.
 *born 1940-1999: also allocated from series 999-900.
 *born 2000-2039: allocated from series 999-500.
 */

public class FodselsnummerManager_alternative {
    /*
     * Taken individual numbers on daily bases.
     */
    private static ArrayList<Integer> dailyList = new ArrayList<>();

    /*
     * Receives date of birth and gender from user interface.
     * Returns Norwegian social security number.
     */
    public static String getPersonnummer(int day, int month, int year, String gender) {
        String d = get2DigitDayOrMonth(day);
        String m = get2DigitDayOrMonth(month);
        String last2DigitsOfYear = get2DigitBirthYear(year);
        int individualNumber = individualNumber(year, gender);
        String controlNumber = getControllNumber(day, month, year, gender);
        System.out.println(dailyList);
        return (d + m + last2DigitsOfYear + individualNumber + controlNumber);
    }

 /*   If the day or the month or both is less then two digits (1,2,...9)
    return with a leading zero (01, 02,... 09).*/

    public static String get2DigitDayOrMonth(int n) {
        int i = 0;
        while (i < 10) {
            i++;
            if (n == i) {
                return ("0" + n);
            }
        }

        return ("" + n);
    }

    //Returns last two digits of the given year.


    public static String get2DigitBirthYear(int year) {
        return (String.valueOf(year).substring(2));
    }

    /*
      Returns available individual number on the registration date.
     century, and gender will be assessed.*/

    public static int individualNumber(int year, String gender) {
        if (year >= 2000) {
            int i = 999;
            while (i >= 500) {
                i--;
                if (gender.equals("m")) {
                    if (i % 2 != 0 && !dailyList.contains(i)) {
                        dailyList.add(i);
                        return i;
                    }
                } else if (gender.equals("f")) {
                    if (i % 2 == 0 && !dailyList.contains(i)) {
                        dailyList.add(i);
                        return i;
                    }
                }
            }
        } else if (year > 1940 && year < 1999) {
            int i = 999;
            while (i > 900) {
                i--;
                if (gender.equals("m")) {
                    if (i % 2 != 0 && !dailyList.contains(i)) {
                        dailyList.add(i);
                        return i;
                    }
                } else if (gender.equals("f")) {
                    if (i % 2 == 0 && !dailyList.contains(i)) {
                        dailyList.add(i);
                        return i;
                    }
                }
            }
        } else if (year < 1940) {
            int i = 499;
            while (i > 000) {
                i--;
                if (gender.equals("m")) {
                    if (i % 2 != 0 && !dailyList.contains(i)) {
                        dailyList.add(i);
                        return i;
                    }
                } else if (gender.equals("f")) {
                    if (i % 2 == 0 && !dailyList.contains(i)) {
                        dailyList.add(i);
                        return i;

                    }
                }

            }

        }

        return 555;
    }


      /*Returns Control digits.
      NB: This is not Optimized yet.*/

    public static String getControllNumber(int day, int month, int year, String gender) {
        int upperBound = 99;
        int lowerBound = 21;
        int number = lowerBound + (int) (Math.random() * ((upperBound - lowerBound) + 1));

        return "" + number;
    }


}
