package com.framework.demo.https;


import com.framework.demo.https.cookie.CookieManger;

public interface IBuilder {


    /**是否开启调试模式
     * @param isDebug
     * @return
     */
    HttpConfig.Builder setDebug(boolean isDebug);


    /**是否开启缓存
     * @param isCache
     * @return
     */
    HttpConfig.Builder setCache(boolean isCache);

    /**设置缓存大小
     * @param cacheSize
     * @return
     */
    HttpConfig.Builder setCacheSize(long cacheSize);

    /**缓存时间
     * @param cacheTime
     * @return
     */
    HttpConfig.Builder setCacheTime(long cacheTime);


    /**
     * 全局公共请求头
     * @param key
     * @param value
     * @return
     */
    HttpConfig.Builder setCommonHeader(String key, String value);

    /**
     * 全局公共Get请求参数
     * @param key
     * @param value
     * @return
     */
    HttpConfig.Builder setCommonGetParams(String key, String value);

    /**
     * 全局公共Post请求参数
     * @param key
     * @param value
     * @return
     */
    HttpConfig.Builder setCommonPostParams(String key, String value);


    /** connect  read  write timeout
     * @param timeOut
     * @return
     */
    HttpConfig.Builder timeout(int timeOut);


    /** connect timeout
     * @param timeOut
     * @return
     */
    HttpConfig.Builder connectTimeout(int timeOut);

    /** read timeout
     * @param timeOut
     * @return
     */
    HttpConfig.Builder readTimeout(int timeOut);

    /** write timeout
     * @param timeOut
     * @return
     */
    HttpConfig.Builder writeTimeout(int timeOut);


    /**添加Cookie
     * @param cookieManger
     * @return
     */
    HttpConfig.Builder cookJar(CookieManger cookieManger);
}
