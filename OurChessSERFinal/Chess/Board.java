//Board.java
import java.util.concurrent.TimeUnit;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

public class Board{
	
	public boolean isWhiteTurn = true;


	static ArrayList<String> startingPositions = new ArrayList<>();
	static ArrayList<String> endingPositions = new ArrayList<>();

	
	Square[][] squares = new Square[8][8]; //Need 64 total

    Piece[] blackLineUp = {new Rook("black"), new Knight("black"), new Bishop("black"),
                           new Queen("black"), new King("black"), new Bishop("black"),
                           new Knight("black"), new Rook("black")};

    Piece[] whiteLineUp = {new Rook("white"), new Knight("white"), new Bishop("white"),
                           new Queen("white"), new King("white"), new Bishop("white"),
                           new Knight("white"), new Rook("white")};

	public static final String PURPLE= "\u001B[35m";
	public static final String CYAN  = "\u001B[36m";
	public static final String RESET = "\u001B[0m";

	public static void main(String[] args){

		Board myBoard = new Board();
		myBoard.boardSetup();
		
		System.out.println("\nWelcome to Chess!");
		System.out.println("Do you have an ongoing chess game? y/n");
		Scanner scanner = new Scanner(System.in);
		String input = scanner.next();
		if (input.equals("y")){
			System.out.println("What is the name of your file? (without .txt)");
			String fileName = scanner.next();
			File myFile = new File(fileName + ".txt");
			FileInputOutput.readFile(myFile, startingPositions, endingPositions); // fills both lists
			myBoard.replayMoves(startingPositions, endingPositions);              // applies the moves
		}

			
		
		
		System.out.println("Starting Game!");

		myBoard.printBoard();

		System.out.println("Cyan has First Move");
		
		Scanner myScanner = new Scanner(System.in);
		
		while (myBoard.isKingAlive()) {
			myBoard.gameRound(myScanner);
		}
		
		System.out.println("Game Over!");
		System.out.println("Winner: " + myBoard.whoWon());
		
		System.out.println("Enter a name for your save file:");
		myScanner.nextLine(); // consume leftover newline from last next() call
		String saveFileName = myScanner.nextLine();
		File userFile = new File(saveFileName + "ChessMoves.txt");
		FileInputOutput.writeFile(userFile, startingPositions, endingPositions);
		
		
	}	
	
	public void replayMoves(ArrayList<String> startingPositions, ArrayList<String> endingPositions) {
		isWhiteTurn = true;
		for (int i = 0; i < startingPositions.size(); i++) {//for every move
			String oldSquare = startingPositions.get(i);//store moves
			String newSquare = endingPositions.get(i);

			int[] indicesOldPos = convertToIndex(oldSquare);//convert them
			int[] indicesNewPos = convertToIndex(newSquare);

			Square pastSquare   = squares[indicesOldPos[0]][indicesOldPos[1]];//create new square at their location
			Square futureSquare = squares[indicesNewPos[0]][indicesNewPos[1]];

			int oldRow = indicesOldPos[0];
			int oldCol = indicesOldPos[1];
			int newRow = indicesNewPos[0];
			int newCol = indicesNewPos[1];

			if (pastSquare.isLegal(oldRow, oldCol, newRow, newCol, this)) {
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt(); // best practice to restore interrupt flag
				}
				pastSquare.move(futureSquare);
				printBoard();
				isWhiteTurn = !isWhiteTurn;
			}
		}
		System.out.println("Previous " + startingPositions.size() + " moves replayed successfully!");
		printBoard();
	}
	
	public void gameRound(Scanner myScanner){// A player's round (They can move a piece)
		
		System.out.println("Choose a Piece to Move (Format: 'A1')"); //"A6"
		String oldSquare = myScanner.next();
		if (oldSquare.equals("EXIT")){
			System.out.println("Game over");
			System.exit(0);
		}
		System.out.println("Choose a Spot to Move to"); // "A6"
		String newSquare = myScanner.next();
		
		int[] indicesOldPos = convertToIndex(oldSquare);
		int[] indicesNewPos= convertToIndex(newSquare);
		
		int count = 0;

		while (count < 2) {
			if (indicesOldPos[count] > 7 || indicesOldPos[count] < 0 || indicesNewPos[count] > 7|| indicesNewPos[count] < 0){
				System.out.println("Whoa there! Stay in bounds!");
				return; //end the previous gameround
			}
			count++;
		}
		//Creates copies of the object to call functions
		Square pastSquare = squares[indicesOldPos[0]][indicesOldPos[1]]; 
		Square futureSquare = squares[indicesNewPos[0]][indicesNewPos[1]];
		
		int oldRow = indicesOldPos[0];
		int oldCol = indicesOldPos[1];
		int newRow = indicesNewPos[0];
		int newCol = indicesNewPos[1];
		
		if (pastSquare.getPiece() == null) {
			System.out.println("No piece at that location.");
			return;
		}
		boolean pieceIsWhite = pastSquare.getPieceColor().equals("white");
		if (pieceIsWhite != isWhiteTurn) {
			System.out.println("Capturing your own piece? Think twice!");
			return;
		}
		

		//String oldPos = oldRow + "" + oldCol;
		//String newPos = newRow + "" + newCol;
		
		if(pastSquare.isLegal(oldRow, oldCol, newRow, newCol, this)){
			pastSquare.move(futureSquare);
			isWhiteTurn = !isWhiteTurn;
			startingPositions.add(oldSquare); //add to list for storage
			endingPositions.add(newSquare);

		}
		printBoard();
	}
	
	public int[] convertToIndex(String location){// Converts User Format to indexes. Example: "A4" --> [0][3].
		char columnChar = location.charAt(0);
		int rowIndex = Integer.parseInt(location.substring(1))-1;
		int colIndex = Character.toUpperCase(columnChar) - 'A';

		return new int[] {rowIndex, colIndex};
	}
	

