package Modules.Cards.impl;

import Core.GameController;
import Modules.Cards.iface.IActionCard;
import utils.Type;

public class ActionCard extends Card implements IActionCard  {

    public ActionCard(String name, int value) {
        super(name, value, Type.ACTION);
    }

    // 使用行动卡
    @Override
    public void use(GameController g) {

    }
}
