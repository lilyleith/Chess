

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
        } else {
            this.currentTurn = p2;
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
        
        Piece source = move.getStart().getPiece();
        
        // if there is actually a piece there
        if (source.getPrintable().equals(" ")) {
            return false;
        }

        if (currentTurn != p) {
            return false;
        }

        if (source.isWhite() != p.isWhiteSide()) {
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


}
