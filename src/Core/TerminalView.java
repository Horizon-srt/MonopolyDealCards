package Core;

import Modules.Cards.iface.IPropertyCard;
import Modules.Cards.impl.Card;
import Modules.Cards.impl.PropertyCard;
import Modules.Piles.iface.IPile;
import Modules.Player.impl.Player;
import utils.Color;

import java.io.IOException;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Scanner;

public class TerminalView implements ITerminalView {
    private static final TerminalView tv = new TerminalView();
    private TerminalView() {}
    public static TerminalView getTerminalView() {
        return tv;
    }

    public void getRules() {
        System.out.println("Rules:");
    }

    public int getPlayers(Deque<Player> players) {
        System.out.println("How many players will play this game? (This number should between 2 to 5)");
        Scanner sc = new Scanner(System.in);
        System.out.print(">");
        String userIn = sc.nextLine();
        while (!isDigit(userIn) || Integer.parseInt(userIn) < 2 || Integer.parseInt(userIn) > 5) {
            System.out.println("The number you input is not in correct range, please input again");
            System.out.print(">");
            userIn = sc.nextLine();
        }
        int playerNumber = Integer.parseInt(userIn);

        for (int i=1; i<=playerNumber; i++) {
            System.out.println("Please input player" + i + "'s name");
            System.out.print(">");
            userIn = sc.nextLine();
            players.addLast(new Player(userIn));
            System.out.println("New player " + userIn + " added!");
        }

        return playerNumber;
    }

    public boolean startGame() {
        System.out.println("Do you want to start game now? Press 'y' to start");
        Scanner sc = new Scanner(System.in);
        System.out.print(">");
        String userIn = sc.nextLine();
        return userIn.equalsIgnoreCase("y");
    }

    public boolean sayWin() {
        System.out.println("Do you want to 'Say win'? Press 'y' to check if you won this game");
        Scanner sc = new Scanner(System.in);
        System.out.print(">");
        String userIn = sc.nextLine();
        return userIn.equalsIgnoreCase("y");
    }

    public void win(Player p) {
        System.out.println(p.getName() + ", YOU ARE WINNER!!!!!");
        System.out.println("Game over and player " + p.getName() + " was the winner!");
    }

    public void notWin() {
        System.out.println("You did not win this game, select more property to reach the goal!");
    }

    public void dropCard(Player p, GameController g) {
        System.out.println("The number of your hand cards is more than 7. You need to drop some cards to keep the number lower or equals than 7.");
        System.out.println("Your hand card");
        p.getHp().listCards();
        while (p.getHp().size() > 7) {
            System.out.println("Select cards by their index split by space:");
            Scanner sc = new Scanner(System.in);
            System.out.print(">");
            String userIn = sc.nextLine();
            String[] cards = userIn.split(" ");
            for (int i=0; i<cards.length; i++) {
                String c = cards[i];
                if (isDigit(c) && Integer.parseInt(c) >= 1 && Integer.parseInt(c) <= p.getHp().size()) {
                    g.dp.setCard(p.getHp().getCardById(Integer.parseInt(c)));
                } else {
                    System.out.println("Invalid index number for the " + i + "th input");
                }
            }
            System.out.println("Your hand card:");
            p.getHp().listCards();
        }
    }

    public void startTurn(Player p) {
        System.out.println("In player " + p.getName() + "'s turn");
        System.out.println("Your bank pile:");
        p.getBp().listCards();
        System.out.println("Your property pile:");
        p.getPp().listCards();
        System.out.println("Your hand pile:");
        p.getHp().listCards();
    }

    public char getOpt(int n) {
        System.out.println("This the " + n + "th card you can play, totally 3 times");
        System.out.println("Press 'a' to put Money/Action Cards into your own bank");
        System.out.println("Press 'b' to put Down Properties into your own Collection");
        System.out.println("Press 'c' to play Action Cards into the center");
        Scanner sc = new Scanner(System.in);
        System.out.print(">");
        String userIn = sc.nextLine();
        while (!userIn.equalsIgnoreCase("a") && !userIn.equalsIgnoreCase("b") && !userIn.equalsIgnoreCase("c")) {
            System.out.println("Invalid option character, please input again");
            System.out.print(">");
            userIn = sc.nextLine();
        }
        return userIn.toCharArray()[0];
    }

