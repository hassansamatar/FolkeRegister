package Folkeregister.personmodel.gui;
import javafx.scene.control.Alert;
import javafx.scene.text.Text;

public class Dialogs {
    public static void showErrorDialog(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(msg);
        alert.showAndWait();
    }
    public static void showSuccessDialog(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setContentText(msg);
        alert.showAndWait();
    }
    public static void showThreadDialog(String msg) {
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setContentText(msg);
           alert.show();

    }

}
