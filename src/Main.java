import Core.GameController;
import Core.TerminalView;

public class Main {
    public static void main(String[] args) {
        // get a game controller and a terminalview
        GameController g = GameController.getGame();
        TerminalView tv = TerminalView.getTerminalView();
        // start the game
        g.start(tv);
        System.out.println("Bye-bye!");
    }
}