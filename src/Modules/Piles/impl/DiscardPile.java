package Modules.Piles.impl;

import Modules.Cards.impl.Card;
import Modules.Piles.iface.IDiscardPile;
import utils.Type;

import java.util.LinkedList;

public class DiscardPile implements IDiscardPile {
    private static DiscardPile dp = new DiscardPile();

    LinkedList<Card> list = new LinkedList<>();

    public static DiscardPile getDiscardPile() {
        return dp;
    }

    @Override
    public Card getCard() {
        return null;
    }

    @Override
    public void setCard(Card c) {
        list.add(c);
    }

    @Override
    public void arrangement() {

    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void listCards() {

    }

    @Override
    public Card getCardById(int id) {
        return null;
    }

}
