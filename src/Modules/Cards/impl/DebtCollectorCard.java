package Modules.Cards.impl;

import Core.GameController;
import Core.TerminalView;
import Modules.Player.impl.Player;
import utils.Type;

import java.util.ArrayDeque;

public class DebtCollectorCard extends ActionCard{
    public DebtCollectorCard(String name, int value) {
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
        int i = tv.askJustSayNo(q, "DebtCollectorCard");
        if (i > 0) {
            Card justSayNo = p.getHp().getCardById(i);
            g.dp.setCard(justSayNo);
            return;
        }
        Card[] rent = tv.rent(p, q, 5);
        if (rent == null)
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
