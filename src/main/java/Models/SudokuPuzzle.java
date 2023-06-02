package Models;

import java.util.ArrayList;
import java.util.List;

public class SudokuPuzzle {

    //A completed puzzle is a 9x9 grid where the numbers 1 through 9 are distributed so that they do not repeat
    //within each row, column, or distinct 3x3 square. In the beginning, a puzzle only has some numbers filled in,
    //and the solver will return the completed solution. Empty spaces are signified as zeros.
    //
    //Rows are numbered 1-9 from top to bottom
    //Columns are numbered 1-9 from left to right
    //Squares are numbered from top to bottom, left to right:
    // [1][2][3]
    // [4][5][6]
    // [7][8][9]

    public List<List<Integer>> puzzle;

    public SudokuPuzzle(List<List<Integer>> puzzle){
        this.puzzle = puzzle;
    }

    public List<List<Integer>> getPuzzle(){
        return this.puzzle;
    }

    public List<Integer> getRow(int rowNumber){
        return this.puzzle.get(rowNumber - 1);
    }

    public List<Integer> getColumn(int columnNumber){
        List<Integer> column = new ArrayList<>();
        for (int i = 0; i < 9; i++){
            column.add(this.puzzle.get(i).get(columnNumber - 1));
        }
        return column;
    }

    public List<List<Integer>> getSquare(int squareNumber){
        //squareNumber is each distinct 3x3 non-overlapping square inside the puzzle, of which there are 9.
        //They are numbered from top to bottom, left to right:
        // [1][2][3]
        // [4][5][6]
        // [7][8][9]

        int rowStart = 0;
        int colStart = 0;
        switch (squareNumber) {
            case 2 -> colStart = 3;
            case 3 -> colStart = 6;
            case 4 -> rowStart = 3;
            case 5 -> {
                rowStart = 3;
                colStart = 3;
            }
            case 6 -> {
                rowStart = 3;
                colStart = 6;
            }
            case 7 -> rowStart = 6;
            case 8 -> {
                rowStart = 6;
                colStart = 3;
            }
            case 9 -> {
                rowStart = 6;
                colStart = 6;
            }
        }

        List<List<Integer>> square = new ArrayList<>();
        for (int i = 0; i < 3; i++){
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < 3; j++){
                row.add(this.puzzle.get(i + rowStart).get(j + colStart));
            }
            square.add(row);
        }

        return square;
    }

    public void setValue(int row, int column, int number){
        this.puzzle.get(row - 1).set(column - 1, number);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (List<Integer> row : this.puzzle){
            sb.append(row.toString()).append("\n");
        }
        return sb.toString();
    }
}
