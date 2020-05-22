package Folkeregister.personmodel.controllers;

import java.io.IOException;

import Folkeregister.App;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("main");
    }
}
