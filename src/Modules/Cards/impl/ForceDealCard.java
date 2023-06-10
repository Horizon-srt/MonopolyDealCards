package Modules.Cards.impl;

import Core.GameController;
import Core.TerminalView;
import Modules.Player.impl.Player;

public class ForceDealCard extends ActionCard{
    public ForceDealCard(String name, int value) {
        super(name, value);
    }

    @Override
    public void use(GameController g, Player p) {
        TerminalView tv = g.tv;
        Player q = tv.getTargetPlayer(p);
        Card[] cards = g.tv.rent(p,q,this.getValue());

    }
}
