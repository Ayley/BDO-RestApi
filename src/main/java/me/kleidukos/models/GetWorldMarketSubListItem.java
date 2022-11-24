package me.kleidukos.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetWorldMarketSubListItem {
    public long pricePerOne;
    public long totalTradeCount;
    public short keyType;
    public int mainKey;
    public short subKey;
    public int count;
    public String name;
    public short grade;
    public short mainCategory;
    public short subCategory;
    public short chooseKey;
    public String icon;

    public void updateIcon(){
        icon = "https://s1.pearlcdn.com/NAEU/TradeMarket/Common/img/BDO/item/" + mainKey + ".png";
    }
}
