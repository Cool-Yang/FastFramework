package com.framework.demo;

import android.app.Application;

import com.framework.demo.https.HttpClient;
import com.framework.demo.https.HttpConfig;
import com.framework.demo.https.cookie.CookieManger;
import com.framework.demo.utils.LogUtil;
import com.framework.demo.utils.Utils;

import java.util.ArrayList;

import okhttp3.Cookie;

public class MyApplication extends Application{

    public static MyApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

        Utils.init(this);

        LogUtil.init(true);

        ArrayList<Cookie> cookies = new ArrayList<>();
        Cookie cookie = new Cookie.Builder()
                .hostOnlyDomain("app.6x.com")
                .name("Language").value("en")
                .build();
        cookies.add(cookie);

        CookieManger cookieManger = new CookieManger(this);
        cookieManger.addCookies(cookies);

        HttpConfig config = HttpConfig.getBulider()
                .connectTimeout(10)
                .setDebug(true)
                .setCache(true)
                .cookJar(cookieManger)
                .build();
        HttpClient.getInstance().init(config);
    }
}
