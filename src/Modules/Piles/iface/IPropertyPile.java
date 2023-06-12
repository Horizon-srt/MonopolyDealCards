package Modules.Piles.iface;

import utils.Building;
import utils.Color;

import java.util.HashMap;

public interface IPropertyPile extends IPile{
    // 判断财产牌堆是否符合胜利条件
    public boolean isWin();

    // 得到财产的房产信息
    public HashMap<Integer, Building> getBuilding();
  
    public int cardSize();
}
