package Core;

import Modules.Cards.impl.Card;
import Modules.Cards.impl.PropertyCard;
import Modules.Piles.iface.IPile;
import Modules.Player.impl.Player;

public interface ITerminalView {
    /*
     * only interfaces for modules
     * for convinent, we use Chinese, and didn't set some of the terminal view
     * methods here
     */

    // p是收租金的一方，q是付租金的一方,value是租金的值
    public Card[] rent(Player p, Player q,int value);

    // 返回 slyCard 或 ForceDeal 或 dealbreaker 或 debt collector 和 rent 相关操作的目标玩家
    public Player getTargetplayer(Player[] player);

    // 返回 slyCard 或 ForceDeal 的目标财产
    public PropertyCard slyForceDealTargetProperty(Player player);

    // 返回 ForceDeal 玩家自己想要交换的财产
    public PropertyCard forceDealOwnProperty(Player player);

    // DealBreaker 获取目标财产堆的序号
    public int getPropertyIndex(Player q);

    // justsayno 询问目标玩家是否使用 justsayno 并返回 卡片序号 或 -1
    public int askJustSayNo(Player q, String cardName);

    // House 获取想要将房子添加到财产堆的序号；
    public int getHouseIndex(Player player);

    // Hotel 获取想要将酒店添加到财产堆的序号；
    public int getHotelIndex(Player player);

    // 得到玩家想要加入到对应财产堆的序号
    public int getIndex(IPile propertyList);

    // 返回用户想要判断对应财产是否满的颜色
    public String getStringColor();

    // 返回用户想选择的用于收租的自己的财产堆的序号
    public int rentPropertyIndex(Player player);

    // 返回用户想要收租的财产堆的颜色
    public String rentPropertyColor(Player player);

    // 返回是否使用double, 如果使用则在rentActionCard中将double牌移到弃牌堆
    public int doubleRentCardCheck(Player player);

}
