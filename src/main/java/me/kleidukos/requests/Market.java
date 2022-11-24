package me.kleidukos.requests;

import io.micronaut.http.annotation.*;
import me.kleidukos.Application;
import me.kleidukos.models.RequieredKey;
import me.kleidukos.util.Lang;
import me.kleidukos.util.Region;

import java.util.Optional;

@Controller("/market")
public class Market {

    @Get("/MarketHotList")
    public String getMarketHotList(@QueryValue("region") Optional<Region> region, @QueryValue("lang") Optional<String> lang){
        if(region.isEmpty())
            return "Querry parameter 'region' is required";
        return Application.getApplication().cacheManager.marketHotList.synchronous().get(
                new RequieredKey(region.get(), lang.isEmpty() ? Lang.ENGLISH : Lang.getLangOrEnglish(lang.get())));
    }

    @Get("/MarketList")
    public String getMarketList(@QueryValue("region") Optional<Region> region, @QueryValue("lang") Optional<String> lang, @QueryValue("mainCategory") Optional<Short> mainCategory, @QueryValue("subCategory") Optional<Short> subCategory){
        return null;
    }
}
