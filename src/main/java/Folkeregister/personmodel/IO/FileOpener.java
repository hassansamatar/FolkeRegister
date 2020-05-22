package Folkeregister.personmodel.IO;
import Folkeregister.personmodel.PersonRegister;
import java.io.IOException;
import java.nio.file.Path;

public interface FileOpener {
    void openFile(PersonRegister personRegister, Path filePath) throws IOException, ClassNotFoundException;

}
