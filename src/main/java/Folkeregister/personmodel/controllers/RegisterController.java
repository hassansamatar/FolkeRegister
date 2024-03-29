package Folkeregister.personmodel.controllers;
import Folkeregister.personmodel.*;
import Folkeregister.personmodel.IO.FileValidator;
import Folkeregister.personmodel.exceptions.*;
import Folkeregister.personmodel.gui.Dialogs;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
public class RegisterController {
    private PersonRegister model;
    public TextField txtName, txtGender, txtEpost, txtPhone, txtDay, txtMonth,txtYear;
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.BASIC_ISO_DATE;
    Dato dato = new Dato(dateTimeFormatter);
    public void registerPerson(ActionEvent event) {
        String name = txtName.getText();
        String gender = txtGender.getText().toLowerCase();
        String epost = txtEpost.getText();
        String telefon = txtPhone.getText();
        String dOfBirth = txtYear.getText()+txtMonth.getText()+txtDay.getText();
        try {
            PersonValidator.isValidNavn(name);
            PersonValidator.isValidGender(gender);
            dato.isValidDate(dOfBirth);
            PersonValidator.isValidEpost(epost);
            PersonValidator.isValidTelefon(telefon);
            int year = dato.getYear(dOfBirth);
            int month = dato.getMonth(dOfBirth);
            int alder = dato.getAlder(dOfBirth);
            int day = dato.getDay(dOfBirth);
            String fodselsnummer = FodselsnummerManager.getPersonnummer(day, month, year, gender);
            System.out.println("Fodselsnummer: " + fodselsnummer);
            Person person = new Person(name, alder, day, month, year, gender, fodselsnummer, epost, telefon);
            System.out.println(person.toString());
            model.add(person);
            hide(event);
        } catch (InvalidNameException | InvalidGenderException | InvalidDateException | InvalidAgeException |
                InvalidEmailException | InvalidTelephoneException e) {
            Dialogs.showErrorDialog(e.getMessage());
        }

    }

    public void cancel(ActionEvent event) throws IOException {
        //App.setRoot("main");
        hide(event);
    }
    private void hide(ActionEvent event) {
        ((Button) event.getSource()).getScene().getWindow().hide();
    }
    public void setModel(PersonRegister model) {
        this.model = model;
    }


}
