import Core.GameController;
import Core.TerminalView;

public class Main {
    public static void main(String[] args) {
        GameController g = GameController.getGame();
        TerminalView tv = TerminalView.getTerminalView();
        g.start(tv);
        System.out.println("Bye-bye!");
    }
}