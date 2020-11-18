package IMT3281;


import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import javafx.scene.control.Alert;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;

import java.io.*;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadFiles {

    public ReadFiles() {
    }

    /**
     * @return returns HashMap with fileName being key, and lists of sentences as value.
     */
    public HashMap<String, String> readTextFile(File file) {
        HashMap<String, String> fileAndLinesMap = new HashMap<>();
        try {
            //reads all lines in the file
            String contents = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
            System.out.println(contents);
            fileAndLinesMap.put(file.toPath().toFile().getName(), contents);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileAndLinesMap;
    }

    public HashMap<String, String> readDocsFile(File file) {
        HashMap<String, String> allLinesAndFileMap = new HashMap<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(file.getAbsolutePath());

            HWPFDocument doc = new HWPFDocument(fileInputStream);

            WordExtractor wordExtractor = new WordExtractor(doc);

            allLinesAndFileMap.put(file.getName(), wordExtractor.getText());
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allLinesAndFileMap;
    }

    public HashMap<String, String> readCSVFile(File file) {
        HashMap<String, String> csvFileAndLines = new HashMap<>();
        String text = "";
        try {
            // Create object of filereader
            // class with csv file as parameter.
            FileReader filereader = new FileReader(file);

            // create csvParser object with
            // custom seperator semi-colon
            CSVParser parser = new CSVParserBuilder().withSeparator(',').build();

            // create csvReader object with
            // parameter filereader and parser
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withCSVParser(parser)
                    .build();

            // Read all data at once
            List<String[]> allData = csvReader.readAll();

             // print Data
            for (String[] row : allData) {
                for (String cell : row) {
                    text += cell;
                    text += ". ";
                }
            }
            csvFileAndLines.put(file.getName(), text);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return csvFileAndLines;
    }
    

    public HashMap<String, String> readFiles(List<File> file) {
        HashMap<String, String> fileAndLines = null;

        for (File files : file) {
            //Check whether file is selected
            if (file != null) {
                if (files.getPath().endsWith(".txt")) {
                    fileAndLines = readTextFile(files);
                }
                else if (files.getPath().endsWith(".doc")) {
                    fileAndLines = readDocsFile(files);
                }
                else if (files.getPath().endsWith(".csv")) {
                    fileAndLines = readCSVFile(files);
                }
            }
        }
        return fileAndLines;
    }
}

