package Modules.Cards.iface;

import Core.GameController;
import Modules.Player.impl.Player;

public interface IActionCard extends ICard{
    public void use(GameController g, Player p);
}
