//Pawn.java
public class Pawn extends Piece{

	public Pawn(String pieceColor){
		super(pieceColor);
	
	}
	
	public String getPieceType() { 
		return "P"; 
	}


	public boolean isLegal(int oldRow, int oldCol, int newRow, int newCol, Board board){
		
		int colDifference = newCol - oldCol; // could be negative
		int rowDifference = newRow - oldRow;
		
		if (this.getPieceColor().equals("white")){	 // if white pawn
			if (newRow == oldRow - 1 && Math.abs(newCol - oldCol) == 1 && board.squares[newRow][newCol].getPieceType() != null){//If theres a piece diagonally in front
				return true;
			}
							
			if (newRow == oldRow - 1 && newCol == oldCol && board.squares[newRow][newCol].getPieceType() == null){
				System.out.println("moving [" + oldRow + "][" + oldCol + " to [" + newRow + "][" + newCol + "]");
				return true;
			}
			
			if (newRow == oldRow - 2 && newCol == oldCol && oldRow == 6// if pawn moves forward 2 squares
				&& board.squares[oldRow-1][oldCol].getPiece() == null
				&& board.squares[newRow][newCol].getPiece() == null) {
				return true;
			}
			failNotice();
			return false;
			
		}else{//if black pawn
			if (newRow == oldRow + 1 && Math.abs(newCol - oldCol) == 1 && board.squares[newRow][newCol].getPiece() != null) {
				return true;
			}
			if (newRow == oldRow + 1 && newCol == oldCol && board.squares[newRow][newCol].getPiece() == null){
				IO.println("moving [" + oldRow + "][" + oldCol + " to [" + newRow + "][" + newCol + "]");
				return true;
			}
			if (newRow == oldRow + 2 && newCol == oldCol && oldRow == 1
				&& board.squares[oldRow+1][oldCol].getPiece() == null
				&& board.squares[newRow][newCol].getPiece() == null) {
				return true;
			}
			failNotice();
			return false;
		}


	}



}
