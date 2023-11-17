import java.util.Scanner;

public class tester {
    
    public static void main(String[] args) throws Exception {
        Player[] players = new Player[2];
        
        Scanner in = new Scanner(System.in);
        Player p1 = new Player(true);
        Player p2 = new Player(false);
        players[0] = p1;
        players[1] = p2;
        Game game = new Game(p1, p2);
        game.printGame();

        Player currentPlayer = players[0];
        System.out.println("You are white. Move!");
        while(true) {
            System.out.println("Enter move in the form <startx> <starty> <endx> <endy>");
            String[] input = in.nextLine().split(" ");
            System.out.println(Integer.valueOf(input[0]));
            if (!game.playerMove(currentPlayer, Integer.valueOf(input[0]), Integer.valueOf(input[1]), Integer.valueOf(input[2]), Integer.valueOf(input[3]))) {
                System.out.println("Play ignored!");
                continue;
            }
            game.printGame();
            System.out.println();

            if (currentPlayer == players[0]) {
                currentPlayer = players[1];
            } else {
                currentPlayer = players[0];
            }
        }



    }
}
