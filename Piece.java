public abstract class Piece {
    private boolean taken = false;
    private boolean white = false;
    private String printable = "";

    public abstract boolean canMove(Board board, Spot start, Spot end);

    public Piece(boolean white, String s) {
        this.setWhite(white);
        this.setPrintable(s);
    }

    public boolean isWhite() {
        return this.white;
    }

    public void setWhite(boolean w) {
        this.white = w;
    }

    public boolean isTaken() {
        return this.taken;
    }

    public void setTaken(boolean t) {
        this.taken = t;
    }

    public String getPrintable() {
        return this.printable;
    }

    public void setPrintable(String s) {
        this.printable = s;
    }

    
    
}