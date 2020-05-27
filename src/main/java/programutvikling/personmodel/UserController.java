package programutvikling.personmodel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
public class UserController {
    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField passwordField;

    /*
     * Admin login
     */
    @FXML
    void Login(ActionEvent event) throws IOException {
        String inUser;
        inUser = txtUser.getText();
        String pass = passwordField.getText();

        try {
            if (inUser.equals("admin") && pass.equals("admin")) {
                Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/computerconfiguration/AdminUser.fxml"));
                Scene scene = new Scene(fxmlLoader);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();

            } else {
               /* Dialogs.showErrorDialog("Invalid Credentials!! " +
                        "\n Username:  admin"+
                        "\n"+"Password:  admin" );*/
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /*
     * cancel admin login
     */
    public void cancelLogin(ActionEvent event) throws IOException {
        close(event);
    }

    /*
     * Go back to standard user after closing admin page
     */
    private void close(ActionEvent event) throws IOException {
        try {
            Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/programutvikling/main.fxml"));
            Scene scene = new Scene(fxmlLoader);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
