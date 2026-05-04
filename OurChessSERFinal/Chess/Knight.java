//Knight.java

public class Knight extends Piece{

	public Knight(String pieceColor){
		super(pieceColor);

	}
	public String getPieceType() { 
		return "N"; 
	}


	public boolean isLegal(int oldRow, int oldCol, int newRow, int newCol, Board board){
		int colDifference = newCol - oldCol; // could be negative
		int rowDifference = newRow - oldRow;
		
		if ((Math.abs(rowDifference) == 2 && Math.abs(colDifference) == 1) || (Math.abs(rowDifference) == 1 && Math.abs(colDifference) == 2)) {
			return true;
		}
		failNotice();
		return false;
	}
}
