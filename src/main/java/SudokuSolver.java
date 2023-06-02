import Exceptions.ErrorInPuzzleException;
import Models.SudokuPuzzle;

import java.util.*;

public class SudokuSolver {

    public SudokuPuzzle puzzle;
    public int numberOfEmptySpaces;
    Map<Integer, Integer> numberCount;

    public SudokuSolver (SudokuPuzzle puzzle){
        this.puzzle = puzzle;
        this.numberCount = getNumberCount();
        this.numberOfEmptySpaces = numberCount.get(0);
    }

    public boolean doesRowContainNumber(int rowNumber, int numberToCheck){
        List<Integer> currentRow = puzzle.getRow(rowNumber);
        return currentRow.contains(numberToCheck);
    }

    public boolean doesColumnContainNumber(int columnNumber, int numberToCheck){
        List<Integer> currentColumn = puzzle.getColumn(columnNumber);
        return currentColumn.contains(numberToCheck);
    }

    public boolean doesSquareContainNumber(int squareNumber, int numberToCheck){
        List<List<Integer>> currentSquare = puzzle.getSquare(squareNumber);
        boolean containsNumber = false;
        for (List<Integer> row : currentSquare){
            if (row.contains(numberToCheck)){
                containsNumber = true;
                break;
            }
        }
        return containsNumber;
    }

    public Map<Integer, Integer> getNumberCount(){
        Map<Integer, Integer> numberCounting = new HashMap<>();
        for (int i = 1; i < 10; i++){
            this.puzzle.getRow(i).forEach(x -> numberCounting.merge(x, 1, Integer::sum));
        }
        return numberCounting;
    }

    public int whichSquareAmIIn(int rowNumber, int columnNumber){
        if (1 <= rowNumber && rowNumber <= 3){
            if (1 <= columnNumber && columnNumber <= 3){
                return 1;
            } else if (4 <= columnNumber && columnNumber <= 6){
                return 2;
            } else {
                return 3;
            }
        } else if (4 <= rowNumber && rowNumber <= 6){
            if (1 <= columnNumber && columnNumber <= 3){
                return 4;
            } else if (4 <= columnNumber && columnNumber <= 6){
                return 5;
            } else {
                return 6;
            }
        } else {
            if (1 <= columnNumber && columnNumber <= 3){
                return 7;
            } else if (4 <= columnNumber && columnNumber <= 6){
                return 8;
            } else {
                return 9;
            }
        }
    }

    public int whichRowAmIIn(int squareNumber, int squareRow){
        if (squareNumber == 1 || squareNumber == 2 || squareNumber == 3){
            return squareRow;
        } else if (squareNumber == 4 || squareNumber == 5 || squareNumber == 6){
            return squareRow + 3;
        } else {
            return squareRow + 6;
        }
    }

    public int whichColumnAmIIn(int squareNumber, int squareColumn){
        if (squareNumber == 1 || squareNumber == 4 || squareNumber == 7){
            return squareColumn;
        } else if (squareNumber == 2 || squareNumber == 5 || squareNumber == 8){
            return squareColumn + 3;
        } else {
            return squareColumn + 6;
        }
    }

    public void solvePuzzle(){
        this.numberCount.remove(0);

        boolean changesMade;

        //while there are still empty spaces
        while (this.numberOfEmptySpaces > 0){
            changesMade = false;

            changesMade = solveAlgorithmOne(changesMade);

//            changesMade = solveAlgorithmTwo(changesMade);

            //If no changes were made and there are still empty spaces, we have a problem!
            if (!changesMade){
                System.out.println(puzzle.toString());
                throw new ErrorInPuzzleException("After going through the whole puzzle, " +
                        "we can't find anymore changes to make");
            }
        }

        System.out.println("Here is your completed puzzle:");
        System.out.println(this.puzzle.toString());
    }

