import Core.GameController;
import Core.ITerminalView;
import Core.TerminalView;
import Modules.Cards.impl.*;
import Modules.Piles.iface.IPile;
import Modules.Piles.impl.AllPile;
import Modules.Player.impl.Player;
import utils.Color;
import utils.Type;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Test {
    /***
     * This the test file for the game and modules
     * For controller and some of the views can be test when the game runs, so those won't test here
     * This test will create two players with all cards.
     * All cards will read from config with the same method as the game controller.
     */

    public static Player player1, player2;

    public static AllPile ap1, ap2;

    public static TerminalView tv = TerminalView.getTerminalView();

    public static GameController g = GameController.getGame();

    public static void main(String[] args) {
        ap1 = new AllPile();
        ap2 = new AllPile();
        player1 = new Player("Test player1");
        player2 = new Player("Test player2");
        initial();

        System.out.println("In ap1:");
        ap1.listCards();
        System.out.println("In ap2:");
        ap2.listCards();
        System.out.println("If they all have 108 cards with no order, that means correct \n");
        while (ap1.size() > 0) {
            player1.getHp().setCard(ap1.getCard());
            player2.getHp().setCard(ap2.getCard());
        }

        System.out.println("Here test player's hand card");
        System.out.println("Player1:");
        player1.getHp().listCards();
        System.out.println("Player2:");
        player2.getHp().listCards();
        System.out.println("If they all have 108 cards with no order, that means correct \n");

        g.testDataIn(player1, player2, tv);
        while (true) {
            Player p = g.players.removeFirst();
            tv.startTurn(p);
            System.out.println("You can select a operation to test this game");
            System.out.println("Press 'a' to put Money/Action Cards into your own bank");
            System.out.println("Press 'b' to put Down Properties into your own Collection");
            System.out.println("Press 'c' to play Action Cards into the center");
            System.out.println("Press 'e' to end the test");
            System.out.println("Press other things to skip this turn");
            System.out.println("Press 'w' to say win");
            System.out.println("Press other character to exit");
            Scanner sc = new Scanner(System.in);
            String userIn = sc.nextLine();
            Card c;
            if (userIn.equalsIgnoreCase("a")) {
                try {
                    c = tv.selectCard(p.getHp(), "Please select a card from your hand cards by index number:");
                    while ((c.getType() != Type.MONEY) && (c.getType() != Type.ACTION)) {
                        tv.wrongCardType();
                        p.getHp().setCard(c);
                        c = tv.selectCard(p.getHp(), "Please select a card from your hand cards by index number:");
                    }
                    p.getBp().setCard(c);
                    System.out.println("Test success!!! \n");
                } catch (Error e) {
                    System.out.println(e.toString());
                }
            } else if (userIn.equalsIgnoreCase("b")) {
                try {
                    c = tv.selectCard(p.getHp(), "Please select a card from your hand cards by index number:");
                    while (c.getType() != Type.PROPERTY) {
                        tv.wrongCardType();
                        p.getHp().setCard(c);
                        c = tv.selectCard(p.getHp(), "Please select a card from your hand cards by index number:");
                    }
                    p.getPp().setCard(c);
                    System.out.println("Test success!!! \n");
                } catch (Error e) {
                    System.out.println(e.toString());
                }
            } else if (userIn.equalsIgnoreCase("c")) {
                try {
                    c = tv.selectCard(p.getHp(), "Please select a card from your hand cards by index number:");
                    while (c.getType() != Type.ACTION) {
                        tv.wrongCardType();
                        p.getHp().setCard(c);
                        c = tv.selectCard(p.getHp(), "Please select a card from your hand cards by index number:");
                    }
                    ((ActionCard) c).use(g, p);
                    System.out.println("Test success!!! \n");
                } catch (Error e) {
                    System.out.println(e.toString());
                }
            } else if (userIn.equalsIgnoreCase("w")) {
                try {
                    if (p.getPp().isWin()) {
                        tv.win(p);
                        break;
                    } else {
                        tv.notWin();
                    }
                    System.out.println("Test success!!! \n");
                } catch (Error e) {
                    System.out.println(e.toString());
                }
            } else if (userIn.equalsIgnoreCase("e")) {
                break;
            }
            g.players.addLast(p);
        }

    }

    private static void initial() {
        // In config.txt, attributes forms like this:
        // amount type value name (attributes)
        String filePath = System.getProperty("user.dir") + "/src/config/config.txt";
        String encoding = "utf-8";
        try {
            File file = new File(filePath);
            InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
            BufferedReader br = new BufferedReader(read);
            String l = br.readLine();
            while (l != null) {
                String[] args = l.split(" ");
                for (int i=0; i<Integer.parseInt(args[0]); i++) {
                    ap1.setCard(createCard(args));
                    ap2.setCard(createCard(args));
                }
                l = br.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        ap1.arrangement();
        ap2.arrangement();
    }

    private static Card createCard(String[] args) {
        HashMap<String, Color> hm = new HashMap<>();
        hm.put("BROWN", Color.BROWN);
        hm.put("BLUE", Color.BLUE);
        hm.put("GREEN", Color.GREEN);
        hm.put("LIGHT_BLUE", Color.LIGHT_BLUE);
        hm.put("ORANGE", Color.ORANGE);
        hm.put("PINK", Color.PINK);
        hm.put("RAILROAD", Color.RAILROAD);
        hm.put("RED", Color.RED);
        hm.put("UTILITY", Color.UTILITY);
        hm.put("YELLOW", Color.YELLOW);
        hm.put("ALL", Color.ALL);
        switch (args[1]) {
            case "P" -> {
                return new NormalPropertyCard(args[3], Integer.parseInt(args[2]), hm.get(args[4]));
            }
            case "W" -> {
                return new PropertyWildCard(args[3], Integer.parseInt(args[2]), hm.get(args[4]), hm.get(args[5]));
            }
            case "R" -> {
                return new RentActionCard(args[3], Integer.parseInt(args[2]), hm.get(args[4]), hm.get(args[5]));
            }
            case "M" -> {
                return new MoneyCard(args[3], Integer.parseInt(args[2]));
            }
            case "A" -> {
                switch (args[3]) {
                    case "DealBreaker" -> {
                        return new DealBreakerCard(args[3], Integer.parseInt(args[2]));
                    }
                    case "JustSayNo" -> {
                        return new JustSayNoCard(args[3], Integer.parseInt(args[2]));
                    }
                    case "SlyDeal" -> {
                        return new SlyDealCard(args[3], Integer.parseInt(args[2]));
                    }
                    case "ForceDeal" -> {
                        return new ForceDealCard(args[3], Integer.parseInt(args[2]));
                    }
                    case "DebtCollector" -> {
                        return new DebtCollectorCard(args[3], Integer.parseInt(args[2]));
                    }
                    case "ItsMyBirthday" -> {
                        return new ItsMyBirthdayCard(args[3], Integer.parseInt(args[2]));
                    }
                    case "PassGo" -> {
                        return new PassGoCard(args[3], Integer.parseInt(args[2]));
                    }
                    case "House" -> {
                        return new HouseCard(args[3], Integer.parseInt(args[2]));
                    }
                    case "Hotel" -> {
                        return new HotelCard(args[3], Integer.parseInt(args[2]));
                    }
                    case "DoubleTheRent" -> {
                        return new DoubleTheRentCard(args[3], Integer.parseInt(args[2]));
                    }
                }
            }
            default -> {
                return null;
            }
        }
        return null;
    }

}
