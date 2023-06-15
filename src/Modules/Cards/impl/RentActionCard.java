package Modules.Cards.impl;

import Core.GameController;
import Core.TerminalView;
import Modules.Cards.iface.IPropertyCard;
import Modules.Player.impl.Player;
import utils.Building;
import utils.Color;
import utils.Type;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;

/**
 * Collect rental fees from designated players for items of designated colors and brands they own
 */
public class RentActionCard extends ActionCard {
    public Color color1;
    public Color color2;

    public RentActionCard(String name, int value, Color color1, Color color2) {
        super(name, value);
        this.color1 = color1;
        this.color2 = color2;
    }

    public static HashMap<String, HashMap<Integer, Integer>> rentNum = new HashMap<>();


    @Override
    public void use(GameController g, Player p) {
        if (rentNum.size() == 0)
            initRentNum();
        TerminalView tv = g.tv;
        int flag = tv.doubleRentCardCheck(p);
        if (flag > 0)
            p.getHp().getCardById(flag);
        ArrayDeque<Player> players = new ArrayDeque<>();
        for (Player player : g.players) {
            if (player != p) {
                players.add(player);
            }
        }
        int index = tv.rentPropertyIndex(p);
        LinkedList<LinkedList<IPropertyCard>> propertyList = p.getPp().getPropertyList();
        int size = propertyList.get(index - 1).size();
        String color = tv.rentPropertyColor(p);

        int money = 0;
        if (color1 == Color.ALL && color2 == Color.ALL) {
            Player targetplayer = tv.getTargetplayer(players.toArray(new Player[players.size()]));
            int i = tv.askJustSayNo(targetplayer, "RentActionCard");
            if (i > 0) {
                Card justSayNo = p.getHp().getCardById(i);
                g.dp.setCard(justSayNo);
                return;
            }
            color = color.toLowerCase();
            Integer m = rentNum.get(color).get(size);
            //加入房子的价格
            Building building = p.getPp().getBuilding().get(index);
            if (building == Building.HOTEL) {
                m += 7;
            } else if (building == Building.HOUSE) {
                m += 3;
            }
            if (flag > 0)
                m *= 2;
            Card[] rent = tv.rent(p, targetplayer, m);
            if (rent == null)
                return;
            for (Card card : rent) {
                if (card.getType() == Type.MONEY || card.getType() == Type.ACTION) {
                    p.getBp().setCard(card);
                    targetplayer.getBp().removeCard(card);
                } else {
                    p.getPp().setCard(card);
                    targetplayer.getPp().removeCard(card);
                }
            }
        } else {
            if (judgeColor(color)) {
                for (Player targetplayer : g.players) {
                    if (targetplayer != p) {
                        int i = tv.askJustSayNo(targetplayer, "RentActionCard");
                        if (i > 0) {
                            Card justSayNo = p.getHp().getCardById(i);
                            g.dp.setCard(justSayNo);
                            continue;
                        }
                        color = color.toLowerCase();
                        Integer m = rentNum.get(color).get(size);
                        //加入房子的价格
                        Building building = p.getPp().getBuilding().get(index);
                        if (building == Building.HOTEL) {
                            m += 7;
                        } else if (building == Building.HOUSE) {
                            m += 3;
                        }
                        if (flag > 0)
                            m *= 2;
                        Card[] rent = tv.rent(p, targetplayer, m);
                        if (rent == null)
                            return;
                        for (Card card : rent) {
                            if (card.getType() == Type.MONEY || card.getType() == Type.ACTION) {
                                p.getBp().setCard(card);
                                targetplayer.getBp().removeCard(card);
                            } else {
                                p.getPp().setCard(card);
                                targetplayer.getPp().removeCard(card);
                            }
                        }
                    }
                }
            }
        }
    }

    private static void initRentNum() {
        HashMap<Integer, Integer> brown = new HashMap<>();
        brown.put(1, 1);
        brown.put(2, 2);
        HashMap<Integer, Integer> blue = new HashMap<>();
        blue.put(1, 3);
        blue.put(2, 8);
        HashMap<Integer, Integer> green = new HashMap<>();
        green.put(1, 2);
        green.put(2, 4);
        green.put(3, 7);
        HashMap<Integer, Integer> lightBlue = new HashMap<>();
        lightBlue.put(1, 1);
        lightBlue.put(2, 2);
        lightBlue.put(3, 3);
        HashMap<Integer, Integer> orange = new HashMap<>();
        orange.put(1, 1);
        orange.put(2, 3);
        orange.put(3, 5);
        HashMap<Integer, Integer> pink = new HashMap<>();
        pink.put(1, 1);
        pink.put(2, 2);
        pink.put(3, 4);
        HashMap<Integer, Integer> railroad = new HashMap<>();
        railroad.put(1, 1);
        railroad.put(2, 2);
        railroad.put(3, 3);
        railroad.put(4, 4);
        HashMap<Integer, Integer> red = new HashMap<>();
        red.put(1, 2);
        red.put(2, 3);
        red.put(3, 6);
        HashMap<Integer, Integer> utility = new HashMap<>();
        utility.put(1, 1);
        utility.put(2, 2);
        HashMap<Integer, Integer> yellow = new HashMap<>();
        yellow.put(1, 2);
        yellow.put(2, 4);
        yellow.put(3, 6);
        rentNum.put("brown", brown);
        rentNum.put("blue", blue);
        rentNum.put("green", green);
        rentNum.put("lightBlue", lightBlue);
        rentNum.put("orange", orange);
        rentNum.put("pink", pink);
        rentNum.put("railroad", railroad);
        rentNum.put("red", red);
        rentNum.put("utility", utility);
        rentNum.put("yellow", yellow);
    }

    public Color getColor1() {
        return color1;
    }

    public Color getColor2() {
        return color2;
    }

    public static HashMap<String, HashMap<Integer, Integer>> getRentNum() {
        return rentNum;
    }

    private boolean judgeColor(String color) {
        if (color1 == Color.ALL && color2 == Color.ALL) {
            return true;
        } else {
            return color1.toString().equals(color) || color2.toString().equals(color);
        }
    }
}
