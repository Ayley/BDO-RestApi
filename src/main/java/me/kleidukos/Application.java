package me.kleidukos;

import io.micronaut.runtime.Micronaut;
import me.kleidukos.cache.CacheManager;

public class Application {

    public final CacheManager cacheManager;
    private static Application application;

    public Application(){
        application = this;
        cacheManager = new CacheManager();
        Micronaut.run(Application.class);
    }

    public static void main(String[] args) {
        new Application();
    }

    public static Application getApplication() {
        return application;
    }
}
