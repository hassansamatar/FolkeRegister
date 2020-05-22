package Folkeregister.personmodel;
import Folkeregister.personmodel.gui.Dialogs;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import  Folkeregister.personmodel.IO.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileManager {
     public   static void saveFileUser(Stage stage, PersonRegister data)  {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a file format");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Text Files", "*.csv"));
        File selectedFile = fileChooser.showSaveDialog(stage);
        if (selectedFile == null) {
            return;
        }
        fileChooser.setInitialDirectory(selectedFile.getParentFile());
        FileSaver saver = null;
        if (getFileExt(selectedFile).endsWith(".csv")) {
            saver = new FileSaverCsv();
        } else {
            System.out.println("hello hello");
        }
        if (saver != null) {
            try {

                saver.saveFile(data, selectedFile.toPath());
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("failed");            }
        }
    }

   public static void openFileUser(Stage stage, PersonRegister data) throws IOException {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose a file");
            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("Texf Files", "*.csv"));
            File selectedFile = fileChooser.showOpenDialog(stage);
            if (selectedFile == null) {
                return;
            }
            fileChooser.setInitialDirectory(selectedFile.getParentFile());
            FileOpener opener = null;
            if (getFileExt(selectedFile).endsWith(".csv")) {
                opener = new FileOpenerCsv();
            } else {
                Dialogs.showErrorDialog("Only .csv files can be read from standard user");
            }
            if (opener != null) {
                try {
                    opener.openFile(data, selectedFile.toPath());
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println("failed opener user");

                }
            }
        }

      public   static void saveFileAdmin(Stage stage, PersonRegister data) throws IOException {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose file");
            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("Jobj Files", "*.jobj"));
            File selectedFile = fileChooser.showSaveDialog(stage);
            if (selectedFile == null) {
                return;
            }
            FileSaver saver = null;
            if (getFileExt(selectedFile).endsWith(".jobj")) {
                List<Person> computerList = data.getRegister();

                if (saver != null) {
                    try {
                        saver.saveFile(data, selectedFile.toPath());

                    } catch (IOException | ClassNotFoundException e) {
                        Dialogs.showErrorDialog("Something went wrong, check format" + e.getMessage());
                    }
                }
            }
        }
     public   static void openFileAdmin(Stage stage, PersonRegister data) throws IOException {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Velg fil");
            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("Jobj Files", "*.jobj"));
            File selectedFile = fileChooser.showOpenDialog(stage);
            if (selectedFile == null) {
                return;
            }
            FileOpener opener = null;
            if (getFileExt(selectedFile).endsWith(".jobj")) {
                opener = new FileOpenerJobj();
            } else {
                Dialogs.showErrorDialog("Only .jobj files can be read from Admin");
            }
            if (opener != null) {
                try {
                    fileChooser.setInitialDirectory(selectedFile);
                    opener.openFile(data, selectedFile.toPath());
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println("hello");
                }
                }
            }

        private static String getFileExt(File file) {
            String fileName = file.getName();
            return fileName.substring(fileName.lastIndexOf('.'));
        }
   }
