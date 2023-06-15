package Modules.Cards.impl;

import Core.GameController;
import Core.TerminalView;
import Modules.Player.impl.Player;
import utils.Building;

import java.util.Scanner;

/**
 * Add this card to the set of property that owns the house,
 * resulting in a 4M increase in rent (i.e. a 7M increase in both the house and hotel),
 * and cannot be added to railways and public utilities.
 * Each property has a maximum of one room and one hotel
 */
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
