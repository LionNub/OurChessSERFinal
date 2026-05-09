/**
 * Empty placeholder for chess move validation.
 * Assume a real rules engine lives here; for the demo every move is legal
 * (except moving onto a piece you already own).
 */

/** Return true if the side-to-move may move from (fromR,fromC) to (toR,toC). */
public class ChessRules {
	
    public boolean isLegalMove(Board board, int fromR, int fromC, int toR, int toC) {
        if (fromR == toR && fromC == toC) return false;
        Piece mover = board.squares[fromR][fromC].getPiece();
        if (mover == null) return false;
        Piece target = board.squares[toR][toC].getPiece();
        if (target != null && target.getColor() == mover.getColor()) return false;
		return mover.isLegal(fromR, fromC, toR, toC, board);  // add this line
    }
    
}
