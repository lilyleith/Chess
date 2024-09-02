public class King extends Piece {
    private boolean castled = false;
    

    public King(boolean white, String s) {
        super(white, s);
    }

    public boolean hasCastled() {
        return this.castled;
    }

    public void setCastled(boolean c) {
        this.castled = c;
    }

    
    // for now, just checking if there is a piece of same color there
    // or if move is too long
    
    public boolean canMove(Board board, Spot start, Spot end)  {
        
        
        // ensuring that the target spot doesn't contain a white piece
        if (!end.getPiece().getPrintable().equals(" ")) {
            if (end.getPiece().isWhite() == this.isWhite()) {
                return false;
            }
        }

        // check that the target spot is not out of range of the source piece
        if (Math.abs(start.getX() - end.getX()) <= 1 && Math.abs(start.getY() - end.getY()) <= 1) {
            // if (this.putsKingInCheck(board, start, end)) {
            //     return false;
            // }
            return true;
        }
        boolean validCastle = isValidCastle(board, start, end);
        this.setCastled(validCastle);
        return validCastle;
    }

    public boolean isValidCastle(Board board, Spot start, Spot end) {
        if (this.hasCastled()) {
            return false;
        }
        return this.isCastleMove(board, start, end);
    }

    public boolean isCastleMove(Board board, Spot start, Spot end)  {
        
        
        // check if the king stays on the same row
        if (start.getX() != end.getX()) {
            return false;
        }

        // check if the move is too far to the left or right
        if (Math.abs(start.getY() - end.getY()) > 2) {
            return false;
        }
        // if the castle is to the left hand rook
        if (start.getY() - end.getY() > 0) {
            Piece intendedCastle = board.getSpot(start.getX(), 0).getPiece();
            // if the piece on far left end is not a rook return false
            if (!intendedCastle.getPrintable().equals("R")) {
                return false;
            } 
            // if the spots between the king and rook are not empty return false
            for (int i = 1; i < 4; i++) {
                if (!board.getSpot(start.getX(), start.getY() - i).getPiece().getPrintable().equals(" ")) {
                    return false;
                }
            }
        // if castling to right
        } else {
            Piece intendedCastle = board.getSpot(start.getX(), 7).getPiece();
            if (!intendedCastle.getPrintable().equals("R")) {
                return false;
            }
            for (int i = 1; i < 3; i++) {
                if (!board.getSpot(start.getX(), start.getY() + i).getPiece().getPrintable().equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }

    // public boolean putsKingInCheck(Board board, Spot start, Spot end) {
    //     /*
    //     * to check if king would be in check:
    //     * check surrounding area: go as far as you can in all directions
    //     * when you hit a piece, check if that piece is a valid move to kings target
    //     * knights: do later
    //     * make sure not out of bounds
    //     * keep multipliers in an array
    //     diagonal x+ y+
    //     vertical x+ y0
    //     diagonal x+ y-
    //     horizontal x0 y-
    //     diagonal x- y-
    //     vertical x- y0
    //     diagonal x- y+
    //     horizontal x0 y+
    //     BASE IS THE TARGET SPOT
    //     */

    //     int[][] multipliers = {{1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1,-1}, {-1, 0}, {-1, 1}, {0, 1}};
    //     int xMultiplier;
    //     int yMultiplier;
    //     int targetX = end.getX();
    //     int targetY = end.getY();
    //     int checkX;
    //     int checkY;
    //     int i;
    //     boolean noPiece;

    //     for (int m = 0; m < 8; m++) {
    //         xMultiplier = multipliers[m][0];
    //         yMultiplier = multipliers[m][1];
    //         noPiece = true;
    //         i = 1;
    //         while(noPiece) {
    //             checkX = targetX + i * xMultiplier;
    //             checkY = targetY + i * yMultiplier;
    //             if (checkX > 7 || checkX < 0 || checkY > 7 || checkY < 0) {
    //                 break;
    //             }
    //             if (!board.getSpot(checkX, checkY).getPiece().getPrintable().equals(" ")) {
    //                 if (board.getSpot(checkX, checkY).getPiece().isWhite() == this.isWhite()) {
    //                     break;
    //                 } else {
    //                     if (board.getSpot(checkX, checkY).getPiece().canMove(board, board.getSpot(checkX, checkY), end)) {
    //                         System.out.println("The " + board.getSpot(checkX, checkY).getPiece().getPrintable() + " puts the King in check!");
    //                         return true;
    //                     }
    //                 }
    //             }
    //             i++;
    //         }

    //     }

    //     /*
    //      * knight adders:
    //      * horiz down right x+1 y+2
    //      * vert down right x+2 y+1
    //      * vert down left x+2 y-1
    //      * horiz down left x+1 y-2
    //      * horiz up left x-1 y-2
    //      * vert up left x-2 y-1
    //      * vert up right x-2 y+1
    //      * horiz up right x-1 y+2
    //      */

    //     int[][] knightAdders = {{1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}};
    //     int xAdder;
    //     int yAdder;
    //     for (int m = 0; m < 8; m++) {
    //         xAdder = knightAdders[m][0];
    //         yAdder = knightAdders[m][1];
    //         checkX = targetX + xAdder;
    //         checkY = targetY + yAdder;
    //         if (checkX > 7 || checkX < 0 || checkY > 7 || checkY < 0) {
    //             continue;
    //         }
    //         if (board.getSpot(checkX, checkY).getPiece().getPrintable().equals("k") && 
    //         board.getSpot(checkX, checkY).getPiece().isWhite() != this.isWhite()) {
    //             System.out.println("the knight would put the king in check, cant move");
    //             return true;
    //         }
    //     }

    //     return false;
    // }

    

}