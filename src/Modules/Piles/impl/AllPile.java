package Modules.Piles.impl;

import Modules.Cards.impl.Card;
import Modules.Piles.iface.IAllPile;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class AllPile implements IAllPile {
    private static AllPile ap = new AllPile();

    LinkedList<Card> list = new LinkedList<>();

    public static AllPile getAllPile() {
        return ap;
    }

    @Override
    public Card getCard() {
        Card firstCard = list.getFirst();
        list.removeFirst();
        return firstCard;
    }

    @Override
    public void setCard(Card c) {
        list.add(c);
    }

    @Override
    public void arrangement() {
        Collections.shuffle(list);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void listCards() {
        Iterator<Card> it = list.iterator();
        int index = 1;
        while (it.hasNext()){
            Card card = it.next();
            System.out.print("Index: "+index+",  ");
            System.out.print("Name: "+card.name+",  ");
            System.out.print("Value: "+card.value+";  ");
            System.out.println(" ");
            index++;
        }
    }

    @Override
    public Card getCardById(int id) {
        if (id - 1 <= list.size() ){
            Card card = list.get(id-1);
            list.remove(id-1);
            return card;
        }
        System.out.println("The index violates the rules");
        return null;
    }
}
