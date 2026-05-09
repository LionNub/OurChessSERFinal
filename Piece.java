//Griffin, Hernest, Jared

//Updated
public abstract class Piece{
	
    private final Type type;
    private final Color color;

	public enum Type { PAWN, ROOK, KNIGHT, BISHOP, QUEEN, KING }
    public enum Color { WHITE, BLACK }

    public Piece(Type type, Color color) {
        this.type = type;
        this.color = color;
    }
    
	public Piece() {
		this.color = null;
		this.type = null;
	}
	
		
	public boolean isEmpty(Board board, int r, int c) {
		return board.squares[r][c].getPiece() == null;
	}

	public abstract boolean isLegal(int oldRow, int oldCol, int newRow, int newCol, Board board);
	
	public abstract String getPieceType();

	public void failNotice() {
		System.out.println("Illegal move, try again.");
	}
	//getPieceColor
	public Color getColor() {
		return color;
	}
	
	public Type getType(){
		return type;
	}
	

		
		
	
}
