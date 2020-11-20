package IMT3281;

public class FileExporter {
    private String target;
    private String polarity;
    private String subject;

    public FileExporter(){

    }

    public FileExporter(String target, String subject, String polarity) {
        this.target = target;
        this.polarity = polarity;
        this.subject = subject;
    }

    public String getTarget() {
        return target;
    }
    public String getPolarity() {
        return polarity;
    }

    public String getSubject() {
        return subject;
    }

}