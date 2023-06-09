package Modules.Cards.impl;

import Modules.Cards.iface.IMoneyCard;
import utils.Type;

/**
 * Pay money
 */
public class MoneyCard extends Card implements IMoneyCard {
    public MoneyCard(String name, int value) {
        super(name, value, Type.MONEY);
    }
}
