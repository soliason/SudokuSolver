import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SampleSudokuPuzzles {

    public static List<List<Integer>> easyPuzzle(){
        List<List<Integer>> puzzle = new ArrayList<>();

        List<Integer> row1 = new ArrayList<>(Arrays.asList(0, 0, 0, 8, 2, 0, 0, 9, 0));
        List<Integer> row2 = new ArrayList<>(Arrays.asList(0, 5, 7, 6, 0, 9, 0, 1, 3));
        List<Integer> row3 = new ArrayList<>(Arrays.asList(0, 8, 4, 0, 3, 1, 0, 0, 0));
        List<Integer> row4 = new ArrayList<>(Arrays.asList(0, 7, 8, 0, 6, 0, 4, 5, 0));
        List<Integer> row5 = new ArrayList<>(Arrays.asList(0, 0, 9, 1, 0, 0, 0, 0, 6));
        List<Integer> row6 = new ArrayList<>(Arrays.asList(5, 6, 0, 3, 0, 0, 9, 8, 0));
        List<Integer> row7 = new ArrayList<>(Arrays.asList(8, 3, 0, 4, 0, 6, 0, 0, 0));
        List<Integer> row8 = new ArrayList<>(Arrays.asList(0, 0, 5, 0, 1, 8, 0, 0, 0));
        List<Integer> row9 = new ArrayList<>(Arrays.asList(1, 0, 6, 7, 5, 0, 2, 0, 0));

        puzzle.add(row1);
        puzzle.add(row2);
        puzzle.add(row3);
        puzzle.add(row4);
        puzzle.add(row5);
        puzzle.add(row6);
        puzzle.add(row7);
        puzzle.add(row8);
        puzzle.add(row9);

        return puzzle;
    }

    public static List<List<Integer>> mediumPuzzle(){
        List<List<Integer>> puzzle = new ArrayList<>();

        List<Integer> row1 = new ArrayList<>(Arrays.asList(0, 1, 0, 0, 0, 0, 0, 0, 6));
        List<Integer> row2 = new ArrayList<>(Arrays.asList(7, 0, 3, 0, 0, 4, 0, 0, 8));
        List<Integer> row3 = new ArrayList<>(Arrays.asList(0, 0, 0, 6, 0, 0, 3, 0, 0));
        List<Integer> row4 = new ArrayList<>(Arrays.asList(0, 0, 2, 8, 4, 0, 0, 1, 0));
        List<Integer> row5 = new ArrayList<>(Arrays.asList(0, 4, 0, 1, 0, 6, 0, 7, 2));
        List<Integer> row6 = new ArrayList<>(Arrays.asList(0, 0, 1, 5, 0, 0, 6, 9, 0));
        List<Integer> row7 = new ArrayList<>(Arrays.asList(0, 0, 0, 3, 0, 0, 4, 0, 9));
        List<Integer> row8 = new ArrayList<>(Arrays.asList(0, 8, 0, 0, 0, 0, 0, 0, 0));
        List<Integer> row9 = new ArrayList<>(Arrays.asList(3, 9, 6, 0, 5, 0, 0, 0, 1));

        puzzle.add(row1);
        puzzle.add(row2);
        puzzle.add(row3);
        puzzle.add(row4);
        puzzle.add(row5);
        puzzle.add(row6);
        puzzle.add(row7);
        puzzle.add(row8);
        puzzle.add(row9);

        return puzzle;
    }

    public static List<List<Integer>> hardPuzzle(){
        List<List<Integer>> puzzle = new ArrayList<>();

        List<Integer> row1 = new ArrayList<>(Arrays.asList(4, 1, 5, 0, 0, 0, 0, 6, 0));
        List<Integer> row2 = new ArrayList<>(Arrays.asList(9, 2, 0, 0, 0, 0, 3, 0, 4));
        List<Integer> row3 = new ArrayList<>(Arrays.asList(7, 0, 0, 0, 0, 0, 0, 0, 0));
        List<Integer> row4 = new ArrayList<>(Arrays.asList(0, 7, 0, 0, 9, 0, 0, 0, 1));
        List<Integer> row5 = new ArrayList<>(Arrays.asList(0, 0, 0, 5, 2, 7, 0, 4, 0));
        List<Integer> row6 = new ArrayList<>(Arrays.asList(5, 0, 9, 0, 0, 0, 0, 2, 0));
        List<Integer> row7 = new ArrayList<>(Arrays.asList(0, 0, 6, 4, 0, 0, 0, 1, 8));
        List<Integer> row8 = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 4, 0, 6));
        List<Integer> row9 = new ArrayList<>(Arrays.asList(2, 0, 0, 9, 0, 6, 0, 0, 3));

        puzzle.add(row1);
        puzzle.add(row2);
        puzzle.add(row3);
        puzzle.add(row4);
        puzzle.add(row5);
        puzzle.add(row6);
        puzzle.add(row7);
        puzzle.add(row8);
        puzzle.add(row9);

        return puzzle;
    }
}
