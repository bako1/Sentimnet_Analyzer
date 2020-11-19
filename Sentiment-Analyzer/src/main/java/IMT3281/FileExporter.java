package IMT3281;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class FileExporter {
    private String sentence;
    private String polarity;
    private String subject;
    private File file;
    public FileExporter(String sentence,String subject,String polarity,File file){
        this.sentence = sentence;
        this.polarity = polarity;
        this.subject = subject;
        this.file = file;

    }
    public void writeXMLFile() throws FileNotFoundException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);


    }
    public void writeCSVFile(){


    }
}
