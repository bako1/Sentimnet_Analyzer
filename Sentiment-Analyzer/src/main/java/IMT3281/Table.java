package IMT3281;

import java.util.ArrayList;

public class Table {
    private String fileName ;
    private String subject;
    private String polarity;
    private int occurrence;
    private String sentence;
    private ArrayList<String> processedSentence;

    public ArrayList<String> getProcessedSentence() {
        return processedSentence;
    }

    public void setProcessedSentence(ArrayList<String> processedSentence) {
        this.processedSentence = processedSentence;
    }

    public Table(String fileName, String subject, String polarity, int occurrence) {

        this.fileName = fileName;
        this.subject = subject;
        this.polarity = polarity;
        this.occurrence = occurrence;

    }

    public String getFileName() {
        return fileName;
    }

    public String getSubject() {
        return subject;
    }

    public String getPolarity() {
        return polarity;
    }

    public int getOccurrence() {
        return occurrence;
    }


    public String getSentence() {
        return sentence;
    }


    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
