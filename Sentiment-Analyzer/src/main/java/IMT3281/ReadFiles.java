package IMT3281;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ReadFiles {
    private List<String>sentence;
    public ReadFiles(){}

    /**
     * @return returns list of sentences which is read from a given file
     * */
    public List<String> readTextFile(File file) {
        List<String > allLines = null;
        try {
          allLines = Files.readAllLines(Path.of(file.getAbsolutePath()));
            this.sentence = allLines;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allLines;
    }

    public List<String> getSentence() {
        return sentence;
    }
}

