module com.example.week9_adt_save {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.week9_adt_save to javafx.fxml;
    exports com.example.week9_adt_save;
}