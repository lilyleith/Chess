public class Player {
    public boolean whiteSide;
    public Integer[] kingLoc;
    
    public Player(boolean whiteSide) {
        this.whiteSide = whiteSide;
        kingLoc = new Integer[2];
    }

    public boolean isWhiteSide() {
        return this.whiteSide;
    }

    public void setKingLoc(int x, int y) {
        kingLoc[0] = x;
        kingLoc[1] = y;
    }

    public int getKingX() {
        return kingLoc[0];
    }

    public int getKingY() {
        return kingLoc[1];
    }

}