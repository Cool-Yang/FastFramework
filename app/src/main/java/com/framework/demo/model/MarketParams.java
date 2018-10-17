package com.framework.demo.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yangzhigang on 2018/3/22.
 */

public class MarketParams implements Parcelable {
    public String symbol;
    public String symbols;
    public String tradPriceUSDX;
    public String tradPriceCNY;
    public float riseFallPercent;
    public int riseFallTage;
    public String transactionSum;
//    public List<MarketLineParams> quotesList;
    public int pricingCoin;//定价币保留位数
    public int tradeCoin;//交易币保留位数
    public int coinArea;//分区(0,主区;1,创新区)

    //创业版新增
    public int boardMarketType;         //版块 0：主版 1：创业版
    public int forcedDisplay;           //是否显示,仅对创业版有效   0：不显示 1：显示  2：按条件显示
    public float hitsVol;               //搜索量 排序用，仅对创业版有效
    public float assetUsdVol;           //持有量 （折算为USDX） 排序用，仅对创业版有效
    public float transactionUsdVol;     //成交量（折算为USDX） 排序用 ，仅对创业版有效
    public int sort;                    //排序序号

    private int marketType;//分区(0,主区;1,创新区)

    public MarketParams(){}


    protected MarketParams(Parcel in) {
        symbol = in.readString();
        symbols = in.readString();
        tradPriceUSDX = in.readString();
        tradPriceCNY = in.readString();
        riseFallPercent = in.readFloat();
        riseFallTage = in.readInt();
        transactionSum = in.readString();
        pricingCoin = in.readInt();
        tradeCoin = in.readInt();
        coinArea = in.readInt();
        boardMarketType = in.readInt();
        forcedDisplay = in.readInt();
        hitsVol = in.readFloat();
        assetUsdVol = in.readFloat();
        transactionUsdVol = in.readFloat();
        sort = in.readInt();
        marketType = in.readInt();
    }

    public static final Creator<MarketParams> CREATOR = new Creator<MarketParams>() {
        @Override
        public MarketParams createFromParcel(Parcel in) {
            return new MarketParams(in);
        }

        @Override
        public MarketParams[] newArray(int size) {
            return new MarketParams[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(symbol);
        parcel.writeString(symbols);
        parcel.writeString(tradPriceUSDX);
        parcel.writeString(tradPriceCNY);
        parcel.writeFloat(riseFallPercent);
        parcel.writeInt(riseFallTage);
        parcel.writeString(transactionSum);
        parcel.writeInt(pricingCoin);
        parcel.writeInt(tradeCoin);
        parcel.writeInt(coinArea);
        parcel.writeInt(boardMarketType);
        parcel.writeInt(forcedDisplay);
        parcel.writeFloat(hitsVol);
        parcel.writeFloat(assetUsdVol);
        parcel.writeFloat(transactionUsdVol);
        parcel.writeInt(sort);
        parcel.writeInt(marketType);
    }

    @Override
    public String toString() {
        return "MarketParams{" +
                "symbol='" + symbol + '\'' +
                ", symbols='" + symbols + '\'' +
                ", tradPriceUSDX='" + tradPriceUSDX + '\'' +
                ", tradPriceCNY='" + tradPriceCNY + '\'' +
                ", riseFallPercent=" + riseFallPercent +
                ", riseFallTage=" + riseFallTage +
                ", transactionSum='" + transactionSum + '\'' +
                ", pricingCoin=" + pricingCoin +
                ", tradeCoin=" + tradeCoin +
                ", coinArea=" + coinArea +
                ", boardMarketType=" + boardMarketType +
                ", forcedDisplay=" + forcedDisplay +
                ", hitsVol=" + hitsVol +
                ", assetUsdVol=" + assetUsdVol +
                ", transactionUsdVol=" + transactionUsdVol +
                ", sort=" + sort +
                ", marketType=" + marketType +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof MarketParams){
            MarketParams marketParams = (MarketParams)obj;
            return marketParams.symbols.equals(symbols);
        }else
            return false;
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
