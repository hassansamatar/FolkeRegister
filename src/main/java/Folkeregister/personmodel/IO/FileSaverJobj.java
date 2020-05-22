package Folkeregister.personmodel.IO;
import Folkeregister.personmodel.PersonRegister;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileSaverJobj implements FileSaver {
    @Override
    public void saveFile(PersonRegister savedItems, Path filePath) throws IOException {
       try (OutputStream os = Files.newOutputStream(filePath);
            ObjectOutputStream out = new ObjectOutputStream(os)) {
             out.writeObject(savedItems);
        }catch (Exception e){
           e.getMessage();
          // Dialogs.showErrorDialog("Tastatur, Mus, Skjerm must be filled inn to save .jobj file");
       }


    }
}
