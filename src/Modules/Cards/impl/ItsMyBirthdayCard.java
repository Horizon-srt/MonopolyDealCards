package Modules.Cards.impl;

import Core.GameController;
import Core.ITerminalView;
import Core.TerminalView;
import Modules.Piles.impl.BankPile;
import Modules.Player.impl.Player;
import utils.Type;

/**
 * Request 2M per player from all players
 */
public class ItsMyBirthdayCard extends ActionCard{
    public ItsMyBirthdayCard(String name, int value) {
        super(name, value);
    }

    @Override
    public void use(GameController g, Player p) {
        TerminalView tv = g.tv;
        for (Player q : g.players) {
            if (q != p) {
                int i = tv.askJustSayNo(q, "ItsMyBirthdayCard");
                if (i > 0) {
                    Card justSayNo = p.getHp().getCardById(i);
                    g.dp.setCard(justSayNo);
                    continue;
                }
                Card[] rent = tv.rent(p, q, 2);
                if (rent == null || rent.length == 0)
                    return;
                for (Card card : rent) {
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

    }
}
