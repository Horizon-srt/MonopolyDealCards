package Modules.Cards.impl;

import Core.GameController;
import Core.TerminalView;
import Modules.Player.impl.Player;

public class SlyDealCard extends ActionCard{
    public SlyDealCard(String name, int value) {
        super(name, value);
    }

    @Override
    public void use(GameController g, Player p) {
        TerminalView tv = g.tv;
        Player targetPlayer = tv.getTargetplayer();
        int i = tv.askJustSayNo(targetPlayer, "SlyDealCard");
        if (i > 0) {
            Card justSayNo = p.getHp().getCardById(i);
            g.dp.setCard(justSayNo);
            return;
        }
        PropertyCard targetProperty = tv.slyForceDealTargetProperty();
        targetPlayer.getPp().removeCard(targetProperty);

        p.getPp().setCard(targetProperty);
    }
}
