package Modules.Cards.impl;

import Core.GameController;
import Core.TerminalView;
import Modules.Player.impl.Player;
import utils.Type;

public class DebtCollectorCard extends ActionCard{
    public DebtCollectorCard(String name, int value) {
        super(name, value);
    }

    @Override
    public void use(GameController g, Player p) {
        TerminalView tv = g.tv;
        Player q = tv.getTargetplayer();
        for (Card card : tv.rent(p, q, 5)) {
            if (card.getType() == Type.MONEY || card.getType() == Type.ACTION) {
                p.getBp().setCard(card);
                q.getBp().removeCard(card);
            } else {
                p.getPp().setCard(card);
                q.getPp().removeCard(card);
            }
        }

    }
}
