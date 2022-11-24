package me.kleidukos.util;

import io.netty.handler.codec.http.HttpHeaders;
import me.kleidukos.models.MarketListKey;

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

        System.out.println(lang != null ? lang.getLang() : Lang.ENGLISH.getLang());

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body);
    }

    public CompletableFuture<String> getMarketList(MarketListKey key) {
        return null;
    }
}

