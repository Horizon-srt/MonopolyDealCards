package Modules.Cards.impl;

import Core.GameController;
import Core.TerminalView;
import Modules.Player.impl.Player;
import utils.Building;

import java.util.Scanner;

public class HouseCard extends ActionCard{
    public HouseCard(String name, int value) {
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
                    return;
                }
            }
        }
        int index = tv.getHouseIndex(p);
        String color = tv.getStringColor().toUpperCase();
        boolean isFull = p.getPp().isFull(p.getPp().getProperty(index), color);
        if (isFull) {
            p.getPp().getBuilding().put(index, Building.HOUSE);
//            System.out.println("A house has added into your property!");
        }
    }
}
