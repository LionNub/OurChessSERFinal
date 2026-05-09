// Griffin, Hernest, Jared

//Updated
public class Pawn extends Piece{

	public Pawn(Type type, Color pieceColor){
		super(type, pieceColor);
	
	}
	
	public String getPieceType() { 
		return "P"; 
	}


	public boolean isLegal(int oldRow, int oldCol, int newRow, int newCol, Board board){
		int colDifference = newCol - oldCol; // could be negative
		int rowDifference = newRow - oldRow;
		
		if (this.getColor() == Color.WHITE){	 // if white pawn
			if (newRow == oldRow - 1 && Math.abs(newCol - oldCol) == 1 && board.squares[newRow][newCol].getPiece() != null &&
board.squares[newRow][newCol].getPiece().getColor() != this.getColor()) {//If theres a piece diagonally in front
				return true;
			}
							
			if (newRow == oldRow - 1 && newCol == oldCol && board.squares[newRow][newCol].getPieceType() == null){
				//System.out.println("moving [" + oldRow + "][" + oldCol + " to [" + newRow + "][" + newCol + "]");
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
			if (newRow == oldRow + 1 && Math.abs(newCol - oldCol) == 1 && board.squares[newRow][newCol].getPiece() != null &&
board.squares[newRow][newCol].getPiece().getColor() != this.getColor()) {
				return true;
			}
			if (newRow == oldRow + 1 && newCol == oldCol && board.squares[newRow][newCol].getPiece() == null){
				//System.out.println("moving [" + oldRow + "][" + oldCol + " to [" + newRow + "][" + newCol + "]");
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
