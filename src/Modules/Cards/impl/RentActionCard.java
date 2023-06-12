package Modules.Cards.impl;

import Core.GameController;
import Core.TerminalView;
import Modules.Player.impl.Player;
import utils.Building;
import utils.Color;

import java.util.ArrayDeque;

public class RentActionCard extends ActionCard{
    public Color color1;
    public Color color2;
    public RentActionCard(String name, int value, Color color1, Color color2) {
        super(name, value);
        this.color1 = color1;
        this.color2 = color2;
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
        int index = tv.rentPropertyIndex(p);
        String color = tv.rentPropertyColor(p);

        int n = tv.rentPropertyIndex(p);
        if (color1  == Color.ALL && color2 == Color.ALL) {
            Building building = p.getPp().getBuilding().get(index);
            
        } else {

        }
    }
}
