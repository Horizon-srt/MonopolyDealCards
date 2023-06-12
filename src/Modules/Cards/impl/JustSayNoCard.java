package Modules.Cards.impl;

import Core.GameController;
import Modules.Player.impl.Player;

public class JustSayNoCard extends ActionCard{
    public JustSayNoCard(String name, int value) {
        super(name, value);
    }

    @Override
    public void use(GameController g, Player p) {

    }
}
