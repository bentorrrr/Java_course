package com.example.week10_chat_client_sever;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.*;
import java.net.Socket;

public class Main extends Application {
    private static final String HOST = "localhost";
    private static final int    PORT = 12345;

    private PrintWriter netOut;
    private BufferedReader netIn;
    private TextArea chatArea;
    private String nickname = "anonymous";

    public static void main(String[] args) { launch(); }

    @Override public void start(Stage stage) {
        askName();

        chatArea = new TextArea();
        chatArea.setEditable(false);
        chatArea.setWrapText(true);

        TextField input = new TextField();
        input.setPromptText("Type a message - press Enter to send");
        input.setOnAction(e -> {
            String msg = input.getText().trim();
            if (!msg.isEmpty()) {
                netOut.println(msg);
                if (msg.equalsIgnoreCase("/quit")) Platform.exit();
            }
            input.clear();
        });

        BorderPane root = new BorderPane(chatArea, null, null, input, null);
        BorderPane.setMargin(input, new Insets(4));

        stage.setTitle("JavaFX Chat – " + nickname);
        stage.setScene(new Scene(root, 500, 400));
        stage.show();

        new Thread(this::connect).start();
    }

    private void askName() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Your nickname");
        dialog.setHeaderText("Enter a nickname for this chat session:");
        dialog.setContentText("Name:");
        dialog.setGraphic(null);
        dialog.getEditor().setText("Alice");

        dialog.showAndWait().ifPresent(name -> nickname = name.trim());
        if (nickname.isEmpty()) nickname = "anonymous";
    }

    private void connect() {
        try (Socket socket = new Socket(HOST, PORT)) {
            netIn  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            netOut = new PrintWriter(socket.getOutputStream(), true);

            netOut.println(nickname);

            String line;
            while ((line = netIn.readLine()) != null) {
                String msg = line;
                Platform.runLater(() ->
                        chatArea.appendText(msg + System.lineSeparator()));
            }
        } catch (IOException e) {
            Platform.runLater(() ->
                    chatArea.appendText("[ERROR] " + e.getMessage() + "\n"));
        }
        Platform.runLater(Platform::exit);
    }

}
