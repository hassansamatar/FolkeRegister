package Folkeregister.personmodel.IO;
import Folkeregister.personmodel.PersonRegister;
import Folkeregister.personmodel.gui.Dialogs;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import java.nio.file.Path;

public class FileOpenerJobj implements FileOpener {
    PersonRegister cm;
    @Override
    public void openFile(PersonRegister personRegister, Path filePath) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(filePath.toFile());
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            cm = (PersonRegister) objectInputStream.readObject();
            personRegister.removeAll();
            cm.getRegister().forEach(personRegister::add);
            Dialogs.showSuccessDialog("Your file is successfully loaded");
        } catch (Exception e) {
            Dialogs.showErrorDialog(e.getMessage());

        }

    }
}


