package com.example.earthquakeapp;

public class quakedetails {
    private String mLoca;

    private String mLocaDetail;

    private String mMag;

    private String mTime;

    private String mDate;

    private String mUrl;

    public quakedetails(String vLoca, String vLocaDetail, String vMag, String vTime, String vDate, String vUrl) {
        mLoca = vLoca;
        mLocaDetail = vLocaDetail;
        mMag = vMag;
        mDate = vDate;
        mTime = vTime;
        mUrl = vUrl;
    }

    public String getDate() {
        return mDate;
    }

    public String getLoca() {
        return mLoca;
    }

    public String getTime() {
        return mTime;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getLocaDetail() {
        return mLocaDetail;
    }

    public String getMag() {
        return mMag;
    }
}
