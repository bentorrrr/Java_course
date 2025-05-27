package com.example.week6_tictactoe;

import java.util.Arrays;
import java.util.Optional;

public class Board {
    public enum Cell { Empty, X, O}
    private final Cell[][] grid = new Cell[3][3];

    public Board() { reset(); }

    public boolean place(int row, int col, Cell Mark) {
        if(grid[row][col] != Cell.Empty){
            return false;
        }
        grid[row][col] = Mark;
        return true;
    }

    public void reset() {
        for (Cell[] row : grid) {
            Arrays.fill(row, Cell.Empty);
        }
    }

    public boolean isFull(){
        for (Cell[] row : grid)
            for (Cell cell : row)
                if (cell == Cell.Empty) return false;
        return true;
    }

    public Optional<Cell> winner() {
        for (int i = 0; i < 3; i++) {
            if (grid[i][0] != Cell.Empty && grid[i][0] == grid[i][1] && grid[i][1] == grid[i][2])
                return Optional.of(grid[i][0]);
            if (grid[0][i] != Cell.Empty && grid[0][i] == grid[1][i] && grid[1][i] == grid[2][i])
                return Optional.of(grid[0][i]);
        }
        // diagonals
        if (grid[0][0] != Cell.Empty && grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2])
            return Optional.of(grid[0][0]);
        if (grid[0][2] != Cell.Empty && grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0])
            return Optional.of(grid[0][2]);
        // nobody yet
        return Optional.empty();
    }

    public Cell[][] snapshot() {
        return Arrays.stream(grid).map(Cell[]::clone).toArray(Cell[][]::new);
    }
}
