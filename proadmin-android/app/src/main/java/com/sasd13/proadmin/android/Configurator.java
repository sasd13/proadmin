package com.sasd13.proadmin.android;

import android.app.Activity;

import com.sasd13.androidex.util.SessionStorage;

/**
 * Created by ssaidali2 on 15/05/2017.
 */

public class Configurator {

    public static class Config {

        private Resolver resolver;

        public Resolver getResolver() {
            return resolver;
        }
    }

    public static Config init(Activity activity) {
        Config config = new Config();
        config.resolver = new Resolver();
        Provider provider = new Provider(config.resolver);

        config.resolver.register(Provider.class, provider);
        config.resolver.register(Router.class, new Router(config.resolver, provider));
        config.resolver.register(SessionStorage.class, new SessionStorage(activity));

        return config;
    }
}
