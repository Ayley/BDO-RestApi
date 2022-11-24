package me.kleidukos.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.handler.codec.http.HttpHeaders;
import me.kleidukos.models.GetWorldMarketSubList;
import me.kleidukos.models.MarketListKey;
import me.kleidukos.models.WorldMarketSubListKey;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class MarketAPI {

    private final String USERAGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36 Edg/106.0.1370.42";

    public MarketAPI() {
        System.setProperty("jdk.httpclient.allowRestrictedHeaders", "Content-Length");
    }

    public CompletableFuture<String> getWorldMarketHotList(Region region, Lang lang) throws URISyntaxException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(region.getRegion() + "GetWorldMarketHotList"))
                .method("POST", HttpRequest.BodyPublishers.noBody())
                .header("User-agent", USERAGENT)
                .header("content-length", "0")
                .header("cookie", "lang=" + (lang != null ? lang.getLang() : Lang.ENGLISH.getLang()))
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body);
    }
    public CompletableFuture<String> getWorldMarketWaitList(Region region, Lang lang) throws URISyntaxException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(region.getRegion() + "GetWorldMarketWaitList"))
                .method("POST", HttpRequest.BodyPublishers.noBody())
                .header("User-agent", USERAGENT)
                .header("content-length", "0")
                .header("cookie", "lang=" + (lang != null ? lang.getLang() : Lang.ENGLISH.getLang()))
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body);
    }

    public CompletableFuture<String> getWorldMarketList(MarketListKey key) throws URISyntaxException {
        HttpClient client = HttpClient.newHttpClient();
        String body = "mainCategory=" + key.mainCategory() + "&subCategory=" + key.subCategory() + "&__RequestVerificationToken=hJsQARTIMDT7h0ZlXqDLQgSeD3j4Tb92wkDx9dDI8rkvBCtx_gBTErHpENECdrVo8BDEA_uwHUiZ74ZaRBGnWVHVYEsQsU3H4TUMF1ioDFM1";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(key.key().region().getRegion() + "GetWorldMarketList"))
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .header("User-agent", USERAGENT)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Cookie", "__RequestVerificationToken=JY00JZ1xlYU9FIj1ZVZtWHCDNJddWbCEh_ZqLUwjgts6IZ3395-yR2jHtg4iHNe0cwHfiwEr_7mc-ZI1tETz9xt_5qDPmGi_eMZadW3f6Cg1; lang=" + (key.key().lang() != null ? key.key().lang().getLang() : Lang.ENGLISH.getLang()) + ";")
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body);
    }

    public CompletableFuture<String> getWorldMarketSubList(WorldMarketSubListKey key) throws URISyntaxException {
        HttpClient client = HttpClient.newHttpClient();
        String body = "mainKey=" + key.mainKey() + "&usingCleint=0&__RequestVerificationToken=hJsQARTIMDT7h0ZlXqDLQgSeD3j4Tb92wkDx9dDI8rkvBCtx_gBTErHpENECdrVo8BDEA_uwHUiZ74ZaRBGnWVHVYEsQsU3H4TUMF1ioDFM1";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(key.key().region().getRegion() + "GetWorldMarketSubList"))
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .header("User-agent", USERAGENT)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Cookie", "__RequestVerificationToken=JY00JZ1xlYU9FIj1ZVZtWHCDNJddWbCEh_ZqLUwjgts6IZ3395-yR2jHtg4iHNe0cwHfiwEr_7mc-ZI1tETz9xt_5qDPmGi_eMZadW3f6Cg1; lang=" + (key.key().lang() != null ? key.key().lang().getLang() : Lang.ENGLISH.getLang()) + ";")
                .build();

        var mapper = new ObjectMapper();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body).thenApply(result -> {
            try {
                return mapper.writeValueAsString(mapper.readValue(result, GetWorldMarketSubList.class).updateIcons());
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }
}

