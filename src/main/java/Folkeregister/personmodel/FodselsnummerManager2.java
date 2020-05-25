package Folkeregister.personmodel;
import Folkeregister.personmodel.exceptions.InvalidDateException;
import Folkeregister.personmodel.gui.Dialogs;
import java.util.ArrayList;
import java.util.Arrays;
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
public class FodselsnummerManager2 {
    /*
     * Taken individual numbers on daily bases.
     */
    private static ArrayList<Integer> usedIndividualNumersList= new ArrayList<>();
    /*
     * Receives date of birth and gender from user interface.
     * Returns Norwegian social security number.
     */
    // private static final int[] K1_WEIGHTS = new int[] { 2, 5, 4, 9, 8, 1, 6, 7, 3 };
    // private static final int[] K2_WEIGHTS = new int[] { 2, 3, 4, 5, 6, 7, 2, 3, 4, 5 };
    private static final int[] K1_WEIGHTS = new int[] { 3,7,6,1,8,9,4,5,2 };
    private static final int[] K2_WEIGHTS = new int[] { 5, 4, 3, 2,7,6,5,4,3,2 };
    private static  ArrayList<Integer> checkDigitProcessStore = new ArrayList<>();
    private static  ArrayList<Integer> checkDigitProcessStore2 = new ArrayList<>();
    /* Alternative two solution - Personal number
     * with real check digits when the weights K1, K2 are known.
     */
      public static void updateIndividualNumbersTakenList(){
          usedIndividualNumersList.add(500);
          usedIndividualNumersList.add(501);
                }
    public static String getPersonnummer(int day, int month, int year, String gender){
        String d =get2DigitDayOrMonth(day);
        String m = get2DigitDayOrMonth(month);
        String yearDigit = get2DigitBirthYear(year);
        String individualNumber = ""+individualNumber(year,gender);
        //  String individualNumber = ""+651;  26 05 1997
        int digit_10 = getDigit_10(day,month,yearDigit,individualNumber);
        int  digit_11 =getDigit_11(day,month,yearDigit,individualNumber,digit_10);
        System.out.println(" List of individual numbers in use : "+usedIndividualNumersList);
        return (d+m+yearDigit+individualNumber+digit_10+""+digit_11);
    }

    public  static String get2DigitBirthYear(int year){
        return (
                String.valueOf(year).substring(2));
    }
    public  static String get2DigitDayOrMonth(int n){
        int[] array ={1,2,3,4,5,6,7,8,9};
        int i =0;
        while(i <array.length){
            i++;
            if(n == i) {
                return ("0" + n);
            }
        }

        return (""+n);
    }


    public static int  individualNumber(int year, String gender){
        if( year >=2000){
            int i = 999;
            while( i >= 500 ){
                i--;
                if(gender.equals("m")){
                    if( i % 2 != 0 && !usedIndividualNumersList.contains(i)) {
                        usedIndividualNumersList.add(i);
                        return i;
                    }
                }else if (gender.equals("f")){
                    if(i % 2 == 0 && !usedIndividualNumersList.contains(i)){
                        usedIndividualNumersList.add(i);
                        return i;

                    }
                }

            }
        }else if( year> 1940 && year < 2000){
            int i = 999;
            while( i > 900 ){
                i--;
                if(gender.equals("m")){
                    if( i % 2 != 0 && !usedIndividualNumersList.contains(i)) {
                        usedIndividualNumersList.add(i);
                        return i;
                    }
                }else if (gender.equals("f")){
                    if(i % 2 == 0 && !usedIndividualNumersList.contains(i)){
                        usedIndividualNumersList.add(i);
                        return i;

                    }
                }

            }

        }else if( year> 1900 && year <= 1940){
            int i = 499;
            while( i > 000 ){
                i--;
                if(gender.equals("m")){
                    if( i % 2 != 0 && !usedIndividualNumersList.contains(i)) {
                        usedIndividualNumersList.add(i);
                        return i;
                    }
                }else if (gender.equals("f")){
                    if(i % 2 == 0 && !usedIndividualNumersList.contains(i)){
                        usedIndividualNumersList.add(i);
                        return i;

                    }
                }

            }

        }

        return 555;
    }

    // testing check digits

