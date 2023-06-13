package Modules.Cards.impl;

import Core.GameController;
import Core.TerminalView;
import Modules.Cards.iface.IPropertyCard;
import Modules.Piles.impl.PropertyPile;
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
        PropertyPile pp = p.getPp();
        PropertyPile qp = q.getPp();
        int index = tv.getPropertyIndex(q);
        LinkedList<IPropertyCard> property = qp.getProperty(index);
        pp.getPropertyList().addLast(property);
        pp.getBuilding().put(pp.getPropertyList().indexOf(property) + 1, qp.getBuilding().get(index));
        qp.getBuilding().remove(index);
    }
}
