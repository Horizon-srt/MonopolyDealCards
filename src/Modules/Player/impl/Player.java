package Modules.Player.impl;

import Modules.Piles.impl.BankPile;
import Modules.Piles.impl.HandPile;
import Modules.Piles.impl.PropertyPile;
import Modules.Player.iface.IPlayer;

public class Player implements IPlayer {
    private String name;

    private HandPile hp;

    private PropertyPile pp;

    private BankPile bp;

    public Player(String name) {
        this.name = name;
        hp = new HandPile();
        pp = new PropertyPile();
        bp = new BankPile();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public HandPile getHp() {
        return hp;
    }

    @Override
    public PropertyPile getPp() {
        return pp;
    }

    @Override
    public BankPile getBp() {
        return bp;
    }
}
