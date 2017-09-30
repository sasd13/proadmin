package com.sasd13.proadmin.android;

import android.app.Activity;

import com.sasd13.androidex.util.SessionStorage;
import com.sasd13.proadmin.android.util.AppProperties;

/**
 * Created by ssaidali2 on 15/05/2017.
 */

public class Configurator {

    public static class Config {

        private Resolver resolver;
        private Provider provider;
        private Router router;

        public Provider getProvider() {
            return provider;
        }

        public Router getRouter() {
            return router;
        }
    }

    public static Config init(Activity activity) {
        AppProperties.init(activity);

        Config config = new Config();
        config.resolver = new Resolver();
        config.provider = new Provider(config.resolver);
        config.router = new Router(config.resolver, config.provider);

        config.resolver.register(Provider.class, config.provider);
        config.resolver.register(Router.class, config.router);
        config.resolver.register(SessionStorage.class, new SessionStorage(activity));

        return config;
    }
}
