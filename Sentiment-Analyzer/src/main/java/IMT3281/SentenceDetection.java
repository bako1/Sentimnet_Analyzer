package IMT3281;


public class SentenceDetection {
private String fileName;
private String subject;
private String polarity;
private int occurrence;

   public SentenceDetection(){
       this.occurrence = 100;
        ReadFiles readFiles = new ReadFiles();
        String simple = "[.?!]";
        String[] splitString = null;


    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setPolarity(String polarity) {
        this.polarity = polarity;
    }

    public void setOccurrence(int occurrence) {
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
}