module com.example.week6_tictactoe {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.week6_tictactoe to javafx.fxml;  // <-- keep this
    exports com.example.week6_tictactoe;
}
