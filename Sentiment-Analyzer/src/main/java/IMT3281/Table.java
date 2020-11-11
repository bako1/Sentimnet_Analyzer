package IMT3281;

public class Table {
    private String fileName;
    private String subject;
    private String polarity;
    private int occurrence;

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
}
