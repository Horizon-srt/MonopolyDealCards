package Modules.Cards.impl;

import Core.GameController;
import Core.TerminalView;
import Modules.Player.impl.Player;

import java.util.ArrayDeque;

/**
 * Swap one of the other players' property cards with one of your property cards,
 * and the resulting card can also be deposited in the bank
 */
public class ForceDealCard extends ActionCard{
    public ForceDealCard(String name, int value) {
        super(name, value);
    }

    @Override
    public void use(GameController g, Player p) {
        TerminalView tv = g.tv;
        ArrayDeque<Player> players = new ArrayDeque<>();
        for (Player player : g.players) {
            if (player != p) {
                players.add(player);
            }
        }
        Player q = tv.getTargetplayer(players.toArray(new Player[players.size()]));
        int i = tv.askJustSayNo(q, "ForceDealCard");
        if (i > 0) {
            Card justSayNo = p.getHp().getCardById(i);
            g.dp.setCard(justSayNo);
            return;
        }
        PropertyCard targetProperty = tv.slyForceDealTargetProperty(q);
        PropertyCard ownProperty = tv.forceDealOwnProperty(p);
        q.getPp().removeCard(targetProperty);
        p.getPp().removeCard(ownProperty);
        q.getPp().setCard(ownProperty);
        p.getPp().setCard(targetProperty);
    }
}
