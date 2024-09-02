public class Rook extends Piece {
    private boolean castled = false;

    public Rook(boolean white, String s) {
        super(white, s);
    }

    public boolean hasCastled() {
        return this.castled;
    }

    public void setCastled(boolean c) {
        this.castled = c;
    }
    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        if (Math.abs(start.getX() - end.getX()) != 0 && Math.abs(start.getY() - end.getY()) != 0) {
            return false;
        }

        if (!end.getPiece().getPrintable().equals(" ")) {
            if (end.getPiece().isWhite() == this.isWhite()) {
                return false;
            }
        }


        if (start.getX() - end.getX() != 0) {
            int change = end.getX() - start.getX();
            if (change < 0) {
                System.out.println(change);
                for (int i = 1; i < Math.abs(change); i++) {
                    System.out.println(board.getSpot(start.getX() - 1, start.getY()).getPiece().getPrintable());
                    if (!board.getSpot(start.getX() - i, start.getY()).getPiece().getPrintable().equals(" ")) {
                        return false;
                    }
                }
            } else {
               for (int i = 1; i < change + 1; i++) {
                    if (!board.getSpot(start.getX() + i, start.getY()).getPiece().getPrintable().equals(" ")) {
                        return false;
                    }
                } 
            }
        } else {
            int change = end.getY() - start.getY();
            if (change < 0) {
                for (int i = 1; i < change + 1; i++) {
                    if (!board.getSpot(start.getX(), start.getY() - i).getPiece().getPrintable().equals(" ")) {
                        return false;
                    }
                }
            } else {
               for (int i = 1; i < change + 1; i++) {
                    if (!board.getSpot(start.getX(), start.getY() + i).getPiece().getPrintable().equals(" ")) {
                        return false;
                    }
                } 
            }
        }
        return true;
    }
    
}
