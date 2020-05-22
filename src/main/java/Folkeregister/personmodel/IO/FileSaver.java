package Folkeregister.personmodel.IO;
import Folkeregister.personmodel.PersonRegister;
import java.io.IOException;
import java.nio.file.Path;

public interface FileSaver {
    void saveFile(PersonRegister savedItems, Path filePath) throws IOException, ClassNotFoundException;

}
