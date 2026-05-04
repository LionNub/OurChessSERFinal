//Square.java

public class Square { 

	private Piece piece;
	//pieceColor is now stored in Piece object
	private boolean isWhiteSquare;
	
	
	public Square(Piece piece, boolean isWhiteSquare){
		this.piece = piece;
		this.isWhiteSquare = isWhiteSquare;
	}
	
	
	public void move(Square newSquare){ //Assumes the move is legal
		newSquare.piece = this.piece; //this.piece is the old square
		erase(); 
	}	
		
	public boolean isLegal(int oldRow, int oldCol, int newRow, int newCol, Board board) {//Instead of switch statement, calls isLegal in piece classes
		if (piece == null) {
			System.out.println("No piece selected");
			return false;
		}
		
		if (!board.isInsideBoard(newRow, newCol)) {
			System.out.println("Illegal move, try again.");
			return false;
		}

		Square destination = board.squares[newRow][newCol];
		
		
		if (destination.getPiece() != null && destination.getPieceColor().equals(this.getPieceColor())) {
			System.out.println("Illegal move, try again.");
			return false;
		}
			return piece.isLegal(oldRow, oldCol, newRow, newCol, board);
		}

	
	public void erase(){
		this.piece = null;
	}

	public Piece getPiece(){//returns the piece
		return piece;
	}
	
	public String getPieceType() {
		if (piece == null) {
			return null;
		}
		return piece.getPieceType();
	}

	public String getPieceColor() {
		if (piece == null) {
			return null;
		}
		return piece.getPieceColor();
	}
	
	public boolean isWhiteSquare(){
		return isWhiteSquare;
	}
	
	public void failNotice(){
		System.out.println("Illegal move, try again.");
	}
	
}
