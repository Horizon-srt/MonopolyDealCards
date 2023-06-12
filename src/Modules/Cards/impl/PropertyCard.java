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

    public boolean judgeColar(PropertyCard c1, PropertyCard c2) {
        PropertyWildCard p1 , p2;
        if (c1 instanceof PropertyWildCard && c2 instanceof PropertyWildCard) {
            p1 = (PropertyWildCard) c1; p2 = (PropertyWildCard) c2;
            if (p1.color1 == p2.color1 || p1.color1 == p2.color2 || p1.color2 == p2.color1 || p1.color2 == p2.color2) {
                return true;
            } else {
                return false;
            }
        } else if (c1 instanceof PropertyWildCard) {
            p1 = (PropertyWildCard) c1;
            if (p1.color1 == c2.getColor() || p1.color2 == c2.getColor()) {
                return true;
            } else {
                return false;
            }
        } else if (c2 instanceof PropertyWildCard) {
            p2 = (PropertyWildCard) c2;
            if (p2.color1 == c1.getColor() || p2.color2 == c1.getColor()) {
                return true;
            } else {
                return false;
            }
        } else {
            if (c1.getColor() == Color.ALL || c2.getColor() == Color.ALL)
                return true;
            return c1.getColor() == c2.getColor();
        }
    }
}
