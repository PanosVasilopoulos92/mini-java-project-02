package codingfactory.project04;

import java.util.Random;
import java.util.Scanner;

/**
 *  This programme simulates a simple "tic-tac-toe" game in which the player compete against
 *  the computer in order to win. Player will be asked to insert a number between 1 and 9 in order
 *  to fill the square he/she wish to.
 * @author Panos V.
 * @version 1.0
 */
public class TrillizaApp {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        char[][] board = {{' ', ' ', ' '},
                          {' ', ' ', ' '},
                          {' ', ' ', ' '}};

        printBoard(board);
        displayTheGame(board);
    }

    /**
     *  This method is responsible for displaying the logic of the program into the "main" method and simultaneously
     *  checks in every step if the game has met the end conditions using if statements inside a while loop . If it has,
     *  it informs the user for the outcome, if not it allows the loop to continue until the end condition are met.
     * @param board The 2D character array as input parameter.
     */
    private static void displayTheGame(char[][] board) {
        while(true) {
            playerMove(board, scanner);
            if (isGameFinished(board)){
                break;
            }
            printBoard(board);
            System.out.println();
            computerMove(board);
            if (isGameFinished(board)){
                break;
            }
            printBoard(board);
        }
        scanner.close();
    }

    /**
     *  This method is responsible for "choosing" the number (1-9), that represent a specific square, for the computer. It uses
     *  the Random Class to generate a pseudo-random number and also checks that the number meets the criteria in order to be accepted.
     * @param board The 2D character array as input parameter.
     */
    private static void computerMove(char[][] board) {
        Random random = new Random();
        int computerMove;
        do {
            computerMove = random.nextInt(9) + 1;
        } while (!isValidMove(board, Integer.toString(computerMove) ));

        System.out.println("Computer chose position number: " + computerMove);
        placeMove(board, Integer.toString(computerMove), 'O');
    }

    /**
     * This method reads the input from the user, verifies that is a valid move and invokes the method "placeMove".
     * @param board The 2D character array as input parameter.
     * @param scanner The typical parameter for Scanner, in order to read the user's input.
     */
    public static void playerMove(char[][] board, Scanner scanner) {
        String userChoice;

        do {
            System.out.println("Please enter the position you want to place your next move (1-9):");
            userChoice = scanner.nextLine();
        } while (!isValidMove(board, userChoice));

        placeMove(board, userChoice, 'X');
    }

    /**
     *  This method checks if the move of the player (human or computer) is valid by using a switch statement
     *  that forbids the overwriting of previous moves. It achieves that by allowing moves only in empty squares.
     * @param board The 2D character array as input parameter.
     * @param position The square that we want to draw our symbol ("X" for the user, "O" for the computer).
     * @return Returns "true" if the square is empty and "false" if the square is already taken.
     */
    private static boolean isValidMove(char[][] board, String position) {
        switch (position) {
            case "1":
                return (board[0][0] == ' ');
            case "2":
                return (board[0][1] == ' ');
            case "3":
                return (board[0][2] == ' ');
            case "4":
                return (board[1][0] == ' ');
            case "5":
                return (board[1][1] == ' ');
            case "6":
                return (board[1][2] == ' ');
            case "7":
                return (board[2][0] == ' ');
            case "8":
                return (board[2][1] == ' ');
            case "9":
                return (board[2][2] == ' ');
            default:
                System.out.println("Not valid move");
        }
        return false;
    }

    /**
     *  This method is responsible for picking the square in witch the next move will be placed, both for user and computer.
     *  It uses a switch-case statement in order to achieve that purpose.
     * @param board The 2D character array as input parameter.
     * @param position The square in which the next move will be placed.
     * @param symbol The typical parameter that represent the symbols used by players.
     */
    private static void placeMove(char[][] board, String position, char symbol) {
        switch (position) {
            case "1":
                board[0][0] = symbol;
                break;
            case "2":
                board[0][1] = symbol;
                break;
            case "3":
                board[0][2] = symbol;
                break;
            case "4":
                board[1][0] = symbol;
                break;
            case "5":
                board[1][1] = symbol;
                break;
            case "6":
                board[1][2] = symbol;
                break;
            case "7":
                board[2][0] = symbol;
                break;
            case "8":
                board[2][1] = symbol;
                break;
            case "9":
                board[2][2] = symbol;
                break;
            default:
                System.out.println("Not valid move");
        }
    }

    /**
     *  This method checks if the game can continue or if it has ended and informs the user accordingly. There are
     *  three ways for the game to finish: 1) The player has won, 2) the computer has won, 3) All squares are full without
     *  any winner.
     * @param board The 2D character array as input parameter.
     * @return Returns false as long there is no winner and there is at least one square free. It returns true if any of the
     * previous is false.
     */
    private static boolean isGameFinished(char[][] board) {
        if (hasSomebodyWon(board,'X')) {
            printBoard(board);
            System.out.println("Congrats! You have won this match!");
            return true;
        }
        if (hasSomebodyWon(board, 'O')) {
            printBoard(board);
            System.out.println("Computer won this match!");
            return true;
        }

        for (int i = 0 ; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        printBoard(board);
        System.out.println("Game ended in a tie!");
        return true;
    }

    /**
     *  This method sets the rules for how victory can be achieved.
     * @param board The 2D character array as input parameter.
     * @param symbol A typical parameter that is used to represent the user's character and the computer's character.
     * @return Returns if the victory conditions were met. If they did, it returns true, if not it returns false.
     */
    private static boolean hasSomebodyWon(char[][] board, char symbol) {
        if ((board[0][0] == symbol && board[0][1] == symbol && board[0][2] == symbol) ||
            (board[1][0] == symbol && board[1][1] == symbol && board[1][2] == symbol) ||
            (board[2][0] == symbol && board[2][1] == symbol && board[2][2] == symbol) ||

            (board[0][0] == symbol && board[1][0] == symbol && board[2][0] == symbol) ||
            (board[0][1] == symbol && board[1][1] == symbol && board[2][1] == symbol) ||
            (board[0][2] == symbol && board[1][2] == symbol && board[2][2] == symbol) ||

            (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) ||
            (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *  A method designed for displaying the board into the console.
     * @param board The 2D character array as input parameter.
     */
    public static void printBoard(char[][] board) {
        System.out.println(board[0][0] + " | " + board[0][1] + " | " + board[0][2]);
        System.out.println("- + - + -");
        System.out.println(board[1][0] + " | " + board[1][1] + " | " + board[1][2]);
        System.out.println("- + - + -");
        System.out.println(board[2][0] + " | " + board[2][1] + " | " + board[2][2]);
    }
}
