package Modules.Cards.impl;

import utils.Color;

public class NormalPropertyCard extends PropertyCard{
    public Color color;

    public NormalPropertyCard(String name, int value, Color color) {
        super(name, value);
        this.color = color;
    }
}
