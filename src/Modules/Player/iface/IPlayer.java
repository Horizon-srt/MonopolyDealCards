package Modules.Player.iface;

import Modules.Piles.impl.BankPile;
import Modules.Piles.impl.HandPile;
import Modules.Piles.impl.PropertyPile;

public interface IPlayer {
    public String getName();

    public HandPile getHp();

    public PropertyPile getPp();

    public BankPile getBp();
}
