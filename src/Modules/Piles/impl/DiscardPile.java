package Modules.Piles.impl;

import Modules.Cards.impl.Card;
import Modules.Piles.iface.IDiscardPile;
import utils.Type;

public class DiscardPile implements IDiscardPile {
    private static DiscardPile dp = new DiscardPile();

    private DiscardPile() {}

    public static DiscardPile getDiscardPile() {
        return dp;
    }


    @Override
    public Card getCard() {
        return null;
    }

    @Override
    public void setCard(Card c) {

    }

    @Override
    public void arrangement() {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void listCards() {

    }

    @Override
    public Card getCardById(int id) {
        return null;
    }

}
