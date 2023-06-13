package Modules.Piles.impl;

import Modules.Cards.impl.Card;
import Modules.Piles.iface.IBankPile;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class BankPile implements IBankPile {
    private LinkedList<Card> list;

    public BankPile() {
        list = new LinkedList<>();
    }

    @Override
    public Card getCard() {
        Card firstCard = this.list.getFirst();
        this.list.removeFirst();
        return firstCard;
    }

    @Override
    public void setCard(Card c) {
        if (c.getValue() != 0){
            list.add(c);
            System.out.println("Add a card into bank successfully!");
        } else {
            System.out.println("This card can't join bank!!");
        }
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
            System.out.print("Type: " + card.getType() + ",  ");
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

    public void removeCard(Card c){
        list.remove(c);
    }

    public int getValue() {
        int value = 0;
        for (Card card : list){
            value = value + card.getValue();
        }
        return value;
    }
}
