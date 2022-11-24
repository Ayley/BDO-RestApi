package me.kleidukos.cache;

import io.micronaut.caffeine.cache.AsyncLoadingCache;
import io.micronaut.caffeine.cache.Caffeine;
import me.kleidukos.models.MarketListKey;
import me.kleidukos.models.RequieredKey;
import me.kleidukos.models.WorldMarketSubListKey;
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

    public AsyncLoadingCache<RequieredKey, String> marketWaitList = Caffeine
            .newBuilder()
            .expireAfterWrite(30, TimeUnit.MINUTES)
            .buildAsync(this::loadMarketWaitList);

    private String loadMarketWaitList(RequieredKey key){
        try {
            return marketAPI.getWorldMarketWaitList(key.region(), key.lang()).get();
        } catch (URISyntaxException | ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public AsyncLoadingCache<MarketListKey, String> marketList = Caffeine
            .newBuilder()
            .expireAfterWrite(30, TimeUnit.MINUTES)
            .buildAsync(this::loadWorldMarketList);

    private String loadWorldMarketList(MarketListKey key){
        try {
            return marketAPI.getWorldMarketList(key).get();
        } catch (InterruptedException | ExecutionException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public AsyncLoadingCache<WorldMarketSubListKey, String> marketSubList = Caffeine
            .newBuilder()
            .expireAfterWrite(30, TimeUnit.MINUTES)
            .buildAsync(this::loadWorldMarketSubList);

    private String loadWorldMarketSubList(WorldMarketSubListKey key){
        try {
            return marketAPI.getWorldMarketSubList(key).get();
        } catch (InterruptedException | ExecutionException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