//Prints the chessboard's status
	//Comes with color
	//Adds the A B C D E F G H and 1 2 3 4 5 6 7 8 to the sides for easy readablility. 
	//When re-called, refreshes the board, reducing clutter
	public void printBoard(){
		System.out.print("\033[H\033[2J");
		System.out.flush();
		int c=0;
		System.out.println("\n  A   B   C   D   E   F   G   H ");
        for (int r = 0; r < 8; r++) {
			System.out.println(">---+---+---+---+---+---+---+---<"); // Divider put between rows to look cool
			for (int i = 0; i < 8; i++){
				if (this.squares[c][i] != null && this.squares[c][i].getPieceType() != null){// If there are no null properties
					if (this.squares[c][i].getPieceColor().equals("black")){ //If it's a black piece
						System.out.print("| " + PURPLE + this.squares[c][i].getPieceType() + " " + RESET); //Print a purple square
					} else {//If it's not a black piece
						System.out.print("| " + CYAN + this.squares[c][i].getPieceType() + " " + RESET); //Print a cyan square
					}
				} else { //If there are null properties (AKA an empty square)
					System.out.print("|   "); //Print an empty square
				}
			}
			System.out.print("| " + (r+1) + "\n"); //Cleanup and labeling
			c++; //Increasing my weird third variable which represents rows
		}
        System.out.print(">---+---+---+---+---+---+---+---<");
        	
    }
    
    
    public void boardSetup(){ 
		
		for (int j= 0; j < this.squares.length; j++){ //Assigns the set up for the first row of pieces.
			if (j % 2 == 0){
				this.squares[0][j] = new Square(this.blackLineUp[j], true);
			}
			else {
				this.squares[0][j] = new Square(this.blackLineUp[j], false);
			}
		}
		
		for (int i = 0; i < this.squares.length; i++){//Assigns a row of black pawns.
			if (i % 2 == 0){
				this.squares[1][i] = new Square(new Pawn("black"), false);
			}
			else{
				this.squares[1][i] = new Square(new Pawn("black"), true);
			}
		}				
		
		for (int r = 2; r < 6; r++) { //Blank squares
			for (int c = 0; c < 8; c++) {
				this.squares[r][c] = new Square(null, (r + c) % 2 == 0);
			}
		}

		
		for (int i = 0; i < this.squares.length; i++){//Assigns a row of white pawns.
			if (i % 2 == 0){
			this.squares[6][i] = new Square(new Pawn("white"), true);
			}
			else {
			this.squares[6][i] = new Square(new Pawn("white"), false);
			}
		}
		
				
		for (int i = 0; i < this.squares.length; i++){ //Assigns the set up for the last row of pieces.
			if (i % 2 == 0){
				this.squares[7][i] = new Square(this.whiteLineUp[i], false);
			}
			else{
				this.squares[7][i] = new Square(this.whiteLineUp[i], true);
			}
		}
	}
	
	public boolean isKingAlive(){
		int numOfKings = 0;
		
		for (int r = 0; r < 8; r++){
			for (int c = 0; c < 8; c++){
				if (squares[r][c].getPiece() instanceof King){
					numOfKings++;
				}
			}
		}
		if (numOfKings != 2){//who wins is determined by whos turn was last
			return false;
		}
		return true;
	}
	
	
	public String whoWon(){
		String winner = "";
		for (int r = 0; r < 8; r++){
			for (int c = 0; c < 8; c++){
				if (squares[r][c].getPiece() instanceof King){					
					if (squares[r][c].getPiece().getPieceColor().equals("white")){
						winner = "white";
					}else {
						winner = "black";
					}
				}
			}
		}
		return winner;
	}
	
	public boolean isInsideBoard(int r, int c) {
		return r >= 0 && r < 8 && c >= 0 && c < 8;
	}

}
