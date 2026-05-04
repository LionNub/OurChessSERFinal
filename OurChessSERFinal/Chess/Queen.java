//Queen.java

public class Queen extends Piece{

	public Queen(String pieceColor){
		super(pieceColor);

	}

	public String getPieceType() { 
		return "Q"; 
	}
	
	public boolean isLegal(int oldRow, int oldCol, int newRow, int newCol, Board board){
		/*
		int colDifference = newCol - oldCol; // could be negative
		int rowDifference = newRow - oldRow;

		int rowStep;
		int colStep;

		if  (Math.abs(rowDifference) == Math.abs(colDifference)) {//If its moving diagonally			

			if (rowDifference > 0) {
				rowStep = 1;   // moving down
			} else {
				rowStep = -1;  // moving up
			}

			if (colDifference > 0) {
				colStep = 1;   // moving right
			} else {
				colStep = -1;  // moving left
			}
			//Every iteration, moves diagonally in the direction of the target square
			for (int row = oldRow + rowStep, col = oldCol + colStep; row != newRow && col != newCol; row += rowStep, col += colStep) { 
				if (!isEmpty(board, row, col)) {
					failNotice();
					return false;
				}
			}
		
			return true;
		}else if (rowDifference == 0 || colDifference == 0) { // Straight (rook logic)
			
			if (rowDifference > 0) {
				rowStep = 1;   // moving down
			} else {
				rowStep = -1;  // moving up
			}

			if (colDifference > 0) {
				colStep = 1;   // moving right
			} else {
				colStep = -1;  // moving left
			}
			
			if (rowDifference == 0) {
				for (int myCol = oldCol + colStep; myCol != newCol; myCol += colStep) {
					if (!isEmpty(board, oldRow, myCol)) return false;
				}
			} else {
				for (int myRow = oldRow + rowStep; myRow != newRow; myRow += rowStep) {
					if (!isEmpty(board, myRow, oldCol)) return false;
				}
			}
			return true;
		}
		failNotice();
		return false; // Neither diagonal nor straight
		*/
		return true;
	}
}
