package Modules.Piles.impl;

import Core.TerminalView;
import Modules.Cards.iface.IPropertyCard;
import Modules.Cards.impl.Card;
import Modules.Cards.impl.PropertyCard;
import Modules.Cards.impl.PropertyWildCard;
import Modules.Piles.iface.IPropertyPile;
import utils.Building;

import java.util.*;

public class PropertyPile implements IPropertyPile {
    // the list store the list of property cards
    private LinkedList<LinkedList<IPropertyCard>> propertyList;
    // the map of buildings
    private HashMap<Integer, Building> buildingMap;
    public PropertyPile() {
        propertyList = new LinkedList<>();
        buildingMap = new HashMap<Integer, Building>();
    }

    public LinkedList<LinkedList<IPropertyCard>> getPropertyList() {
        return propertyList;
    }

    @Override
    public Card getCard() {
        return null;
    }

    @Override
    public void setCard(Card c) {
        TerminalView tv = TerminalView.getTerminalView();
        int index = tv.getIndex(this);
        String color = tv.getStringColor();
        if (index <= propertyList.size()) {
            LinkedList<IPropertyCard> list = propertyList.get(index-1);
            if (c instanceof PropertyWildCard) {
                if (((PropertyWildCard) c).color1.toString().equals(color) || ((PropertyWildCard) c).color2.toString().equals(color) || ((PropertyWildCard) c).color2.toString().equals("ALL")){
                    list.add((IPropertyCard) c);
                    System.out.println("Add a property card into a existed property successfully!");
                }
            } else if (c instanceof PropertyCard) {
                if (((IPropertyCard) c).getColor().toString().equals(color)){
                    list.add((IPropertyCard) c);
                    System.out.println("Add a property card into a existed property successfully!");
                }
            }
            System.out.println("aaa");
        } else {
            LinkedList<IPropertyCard> list = new LinkedList<>();
            list.add((IPropertyCard) c);
            propertyList.add(list);
            System.out.println("Add a property card as a new property successfully!");
        }
    }

    @Override
    public void arrangement() {
    }

    @Override
    public int size() {
        return propertyList.size();
    }

    @Override
    public void listCards() {
        Iterator<LinkedList<IPropertyCard>> it = propertyList.iterator();
        int index = 1;
        int indexx = 1;
        while (it.hasNext()){
            LinkedList<IPropertyCard> list = it.next();
            for (IPropertyCard card : list) {
                System.out.print("Properties"+index+": ");
                System.out.print("Index: "+indexx+", ");
                System.out.print("Name: "+card.getName()+",  ");
                System.out.print("Type: " + card.getType() + ",  ");
                System.out.print("Value: "+card.getValue()+";  ");
                if (card instanceof PropertyWildCard) {
                    System.out.print("Color: "+((PropertyWildCard) card).color1+"/"+((PropertyWildCard) card).color2+";  ");
                } else {
                    System.out.print("Color: "+card.getColor()+";  ");
                }
                System.out.println(" ");
                indexx++;
            }
            index++;
        }
    }

    @Override
    public Card getCardById(int id) {
        for (LinkedList<IPropertyCard> list : propertyList){
            if (id > list.size()){
                id = id - list.size();
            } else {
                IPropertyCard card = list.get(id-1);
                list.remove(id -1);
                return (Card) card;
            }
        }
        return null;
    }

    @Override
    public HashMap<Integer, Building> getBuilding(){
        return buildingMap;
    }

    @Override
    public int cardSize() {
        int cardSize = 0;
        for (LinkedList<IPropertyCard> l: propertyList) {
            cardSize += l.size();
        }
        return cardSize;
    }

    // 使用索引号获取财产堆，返回并删除
    public LinkedList<IPropertyCard> getProperty(int id){
        if (id - 1 <= propertyList.size() ){
            LinkedList<IPropertyCard> list = propertyList.get(id-1);
            propertyList.remove(id-1);
            return list;
        }
        System.out.println("The index violates the rules");
        return null;
    }

    @Override
    public boolean isWin() {
        TerminalView tv = TerminalView.getTerminalView();
        int num = 0;
        for (LinkedList<IPropertyCard> list : propertyList){
            String color = tv.getStringColor();
            if (isFull(list, color)) {
                num++;
                System.out.println("This color is full! Congratulation!");
                if (num == 3){
                    return true;
                }
            } else {
                System.out.println("This color is not full! Keep trying!");
            }
        }
        return false;
    }

    // the map of full condition
    Map<String, Integer> map = Map.of(
            "BROWN",2,
            "BLUE",2,
            "GREEN",3,
            "LIGHT_BLUE",3,
            "ORANGE",3,
            "PINK",3,
            "RAILROAD",4,
            "RED",3,
            "UTILITY",2,
            "YELLOW",3);

    // judge the property whether is full
    public boolean isFull(LinkedList<IPropertyCard> list, String color) {
        for (IPropertyCard card: list){
            if (card instanceof PropertyWildCard) {
                String c1 = ((PropertyWildCard) card).color1.toString();
                String c2 = ((PropertyWildCard) card).color2.toString();
                if (c1.equals(color) && c2.equals(color)){
                    return false;
                }
            }
            String c = card.getColor().toString();
            if (c.equals("ALL") && c.equals(color)){
                return false;
            }
        }
        return list.size() == map.get(color);
    }

    // remove a card
    public void removeCard(Card c){
        Iterator<LinkedList<IPropertyCard>> it = propertyList.iterator();
        while (it.hasNext()){
            LinkedList<IPropertyCard> list = it.next();
            Iterator<IPropertyCard> itt = list.iterator();
            while (itt.hasNext()){
                IPropertyCard card = itt.next();
                if (card.getName().equals(c.name)){
                    list.remove((IPropertyCard) c);
                }
            }
        }
    }

    public int getValue(){
        int value = 0;
        for (LinkedList<IPropertyCard> list: propertyList){
            for (IPropertyCard card: list){
                value = value + card.getValue();
            }
        }
        return value;
    }
}
