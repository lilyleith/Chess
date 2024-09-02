public class Empty extends Piece {

    public Empty(boolean white, String s) {
        super(white, s);
    }

    


    @Override
    public boolean canMove(Board board, Spot s, Spot e) {
        return false;
    }

    
    
}
