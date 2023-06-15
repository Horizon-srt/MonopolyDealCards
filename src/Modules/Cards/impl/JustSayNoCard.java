package Modules.Cards.impl;

import Core.GameController;
import Modules.Player.impl.Player;

public class JustSayNoCard extends ActionCard{
    public JustSayNoCard(String name, int value) {
        super(name, value);
    }

    /**
     * Can be played while any player is using action cards or rental action cards to prevent this behavior.
     * I implement this function in other action cards.
     * @param g
     * @param p
     */
    @Override
    public void use(GameController g, Player p) {

    }
}
