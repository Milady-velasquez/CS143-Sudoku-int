import java.util.*;
import java.io.*;

public class SudokuEngine {
   public static void main(String[] args) {
      String fileName = "boards/data1.sdk";
      SudokuBoard board = new SudokuBoard(fileName);  
      System.out.println("\nLoading... " + fileName);
      System.out.println(board);
      
      //can we even solve this board? is it already filled out?
      //Check if the board should be solved before calling board.solve()
      if (!board.isValid()) {
         System.out.println("This is an invalid board and cannot be solved :(");
      } else if (board.isSolved()) {
         System.out.println("Board is already solved! Good job:)");
      } else {
         //This boolean flag stores whether our recursion board.solve() found a solution
         boolean solved = board.solve();
         
         if (solved) {
            System.out.println("\nBoard is solved!\n");
            System.out.println(board);
         } else {
            System.out.println("Sorry, this board cannot be solved :(");
         }
         
      }
      
   }
}

/*
# PROGRAM OUTPUT

  ----jGRASP exec: java SudokuEngine
 
 Loading... boards/data1.sdk
 +-------+-------+-------+
 | 2 - - | 1 - 5 | - - 3 |
 | - 5 4 | - - - | 7 1 - |
 | - 1 - | 2 - 3 | - 8 - |
 +-------+-------+-------+
 | 6 - 2 | 8 - 7 | 3 - 4 |
 | - - - | - - - | - - - |
 | 1 - 5 | 3 - 9 | 8 - 6 |
 +-------+-------+-------+
 | - 2 - | 7 - 1 | - 6 - |
 | - 8 1 | - - - | 2 4 - |
 | 7 - - | 4 - 2 | - - 1 |
 +-------+-------+-------+
 
 
 Board is solved!
 
 +-------+-------+-------+
 | 2 7 8 | 1 4 5 | 6 9 3 |
 | 3 5 4 | 6 9 8 | 7 1 2 |
 | 9 1 6 | 2 7 3 | 4 8 5 |
 +-------+-------+-------+
 | 6 9 2 | 8 1 7 | 3 5 4 |
 | 8 3 7 | 5 6 4 | 1 2 9 |
 | 1 4 5 | 3 2 9 | 8 7 6 |
 +-------+-------+-------+
 | 4 2 3 | 7 5 1 | 9 6 8 |
 | 5 8 1 | 9 3 6 | 2 4 7 |
 | 7 6 9 | 4 8 2 | 5 3 1 |
 +-------+-------+-------+
 
 
  ----jGRASP: Operation complete.
 
 
*/