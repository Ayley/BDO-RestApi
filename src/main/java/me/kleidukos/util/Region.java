package me.kleidukos.util;

public enum Region {
    EU("https://eu-trade.naeu.playblackdesert.com/Home/"),
    NA("https://na-trade.naeu.playblackdesert.com/Home/");

    private final String region;

    Region(String region){
        this.region = region;
    }

    public String getRegion() {
        return region;
    }
}
