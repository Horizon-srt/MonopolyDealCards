package Modules.Cards.impl;

import Core.GameController;
import Core.TerminalView;
import Modules.Piles.impl.AllPile;
import Modules.Piles.impl.HandPile;
import Modules.Player.impl.Player;

public class PassGoCard extends ActionCard {
    public PassGoCard(String name, int value) {
        super(name, value);
    }

    @Override
    public void use(GameController g, Player p) {
        TerminalView tv = g.tv;
        for (Player q : g.players) {
            if (q != p) {
                int i = tv.askJustSayNo(q, "PassGoCard");
                if (i > 0) {
                    Card justSayNo = p.getHp().getCardById(i);
                    g.dp.setCard(justSayNo);
                    return;
                }
            }
        }
        HandPile hp = p.getHp();
        hp.setCard(g.ap.getCard());
        hp.setCard(g.ap.getCard());
    }
}