    private boolean solveAlgorithmOne(boolean changesMade){
        //go by row
        for (int i = 1; i < 10; i++){
            List<Integer> currentRow = this.puzzle.getRow(i);
            //if there are empty spaces in this row
            if (currentRow.contains(0)){
                //go through the values in the row
                for (int j = 0; j < 9; j++){
                    //when we get to an empty space
                    if (currentRow.get(j).equals(0)){
                        List<Integer> possibleValues = new ArrayList<>();
                        //check each number
                        for (int k = 1; k < 10; k++){
                            boolean couldThisNumberGoHere = !doesRowContainNumber(i, k)
                                    && !doesColumnContainNumber(j + 1, k)
                                    && !doesSquareContainNumber(whichSquareAmIIn(i, j + 1), k);
                            if (couldThisNumberGoHere){
                                possibleValues.add(k);
                            }
                        }
                        //check for issues!
                        if (possibleValues.size() == 0){
                            System.out.println(puzzle.toString());
                            throw new ErrorInPuzzleException("Error in puzzle: current row is "
                                    + i + " and current column is " + (j + 1)
                                    + " and there are no possible values for this space");
                        }
                        //if only one number can go there, put it in and update numberCount
                        if (possibleValues.size() == 1){
                            this.puzzle.setValue(i, j + 1, possibleValues.get(0));
                            this.numberCount.merge(possibleValues.get(0), 1, Integer::sum);
                            this.numberOfEmptySpaces--;
                            //check for issues!
                            if (this.numberCount.containsValue(10)){
                                System.out.println(puzzle.toString());
                                throw new ErrorInPuzzleException(
                                        "Error in puzzle: there are more than 9 of the current number: "
                                                + possibleValues.get(0));
                            }
                            changesMade = true;
                        }
                    }
                }
            }
        }
        return changesMade;
    }

    private boolean solveAlgorithmTwo(boolean changesMade){
        //for each square in the puzzle
        for (int i = 1; i <= 9; i++){
            List<List<Integer>> square = puzzle.getSquare(i);
            //check each number and keep track of how many places it could go
            Map<Integer, Integer> availablePlacesForNumbers = new HashMap<>();
            for (int j = 1; j <= 9; j++){
                //if the number is missing from the square
                if (!doesSquareContainNumber(i, j)){
                    //go through each row in the square
                    for(int k = 1; k < 3; k++){
                        List<Integer> row = square.get(k);
                        //go through each space in the row
                        for (int c = 1; c < 3; c++){
                            //if it's an empty space
                            if (row.get(c) == 0){
                                boolean couldThisNumberGoHere = !doesRowContainNumber(whichRowAmIIn(i, k), j)
                                        && !doesColumnContainNumber(whichColumnAmIIn(i, c), j)
                                        && !doesSquareContainNumber(i, j);
                                if (couldThisNumberGoHere){
                                    availablePlacesForNumbers.merge(j, 1, Integer::sum);
                                }
                            }
                        }
                    }
                }
            }
            //check to see if any numbers could only go in one space
            for (Map.Entry<Integer, Integer> entry : availablePlacesForNumbers.entrySet()){
                if (entry.getValue() == 1){
                    int num = entry.getKey();
                    //go find where it should go and put that number in
                    //go through each row
                    for(int k = 1; k < 3; k++){
                        List<Integer> row = square.get(k);
                        //go through each space in the row
                        for (int c = 1; c < 3; c++){
                            //if it's an empty space
                            if (row.get(c) == 0){
                                boolean couldThisNumberGoHere = !doesRowContainNumber(whichRowAmIIn(i, k), num)
                                        && !doesColumnContainNumber(whichColumnAmIIn(i, c), num)
                                        && !doesSquareContainNumber(i, num);
                                if (couldThisNumberGoHere){
                                    //put it in the puzzle and update numberCount
                                    this.puzzle.setValue(whichRowAmIIn(i, k), whichColumnAmIIn(i, c), num);
                                    this.numberCount.merge(num, 1, Integer::sum);
                                    this.numberOfEmptySpaces--;
                                }
                                //check for issues!
                                if (this.numberCount.containsValue(10)){
                                    System.out.println(puzzle.toString());
                                    throw new ErrorInPuzzleException(
                                            "Error in puzzle: there are more than 9 of the current number: "
                                                    + num);
                                }
                                changesMade = true;
                            }
                        }
                    }
                }
            }
        }

        return changesMade;
    }

    public void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<List<Integer>> beginningPuzzle = new ArrayList<>();

        for (int z = 1; z < 10; z++){
            List<Integer> row = new ArrayList<>();
            System.out.println("Enter the numbers in line " + z
                    + " of the puzzle, one at a time, using zeros for empty spaces:");
            for (int y = 0; y < 9; y++){
                row.add(Integer.valueOf(scanner.nextLine()));
            }
            beginningPuzzle.add(row);
        }

        this.puzzle = new SudokuPuzzle(beginningPuzzle);

        solvePuzzle();
    }
}
