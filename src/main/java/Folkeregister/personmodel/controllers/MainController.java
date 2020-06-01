package Folkeregister.personmodel.controllers;

import Folkeregister.personmodel.FileManager;
import Folkeregister.personmodel.FodselsnummerManager;
import Folkeregister.personmodel.Person;
import Folkeregister.personmodel.PersonRegister;
import Folkeregister.personmodel.exceptions.*;
import Folkeregister.personmodel.gui.Dialogs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class MainController {

    @FXML
    private TableView<Person> personTableView;
    @FXML
    TextField txtSearch;
    @FXML
    ChoiceBox<String> filterChoice;
    private  final PersonRegister personRegister = new PersonRegister();

    public void initialize() {
        personTableView.setEditable(true);
        updatePersonList();
        personRegister.loadTestData();
        filterChoice.setValue("Name");
        FodselsnummerManager.updateIndividualNumbersTakenList();
    }
    Stage s;
    private void updatePersonList() {
        personRegister.attachTableView(personTableView);
    }
    public void helloClicked(ActionEvent event) {
    }

    public void openFileClicked(ActionEvent event) throws IOException {
        FileManager.openFileUser(s,personRegister);
        updatePersonList();
    }

    public void SaveFileCliked(ActionEvent event) throws IOException {
        try {
            FileManager.saveFileUser(s,personRegister);
        }catch (InvalidPersonException e){
            e.getCause();
        }

    }
    public void Register(ActionEvent event) throws IOException {
        //App.setRoot("register");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Folkeregister/register.fxml"));
            Parent p = fxmlLoader.load();
            RegisterController c = fxmlLoader.getController();
            Scene scene = new Scene(p);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Register Person");
            stage.setOnShown((action) -> {
                c.setModel(personRegister);
            });
            stage.show();

        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    public void Delete(ActionEvent event) {
        deletePerson();
    }
    /*
     * Deleting a row from the list.
     */
    private void deletePerson(){
        Person selectedPerson = personTableView.getSelectionModel().getSelectedItem();

        if( selectedPerson != null ) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            String s = "Are you sure to permanently delete this Person ?";
            alert.setContentText(s);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                personRegister.remove(selectedPerson);
            }

        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            String s = "Please select a row to delete ?";
            alert.setContentText(s);
            alert.showAndWait();
        }
    }
    /*
     * Update name
     */
    @FXML
    public void updateName(TableColumn.CellEditEvent<Person, String> cellEditEvent) {
        try{
            cellEditEvent.getRowValue().setName(cellEditEvent.getNewValue());
        }catch (InvalidNameException e){
            Dialogs.showErrorDialog(e.getMessage());
        }
        personTableView.refresh();
    }
    /*
     * Update email
     */
    @FXML
    public void updateEmail(TableColumn.CellEditEvent<Person,String> cellEditEvent) {
        try{
            cellEditEvent.getRowValue().setEmail(cellEditEvent.getNewValue());
        }catch (InvalidEmailException e){
            Dialogs.showErrorDialog(e.getMessage());
        }
        personTableView.refresh();
    }
    /*
     * Update phone
     */
    @FXML
    public void updatePhone(TableColumn.CellEditEvent<Person, String> cellEditEvent) {
        try{
            cellEditEvent.getRowValue().setPhone(cellEditEvent.getNewValue());
        }catch (InvalidTelephoneException e){
            Dialogs.showErrorDialog(e.getMessage());
        }
        personTableView.refresh();
    }
    /*
     * Update gender
     */
    @FXML
    public void updateGender(TableColumn.CellEditEvent<Person, String> cellEditEvent) {
        try{
            cellEditEvent.getRowValue().setGender(cellEditEvent.getNewValue());
        }catch (InvalidGenderException e){
            Dialogs.showErrorDialog(e.getMessage());
        }
        personTableView.refresh();

    }

    @FXML
    private void filterChoiceChanged() {
        filter();
    }

    @FXML
    private void searchTxtEntered() {
        filter();
    }

    private void filter() {
        if(txtSearch.getText().isBlank()) {
            updatePersonList();
            return;
        }

        ObservableList<Person> result = null;
        switch (filterChoice.getValue().toLowerCase()) {
            case "name": result =  personRegister.filterByName(txtSearch.getText()); break;
            case "gender" : result =  personRegister.filterByGender(txtSearch.getText()); break;
            case "fodselsnummer" : result =  personRegister.filterByFodselsnummer(txtSearch.getText()); break;
            case "epost" : result =  personRegister.filterByEmail(txtSearch.getText()); break;
            case "phone" : result =  personRegister.filterByPhone(txtSearch.getText());
        }

        if(result == null) {
            personTableView.setItems(FXCollections.observableArrayList());
        } else {
            personTableView.setItems(result);
        }
    }
}
