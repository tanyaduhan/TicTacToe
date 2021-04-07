//********************************************************************
//  Tanya .
//  T00609196
//  Assignment #3
//********************************************************************
import java.util.Scanner;

public class TicTacToe {
 static Scanner s1;
 static String[][] board;
 static String turn;
 static User playerOne,playerTwo;

 public static void main(String[] args) {
  s1 = new Scanner(System.in);
  board = new String[3][3];  
  turn = "X";
  String winner = null;
  String input;
  emptyBoard();
  
  System.out.print("Enter PlayerOne name: ");
  input = s1.next().trim();
  playerOne = new User(input);
  
  System.out.print("Enter PlayerTwo name: ");
  input = s1.next().trim();
  playerTwo = new User(input);
  
  
  System.out.println("");
  printBoard();
  System.out.println(playerOne.name + " , it is your turn. Enter your next move " + turn + ": ");

  while (winner == null) {
   
   try {
    input = s1.next().trim().toUpperCase();
    if (checkMove(input)== false) {
     System.out.println("Invalid input; re-enter next move:");
     continue;
    }
   } catch (Exception e) {
    System.out.println("Invalid input; re-enter next move:");
    continue;
   }   
   
   boolean res = placeSymbol(input,turn); // put the symbol according to the input
   
   if (res == true) {    
    if (turn.equals("X")) {
     turn = "O";
    } else {
     turn = "X";
    }
    printBoard();
    winner = checkWinner();
   } else {
    System.out.println("Slot already taken; re-enter slot number:");
    continue;
   }
  if( winner.equalsIgnoreCase("next") )
   { 
    winner = null; // If there is no winner still then continue next move
    continue;    
   }
  else if (winner.equalsIgnoreCase("draw")) {
   System.out.println("It's a draw! Thanks for playing.");
  } else {
   System.out.println("Congratulations! " + winner + " won! Thanks for playing.");  
  }
  System.out.println("Do you want to play again y/n : ");
   input = s1.next().trim().toUpperCase();
   if(input.equals("Y"))
    {
     winner = null; // Reset the Winner for the game and continue
     emptyBoard(); // empty the board for a new game     
     System.out.println("");
     printBoard();
     turn = "X"; // let playerOne take his turn
     System.out.println(playerOne.name + " , it is your turn. Please enter your next move " + turn + ": ");
     continue;    
    }
   else
    break;
 }
}
 
 static boolean checkMove(String input)
 {
   if   (input.equals("1A")) return true;
   else if (input.equals("1B")) return true;
   else if (input.equals("1C")) return true;
                              
   else if (input.equals("2A")) return true;
   else if (input.equals("2B")) return true;
   else if (input.equals("2C")) return true;
                             
   else if (input.equals("3A")) return true;
   else if (input.equals("3B")) return true;
   else if (input.equals("3C")) return true;
   
   else
    return false;
 }
 
 static boolean placeSymbol(String input, String turn)
 {
   //first replace A,B,C to 1, 2, 3
   //so convert 1A to 11, 2c to 23
   
   input = input.replace('A','1');
   input = input.replace('B','2');
   input = input.replace('C','3');
   
   // then separate two digits from input (23 >> r=2 c=3)
   // and subtract -1 from r, c to get actual array index so 23 become r=1 c= 2
   int r = Integer.parseInt( input.substring(0,1) ) - 1; //get 1st digit
   int c = Integer.parseInt( input.substring(1,2) ) - 1; //get 2nd digit
   //System.out.println(r+ ""+c); // print symbol's location
   
   if( board[r][c].equals(" ") ) // if its empty
   {
    board[r][c] = turn; // place sympbol into array index r c, 
    return true;
   }
   else
    return false; // if slot already filled return false
 }

 static String checkWinner() {
  for (int a = 0; a < 8; a++) {
   String line = null;
   switch (a) {
   case 0:
    line = board[0][0] + board[0][1] + board[0][2];
    break;
   case 1:
    line = board[1][0] + board[1][1] + board[1][2];
    break;
   case 2:
    line = board[2][0] + board[2][1] + board[2][2];
    break;
   case 3:
    line = board[0][0] + board[1][0] + board[2][0];
    break;
   case 4:
    line = board[0][1] + board[1][1] + board[2][1];
    break;
   case 5:
    line = board[0][2] + board[1][2] + board[2][2];
    break;
   case 6:
    line = board[0][0] + board[1][1] + board[2][2];
    break;
   case 7:
    line = board[0][2] + board[1][1] + board[2][0];
    break;
   }
   if (line.equals("XXX")) {
    return playerOne.name;
   } else if (line.equals("OOO")) {
    return playerTwo.name;    
   }
  }

  boolean allFilled = true;
  for (int r = 0; r < 3; r++){
   for (int c = 0; c < 3; c++){
    if( board[r][c].equals(" ") ) 
    {
     allFilled = false; // set false because there is still an empty slot.
     r = 3; // set 3 to break r loop 
     break; // break the c loop
    }
   }
  }
  
  if(allFilled==true)
   return "draw";
  
  //if turn is equal to X then print player one's name; else print player Two's name
  System.out.print(turn.equals("X")? playerOne.name: playerTwo.name);
  System.out.println(", it is your turn. Enter your next move " + turn + ": ");
  return "next";
 }

 static void printBoard() {
  
  System.out.println("Player One: "+playerOne.name+": Xs    Player Two: "+playerTwo.name+": Os\n");
  System.out.println("   A | B | C ");
  System.out.println(" |--- --- ---|");
  System.out.println("1| " + board[0][0] + " | " + board[0][1] + " | " + board[0][2] + " |");
  System.out.println(" |-----------|");
  System.out.println("2| " + board[1][0] + " | " + board[1][1] + " | " + board[1][2] + " |");
  System.out.println(" |-----------|");
  System.out.println("3| " + board[2][0] + " | " + board[2][1] + " | " + board[2][2] + " |");
  System.out.println(" |---|---|---|");
  System.out.println("");
 }

 static void emptyBoard() {
  for (int r = 0; r < 3; r++)
   for (int c = 0; c < 3; c++)
    board[r][c] = " ";  
 }
}
