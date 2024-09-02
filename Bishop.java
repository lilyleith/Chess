public class Bishop extends Piece {
    
    public Bishop(boolean white, String s) {
        super(white, s);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        if (!end.getPiece().getPrintable().equals(" ")) {
            if (end.getPiece().isWhite() == this.isWhite()) {
                return false;
            }
        }

        if (Math.abs(start.getX() - end.getX()) / (Math.abs(start.getY() - end.getY())) != 1) {
            return false;
        }
        int change = Math.abs(start.getX() - end.getX());
        int xMultiplier = 1;
        int yMultiplier = 1;
        if (end.getX() - start.getX() < 0) {
            xMultiplier = -1;
        }
        if (end.getY() - start.getY() < 0) {
            yMultiplier = -1;
        }

        for (int i = 1; i < change; i++) {
            if (!board.getSpot(start.getX() + i * xMultiplier, start.getY() + i * yMultiplier).getPiece().getPrintable().equals(" ")) {
                return false;
            }
        }
        return true;
    }
    
}
