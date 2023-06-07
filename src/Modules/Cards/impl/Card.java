package Modules.Cards.impl;

import Modules.Cards.iface.ICard;
import utils.Color;
import utils.Type;

public class Card implements ICard {
    public String name;

    public int value;

    public Type type;

    public Card(String name, int value, Type type) {
        this.name = name;
        this.value = value;
        this.type = type;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public Type getType() {
        return type;
    }

}
