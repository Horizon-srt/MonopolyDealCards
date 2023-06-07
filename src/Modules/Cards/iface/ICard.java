package Modules.Cards.iface;

import utils.Color;
import utils.Type;

public interface ICard {
    // 获取卡片名称
    public String getName();

    // 获取卡片价值
    public int getValue();

    // 获取卡片类型
    public Type getType();

}
