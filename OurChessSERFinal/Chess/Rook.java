//Rook.java

public class Rook extends Piece{

	public Rook(String pieceColor){
		super(pieceColor);

	}
	
	public String getPieceType() { 
		return "R"; 
	}

	public boolean isLegal(int oldRow, int oldCol, int newRow, int newCol, Board board){
		
		int colDifference = newCol - oldCol; // could be negative
		int rowDifference = newRow - oldRow;
		
		if (rowDifference != 0 && colDifference != 0) { //if the selected position is diagonal, return false
			failNotice();
			return false;
		}
		int step;

		if (rowDifference == 0) { //If no change in Y

			if (colDifference > 0){
				step = 1; //Moving Right
			}else{
				step = -1; //Moving Left
			}
		
			for (int c = oldCol + step; c != newCol; c += step) { // Starts next to the rook, goes right or left until it hits the target (newCol)
				if (!isEmpty(board, oldRow, c)) {//Cannot skip over other pieces
					failNotice();
					return false;
				}

			}
			return true;	
		}
		if (colDifference == 0) { //If no change in X
			
			if (rowDifference > 0){
				step = 1; //Moving Up
			}else{
				step = -1; //Moving Down
			}

			for (int r = oldRow + step; r != newRow; r += step) { // Starts next to the rook, goes up or down until it hits the target (newRow)
				if (!isEmpty(board, r, oldCol)) {//Cannot skip over other pieces
					failNotice();
					return false;
				}

			}
			return true;
		}
		failNotice();
		return false;
		
	}
}
