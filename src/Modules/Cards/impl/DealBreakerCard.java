package Modules.Cards.impl;

import Core.GameController;
import Core.TerminalView;
import Modules.Cards.iface.IPropertyCard;
import Modules.Player.impl.Player;

import java.util.ArrayDeque;
import java.util.LinkedList;

public class DealBreakerCard extends ActionCard{
    public DealBreakerCard(String name, int value) {
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
        int i = tv.askJustSayNo(q, "DealBeakerCard");
        if (i > 0) {
            Card justSayNo = p.getHp().getCardById(i);
            g.dp.setCard(justSayNo);
            return;
        }
        LinkedList<IPropertyCard> property = tv.getProperty();
    }
}
