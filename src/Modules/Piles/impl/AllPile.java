package Modules.Piles.impl;

import Modules.Cards.impl.Card;
import Modules.Piles.iface.IAllPile;

import java.util.LinkedList;

public class AllPile implements IAllPile {
    private static AllPile ap = new AllPile();

    LinkedList<Card> list = new LinkedList<>();

    private AllPile() {}

    public static AllPile getAllPile() {
        return ap;
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
