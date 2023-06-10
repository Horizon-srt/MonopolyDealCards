package Modules.Cards.impl;

import Core.GameController;
import Core.TerminalView;
import Modules.Cards.iface.IPropertyCard;
import Modules.Player.impl.Player;

import java.util.LinkedList;

public class DealBreakerCard extends ActionCard{
    public DealBreakerCard(String name, int value) {
        super(name, value);
    }

    @Override
    public void use(GameController g, Player p) {
        TerminalView tv = g.tv;
        LinkedList<IPropertyCard> property = tv.getProperty();

    }
}
