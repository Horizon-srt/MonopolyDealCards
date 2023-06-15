package Core;

import Modules.Player.impl.Player;

public interface IGameController {
    /*
     * public methods for the game controller
     * Main.java will use start, Test.java will use testDataIn
     */

    public void start(TerminalView tv);

    public void testDataIn(Player p1, Player p2, TerminalView tv);
}
