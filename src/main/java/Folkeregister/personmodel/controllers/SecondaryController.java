package Folkeregister.personmodel.controllers;

import java.io.IOException;

import Folkeregister.App;
import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}