module com.example.as03_network_fish_tank {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.google.gson;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.as03_network_fish_tank to javafx.fxml;
    exports com.example.as03_network_fish_tank;
    exports com.example.as03_network_fish_tank.Common;
    exports com.example.as03_network_fish_tank.Client;
    opens com.example.as03_network_fish_tank.Common to javafx.fxml;
}