package Modules.Cards.impl;

import Modules.Cards.iface.IPropertyCard;
import utils.Color;
import utils.Type;

public class PropertyCard extends Card implements IPropertyCard {

    public PropertyCard(String name, int value) {
        super(name, value, Type.PROPERTY);
    }

    @Override
    public Color getColor() {
        return null;
    }
}
