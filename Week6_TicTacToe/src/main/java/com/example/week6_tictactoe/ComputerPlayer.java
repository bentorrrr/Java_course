package com.example.week6_tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ComputerPlayer {
    private final Random rnd = new Random();

    public int[] chooseMove(Board board) {
        Board.Cell[][] g = board.snapshot();

        int[] move = findWinningMove(g, Board.Cell.O);
        if (move != null) return move;

        move = findWinningMove(g, Board.Cell.X);
        if (move != null) return move;

        if (g[1][1] == Board.Cell.Empty) return new int[]{1, 1};

        List<int[]> empties = new ArrayList<>();
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++)
                if (g[r][c] == Board.Cell.Empty) empties.add(new int[]{r, c});
        return empties.get(rnd.nextInt(empties.size()));
    }

    private int[] findWinningMove(Board.Cell[][] g, Board.Cell mark) {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (g[r][c] != Board.Cell.Empty) continue;
                g[r][c] = mark;
                if (isWinner(g, mark)) { g[r][c] = Board.Cell.Empty; return new int[]{r, c}; }
                g[r][c] = Board.Cell.Empty;
            }
        }
        return null;
    }

    private boolean isWinner(Board.Cell[][] g, Board.Cell m) {
        for (int i = 0; i < 3; i++)
            if ((g[i][0] == m && g[i][1] == m && g[i][2] == m) ||
                    (g[0][i] == m && g[1][i] == m && g[2][i] == m)) return true;
        return (g[0][0] == m && g[1][1] == m && g[2][2] == m) ||
                (g[0][2] == m && g[1][1] == m && g[2][0] == m);
    }
}