    public static int getDigit_10(int dDigit, int mDigit, String yDigit, String individDigit){
        checkDigitProcessStore.clear();
        int d =dDigit;
        String dd= String.valueOf(d);
        for(int i =0; i < dd.length(); i++){
            if(d < 10){
                checkDigitProcessStore.add(0);
                checkDigitProcessStore.add(d);
            }else {
                int b = Character.digit(dd.charAt(i), 10);
                checkDigitProcessStore.add(b);
            }

        }

        int m =mDigit;
        String mm= String.valueOf(m);
        for(int n =0; n < mm.length(); n++){
            if( m < 10 ){
                checkDigitProcessStore.add(0);
                checkDigitProcessStore.add(m);
            }else {
                int x = Character.digit(mm.charAt(n), 10);
                checkDigitProcessStore.add(x);
            }

        }
        int y =Integer.parseInt(yDigit);
        String yy= String.valueOf(y);
        for(int e =0; e < yy.length(); e++){
            if( y < 10){
                checkDigitProcessStore.add(0);
                checkDigitProcessStore.add(y);
            }else {
                int z = Character.digit(yy.charAt(e), 10);
                checkDigitProcessStore.add(z);
            }


        }

        int ii =Integer.parseInt(individDigit);
        String iii= String.valueOf(ii);
        for(int i =0; i < iii.length(); i++){
            int z = Character.digit(iii.charAt(i), 10);
            checkDigitProcessStore.add(z);
        }
        System.out.println("Fodselsnummer last two Check digts is constructed as flows:- ");
        System.out.println("Date of birth + Individual number multiply by  K1_WEIGHTS");
        System.out.println(checkDigitProcessStore);
        System.out.println(Arrays.toString(K1_WEIGHTS));
        int digit_10 =  produceDigit_10(K1_WEIGHTS,checkDigitProcessStore);
        return digit_10;
    }
    private static int produceDigit_10(int[] k1, ArrayList<Integer> checkOne){
        String result ="";
        int sum = 0;
        for(int i = 0; i < k1.length; i++){
            int k =k1[i];
            int c = checkOne.get(i);
            result += Integer.toString(k * c) + " ";
            sum+=(k*c);
        }
        System.out.println(result);
        System.out.println("sum :"+sum);
        int dividedBy_11 = sum / 11;
        System.out.println("modulo_11: "+dividedBy_11);
        int reminder = sum-(dividedBy_11*11);
        System.out.println( "Remainder: "+reminder);
        int digit_10;
        try {
            if(reminder == 0){
                digit_10 = 0;
                return digit_10;
            }else if(reminder == 1){
                /* if the remainder becomes 1, the personal number would be incorrect
                 * and next valid individual number is recalculated, but for simplicity reason
                 * we sett  digit_10 to 3 ( e.i 11-8 =3).
                 */
                digit_10 = 11 - 8;
                System.out.println("Digit 10: "+digit_10);
                return digit_10;
               // Dialogs.showErrorDialog("Incorrect Personal number! \n Make sure you if you have registered Correct info?");
                //throw new InvalidDateException("");
            }else {
                digit_10 = 11 - reminder;
                System.out.println("Digit 10: "+digit_10);
                return digit_10;
            }

        }catch (InvalidDateException e){
            e.getMessage();
        }

        return 00;
    }

    // Digit 11
    public static int getDigit_11(int dDigit, int mDigit, String yDigit, String individDigit, int digit_10){
        checkDigitProcessStore2.clear();
        int d =dDigit;
        String dd= String.valueOf(d);
        for(int i =0; i < dd.length(); i++){
            if(d < 10){
                checkDigitProcessStore2.add(0);
                checkDigitProcessStore2.add(d);
            }else {
                int b = Character.digit(dd.charAt(i), 10);
                checkDigitProcessStore2.add(b);
            }

        }

        int m =mDigit;
        String mm= String.valueOf(m);
        for(int n =0; n < mm.length(); n++){
            if( m < 10 ){
                checkDigitProcessStore2.add(0);
                checkDigitProcessStore2.add(m);
            }else {
                int x = Character.digit(mm.charAt(n), 10);
                checkDigitProcessStore2.add(x);
            }

        }
        int y =Integer.parseInt(yDigit);
        String yy= String.valueOf(y);
        for(int e =0; e < yy.length(); e++){
            if( y < 10){
                checkDigitProcessStore2.add(0);
                checkDigitProcessStore2.add(y);
            }else {
                int z = Character.digit(yy.charAt(e), 10);
                checkDigitProcessStore2.add(z);
            }

        }

        int ii =Integer.parseInt(individDigit);
        String iii= String.valueOf(ii);
        for(int i =0; i < iii.length(); i++){
            int z = Character.digit(iii.charAt(i), 10);
            checkDigitProcessStore2.add(z);
        }
        checkDigitProcessStore2.add(digit_10);
        System.out.println("First 10 digits of fodselsnummer multiply by  K2_WEIGHTS:");
        System.out.println(checkDigitProcessStore2);
        System.out.println(Arrays.toString(K2_WEIGHTS));
        int digit_11= produceDigit_11(K2_WEIGHTS,checkDigitProcessStore2);
        return digit_11;

    }
    private static int produceDigit_11(int[] k2, ArrayList<Integer> checkTwo){
        String result ="";
        int sum = 0;
        for(int i = 0; i < k2.length; i++){
            int k =k2[i];
            int c = checkTwo.get(i);
            result += Integer.toString(k * c) + " ";
            sum+=(k*c);
        }

        System.out.println(result);
        System.out.println("Sum: "+sum);
        int dividedBy_11 = sum / 11;
        System.out.println("modulo_11: "+dividedBy_11);
        int reminder = sum-(dividedBy_11*11);
        System.out.println("Remainder: "+reminder);
        int digit_11;
        try {
            if(reminder == 0){
                digit_11 = 1;
                return digit_11;
            }else if(reminder == 1){
                /* if the remainder becomes 1, the personal number would be incorrect
                 * and next valid individual number is recalculated, but for simplicity reason
                 * we sett  digit_11 to 1 ( e.i 11-10 =1).
                 */
                digit_11 = 11 - 10;
                System.out.println("Digit 10: "+digit_11);
                return digit_11;
                // Dialogs.showErrorDialog("Incorrect Personal number! \n Make sure you if you have registered Correct info?");
                // throw new InvalidDateException("");
            }else {
                digit_11 = 11 - reminder;
                System.out.println("Digit 11: "+digit_11);
                return digit_11;
            }

        }catch (InvalidDateException e){
            e.getMessage();
        }
        return 00;
    }


}
