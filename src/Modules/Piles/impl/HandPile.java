package Modules.Piles.impl;

import Modules.Cards.impl.Card;
import Modules.Cards.impl.PropertyCard;
import Modules.Cards.impl.PropertyWildCard;
import Modules.Piles.iface.IHandPile;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

// This class is responsible for the content of the hand pile function
public class HandPile implements IHandPile {
    public LinkedList<Card> list;

    public HandPile() {
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
            System.out.print("Type: " + card.getType() + ",  ");
            System.out.print("Value: "+card.value+";  ");
            if (card instanceof PropertyWildCard) {
                System.out.print("Color: "+((PropertyWildCard)card).color1+"/"+((PropertyWildCard)card).color2+";  ");
            } else if (card instanceof PropertyCard) {
                System.out.print("Color: "+((PropertyCard)card).getColor()+";  ");
            }
            System.out.println(" ");
            index++;
        }
    }

    @Override
    public Card getCardById(int id) {
        if (id - 1 < list.size() ){
            Card card = list.get(id-1);
            list.remove(id-1);
            return card;
        }
        System.out.println("The index violates the rules");
        return null;
    }
}
