package me.kleidukos.requests;

import io.micronaut.http.annotation.*;
import me.kleidukos.Application;
import me.kleidukos.models.MarketListKey;
import me.kleidukos.models.RequieredKey;
import me.kleidukos.models.WorldMarketSubListKey;
import me.kleidukos.util.Lang;
import me.kleidukos.util.Region;

import java.util.Optional;

@Controller("/market")
public class Market {

    @Get("/WorldMarketHotList")
    public String getWorldMarketHotList(@QueryValue("region") Optional<Region> region, @QueryValue("lang") Optional<String> lang) {
        if (region.isEmpty())
            return "Querry parameter 'region' is required";
        return Application.getApplication().cacheManager.marketHotList.synchronous().get(
                new RequieredKey(region.get(), lang.isEmpty() ? Lang.ENGLISH : Lang.getOrDefault(lang.get())));
    }

    @Get("/WorldMarketWaitList")
    public String getWorldMarketWaitList(@QueryValue("region") Optional<Region> region, @QueryValue("lang") Optional<String> lang) {
        if (region.isEmpty())
            return "Querry parameter 'region' is required";
        return Application.getApplication().cacheManager.marketWaitList.synchronous().get(
                new RequieredKey(region.get(), lang.isEmpty() ? Lang.ENGLISH : Lang.getOrDefault(lang.get())));
    }

    @Get("/WorldMarketList")
    public String getWorldMarketList(@QueryValue("region") Optional<Region> region, @QueryValue("lang") Optional<String> lang, @QueryValue("mainCategory") Optional<Short> mainCategory, @QueryValue("subCategory") Optional<Short> subCategory) {
        if (region.isEmpty())
            return "Querry parameter 'region' is required";
        if (mainCategory.isEmpty())
            return "Querry parameter 'mainCategory' is required";
        if (subCategory.isEmpty())
            return "Querry parameter 'subCategory' is required";

        return Application.getApplication().cacheManager.marketList.synchronous().get(
                new MarketListKey(new RequieredKey(region.get(), lang.isEmpty() ? Lang.ENGLISH : Lang.getOrDefault(lang.get())),
                        mainCategory.get(),
                        subCategory.get()));
    }

    @Get("/WorldMarketSubList")
    public String getWorldMarketSubList(@QueryValue("region") Optional<Region> region, @QueryValue("lang") Optional<String> lang, @QueryValue("mainKey") Optional<Short> mainKey) {
        if (region.isEmpty())
            return "Querry parameter 'region' is required";
        if (mainKey.isEmpty())
            return "Querry parameter 'mainCategory' is required";

        return Application.getApplication().cacheManager.marketSubList.synchronous().get(
                new WorldMarketSubListKey(new RequieredKey(region.get(), lang.isEmpty() ? Lang.ENGLISH : Lang.getOrDefault(lang.get())),
                        mainKey.get()));
    }
}
