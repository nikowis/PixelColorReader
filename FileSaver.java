
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileSaver {
    private PrintWriter file;
    
    public FileSaver(String fileName){
        try {
            file = new PrintWriter(fileName+".txt", "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(FileSaver.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileSaver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void writeToFile(String[] text){
        for(int i =0; i<text.length;i++)
        	file.println(text[i]);
        file.close();
    }
}