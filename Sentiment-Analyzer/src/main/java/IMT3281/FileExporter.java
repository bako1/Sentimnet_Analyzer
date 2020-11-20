

package IMT3281;

public class FileExporter {
    private String target;
    private String polarity;
    private String subject;
    Analyzer analyzer;
    public FileExporter(){
        analyzer = new Analyzer();


    }

    public void setTarget(String target) {
        this.target = target;
    }

    public void setPolarity(String polarity) {
        this.polarity = polarity;
    }

    public void setSubject(String subject) {
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
    //public
}


