//Bishop.java
public class Bishop extends Piece{

	public Bishop(String pieceColor){
		super(pieceColor);

	}
	
	public String getPieceType() { 
		return "B"; 
	}

	public boolean isLegal(int oldRow, int oldCol, int newRow, int newCol, Board board){
		
		int colDifference = newCol - oldCol; // could be negative
		int rowDifference = newRow - oldRow;
		
		if (Math.abs(rowDifference) != Math.abs(colDifference)){//Checks if the new position isn't straight in any diagonal direction.
			failNotice();
			return false;
		}
				
		int rowStep;
		int colStep;

		if (rowDifference > 0) {//Checks if moving left or right
			rowStep = 1;
		} else {
			rowStep = -1;
		}

		
		if (colDifference > 0) {//Check if moving up or down
			colStep = 1;
		} else {
			colStep = -1;
		}
		
		// 2. Check all squares between start and end
		int r = oldRow + rowStep; //Where the piece is on y + 1
		int c = oldCol + colStep; //Where the piece is on x + 1

		while (r != newRow && c != newCol) { //While neither the x or y positions are reached
			if (!isEmpty(board, r, c)) { // Checks if there is a piece in between the starting position and the new position
				failNotice();
				return false; // If there is, it's illegal
			}
			r += rowStep;//Moves to the next row and col.
			c += colStep;
		}

		return true;
	}
}
