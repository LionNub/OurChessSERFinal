//King.java

public class King extends Piece{

	public King(String pieceColor){
		super(pieceColor);

	}
	
	public String getPieceType() { 
		return "K"; 
	}

	public boolean isLegal(int oldRow, int oldCol, int newRow, int newCol, Board board){
		
		int colDifference = newCol - oldCol; // could be negative
		int rowDifference = newRow - oldRow;

		if ((Math.abs(rowDifference) <= 1 && Math.abs(colDifference) <= 1) && !(board.squares[newRow][newCol].getPiece() instanceof King)) {
			return true;
		}
		failNotice();
		return false;
	}
}
