package Modules.Piles.iface;

import utils.Building;
import utils.Color;

import java.util.HashMap;

public interface IPropertyPile extends IPile{
    // 判断财产牌堆是否符合胜利条件
    public boolean isWin();


    // 得到房产的映射
    public HashMap<Color, Building> getHouseMap();
}
