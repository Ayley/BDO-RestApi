package me.kleidukos.cache;

import io.micronaut.caffeine.cache.AsyncLoadingCache;
import io.micronaut.caffeine.cache.Caffeine;
import me.kleidukos.models.MarketListKey;
import me.kleidukos.models.RequieredKey;
import me.kleidukos.util.MarketAPI;

import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CacheManager {

    private final MarketAPI marketAPI;

    public CacheManager(){
        marketAPI = new MarketAPI();
    }

    public AsyncLoadingCache<RequieredKey, String> marketHotList = Caffeine
            .newBuilder()
            .expireAfterWrite(30, TimeUnit.MINUTES)
            .buildAsync(this::loadMarketHotList);

    private String loadMarketHotList(RequieredKey key){
        try {
            return marketAPI.getWorldMarketHotList(key.region(), key.lang()).get();
        } catch (URISyntaxException | ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public AsyncLoadingCache<MarketListKey, String> marketList = Caffeine
            .newBuilder()
            .expireAfterWrite(30, TimeUnit.MINUTES)
            .buildAsync(null);

    private String loadMarketList(MarketListKey key){
        try {
            return marketAPI.getMarketList(key).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