    public Card selectCard(IPile pile) {
        System.out.println("Please select a card from your hand cards by index number:");
        pile.listCards();
        Scanner sc = new Scanner(System.in);
        System.out.print(">");
        String userIn = sc.nextLine();
        while (!isDigit(userIn) || Integer.parseInt(userIn) <= 0 || Integer.parseInt(userIn) >= pile.size()) {
            System.out.println("Invalid index number, please input again");
            System.out.print(">");
            userIn = sc.nextLine();
        }
        return pile.getCardById(Integer.parseInt(userIn));
    }

    public boolean isDigit(String s) {
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            if (!Character.isDigit(aChar)) return false;
        }
        return true;
    }

    public void wrongCardType() {
        System.out.println("Wrong card type, please input again");
    }

    public void wrongFile(IOException e) {
        System.out.println("Can not find cards config, please check config file in path 'src/config'");
        System.out.println("Error with: " + e.toString());
    }

    private boolean checkRentInput(String s, int handPileSize, int propertyPileSize) {
        String[] ss = s.split(" ");
        if (!ss[0].equalsIgnoreCase("h") || !ss[0].equalsIgnoreCase("p")) {
            System.out.println("Type input wrong!");
            return false;
        }
        if (ss.length < 2) {
            System.out.println("Need index params!");
            return false;
        }
        for (int i=1; i< ss.length; i++) {
            if (!isDigit(ss[i])) {
                System.out.println("The " + i + "th index is not a digit!");
                return false;
            }
            if (ss[0].equalsIgnoreCase("h")) {
                if (Integer.parseInt(ss[i]) <= 0 || Integer.parseInt(ss[i]) > handPileSize) {
                    System.out.println("The \" + i + \"th index out of the range of hand pile");
                    return false;
                }
            } else {
                if (Integer.parseInt(ss[i]) <= 0 || Integer.parseInt(ss[i]) > propertyPileSize) {
                    System.out.println("The \" + i + \"th index out of the range of property pile");
                    return false;
                }
            }
        }

        return true;
    }

    private int checkRentValue(Card[] cardList) {
        int allValue = 0;
        for (Card c: cardList) {
            allValue += c.getValue();
        }

        return allValue;
    }

    @Override
    public Card[] rent(Player p, Player q, int value) {
        System.out.println(q.getName() + ", you need pay rent to " + p.getName() + " " + value + "$");
        System.out.println("Your hand pile");
        q.getHp().listCards();
        System.out.println("Your property pile");
        q.getPp().listCards();
        System.out.println("Input pile type and indexes for card you choose to use.");
        System.out.println("h for hand pile, p for property pile, and split by space");

        Card[] cardList = new Card[50];
        int index = 0;
        int allValue = checkRentValue(cardList);
        while (allValue < value) {
            System.out.println("You have choose " + allValue + "$, still need at least " + (value - allValue) + "$");
            System.out.print(">");
            Scanner sc = new Scanner(System.in);
            String userIn = sc.nextLine();
            while (!checkRentInput(userIn, q.getHp().size(), q.getPp().size())) {
                System.out.print(">");
                userIn = sc.nextLine();
            }
            String[] userInputParam = userIn.split(" ");
            for (int i=1; i<userInputParam.length; i++) {
                Card c;
                if (userInputParam[0].equalsIgnoreCase("h")) c = q.getHp().getCardById(Integer.parseInt(userInputParam[i]));
                else c = q.getPp().getCardById(Integer.parseInt(userInputParam[i]));
                cardList[index] = c;
                index++;
            }
            allValue = checkRentValue(cardList);
        }

        return cardList;
    }

    @Override
    public Player getTargetplayer(Player[] player) {
        System.out.println("Please select a target player");
        for (int i=0; i<player.length; i++) {
            System.out.println("Player " + (i + 1) + ": " + player[i].getName());
        }
        System.out.print(">");
        Scanner sc = new Scanner(System.in);
        String userIn = sc.nextLine();
        while (!isDigit(userIn) || Integer.parseInt(userIn) > player.length) {
            System.out.println("Input is not a valid digit!");
            System.out.print(">");
            userIn = sc.nextLine();
        }

        return player[Integer.parseInt(userIn)];
    }


    @Override
    public PropertyCard slyForceDealTargetProperty() {
        return null;
    }

    @Override
    public PropertyCard forceDealOwnProperty() {
        return null;
    }

    @Override
    public LinkedList<IPropertyCard> getProperty() {
        return null;
    }

    @Override
    public int askJustSayNo(Player q, String cardName) {
        return 0;
    }
}
