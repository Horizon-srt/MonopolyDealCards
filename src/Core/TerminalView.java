package Core;

import Modules.Cards.impl.Card;
import Modules.Piles.iface.IPile;
import Modules.Player.impl.Player;

import java.io.IOException;
import java.util.Deque;
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
}
