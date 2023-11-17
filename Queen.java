public class Queen extends Piece {

    public Queen(boolean white, String s) {
        super(white, s);
    }
    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        if (!end.getPiece().getPrintable().equals(" ")) {
            if (end.getPiece().isWhite() == this.isWhite()) {
                return false;
            }
        }

        int xchange = Math.abs(end.getX() - start.getX());
        int ychange = Math.abs(end.getY() - start.getY());
        int xMultiplier = 1;
        int yMultiplier = 1;
        if (end.getX() - start.getX() < 0) {
            xMultiplier = -1;
        }
        if (end.getY() - start.getY() < 0) {
            yMultiplier = -1;
        }
        if (Math.abs(start.getX() - end.getX())  ==  Math.abs(start.getY() - end.getY())) {
            int change = Math.abs(start.getX() - end.getX());

            for (int i = 1; i < change; i++) {
                if (!board.getSpot(start.getX() + i * xMultiplier, start.getY() + i * yMultiplier).getPiece().getPrintable().equals(" ")) {
                    return false;
                }
            }
        } else if (xchange != 0 && ychange == 0) {
            
            for (int i = 1; i < xchange; i++) {
                if (!board.getSpot(start.getX() + i * xMultiplier, start.getY()).getPiece().getPrintable().equals(" ")) {
                    return false;
                }
            }
        } else if (xchange == 0 && ychange != 0) {
            for (int i = 1; i < ychange; i++) {
                if (!board.getSpot(start.getX(), start.getY() + i * yMultiplier).getPiece().getPrintable().equals(" ")) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }
    
}
