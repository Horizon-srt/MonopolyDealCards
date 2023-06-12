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
        String color = tv.getStringColor().toUpperCase();
        for (String a : p.getPp().getBuilding().keySet()){
            if (a.equals(color) || p.getPp().getBuilding().get(a) == Building.HOUSE){
                p.getPp().getBuilding().put(a, Building.HOTEL);
            }
        }
    }
}
