public class Board {
    Spot[][] spots = new Spot[8][8];

    public Board() {
        this.resetBoard();
    }

    public Spot getSpot(int x, int y){
        return spots[x][y];
    }

    public void resetBoard() {

        spots[0][4] = new Spot(0, 4, new King(false, "K"));
        spots[7][4] = new Spot(7, 4, new King(true, "K"));

        spots[0][3] = new Spot(0, 3, new Queen(false, "Q"));
        spots[7][3] = new Spot(7,3, new Queen(true, "Q"));

        spots[0][0] = new Spot(0,0, new Rook(false, "R"));
        spots[0][7] = new Spot(0, 7, new Rook(false, "R"));
        spots[7][0] = new Spot(7,0, new Rook(true, "R"));
        spots[7][7] = new Spot(7,7, new Rook(true, "R"));

        spots[0][1] = new Spot(0,1, new Knight(false, "k"));
        spots[0][6] = new Spot(0, 6, new Knight(false, "k"));
        spots[7][1] = new Spot(7,1, new Knight(true, "k"));
        spots[7][6] = new Spot(7,6, new Knight(true, "k"));

        spots[0][2] = new Spot(0,2, new Bishop(false, "B"));
        spots[0][5] = new Spot(0,5, new Bishop(false, "B"));
        spots[7][2] = new Spot(7,2, new Bishop(true, "B"));
        spots[7][5] = new Spot(7,5, new Bishop(true, "B"));

        for (int y = 0; y < 8; y++) {
            spots[1][y] = new Spot(1, y, new Pawn(false, "p"));
        }
        for (int y = 0; y < 8; y++) {
            spots[6][y] = new Spot(6, y, new Pawn(true, "p"));
        }

        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                spots[i][j] = new Spot(i,j, new Empty(false, " "));
            }
        }

    }

    public void resetBoardTest() {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                spots[i][j] = new Spot(i,j, new Empty(false, " "));
            }
        }

        spots[2][3] = new Spot(2, 2, new King(false, "K"));
        spots[6][3] = new Spot(6, 3, new King(true, "K"));

        spots[4][6] = new Spot(4, 6 ,new Queen(false, "Q"));
        spots[5][1] = new Spot(5,1, new Queen(true, "Q"));

        
        spots[3][7] = new Spot(3,7, new Rook(true, "R"));
        

        spots[7][6] = new Spot(7,6, new Knight(false, "k"));
   
    }

    public void printBoard() {
        System.out.println("     a   b   c   d   e   f   g   h  ");
        System.out.println("   ---------------------------------");
        for (int i = 0; i < 8; i++) {
            System.out.print(i + 1 + "  | ");
            for (int j = 0; j < 8; j++) {
                System.out.print(spots[i][j].getPiece().getPrintable() + " | " );
                
            }
            System.out.println();
            System.out.println("   ---------------------------------");
        }
    }

    public void printBoardTest() {
        System.out.println(" y   0   1   2   3   4   5   6   7  ");
        System.out.println("x  ---------------------------------");
        for (int i = 0; i < 8; i++) {
            System.out.print(i + "  | ");
            for (int j = 0; j < 8; j++) {
                System.out.print(spots[i][j].getPiece().getPrintable() + " | " );
                
            }
            System.out.println();
            System.out.println("   ---------------------------------");
        }
    }
}