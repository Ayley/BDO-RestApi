package me.kleidukos.models;

import java.util.List;

public final class GetWorldMarketSubList {

    public List<GetWorldMarketSubListItem> detailList;
    public short resultCode;
    public String resultMsg;

    public GetWorldMarketSubList updateIcons(){
        detailList.forEach(item -> item.updateIcon());
        return this;
    }

}
