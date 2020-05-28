package Folkeregister.personmodel.IO;

import Folkeregister.personmodel.Person;
import Folkeregister.personmodel.PersonRegister;
import Folkeregister.personmodel.PersonValidator;
import Folkeregister.personmodel.exceptions.InvalidDateException;
import Folkeregister.personmodel.exceptions.InvalidEmailException;
import Folkeregister.personmodel.exceptions.InvalidFodselsnummerException;
import Folkeregister.personmodel.exceptions.InvalidPersonException;
import Folkeregister.personmodel.gui.Dialogs;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import static Folkeregister.personmodel.IO.FileValidator.*;
public class FileOpenerCsv implements FileOpener {
    @Override
    public void openFile(PersonRegister personRegister, Path filePath) {
        ArrayList<Person> peopleList = new ArrayList<>();
        try (BufferedReader bufferedReader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                peopleList.add(parseData(line));
            }
            if( peopleList.size() > 0) {
                 personRegister.removeAll();
                for (Person onePerson : peopleList) {
                    personRegister.add(onePerson);
                }
                Dialogs.showSuccessDialog("Your file is successfully loaded");
            }else {
                throw new InvalidPersonException("No content in this file!");
            }

        } catch (InvalidDateException | InvalidPersonException | InvalidFodselsnummerException e) {
            Dialogs.showErrorDialog("A value was changed: " + e.getMessage());
        } catch (Exception e){
             Dialogs.showErrorDialog("Some error occured while openning file: " + e.getMessage());
        }

    }
    public Person parseData(String data) {
        String[] split = data.split(",");
        if (split.length != 9) {
            System.out.println("Error");
        }
        String name = split[0];
        int age = FileValidator.parseAge(split[1]);
        int  day = FileValidator.parseDay(split[2]);
        int month = FileValidator.parseMonth(split[3]);
        int year = FileValidator.parseYear(split[4]);
        String gender = split[5];
        String fodselsnummer =split[6];
        String email = split[7];
        String phone = split[8];
        try {
            PersonValidator.isValidNavn(name);
            PersonValidator.isValidEpost(email);
            PersonValidator.isValidGender(gender);
            FileValidator.isValidFodselsnummer(split[6]);
            PersonValidator.isValidTelefon(phone);
            FileValidator.isValidAge(split[1]);
            FileValidator.isValidDay(split[2]);
            FileValidator.isValidMonth(split[3]);
            FileValidator.isValidYear(split[4]);

            return new Person(name,age, day, month, year,gender,fodselsnummer,email,phone);
        }catch(InvalidDateException |InvalidPersonException e){
            Dialogs.showErrorDialog(e.getMessage());
        }
        return null;

    }


}

