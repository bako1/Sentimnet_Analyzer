package IMT3281;

import java.util.ArrayList;

// Information needed by the FXML table.
public class Table {
    private String target;
    private String subject;
    private String polarity;
    private String sentence;

    public Table(String target, String polarity){
        this.target = target;
        this.polarity = polarity;
    }
    public Table(String target,String subject,String polarity){
        this.target = target;
        this.subject = subject;
        this.polarity = polarity;
    }

    public String getTarget() {
        return target;
    }

    public String getSubject() {
        return subject;
    }

    public String getPolarity() {
        return polarity;
    }

    public void setTarget(String fileName) {
        this.target = fileName;
    }
}
