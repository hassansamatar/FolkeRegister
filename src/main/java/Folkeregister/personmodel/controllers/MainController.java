package Folkeregister.personmodel.controllers;

import Folkeregister.App;
import Folkeregister.personmodel.FileManager;
import Folkeregister.personmodel.Person;
import Folkeregister.personmodel.PersonRegister;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController  {

    @FXML
    private TableView<Person> personTableView;
    private  final PersonRegister personRegister = new PersonRegister();

    public void initialize() {

        personTableView.setEditable(true);
        updateComputerList();
        personRegister.loadTestData();
    }
    Stage s;
    private void updateComputerList() {
        personRegister.attachTableView(personTableView);
    }
    public void helloClicked(ActionEvent event) {
    }

    public void openFileClicked(ActionEvent event) {
    }

    public void SaveFileCliked(ActionEvent event) {
        FileManager.saveFileUser(s,personRegister);

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
            stage.setTitle("Register Computer");
            stage.setOnShown((action) -> {
                c.setModel(personRegister);
            });
            stage.show();

        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    public void Delete(ActionEvent event) {
    }
}
