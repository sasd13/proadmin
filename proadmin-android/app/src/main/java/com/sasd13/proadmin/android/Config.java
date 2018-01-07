package com.sasd13.proadmin.android;

import android.app.Activity;

import com.sasd13.androidex.util.LocalStorage;
import com.sasd13.androidex.util.SessionStorage;
import com.sasd13.proadmin.android.util.AppProperties;

/**
 * Created by ssaidali2 on 15/05/2017.
 */

public class Config {

    private Resolver resolver;
    private Provider provider;
    private Router router;

    public Config(Activity activity) {
        AppProperties.init(activity);

        resolver = new Resolver();
        provider = new Provider(resolver);
        router = new Router(resolver, provider);

        resolver.register(Provider.class, provider);
        resolver.register(Router.class, router);
        resolver.register(SessionStorage.class, new SessionStorage(activity));
        resolver.register(LocalStorage.class, new LocalStorage(activity.getIntent()));
    }

    public Provider getProvider() {
        return provider;
    }

    public Router getRouter() {
        return router;
    }
}
