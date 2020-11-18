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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadFiles {
    private List<String> sentence;

    public ReadFiles() {
    }

    /**
     * @return returns HashMap with fileName being key, and lists of sentences as value.
     */
    public HashMap<String, List> readTextFile(File file) {
        HashMap<String, List> fileAndLinesMap = new HashMap<>();
        List allLines;
        try {
            //reads all lines in the file
            allLines = Files.readAllLines(file.toPath());
            fileAndLinesMap.put(file.toPath().toFile().getName(), allLines);
        } catch (IOException e) {
            e.printStackTrace();

        }
        return fileAndLinesMap;
    }

    public HashMap<String, List> readDocsFile(File file) {
        HashMap<String, List> allLinesAndFileMap = new HashMap<>();
        List<String> linesList = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(file.getAbsolutePath());

            HWPFDocument doc = new HWPFDocument(fileInputStream);

            WordExtractor wordExtractor = new WordExtractor(doc);

            String[] paragraphs = wordExtractor.getParagraphText();

            for (String paragraph : paragraphs) {
                linesList.add(paragraph);
                allLinesAndFileMap.put(file.getName(), linesList);
            }
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allLinesAndFileMap;
    }

    public HashMap<String, List> readCSVFile(File file) {
        HashMap<String, List> csvFileAndLines = new HashMap<>();
        List<String> linesList = new ArrayList<>();
        try {
            // Create object of filereader
            // class with csv file as parameter.
            FileReader filereader = new FileReader(file);

            // create csvParser object with
            // custom seperator semi-colon
            CSVParser parser = new CSVParserBuilder().withSeparator(';').build();

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
                    linesList.add(cell);
                    csvFileAndLines.put(file.getName(), linesList);
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return csvFileAndLines;
    }

    public List<String> getSentence() {
        return sentence;
    }

    public HashMap<String, List> readFiles(List<File> file) {
        HashMap<String, List> fileAndLines = null;

        for (File files : file) {
            //Check whether file is selected
            if (file != null) {

                if (files.getAbsolutePath().endsWith(".csv")) {
                    fileAndLines = readCSVFile(files);

                } else if (files.getPath().endsWith(".txt")) {
                    fileAndLines = readTextFile(files);
                } else if (files.getPath().endsWith(".doc")) {
                    fileAndLines = readDocsFile(files);

                }
            }
        }
        return fileAndLines;
    }
}

