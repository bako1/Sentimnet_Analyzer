module IMT3281 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.opennlp.tools;


    opens IMT3281 to javafx.fxml;
    exports IMT3281;
}