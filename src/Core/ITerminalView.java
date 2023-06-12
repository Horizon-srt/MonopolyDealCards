package Core;

import Modules.Cards.iface.IPropertyCard;
import Modules.Cards.impl.Card;
import Modules.Cards.impl.PropertyCard;
import Modules.Piles.impl.PropertyPile;
import Modules.Player.impl.Player;

import java.util.LinkedList;

public interface ITerminalView {
    // p是收租金的一方，q是付租金的一方,value是租金的值
    public Card[] rent(Player p, Player q,int value);

    // 返回 slyCard 或 ForceDeal 或 dealbreaker 或 debt collector 的目标玩家
    public Player getTargetplayer(Player[] player);

    // 返回 slyCard 或 ForceDeal 的目标财产
    public PropertyCard slyForceDealTargetProperty(Player player);

    // 返回 ForceDeal 玩家自己想要交换的财产
    public PropertyCard forceDealOwnProperty(Player player);

    // DealBreaker 获取目标财产堆并删除
    public LinkedList<IPropertyCard> getProperty(PropertyPile pPile);

    // justsayno 询问目标玩家是否使用 justsayno 并返回 卡片序号 或 -1
    public int askJustSayNo(Player q, String cardName);

    // 得到玩家想要加入到对应财产堆的序号
    public int getIndex();

    // 返回用户想要判断对应财产是否满的颜色
    public String getStringColor();
}
