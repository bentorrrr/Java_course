package com.example.week6_tictactoe;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.Optional;

public class GameController {

    @FXML private GridPane grid;

    @FXML private Label turnLabel;
    @FXML private Label playerScoreLabel;
    @FXML private Label computerScoreLabel;
    @FXML private Label drawLabel;

    private final Board board           = new Board();
    private final ComputerPlayer bot    = new ComputerPlayer();
    private final Button[][] btn        = new Button[3][3];

    private int playerScore, compScore, draws;

    @FXML
    public void initialize() {
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++) {
                Button b = new Button();
                b.setPrefSize(100, 100);
                final int row = r, col = c;
                b.setOnAction(e -> handlePlayerMove(row, col));
                grid.add(b, c, r);
                btn[r][c] = b;
            }
    }

    private void handlePlayerMove(int r, int c) {
        if (!board.place(r, c, Board.Cell.X)) return;

        btn[r][c].setText("X");
        evaluateGameState();

        if (!grid.isDisabled()) {
            int[] mv = bot.chooseMove(board);
            board.place(mv[0], mv[1], Board.Cell.O);
            btn[mv[0]][mv[1]].setText("O");
            evaluateGameState();
        }
    }

    private void evaluateGameState() {
        Optional<Board.Cell> win = board.winner();

        if (win.isPresent()) {
            if (win.get() == Board.Cell.X) {
                ++playerScore;
                gameOver("You win!");
            } else {
                ++compScore;
                gameOver("Computer wins!");
            }
        } else if (board.isFull()) {
            ++draws;
            gameOver("Draw!");
        }
    }


    private void gameOver(String msg) {
        turnLabel.setText(msg);
        updateScoreLabels();
        grid.setDisable(true);
    }

    private void updateScoreLabels() {
        playerScoreLabel.setText(String.valueOf(playerScore));
        computerScoreLabel.setText(String.valueOf(compScore));
        drawLabel.setText(String.valueOf(draws));
    }

    @FXML private void startNewGame() {
        board.reset();
        grid.setDisable(false);
        turnLabel.setText("Your turn");

        for (Button[] row : btn)
            for (Button b : row) {
                b.setText("");
                b.setDisable(false);
            }
    }

    @FXML private void resetScore() {
        playerScore = compScore = draws = 0;
        updateScoreLabels();
    }

    @FXML private void quitGame() {
        Platform.exit();
    }
}
