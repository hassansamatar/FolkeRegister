package Folkeregister.personmodel;
import java.util.Random;
import static java.lang.Integer.parseInt;
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

public class PersonNumberGenerator {
    /*
    * Receives date of birth and gender from user interface.
    * Returns Norwegian social security number.
     */
    public static String getPersonnummer(int day, int month, int year, String gender){
        String d =get2DigitDayOrMonth(day);
        String m = get2DigitDayOrMonth(month);
        String last2DigitsOfYear = get2DigitBirthYear(year);
        String individualNumber = generateIndividualNumber(year, gender);
        String controlNumber = getControllNumber();
        return (d + m + last2DigitsOfYear + individualNumber + controlNumber);
    }
    /*
    * If the day or the month or both is less then two digits (1,2,...9)
    *  return with a leading zero (01, 02,... 09).
     */
    public  static String get2DigitDayOrMonth(int n){
        int i =0;
        while(i < 10){
            i++;
            if(n == i) {
                return ("0" + n);
            }
        }

        return (""+n);
    }
    /*
    *Returns last two digits of the given year.
     */
    public  static String get2DigitBirthYear(int year){
        return (String.valueOf(year).substring(2));
    }
      /*
       * Returns individual number
       */
    public static String generateIndividualNumber(int year, String gender){
        String[] mArray ={"1","3","5","7","9"};
        String[] fArray ={"0","2","4","6","8"};
        Random r = new Random();
        if(year >= 2000){
            int upperBound = 99;
            int lowerBound = 50;
            int number = lowerBound + (int)(Math.random() * ((upperBound - lowerBound) + 1));
            if(gender.equals("m")){
                int genderDigit = r.nextInt(mArray.length);
                int m = parseInt(mArray[genderDigit]);
                return number+""+m;
            }else if( gender.equals("f")){
                int genderDigit = r.nextInt(fArray.length);
                int f = parseInt(fArray[genderDigit]);
                return number+""+f;

            }
        }else if( year> 1940 && year < 2000){
            int upperBound = 99;
            int lowerBound = 90;
            int number = lowerBound + (int)(Math.random() * ((upperBound - lowerBound) + 1));
            if( gender.equals("m")){
                int genderDigit = r.nextInt(mArray.length);
                int m = parseInt(mArray[genderDigit]);
                return number+""+m;
            }else if( gender.equals("f")){
                int genderDigit = r.nextInt(fArray.length);
                int f = parseInt(fArray[genderDigit]);
                return number+""+f;

            }
        }else if(year >= 1900 && year <= 1999){
            int upperBound = 99;
            //?????????
            int lowerBound = 00;
            int number = lowerBound + (int)(Math.random() * ((upperBound - lowerBound) + 1));
            if(gender.equals("m")){
                int genderDigit = r.nextInt(mArray.length);
                int m = parseInt(mArray[genderDigit]);
                return number+""+m;
            }else if( gender.equals("f")){
                int genderDigit = r.nextInt(fArray.length);
                int f = parseInt(fArray[genderDigit]);
                return number+""+f;

            }
        }

        return "000";
    }
      /*
      * Returns Control digits.
      * NB: This is not Optimized yet.
       */
    public static String getControllNumber(){
        int upperBound = 99;
        int lowerBound = 21;
        int number = lowerBound + (int)(Math.random() * ((upperBound - lowerBound) + 1));

        return ""+number;
    }


}
