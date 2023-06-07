package Modules.Piles.iface;

import Modules.Cards.impl.Card;

public interface IPile {
    // 给出牌堆中第一张牌并删除
    public Card getCard();

    // 向牌堆中插入一张牌
    public void setCard(Card c);

    // 将牌堆随机排列
    public void arrangement();

    // 返回牌堆大小
    public int size();

    // 格式化输出索引号，卡片名称，卡片面额
    // 索引号从1开始
    public void listCards();

    // 使用索引号获取这张牌，返回并删除
    public Card getCardById(int id);
}
