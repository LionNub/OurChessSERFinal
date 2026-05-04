//Piece.java`

public abstract class Piece{
	
	protected String pieceColor;

	
	public Piece(String pieceColor){
		this.pieceColor = pieceColor;
	}
	public Piece(){
		
		this.pieceColor = null;
	}
	
		
	public boolean isEmpty(Board board, int r, int c) {
		return board.squares[r][c].getPiece() == null;
	}

	public abstract boolean isLegal(int oldRow, int oldCol, int newRow, int newCol, Board board);
	
	public abstract String getPieceType();

	public void failNotice(){
		System.out.println("Illegal move, try again.");
	}
	
	public String getPieceColor() {
		return pieceColor;
	}
	

		
		
	
}
