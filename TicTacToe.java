import java.util.Scanner;

public class TicTacToe {
    private char[][] board;
    private int currentPlayer;

    public static void main(String[] args) {
        TicTacToe ticTacToe = new TicTacToe();    // create an instance
        ticTacToe.InitializeGame();
        ticTacToe.CheckWin();
    }
    
    // reset the table and set the player 1 as the starter player
    void InitializeGame() {
        board = new char[3][3];
        currentPlayer = 1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    // print the current state of the board
    void PrintBoard(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2) System.out.print("|");
            }
            if (i < 2) System.out.println("\n-----");
        }
        System.out.println();
    }

    // choose a tile to mark with your player
    void MakeMove() {
        int tile = 0;
        Scanner sc = new Scanner(System.in);

        System.out.println("Player " + currentPlayer + ", enter your move (1-9): ");
        tile = sc.nextInt();

        int row = (tile - 1) / 3;
        int col = (tile - 1) % 3;
        if (board[row][col] == ' ') {
            board[row][col] = (currentPlayer == 1) ? 'X' : 'O';
            currentPlayer = 3 - currentPlayer;
        } else {
            System.out.println("Tile already occupied. Try again.");
            PlayerTurn();
        }
    }

    void PlayerTurn(){
        PrintBoard();
        MakeMove();
        CheckWin();
    }

    // check rows, columns, and diagonals for a win
    void CheckWin(){
        // rows and columns
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                PrintBoard();
                System.out.println("Player " + (board[i][0] == 'X' ? 1 : 2) + " wins!");
                PlayAgain();
                return;
            }
            if (board[0][i] != ' ' && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                PrintBoard();
                System.out.println("Player " + (board[0][i] == 'X' ? 1 : 2) + " wins!");
                PlayAgain();
                return;
            }
        }

        // diagonals
        if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            PrintBoard();
            System.out.println("Player " + (board[0][0] == 'X' ? 1 : 2) + " wins!");
            PlayAgain();
            return;
        }
        if (board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            PrintBoard();
            System.out.println("Player " + (board[0][2] == 'X' ? 1 : 2) + " wins!");
            PlayAgain();
            return;
        }

        // draw
        boolean draw = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    draw = false;
                    break;
                }
            }
        }
        if (draw) {
            PrintBoard();
            System.out.println("It's a draw!");
            PlayAgain();
        } else {
            PlayerTurn();
        }
    }

    void PlayAgain(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Do you want to play again? (y/n): ");
        String response = sc.next();
        char finalResponse = (response.length() < 2) ? response.charAt(0) : 'k';
        if (finalResponse == 'y' || finalResponse == 'Y') {
            InitializeGame();
            CheckWin();
        } else if(finalResponse == 'n' || finalResponse == 'N'){
            System.out.println("Thanks for playing!");
            return;
        }else {
            System.out.println("Invalid input. Please enter 'y' or 'n'.");
            PlayAgain();
        }
    }
}