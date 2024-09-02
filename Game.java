

public class Game {
    private Player[] players;
    private Player currentTurn;
    private String whiteCaptures;
    private String blackCaptures;
    private Board board = new Board();
    
    public Game(Player p1, Player p2) {
        players = new Player[2];
        this.players[0] = p1;
        this.players[1] = p2;

        if (p1.isWhiteSide()) {
            this.currentTurn = p1;
            this.players[0].setKingLoc(7, 4);
            this.players[1].setKingLoc(0, 4);
        } else {
            this.currentTurn = p2;
            this.players[1].setKingLoc(7, 4);
            this.players[0].setKingLoc(0, 4);
        }

        whiteCaptures = "";
        blackCaptures = "";
        board.resetBoard();
    }

    /* private void startGame(Player p) {
        this.player = p;
        board.resetBoard();
    } */

    public void printGame() {
        board.printBoardTest();
    }

    public boolean playerMove(Player p, int startX, int startY, int endX, int endY) throws Exception {
        if (startX > 7 || startX < 0 || startY > 7 || startY < 0 || endX > 7 || endX < 0 || endY > 7 || endY < 0 ) {
            System.out.println("Invalid start or end position!");
            return false;
        }
        Spot start = board.getSpot(startX, startY);
        Spot end = board.getSpot(endX, endY);
        Move move = new Move(p, start, end);
        return this.makeMove(p, move);
    }

    public boolean makeMove(Player p, Move move) {
        if (currentTurn.whiteSide) {
            System.out.println("current turn is white side");
        } else {
            System.out.println("current turn is black side");
        }
        Piece source = move.getStart().getPiece();
        
        // if there is actually a piece there
        if (source.getPrintable().equals(" ")) {
            return false;
        }

        if (currentTurn != p) {
            System.out.println("It's not your move!");
            return false;
        }

        if (source.isWhite() != p.isWhiteSide()) {
            System.out.println("Souce piece doesn't belong to you!");
            return false;
        }

        // if the move is valid
        if (!source.canMove(board, move.getStart(), move.getEnd())) {
            return false;
        }

        Piece targetPiece = move.getEnd().getPiece();
        if (!targetPiece.getPrintable().equals(" ")) {
            targetPiece.setTaken(true);
            if (p.isWhiteSide()) {
                whiteCaptures = whiteCaptures + " " + targetPiece.getPrintable();
            } else {
                blackCaptures = blackCaptures + " " + targetPiece.getPrintable();
            }
        }


        if (source.getPrintable().equals("K") && Math.abs(move.getStart().getY() - move.getEnd().getY()) > 1) {
            if (move.getEnd().getY() - move.getStart().getY() < 0) {
                Spot rook = board.getSpot(move.getStart().getX(), 0);
                Spot target = board.getSpot(move.getStart().getX(), move.getStart().getY() - 1);
                Move rookMove = new Move(p, rook, target);
                rookMove.getEnd().setPiece(rookMove.getStart().getPiece());
                rookMove.getStart().setPiece(new Empty(false, " "));

            } else {
                Spot rook = board.getSpot(move.getStart().getX(), 7);
                Spot target = board.getSpot(move.getStart().getX(), move.getStart().getY() + 1);
                Move rookMove = new Move(p, rook, target);
                rookMove.getEnd().setPiece(rookMove.getStart().getPiece());
                rookMove.getStart().setPiece(new Empty(false, " "));

            }
        }

        

        
        // set the end spot to the source piece
        move.getEnd().setPiece(move.getStart().getPiece());
        move.getStart().setPiece(new Empty(false, " "));

        Integer[] oldKingLoc = new Integer[2];
        oldKingLoc[0] = move.getStart().getX();
        oldKingLoc[1] = move.getStart().getY(); 
        // check if either player is in check:
        if (source.getPrintable().equals("K")) {
            
            if (this.currentTurn == players[0]) {
                this.players[0].setKingLoc(move.getEnd().getX(), move.getEnd().getY());
            } else {
                this.players[1].setKingLoc(move.getEnd().getX(), move.getEnd().getY());
            }

        }

        // if the current player put themselves in check, undo the action
        if(inCheck(p)) {
            System.out.println("You put yourself in check! This is not allowed.");
            // reset the king location of the current player
            if (this.currentTurn == players[0]) {
                players[0].setKingLoc(oldKingLoc[0], oldKingLoc[1]);
            } else {
                players[1].setKingLoc(oldKingLoc[0], oldKingLoc[1]);
            }

            // reset the board
            move.getEnd().setPiece(targetPiece);
            move.getStart().setPiece(source);
        }

        if (this.currentTurn == this.players[0]) {
            if (inCheck(this.players[1])) {
                System.out.println("You put the other side in check! Good job!");
            } 
        } else {
            if (inCheck(this.players[0])) {
                System.out.println("You put the other side in check! Good job!");
            }
        }



        if (targetPiece.getPrintable().equals("K")) {
            if (p.isWhiteSide()) {
                System.out.println("White side win!");
            } else {
                System.out.println("Black side win!");
            }
        }

        if (this.currentTurn == players[0]) {
            this.currentTurn = players[1];
        } else {
            this.currentTurn = players[0];
        }
        
        return true;
    }

