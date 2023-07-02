package Core;

import Modules.Cards.impl.*;
import Modules.Piles.impl.AllPile;
import Modules.Piles.impl.DiscardPile;
import Modules.Player.impl.Player;
import utils.Color;
import utils.Type;
import java.io.*;
import java.util.ArrayDeque;
import java.util.HashMap;

public class GameController implements IGameController {
    /*
     * This class contains controller for the whole game
     * All logic code was writen here
     * Includes a terminalview, a card pile for all cards, a card pile discard
     * Also contains player list and player number
     */
    public TerminalView tv;

    public AllPile ap;

    public DiscardPile dp;

    public ArrayDeque<Player> players;

    public int playerNumber;

    private static final GameController g = new GameController();

    // initial ap, dp, players
    private GameController() {
        this.ap = AllPile.getAllPile();
        this.dp = DiscardPile.getDiscardPile();
        this.players = new ArrayDeque<Player>();
    }

    // use Singleton Pattern, return the only entity g
    public static GameController getGame() {
        return g;
    }

    // start the game, use initial , then read players, and finally play the game
    public void start(TerminalView tv) {
        this.tv = tv;
        initial();
        readPlayer();
        if (tv.startGame()) play();
    }

    private void initial() {
        // In config.txt, attributes forms like this:
        // amount type value name (attributes)
        String filePath = System.getProperty("user.dir") + "/src/config/config.txt";
        String encoding = "utf-8";
        // make sure there exist the file and can be read for the game
        try {
            File file = new File(filePath);
            InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
            BufferedReader br = new BufferedReader(read);
            String l = br.readLine();
            while (l != null) {
                String[] args = l.split(" ");
                for (int i=0; i<Integer.parseInt(args[0]); i++) {
                    ap.setCard(createCard(args));
                }
                l = br.readLine();
            }
        } catch (IOException e) {
            tv.wrongFile(e);
        }
        ap.arrangement();
    }

    private Card createCard(String[] args) {
        // create the card according to the params from the config file
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

    private void readPlayer() {
        // raed players use terminal
        playerNumber = tv.getPlayers(players);
    }

    public void getCards(Player p, AllPile pile, int cardNumber) {
        // set a number of cards from all pile to the player
        for (int j=0; j<cardNumber; j++) {
            p.getHp().setCard(pile.getCard());
        }
    }

    private void play() {
        // the whole logic for this game
        // initial first 5 cards
        for (int i=0; i<playerNumber; i++) {
            Player p = players.removeFirst();
            getCards(p, ap, 5);
            players.addLast(p);
        }

        // take turns for players
        while (true) {
            Player p = players.removeFirst();
            if (p.getHp().size() == 0) {
                getCards(p, ap, 5);
            } else {
                getCards(p, ap, 2);
            }

            // ask for a operation
            playerOperation(p);

            // check whether the player is win
            if (tv.sayWin()) {
                if (p.getPp().isWin()) {
                    tv.win(p);
                    break;
                } else {
                    tv.notWin();
                }
            }

            // drop cards
            if (p.getHp().size() > 7) {
                tv.dropCard(p, this);
            }

            players.addLast(p);
        }
    }

    private void playerOperation(Player p) {
        tv.startTurn(p);

        // 3 times to play a card
        int n = 1;
        while (n <= 3) {
            char opt = tv.getOpt(n);
            if (opt == 'd') {
                n++;
                continue;
            }
            Card c = null;
            switch (opt) {
                case 'a' -> {
                    // "a" operation
                    c = tv.selectCard(p.getHp(), "Please select a card from your hand cards by index number:");
                    while ((c.getType() != Type.MONEY) && (c.getType() != Type.ACTION)) {
                        tv.wrongCardType();
                        p.getHp().setCard(c);
                        c = tv.selectCard(p.getHp(), "Please select a card from your hand cards by index number:");
                    }
                    p.getBp().setCard(c);
                }
                case 'b' -> {
                    // "b" operation
                    c = tv.selectCard(p.getHp(), "Please select a card from your hand cards by index number:");
                    while (c.getType() != Type.PROPERTY) {
                        tv.wrongCardType();
                        p.getHp().setCard(c);
                        c = tv.selectCard(p.getHp(), "Please select a card from your hand cards by index number:");
                    }
                    p.getPp().setCard(c);
                }
                case 'c' -> {
                    // "c" operation
                    c = tv.selectCard(p.getHp(), "Please select a card from your hand cards by index number:");
                    while (c.getType() != Type.ACTION) {
                        tv.wrongCardType();
                        p.getHp().setCard(c);
                        c = tv.selectCard(p.getHp(), "Please select a card from your hand cards by index number:");
                    }
                    ((ActionCard) c).use(this, p);
                }
                default -> {}
            }
            n++;
        }
    }

    public void testDataIn(Player p1, Player p2, TerminalView tv) {
        // only for test
        this.players.addLast(p1);
        this.players.addLast(p2);
        this.tv = tv;
        this.playerNumber = 2;
    }

}
