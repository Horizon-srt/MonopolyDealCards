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
        int index = tv.getIndex();
        String color = tv.getStringColor().toUpperCase();
        boolean isFull = p.getPp().isFull(p.getPp().getProperty(index), color);
        if (isFull) {
            p.getPp().getBuilding().put(color, Building.HOUSE);
//            System.out.println("A house has added into your property!");
        }
    }
}
