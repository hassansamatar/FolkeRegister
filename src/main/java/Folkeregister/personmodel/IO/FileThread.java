package Folkeregister.personmodel.IO;
import javafx.concurrent.Task;
public class FileThread extends Task<String>{
    @Override
    protected String call() throws Exception {
        String threadMessage = "Thread running ......";
        try {
            Thread.sleep(3000);
        }catch (InterruptedException e){
            throw new Exception("Something went wrong!");
        }
        return threadMessage;
    }




}