package Folkeregister.personmodel.controllers;

import Folkeregister.App;
import Folkeregister.personmodel.*;
import Folkeregister.personmodel.exceptions.*;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RegisterController {
    private PersonRegister model;
    public TextField txtName,txtGender,txtEpost,txtTelefon,txtDateOfBirth;
    public Label lblNameError,lblGenderError,lblEpostError,lblTelefonError,lblDateError;

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.BASIC_ISO_DATE;
    Dato dato =  new Dato(dateTimeFormatter);

    public void registerPerson(ActionEvent event) {
        String name = txtName.getText();
        String gender = txtGender.getText();
        String epost = txtEpost.getText();
        String telefon = txtTelefon.getText();
        String dOfBirth = txtDateOfBirth.getText().replace(".","");
        try{
            PersonValidator.isValidNavn(name);
            PersonValidator.isValidGender(gender);
            dato.isValidDate(dOfBirth);
            PersonValidator.isValidEpost(epost);
            PersonValidator.isValidTelefon(telefon);
            int year = dato.getYear(dOfBirth);
            int month = dato.getMonth(dOfBirth);
            System.out.println("month: "+month);
            int alder = dato.getAlder(dOfBirth);
            int day = dato.getDay(dOfBirth);
            String fodselsnummer =PersonNumberGenerator.getPersonnummer(day,month,year,gender);
            System.out.println(fodselsnummer);
            Person person = new Person(name,alder,day,month,year,gender,fodselsnummer,epost,telefon);
            System.out.println(person.toString());
            model.add(person);
            hide(event);

        } catch (InvalidNameException e){

            lblNameError.setText(e.getMessage());
        }catch (InvalidGenderException e){
            lblGenderError.setText(e.getMessage());
        }
        catch (InvalidEmailException e){
            lblEpostError.setText(e.getMessage());
        }
        catch (InvalidTelephoneException e){
            lblTelefonError.setText(e.getMessage());
        }
        catch (InvalidAgeException e){
            lblDateError.setText(e.getMessage());
        }
        catch (InvalidDateException e){
            lblDateError.setText(e.getMessage());
        }


    }

    public void cancel(ActionEvent event) throws IOException {

        //App.setRoot("main");
    hide(event);
    }

    private void hide(ActionEvent event) {
        ((Button)event.getSource()).getScene().getWindow().hide();
    }
    public void setModel(PersonRegister model){
        this.model = model;
    }





}
