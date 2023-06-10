package Modules.Piles.impl;

import Modules.Cards.iface.IPropertyCard;
import Modules.Cards.impl.Card;
import Modules.Piles.iface.IPropertyPile;

import java.util.Iterator;
import java.util.LinkedList;

import static utils.Type.PROPERTY;

public class PropertyPile implements IPropertyPile {
    private LinkedList<LinkedList<Card>> propertyList;
    public PropertyPile() {propertyList = new LinkedList<>();}
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

    @Override
    public boolean isWin() {
        return false;
    }

    public void removeCard(Card c){
        Iterator<LinkedList<Card>> it = propertyList.iterator();
        while (it.hasNext()){
            LinkedList list = it.next();
            Iterator<Card> itt = list.iterator();
            while (itt.hasNext()){
                Card card = itt.next();
                if (card == c){
                    list.remove(c);
                }
            }
        }
    }
}
