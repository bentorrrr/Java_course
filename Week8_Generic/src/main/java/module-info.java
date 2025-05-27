module com.example.week8_generic {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.week8_generic to javafx.fxml;
    exports com.example.week8_generic;
}