package Modules.Cards.impl;

import utils.Color;

public class PropertyWildCard extends PropertyCard {
    public Color color1;

    public Color color2;

    public PropertyWildCard(String name, int value, Color color1, Color color2) {
        super(name, value, color1);
        this.color1 = color1;
        this.color2 = color2;
    }
}
