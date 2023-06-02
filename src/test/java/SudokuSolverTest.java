import Models.SudokuPuzzle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SudokuSolverTest {

    private boolean checkSolution(SudokuPuzzle completedPuzzle){

        //check rows
        for (List<Integer> row : completedPuzzle.getPuzzle()){
            Map<Integer, Integer> frequencyMap = new HashMap<>();
            for (Integer i : row){
                frequencyMap.merge(i, 1, Integer::sum);
            }
            if (frequencyMap.size() < 9){
                System.out.println("check rows" + frequencyMap.size());
                return false;
            }
        }

        //check columns
        for (int i = 1; i < 10; i++){
            Map<Integer, Integer> frequencyMap = new HashMap<>();
            List<Integer> column = completedPuzzle.getColumn(i);
            for (Integer j : column){
                frequencyMap.merge(j, 1, Integer::sum);
            }
            if (frequencyMap.size() < 9){
                System.out.println("check columns");
                return false;
            }
        }

        //check squares
        for (int i = 1; i < 10; i++){
            Map<Integer, Integer> frequencyMap = new HashMap<>();
            List<List<Integer>> square = completedPuzzle.getSquare(i);
            for (List<Integer> row : square){
                for (Integer j : row){
                    frequencyMap.merge(j, 1, Integer::sum);
                }
            }
            if (frequencyMap.size() < 9){
                System.out.println("check squares");
                return false;
            }
        }

        return true;
    }

    @Test
    public void doesRowContainNumberTest(){
        //GIVEN
        SudokuPuzzle puzzle = new SudokuPuzzle(SampleSudokuPuzzles.easyPuzzle());

        SudokuSolver solver = new SudokuSolver(puzzle);

        //WHEN
        boolean result = solver.doesRowContainNumber(1, 8);

        //THEN
        Assertions.assertTrue(result);
    }

    @Test
    public void doesColumnContainNumberTest(){
        //GIVEN
        SudokuPuzzle puzzle = new SudokuPuzzle(SampleSudokuPuzzles.easyPuzzle());

        SudokuSolver solver = new SudokuSolver(puzzle);

        //WHEN
        boolean result = solver.doesColumnContainNumber(1, 8);

        //THEN
        Assertions.assertTrue(result);
    }

    @Test
    public void doesSquareContainNumberTest(){
        //GIVEN
        SudokuPuzzle puzzle = new SudokuPuzzle(SampleSudokuPuzzles.easyPuzzle());

        SudokuSolver solver = new SudokuSolver(puzzle);

        //WHEN
        boolean result = solver.doesSquareContainNumber(1, 8);

        //THEN
        Assertions.assertTrue(result);
    }

    @Test
    public void getNumberCountTest(){
        //GIVEN
        SudokuPuzzle puzzle = new SudokuPuzzle(SampleSudokuPuzzles.easyPuzzle());

        SudokuSolver solver = new SudokuSolver(puzzle);

        Map<Integer, Integer> manualCountMap = new HashMap<>();
        manualCountMap.put(0, 43);
        manualCountMap.put(1, 5);
        manualCountMap.put(2, 2);
        manualCountMap.put(3, 4);
        manualCountMap.put(4, 3);
        manualCountMap.put(5, 5);
        manualCountMap.put(6, 6);
        manualCountMap.put(7, 3);
        manualCountMap.put(8, 6);
        manualCountMap.put(9, 4);

        //WHEN
        Map<Integer, Integer> returnedMap = solver.getNumberCount();

        //THEN
        Assertions.assertEquals(manualCountMap.get(0), returnedMap.get(0));
        Assertions.assertEquals(manualCountMap.get(1), returnedMap.get(1));
        Assertions.assertEquals(manualCountMap.get(2), returnedMap.get(2));
        Assertions.assertEquals(manualCountMap.get(3), returnedMap.get(3));
        Assertions.assertEquals(manualCountMap.get(4), returnedMap.get(4));
        Assertions.assertEquals(manualCountMap.get(5), returnedMap.get(5));
        Assertions.assertEquals(manualCountMap.get(6), returnedMap.get(6));
        Assertions.assertEquals(manualCountMap.get(7), returnedMap.get(7));
        Assertions.assertEquals(manualCountMap.get(8), returnedMap.get(8));
        Assertions.assertEquals(manualCountMap.get(9), returnedMap.get(9));

    }

    @Test
    public void whichSquareAmIInTest(){
        //GIVEN
        SudokuPuzzle puzzle = new SudokuPuzzle(SampleSudokuPuzzles.easyPuzzle());

        SudokuSolver solver = new SudokuSolver(puzzle);

        int rowNumber = 4;
        int columnNumber = 8;

        //WHEN
        int squareNumber = solver.whichSquareAmIIn(4, 8);

        //THEN
        Assertions.assertEquals(6, squareNumber);
    }

    @Test
    public void solvePuzzle_easyPuzzleTest(){
        //GIVEN
        SudokuPuzzle puzzle = new SudokuPuzzle(SampleSudokuPuzzles.easyPuzzle());

        SudokuSolver solver = new SudokuSolver(puzzle);

        //WHEN
        solver.solvePuzzle();

        //THEN
        Assertions.assertTrue(checkSolution(solver.puzzle));
    }

    @Test
    public void solvePuzzle_mediumPuzzleTest(){
        //GIVEN
        SudokuPuzzle puzzle = new SudokuPuzzle(SampleSudokuPuzzles.mediumPuzzle());

        SudokuSolver solver = new SudokuSolver(puzzle);

        //WHEN
        solver.solvePuzzle();

        //THEN
        Assertions.assertTrue(checkSolution(solver.puzzle));
    }
}
