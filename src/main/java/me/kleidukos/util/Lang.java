package me.kleidukos.util;

import java.util.Arrays;

public enum Lang {
    GERMAN("de-DE"),
    ENGLISH("en-US"),
    ESPANIOL("es-ES"),
    FRENCH("fr-FR");

    private final String lang;

    Lang(String lang){
        this.lang = lang;
    }

    public String getLang() {
        return lang;
    }

    public static Lang getLangOrEnglish(String lang){
        try {
            return Lang.valueOf(lang);
        }catch (Exception e){
            return Arrays.stream(Lang.values()).filter(l -> l.getLang().toLowerCase().equals(lang.toLowerCase())).findFirst().orElseGet(() -> Lang.ENGLISH);
        }
    }
}
