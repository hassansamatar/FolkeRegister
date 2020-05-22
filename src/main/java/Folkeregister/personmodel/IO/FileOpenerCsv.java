package Folkeregister.personmodel.IO;

import Folkeregister.personmodel.PersonRegister;
import java.nio.file.Path;
import java.util.ArrayList;
public class FileOpenerCsv implements FileOpener {
    @Override
    public void openFile(PersonRegister personRegister, Path filePath) {

       /* ArrayList<Person> computerList = new ArrayList<>();
        try (BufferedReader bufferedReader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                computerList.add(parseData(line));
            }
            if( computerList.size() > 0) {
                computer.removeAll(computer);
                for (Computer oneComputer : computerList) {
                    computer.add(oneComputer);
                }
                Dialogs.showSuccessDialog("Your file is successfully loaded");
            }
            else {
                throw new InvalidComponentException("No content in this file!");
            }

        } catch (InvalidComponentException | InvalidCommanException e) {
            Dialogs.showErrorDialog("A value was changed: " + e.getMessage());
        } catch (Exception e){
           // Dialogs.showErrorDialog("Some error occured while openning file: " + e.getMessage());
        }

    }
    public Computer parseData(String data) {
                    String[] split = data.split(",");
                    if (split.length != 10) {
                        System.out.println("Error");
                    }
                    String type = split[0];
                    String computername = split[1];
                    String prosessor = split[2];
                    String skjermkort = split[3];
                    String minne = split[4];
                    String harddisk = split[5];
                    String tastatur = split[6];
                    String mus = split[7];
                    String skjerm = split[8];
                    double price = parsePris(split[9], "Price should be a number");
                    try {
                          if(Validator.isValidComponent(type,computername,prosessor, skjermkort, minne,
                                  harddisk,tastatur,mus,skjerm) && Validator.isValidTotalPrice(split[9])){
                              return new Computer(type,computername, prosessor, skjermkort, minne, harddisk, tastatur, mus, skjerm, price);
                          };

                     }catch(InvalidCommanException e){
                        Dialogs.showErrorDialog(e.getMessage());
                    }
                    return null;
*/
          }

    /*private double parsePris(String s, String errorMessage) throws IllegalArgumentException {
                double number = 0;
                try {
                    if (Validator.isValidTotalPrice(s)) {
                        number = Double.parseDouble(s);
                    }
                } catch (InvalidPriceExection e) {
                    Dialogs.showErrorDialog("A value was changed: " + e.getMessage());
                }
                return number;
            }*/
}

