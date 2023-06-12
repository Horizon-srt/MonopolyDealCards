package Modules.Cards.impl;

import Core.GameController;
import Core.TerminalView;
import Modules.Player.impl.Player;
import utils.Building;

import java.util.Scanner;

public class HotelCard extends ActionCard{
    public HotelCard(String name, int value) {
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
        int index = tv.getHotelIndex(p);
        for (int a : p.getPp().getBuilding().keySet()){
            if (a == index || p.getPp().getBuilding().get(a) == Building.HOUSE){
                p.getPp().getBuilding().put(a, Building.HOTEL);
            }
        }
    }
}
