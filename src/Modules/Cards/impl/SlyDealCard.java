package Modules.Cards.impl;

import Core.GameController;
import Core.TerminalView;
import Modules.Player.impl.Player;

import java.util.ArrayDeque;

/**
 * Stealing a piece of property from another player,
 * and unable to steal one piece of the complete set of property.
 * It can be added to one's own properties or deposited into a bank.
 */
public class SlyDealCard extends ActionCard{
    public SlyDealCard(String name, int value) {
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
        int i = tv.askJustSayNo(q, "SlyDealCard");
        if (i > 0) {
            Card justSayNo = p.getHp().getCardById(i);
            g.dp.setCard(justSayNo);
            return;
        }
        PropertyCard targetProperty = tv.slyForceDealTargetProperty(q);
        q.getPp().removeCard(targetProperty);

        p.getPp().setCard(targetProperty);
    }
}
