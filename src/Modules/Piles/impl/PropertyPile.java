package Modules.Piles.impl;

import Core.TerminalView;
import Modules.Cards.iface.IPropertyCard;
import Modules.Cards.impl.Card;
import Modules.Piles.iface.IPropertyPile;
import utils.Building;
import utils.Color;

import java.util.*;

public class PropertyPile implements IPropertyPile {
    private LinkedList<LinkedList<IPropertyCard>> propertyList;
    private HashMap<Color, Building> buildingMap;
    public PropertyPile() {
        propertyList = new LinkedList<>();
        buildingMap = new HashMap<Color, Building>();
    }
    @Override
    public Card getCard() {
        return null;
    }

    @Override
    public void setCard(Card c) {
        Scanner sc = new Scanner(System.in);
        int index = TerminalView.getTerminalView().getIndex();
        System.out.println("What's the color of this property pile?");
        String color = sc.nextLine();
        if (index <= propertyList.size()) {
            LinkedList<IPropertyCard> list = propertyList.get(index-1);
            if (((IPropertyCard) c).getColor().toString() == color){
                list.add((IPropertyCard) c);
            }
        } else {
            LinkedList<IPropertyCard> list = new LinkedList<>();
            list.add((IPropertyCard) c);
            propertyList.add(list);
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
                System.out.print("Value: "+card.getValue()+";  ");
                System.out.print("Color: "+card.getColor()+";  ");
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
    public HashMap<Color, Building> getBuilding(){
        return buildingMap;
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
        Scanner sc = new Scanner(System.in);
        int num = 0;
        for (LinkedList<IPropertyCard> list : propertyList){
            System.out.println("What's the color of this list?");
            String color = sc.nextLine();
            if (isFull(list, color)) {
                num++;
                if (num == 3){
                    return true;
                }
            }
        }
        return false;
    }

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
            
    public boolean isFull(LinkedList<IPropertyCard> list, String color) {
        for (IPropertyCard card: list){
            String c = card.getColor().toString();
            if (c != "ALL" && c != color){
                return false;
            }
        }
        return list.size() == map.get(color);
    }

    public void removeCard(Card c){
        Iterator<LinkedList<IPropertyCard>> it = propertyList.iterator();
        while (it.hasNext()){
            LinkedList<IPropertyCard> list = it.next();
            Iterator<IPropertyCard> itt = list.iterator();
            while (itt.hasNext()){
                IPropertyCard card = itt.next();
                if (card.getName() == c.name){
                    list.remove((IPropertyCard) c);
                }
            }
        }
    }
}
