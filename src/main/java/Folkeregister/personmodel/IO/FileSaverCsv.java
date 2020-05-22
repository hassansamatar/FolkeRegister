package Folkeregister.personmodel.IO;

import Folkeregister.personmodel.PersonRegister;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileSaverCsv implements FileSaver {
    @Override
    public void saveFile(PersonRegister savedItems, Path filePath) throws IOException {
        Files.write(filePath, savedItems.toString().getBytes());
    }
}
