package Modules.Cards.impl;

import Core.GameController;
import Modules.Piles.impl.AllPile;
import Modules.Piles.impl.HandPile;
import Modules.Player.impl.Player;

public class PassGoCard extends ActionCard {
    public PassGoCard(String name, int value) {
        super(name, value);
    }

    @Override
    public void use(GameController g, Player p) {
        HandPile hp = p.getHp();
        hp.setCard(g.ap.getCard());
        hp.setCard(g.ap.getCard());
    }
}
