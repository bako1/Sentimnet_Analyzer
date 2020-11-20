package IMT3281;


import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;

import java.io.*;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;

import java.util.HashMap;
import java.util.List;


public class ReadFiles {

    public ReadFiles() {
    }

    // Logic to get raw string from .txt files
    public int readTextFile(File file, HashMap<String, String> map) {
        try {
            //reads all lines in the file
            String contents = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
            map.put(file.toPath().toFile().getName(), contents);
        } catch (IOException e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }

    // Logic to get raw string from .doc files
    public int readDocsFile(File file, HashMap<String, String> map) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file.getAbsolutePath());

            HWPFDocument doc = new HWPFDocument(fileInputStream);

            WordExtractor wordExtractor = new WordExtractor(doc);

            map.put(file.getName(), wordExtractor.getText());
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }

    // Logic to get raw string from .csv files
    public int readCSVFile(File file, HashMap<String, String> map) {
        String text = "";
        try {
            // Create object of filereader
            // class with csv file as parameter.
            FileReader filereader = new FileReader(file);

            // create csvParser object
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
            map.put(file.getName(), text);
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }

    /**
     *
     * @param files List of files to read
     * @return HashMap where the keys are the file names and the value of each key is the file contents.
     */
    public HashMap<String, String> readFiles(List<File> files) {
        HashMap<String, String> fileAndLines = new HashMap<String, String>();

        for (File file : files) {
            //Check whether file is selected
            if (file != null) {
                if (file.getPath().endsWith(".txt")) {
                    readTextFile(file, fileAndLines);
                }
                else if (file.getPath().endsWith(".doc")) {
                    readDocsFile(file, fileAndLines);
                }
                else if (file.getPath().endsWith(".csv")) {
                    readCSVFile(file, fileAndLines);
                }
            }
        }
        return fileAndLines;
    }
}