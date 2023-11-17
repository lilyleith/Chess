public class Knight extends Piece {

    public Knight(boolean white, String s) {
        super(white, s);
    }


    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        if (!end.getPiece().getPrintable().equals(" ")) {
            if (end.getPiece().isWhite() == this.isWhite()) {
                return false;
            }
        }
        if (Math.abs(start.getX() - end.getX()) * Math.abs(start.getY() - end.getY()) != 2) {
            return false; 
        }
        return true;
    }
    
    
}
