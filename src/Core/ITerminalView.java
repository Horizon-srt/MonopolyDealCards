package Core;

import Modules.Cards.impl.Card;
import Modules.Player.impl.Player;

public interface ITerminalView {
    // p是收租金的一方，q是付租金的一方,value是租金的值
    public Card[] rent(Player p, Player q,int value);

    public Player getTargetPlayer(Player p);

    // 得到玩家想要加入到对应财产堆的序号
    public int getIndex();
}
