package IMT3281;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

public class ReadFiles {
    private List<String>sentence;
    public ReadFiles(){}

    /**
     * @return returns list of sentences which is read from a given file
     * */
    public HashMap<String,List > readTextFile(File file) {
        HashMap<String, List> fileAndLinesMap = new HashMap<>();
        List allLines;
        try {
          allLines = Files.readAllLines(file.toPath());
            fileAndLinesMap.put(file.toPath().toFile().getName(), allLines);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileAndLinesMap;
    }

    public List<String> getSentence() {
        return sentence;
    }
}

