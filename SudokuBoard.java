import java.util.*;
import java.io.*;

public class SudokuBoard {
   private int[][] board;
   
   //Constructor to create empty 9x9 Sudoku Board (0 indicates empty)  
   public SudokuBoard() {
      board = new int[9][9];
   }

   //Constructor for board from a known filename
   //Empty spots as periods are converted to 0s and digits stored as numbers
   public SudokuBoard(String fileName) {
      this();
      try {
         Scanner console = new Scanner(new File(fileName));
         for (int r = 0; r < board.length; r++) {
            if (console.hasNext()) {
               String line = console.next();
               for (int c = 0; c < board[0].length; c++) {
                  char value = line.charAt(c);
                  if (value == '.') {
                      board[r][c] = 0;
                  } else {
                      board[r][c] = value - '0';
                  }
               }
            }
         }
      } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
      }
   }
    
    
   //Uses recursion and backtracking to solve current board.
   //If finds an empty cell, attempts values 1 - 9, checks if that leads to a solution, resets to 0 if not.
   //If board is invalid returns false, or solved already returns true
   public boolean solve() {
      if(!isValid()) {
         return false;
      } 
      if (isSolved()) {
         return true;
      } 
      for (int c = 0; c < board[0].length; c++) {
         for (int r = 0; r < board.length; r ++) {
            if (board[r][c] == 0) {
               for (int i = 1; i <= 9; i++) {
                  board[r][c] = i;
                  if (isValid() && solve()) {
                     return true;
                  }
                  board[r][c] = 0;
               }
               return false;
            }
         }
      }
      return false;
   }
   
    
   //Method to check if our board follows the rules of Sudoku
   //All numbers are within valid range and no repeats in a row, col, or minibox
   public boolean isValid() {
      if (!checkNumbers()) {
         return false;
      }
      if (!checkRow()) {
         return false;
      }
      if (!checkCol()) {
         return false;
      }
      if (!checkBox()) {
         return false;
      }
      return true;
   }
   
   
   //Checking that we have valid numbers (0 to 9) on the board
   //0 being an empty spot
   private boolean checkNumbers() {
      for (int r = 0; r < board.length; r++) {
         for (int c = 0; c < board[0].length; c++) {
            if (board[r][c] < 0 || board[r][c] > 9) {
               return false;
            }
         }
      }
      return true;
   }


   //Checks for duplicates in a row (ignoring 0s)
   //Any repeat of 1-9 results in an invalid board
   private boolean checkRow() {
      for (int r = 0; r < board.length; r++) {
         Set < Integer > set = new HashSet < > ();
         for (int c = 0; c < board[0].length; c++) {
            int val = board[r][c];
            if (val != 0) {
               if (set.contains(val)) {
                  return false;
               }
               set.add(val);
            }
         }
      }
      return true;
   }
   
   
   
   //Checks for duplicates in a col (ignoring 0s)
   //Any repeat of 1-9 results in an invalid board
   private boolean checkCol() {
      for (int r = 0; r < board.length; r++) {
         Set < Integer > set = new HashSet < > ();
         for (int c = 0; c < board[0].length; c++) {
            int val = board[c][r];
            if (val != 0) {
               if (set.contains(val)) {
                  return false;
               }
               set.add(val);
            }
         }
      }
      return true;
   }
   
   
   
   //Checks for duplicates in a 3x3 minibox (ignoring 0s) using a Set
   //Any repeat of 1-9 results in an invalid board
   private boolean checkBox() {
      for (int boxRow = 0; boxRow < 3; boxRow++) {
         for (int boxCol = 0; boxCol < 3; boxCol++) { 
            Set < Integer > set = new HashSet < > ();
            for (int r = 0; r < 3; r++) {
               for (int c = 0; c < 3; c++) { 
                  int val = board[boxRow * 3 + r][boxCol * 3 + c]; 
                  if (val != 0) {
                     if (set.contains(val)) {
                        return false;
                     }
                     set.add(val);
                  }
               }
            }
         }
      }
      return true;
   }
    
    
    
   //Checks if the board is already solved
   //First checks if board is valid or not, then each number 1 - 9 must appear 9 times to be solved
   public boolean isSolved() {
      if (!isValid()) {
         return false;
      }
      Map < Integer, Integer > map = new HashMap < > ();
      for (int r = 0; r < board.length; r++) {
         for (int c = 0; c < board[0].length; c++) {
            int val = board[r][c];
            if (val != 0) {
               if (map.containsKey(val)) { 
                  map.put(val, map.get(val) + 1);
               } else {
                  map.put(val, 1);
               }
            }
         }
      }
      for (int num = 1; num <= 9; num++) {
         if (!map.containsKey(num) || map.get(num) !=9 ) { 
            return false;
         }
      }
      return true;
   }
    
    
    
    
   //Builds the board as a formatted string (0s represented as dash)
   //Vertical lines separate 3x3 boxes
   public String toString() {
      String result = "";
      String line = "+-------+-------+-------+\n";
      for (int r = 0; r < board.length; r++) {
         if (r % 3 == 0) {
            result = result + line;
         }
         for (int c = 0; c < board[0].length; c++) {
            if (c % 3 == 0) {
               result = result + "| ";
            }
            if (board[r][c] == 0) {
               result = result + "- ";
            } else {
               result = result + board[r][c] + " ";
            }
         }
         result = result + "|\n";
      }
      result = result + line;
      return result;
   }
   
    
}