    // check if the current player is in check
    public boolean inCheck(Player p) {

        // multipliers for checking the area around the king in all directions
        int[][] multipliers = {{1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1,-1}, {-1, 0}, {-1, 1}, {0, 1}};
        int xMultiplier;
        int yMultiplier;

        // the spots that we are checking
        int checkX;
        int checkY;

        int i;
        boolean noPiece;

        // to iterate through the 2 players
        

    
        int targetX = p.getKingX();
        int targetY = p.getKingY();

        for (int m = 0; m < 8; m++) {
            xMultiplier = multipliers[m][0];
            yMultiplier = multipliers[m][1];
            noPiece = true;
            i = 1;
            while(noPiece) {
                checkX = targetX + i * xMultiplier;
                checkY = targetY + i * yMultiplier;
                if (checkX > 7 || checkX < 0 || checkY > 7 || checkY < 0) {
                    break;
                }
                if (!board.getSpot(checkX, checkY).getPiece().getPrintable().equals(" ")) {
                    if (board.getSpot(checkX, checkY).getPiece().isWhite() == p.isWhiteSide()) {
                        break;
                    } else {
                        if (board.getSpot(checkX, checkY).getPiece().canMove(board, board.getSpot(checkX, checkY), board.getSpot(targetX, targetY))) {
                            if (p.isWhiteSide()) {
                                System.out.println("The black " + board.getSpot(checkX, checkY).getPiece().getPrintable() + " puts the white King in check!");

                            } else {
                                System.out.println("The white " + board.getSpot(checkX, checkY).getPiece().getPrintable() + " puts black the King in check!");
                            }
                            return true;
                        }
                    }
                }
                i++;
            }

        }

        /*
        * knight adders:
        * horiz down right x+1 y+2
        * vert down right x+2 y+1
        * vert down left x+2 y-1
        * horiz down left x+1 y-2
        * horiz up left x-1 y-2
        * vert up left x-2 y-1
        * vert up right x-2 y+1
        * horiz up right x-1 y+2
        */

        int[][] knightAdders = {{1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}};
        int xAdder;
        int yAdder;
        for (int m = 0; m < 8; m++) {
            xAdder = knightAdders[m][0];
            yAdder = knightAdders[m][1];
            checkX = targetX + xAdder;
            checkY = targetY + yAdder;
            if (checkX > 7 || checkX < 0 || checkY > 7 || checkY < 0) {
                continue;
            }
            if (board.getSpot(checkX, checkY).getPiece().getPrintable().equals("k") && 
            board.getSpot(checkX, checkY).getPiece().isWhite() != p.isWhiteSide()) {
                System.out.println("the knight would put the king in check, cant move");
                return true;
            }
        }
        
        return false;
        
    }   


}
