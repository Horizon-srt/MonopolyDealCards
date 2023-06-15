package Modules.Piles.impl;

import Modules.Cards.impl.Card;
import Modules.Piles.iface.IDiscardPile;
import utils.Type;

import java.util.LinkedList;

// This class is responsible for the content of the discard pile function
public class DiscardPile implements IDiscardPile {
    // Create a discard pile
    // This part uses the singleton pattern
    private static DiscardPile dp = new DiscardPile();

    // create a list to store all information of used card
    LinkedList<Card> list = new LinkedList<>();

    // get discard pile
    public static DiscardPile getDiscardPile() {
        return dp;
    }

    // don't use, so no content
    @Override
    public Card getCard() {
        return null;
    }

    @Override
    public void setCard(Card c) {
        list.add(c);
    }

    // don't use, so no content
    @Override
    public void arrangement() {

    }

    @Override
    public int size() {
        return list.size();
    }

    // don't use, so no content
    @Override
    public void listCards() {

    }

    // don't use, so no content
    @Override
    public Card getCardById(int id) {
        return null;
    }

}
