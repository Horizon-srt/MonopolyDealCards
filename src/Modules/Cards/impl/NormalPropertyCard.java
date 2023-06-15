package Modules.Cards.impl;

import utils.Color;

/**
 * Normal property card
 */
public class NormalPropertyCard extends PropertyCard{
    public Color color;

    public NormalPropertyCard(String name, int value, Color color) {
        super(name, value, color);
        this.color = color;
    }
}
