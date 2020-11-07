module IMT3281 {
    requires javafx.controls;
    requires javafx.fxml;

    opens IMT3281 to javafx.fxml;
    exports IMT3281;
}