package com.framework.demo.https;


import com.framework.demo.https.cookie.CookieManger;

import java.util.LinkedHashMap;
import java.util.Map;

public class HttpConfig {
    private int connectTimeOut;                                             //connect timeout
    private int readTimeOut;                                                //read timeout
    private int writeTimeOut;                                               //write timeout

    private boolean isDebug;                                                //是否为debug模式
    private boolean isCache;                                                //是否设置缓存
    private long cachaSize;                                                 //缓存大小
    private long cacheTime;                                                 //缓存时间
    private Map<String, String> mCommonHeaders;                             //全局公共请求头
    private Map<String, String> mCommonGetParams;                           //Get全局公共请求参数
    private Map<String, String> mCommonPostParams;                          //Post全局公共请求参数

    private CookieManger cookieJar;                                         //Cookie管理

    private HttpConfig() {
    }

    public int getConnectTimeOut() {
        return connectTimeOut;
    }

    public int getReadTimeOut() {
        return readTimeOut;
    }

    public int getWriteTimeOut() {
        return writeTimeOut;
    }

    public boolean isDebug() {
        return isDebug;
    }

    public boolean isCache() {
        return isCache;
    }

    public long getCachaSize() {
        return cachaSize;
    }

    public long getCacheTime() {
        return cacheTime;
    }

    public Map<String, String> getmCommonHeaders() {
        return mCommonHeaders;
    }

    public Map<String, String> getmCommonGetParams() {
        return mCommonGetParams;
    }

    public Map<String, String> getmCommonPostParams() {
        return mCommonPostParams;
    }

    public CookieManger getCookieJar() {
        return cookieJar;
    }

    public static Builder getBulider() {
        return new Builder();
    }

    public static class Builder implements IBuilder {
        private final int DEFAULT_MILLISECONDS = 60000;                         //默认的超时时间
        private final long DEFAULT_CACHE_SIZE = 10 * 1024 * 1024;               //默认缓存大小 10M
        private final long DEFAULT_CACHE_TIME = 60 * 60 * 24 * 3;               //默认缓存时间 3天

        private int connectTimeOut = DEFAULT_MILLISECONDS;                      //connect timeout
        private int readTimeOut = DEFAULT_MILLISECONDS;                         //read timeout
        private int writeTimeOut = DEFAULT_MILLISECONDS;                        //write timeout

        private boolean isDebug;                                                //是否为debug模式
        private boolean isCache;                                                //是否设置缓存
        private long cachaSize = DEFAULT_CACHE_SIZE;                            //缓存大小
        private long cacheTime = DEFAULT_CACHE_TIME;                            //缓存时间
        private Map<String, String> mCommonHeaders;                             //全局公共请求头
        private Map<String, String> mCommonGetParams;                           //Get全局公共请求参数
        private Map<String, String> mCommonPostParams;                          //Post全局公共请求参数
        private CookieManger cookieJar;                                         //Cookie管理

        @Override
        public Builder setDebug(boolean isDebug) {
            this.isDebug = isDebug;
            return this;
        }

        @Override
        public Builder setCache(boolean isCache) {
            this.isCache = isCache;
            return this;
        }

        @Override
        public Builder setCacheSize(long cacheSize) {
            this.cachaSize = cacheSize;
            return this;
        }

        @Override
        public Builder setCacheTime(long cacheTime) {
            this.cacheTime = cacheTime;
            return this;
        }

        @Override
        public Builder setCommonHeader(String key, String value) {
            if (this.mCommonHeaders == null)
                this.mCommonHeaders = new LinkedHashMap<>();
            this.mCommonHeaders.put(key, value);

            return this;
        }

        @Override
        public Builder setCommonGetParams(String key, String value) {
            if (this.mCommonGetParams == null)
                mCommonGetParams = new LinkedHashMap<>();
            mCommonGetParams.put(key, value);
            return this;
        }

        @Override
        public Builder setCommonPostParams(String key, String value) {
            if (this.mCommonPostParams == null)
                this.mCommonPostParams = new LinkedHashMap<>();
            this.mCommonPostParams.put(key, value);
            return this;
        }

        @Override
        public Builder timeout(int timeOut) {
            this.connectTimeOut = timeOut;
            this.readTimeOut = timeOut;
            this.writeTimeOut = timeOut;
            return this;
        }

        @Override
        public Builder connectTimeout(int timeOut) {
            this.connectTimeOut = timeOut;
            return this;
        }

        @Override
        public Builder readTimeout(int timeOut) {
            this.readTimeOut = timeOut;
            return this;
        }

        @Override
        public Builder writeTimeout(int timeOut) {
            this.writeTimeOut = timeOut;
            return this;
        }

        @Override
        public Builder cookJar(CookieManger cookieManger) {
            this.cookieJar = cookieManger;
            return this;
        }

        public HttpConfig build() {
            HttpConfig config = new HttpConfig();
            config.isDebug = this.isDebug;
            config.isCache = this.isCache;
            config.cachaSize = this.cachaSize;
            config.cacheTime = this.cacheTime;
            config.connectTimeOut = this.connectTimeOut;
            config.readTimeOut = this.readTimeOut;
            config.writeTimeOut = this.writeTimeOut;
            config.cookieJar = this.cookieJar;

            if (this.mCommonHeaders != null && !this.mCommonHeaders.isEmpty()) {
                config.mCommonHeaders = new LinkedHashMap<>();
                config.mCommonHeaders.putAll(this.mCommonHeaders);
            }


            if (this.mCommonGetParams != null && !this.mCommonGetParams.isEmpty()) {
                config.mCommonGetParams = new LinkedHashMap<>();
                config.mCommonGetParams.putAll(this.mCommonGetParams);
            }

            if (this.mCommonPostParams != null && !this.mCommonPostParams.isEmpty()) {
                config.mCommonPostParams = new LinkedHashMap<>();
                config.mCommonPostParams.putAll(this.mCommonPostParams);
            }
            return config;
        }
    }


}
