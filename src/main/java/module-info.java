module de.hhn.ai.pmt.gruppeb.ui {
    requires javafx.controls;
    requires javafx.fxml;


    opens de.hhn.ai.pmt.gruppeb.ui to javafx.fxml;
    exports de.hhn.ai.pmt.gruppeb.ui;
}