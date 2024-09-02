public class Pawn extends Piece {
    private boolean hasMoved = false;

    public Pawn(boolean white, String s) {
        super(white, s);
    }

    public void setMoved(boolean moved) {
        this.hasMoved = moved;
    }

    public boolean getHasMoved() {
        return this.hasMoved;
    }
    @Override
    public boolean canMove(Board board, Spot start, Spot end) {

        System.out.println(this.isWhite());
        if (this.isWhite()) {
            if (end.getX() - start.getX() > 0) {
                System.out.println("hello 1");
                return false;
            } else {
                if (end.getX() - start.getX() == -1 && end.getY() == start.getY()) {
                    if (!board.getSpot(end.getX(),start.getY()).getPiece().getPrintable().equals(" ")) {
                        return false;
                    }
                } else if (end.getX() - start.getX() == -1 && Math.abs(end.getY() - start.getY()) == 1) {
                    if (board.getSpot(end.getX(), end.getY()).getPiece().getPrintable().equals(" ")) {
                        return false;
                    } else if (board.getSpot(end.getX(), end.getY()).getPiece().isWhite() == this.isWhite()) {
                        return false;
                    }
                } else if (end.getX() - start.getX() == -2 && end.getY() - start.getY() == 0 && !this.getHasMoved()) {
                    if (!board.getSpot(start.getX() - 1, start.getY()).getPiece().getPrintable().equals(" ") ||
                    !board.getSpot(start.getX() - 2, start.getY()).getPiece().getPrintable().equals(" ")) {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        } else {
            if (end.getX() - start.getX() < 0) {
                return false;
            } else {
                if (end.getX() - start.getX() == 1 && end.getY() - start.getY() == 0) {
                    if (!board.getSpot(end.getX(),start.getY()).getPiece().getPrintable().equals(" ")) {
                        return false;
                    }
                } else if (end.getX() - start.getX() == 1 && Math.abs(end.getY() - start.getY()) == 1) {
                    if (board.getSpot(end.getX(), end.getY()).getPiece().getPrintable().equals(" ")) {
                        return false;
                    } else if (board.getSpot(end.getX(), end.getY()).getPiece().isWhite() == this.isWhite()) {
                        return false;
                    }
                } else if (end.getX() - start.getX() == 2 && end.getY() - start.getY() == 0 && !this.getHasMoved()) {
                    if (!board.getSpot(start.getX() + 1, start.getY()).getPiece().getPrintable().equals(" ") ||
                    !board.getSpot(start.getX() + 2, start.getY()).getPiece().getPrintable().equals(" ")) {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }

        this.setMoved(true);
        return true;

        
    }
    
}
