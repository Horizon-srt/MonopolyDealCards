package Core;

import Modules.Cards.impl.Card;
import Modules.Cards.impl.PropertyCard;
import Modules.Piles.iface.IPile;
import Modules.Player.impl.Player;
import java.io.IOException;
import java.util.*;

public class TerminalView implements ITerminalView {
    /*
     * a terminal I/O for the game, normally as a view in MVC patten
     * not special for any of them, so only gives simple description
     */

    private static final TerminalView tv = new TerminalView();

    private TerminalView() {}

    // use Singleton Pattern
    public static TerminalView getTerminalView() {
        return tv;
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
        sc.close();

        return playerNumber;
    }

    public boolean startGame() {
        System.out.println("Do you want to start game now? Press 'y' to start");
        Scanner sc = new Scanner(System.in);
        System.out.print(">");
        String userIn = sc.nextLine();
        sc.close();

        return userIn.equalsIgnoreCase("y");
    }

    public boolean sayWin() {
        System.out.println("Do you want to 'Say win'? Press 'y' to check if you won this game");
        Scanner sc = new Scanner(System.in);
        System.out.print(">");
        String userIn = sc.nextLine();
        sc.close();

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
        // need while() to get drop cards
        System.out.println("The number of your hand cards is more than 7. You need to drop some cards to keep the number lower or equals than 7.");
        System.out.println("Your hand card");
        p.getHp().listCards();
        while (p.getHp().size() > 7) {
            System.out.println("Your hand card");
            p.getHp().listCards();
            System.out.println("Select cards by their index:");
            Scanner sc = new Scanner(System.in);
            System.out.print(">");
            String userIn = sc.nextLine();
            while (!isDigit(userIn) || Integer.parseInt(userIn) <= 0 || Integer.parseInt(userIn) > p.getHp().size()) {
                System.out.println("Invalid index number!");
                System.out.print(">");
                userIn = sc.nextLine();
            }
            g.dp.setCard(p.getHp().getCardById(Integer.parseInt(userIn)));
            sc.close();
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
        System.out.println("Press 'd' to pass this turn");
        Scanner sc = new Scanner(System.in);
        System.out.print(">");
        String userIn = sc.nextLine();
        while (!userIn.equalsIgnoreCase("a") && !userIn.equalsIgnoreCase("b") && !userIn.equalsIgnoreCase("c") && !userIn.equalsIgnoreCase("d")) {
            System.out.println("Invalid option character, please input again");
            System.out.print(">");
            userIn = sc.nextLine();
        }
        sc.close();

        return userIn.toCharArray()[0];
    }

    public Card selectCard(IPile pile, String ask) {
        System.out.println(ask);
        pile.listCards();
        Scanner sc = new Scanner(System.in);
        System.out.print(">");
        String userIn = sc.nextLine();
        while (!isDigit(userIn) || Integer.parseInt(userIn) <= 0 || Integer.parseInt(userIn) > pile.size()) {
            System.out.println("Invalid index number, please input again");
            System.out.print(">");
            userIn = sc.nextLine();
        }
        sc.close();

        return pile.getCardById(Integer.parseInt(userIn));
    }

    public boolean isDigit(String s) {
        // a tool method for test whether s is a number
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

    private boolean checkRentInput(String s, int bankPileSize, int propertyPileSize) {
        // a check method for rent input
        String[] ss = s.split(" ");
        if (!ss[0].equalsIgnoreCase("b") && !ss[0].equalsIgnoreCase("p")) {
            System.out.println("Type input wrong!");
            return false;
        }
        if (ss.length != 2) {
            System.out.println("Only need pile type and a index number");
            return false;
        }
        if (!isDigit(ss[1])) {
            System.out.println("The index is not a digit!");
            return false;
        }
        if (ss[0].equalsIgnoreCase("b")) {
            if (Integer.parseInt(ss[1]) <= 0 || Integer.parseInt(ss[1]) > bankPileSize) {
                System.out.println("The index out of the range of bank pile");
                return false;
            }
        } else {
            if (Integer.parseInt(ss[1]) <= 0 || Integer.parseInt(ss[1]) > propertyPileSize) {
                System.out.println("The index out of the range of property pile");
                return false;
            }
        }

        return true;
    }

    private int checkRentValue(Card[] cardList, int size) {
        // a tool methof for check rent value
        int allValue = 0;
        if (cardList.length == 0) return 0;
        for (int i=0; i<size; i++) {
            allValue += cardList[i].getValue();
        }

        return allValue;
    }

    @Override
    public Card[] rent(Player p, Player q, int value) {
        // deal rent input and return cards for renter

        if (q.getBp().getValue() + q.getPp().getValue() < value) return new Card[0];

        System.out.println(q.getName() + ", you need pay rent to " + p.getName() + " " + value + "$");
        System.out.println("Your bank pile");
        q.getBp().listCards();
        System.out.println("Your property pile");
        q.getPp().listCards();
        System.out.println("Input pile type and index for card you choose to use.");
        System.out.println("b for bank pile, p for property pile, and split by space");

        Card[] cardList = new Card[50];
        int index = 0;
        int allValue = 0;
        while (allValue < value) {
            System.out.println("You have choose " + allValue + "$, still need at least " + (value - allValue) + "$");
            System.out.print(">");
            Scanner sc = new Scanner(System.in);
            String userIn = sc.nextLine();
            while (!checkRentInput(userIn, q.getBp().size(), q.getPp().size())) {
                System.out.print(">");
                userIn = sc.nextLine();
            }
            sc.close();
            String[] userInputParam = userIn.split(" ");
            Card c;
            if (userInputParam[0].equalsIgnoreCase("b")) c = q.getBp().getCardById(Integer.parseInt(userInputParam[1]));
            else c = q.getPp().getCardById(Integer.parseInt(userInputParam[1]));
            cardList[index] = c;
            index++;
            allValue = checkRentValue(cardList, index);
        }
        Card[] result = new Card[index];
        for (int i=0; i<index; i++) result[i] = cardList[i];

        return result;
    }

    @Override
    public int rentPropertyIndex(Player player) {
        System.out.println("Please select a set of property you want to use for rent");
        player.getPp().listCards();
        System.out.print(">");
        Scanner sc = new Scanner(System.in);
        String userIn = sc.nextLine();
        while (!isDigit(userIn) || Integer.parseInt(userIn) <= 0 || Integer.parseInt(userIn) >player.getPp().size()) {
            System.out.println("Input is not a valid digit!");
            System.out.print(">");
            userIn = sc.nextLine();
        }
        sc.close();

        return Integer.parseInt(userIn);
    }

    @Override
    public String rentPropertyColor(Player player) {
        player.getPp().listCards();
        return getColor();
    }

    @Override
    public int doubleRentCardCheck(Player player) {
        LinkedList<Card> hp = player.getHp().list;
        int index = 1;
        for (Card c: hp) {
            if (c.getName().equalsIgnoreCase("DoubleTheRent")) {
                break;
            }
            index++;
        }
        if (index > hp.size()) return 0;
        System.out.println("Do you want to use double rent card? y for yes");
        System.out.print(">");
        Scanner sc = new Scanner(System.in);
        String userIn = sc.nextLine();
        sc.close();
        if (userIn.equalsIgnoreCase("y")) return index;
        else return 0;
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
        while (!isDigit(userIn) || Integer.parseInt(userIn) > player.length || Integer.parseInt(userIn) <=0 ) {
            System.out.println("Input is not a valid digit!");
            System.out.print(">");
            userIn = sc.nextLine();
        }
        sc.close();

        return player[Integer.parseInt(userIn) - 1];
    }

    @Override
    public PropertyCard slyForceDealTargetProperty(Player player) {
        System.out.println("Please select a target property from target player");
        player.getPp().listCards();
        System.out.print(">");
        Scanner sc = new Scanner(System.in);
        String userIn = sc.nextLine();
        while (!isDigit(userIn) || Integer.parseInt(userIn) <= 0 || Integer.parseInt(userIn) > player.getPp().cardSize()) {
            System.out.println("Input is not a valid digit!");
            System.out.print(">");
            userIn = sc.nextLine();
        }
        sc.close();

        return (PropertyCard) player.getPp().getCardById(Integer.parseInt(userIn));
    }

    @Override
    public PropertyCard forceDealOwnProperty(Player player) {
        System.out.println("Please select a property from your property");
        player.getPp().listCards();
        System.out.print(">");
        Scanner sc = new Scanner(System.in);
        String userIn = sc.nextLine();
        while (!isDigit(userIn) || Integer.parseInt(userIn) <= 0 || Integer.parseInt(userIn) > player.getPp().cardSize()) {
            System.out.println("Input is not a valid digit!");
            System.out.print(">");
            userIn = sc.nextLine();
        }
        sc.close();

        return (PropertyCard) player.getPp().getCardById(Integer.parseInt(userIn));
    }

    @Override
    public int getPropertyIndex(Player q) {
        System.out.println("Here is player " + q.getName() + "'s property pile");
        return inputPropertyParam(q.getPp(), "Please select a pile set");
    }

    @Override
    public int askJustSayNo(Player q, String cardName) {
        System.out.println(q.getName() + ", someone used " + cardName + ", do you want use Just Say No? y for yes");
        System.out.print(">");
        try (Scanner sc = new Scanner(System.in)) {
            String userIn = sc.nextLine();
            if (userIn.equalsIgnoreCase("y")) {
                int index = 1;
                for (Card c: q.getHp().list) {
                    if (c.name.equalsIgnoreCase("JustSayNo")) return index;
                    index++;
                }
            }
            sc.close();
        }
        return 0;
    }

    private int inputPropertyParam (IPile pile, String ask) {
        // reuse this method in getHouseIndex, getHotelIndex, getIndex
        System.out.println(ask);
        System.out.println("Your property pile");
        pile.listCards();
        System.out.print(">");
        Scanner sc = new Scanner(System.in);
        String userIn = sc.nextLine();
        while (!isDigit(userIn) || Integer.parseInt(userIn) <= 0 || Integer.parseInt(userIn) > pile.size() + 1) {
            System.out.println("Input is not a valid digit!");
            System.out.print(">");
            userIn = sc.nextLine();
        }
        sc.close();
        return Integer.parseInt(userIn);
    }

    @Override
    public int getHouseIndex(Player player) {
        return inputPropertyParam(player.getPp(), "Please a property set you want to use");
    }

    @Override
    public int getHotelIndex(Player player) {
        return inputPropertyParam(player.getPp(), "Please a property set you want to use");
    }

    @Override
    public int getIndex(IPile propertyList) {
        return inputPropertyParam(propertyList, "Please a property set you want to use");
    }

    private String getColor() {
        // get a correct color
        System.out.println("Please input a color");
        System.out.print(">");
        try (Scanner sc = new Scanner(System.in)) {
            String userIn = sc.nextLine();
            while (true) {
                if (userIn.equalsIgnoreCase("BROWN")) return "BROWN";
                if (userIn.equalsIgnoreCase("BLUE")) return "BLUE";
                if (userIn.equalsIgnoreCase("GREEN")) return "GREEN";
                if (userIn.equalsIgnoreCase("LIGHT_BLUE")) return "LIGHT_BLUE";
                if (userIn.equalsIgnoreCase("ORANGE")) return "ORANGE";
                if (userIn.equalsIgnoreCase("PINK")) return "PINK";
                if (userIn.equalsIgnoreCase("RAILROAD")) return "RAILROAD";
                if (userIn.equalsIgnoreCase("RED")) return "RED";
                if (userIn.equalsIgnoreCase("UTILITY")) return "UTILITY";
                if (userIn.equalsIgnoreCase("YELLOW")) return "YELLOW";
                System.out.println("Input is not a valid color");
                System.out.print(">");
                userIn = sc.nextLine();
            }
        }
    }

    @Override
    public String getStringColor() {
        return getColor();
    }

}
