package com.example.example;


import java.io.Serializable;

public class DataRestaurants implements Serializable
        {
            private static final long serialVersionUID = 1L;
    private String mBlurhash;
    private String mCity;
    private String mCurrency;// M
    private String mLocationTime;
    private String mDescription;
    private String mImage;
    private String mName;
    private Boolean mOnline;
    private String mTags;
    private String mDelivery_price;
    private String Distance;
   // public DataRestaurants(String mBlurhash, String mCity, String mCurrency, String mImage, String mLocationTime, String mDescription,String mName,String mOnline,String mTags,String mDelivery_price) {

  //  }
  /*  public DataRestaurants( String mBlurhash,  String mImage, String mLocationTime, String mDescription,String mName,Boolean mOnline,String mTags,String mDelivery_price) {
     this.mBlurhash=mBlurhash;
      this.mImage=mImage;
      this.mLocationTime=mLocationTime;
      this.mDescription=mDescription;
      this.mDelivery_price=mDelivery_price;
      this.mName=mName;
      this.mOnline=mOnline;
      this.mTags=mTags;

    }*/

    public DataRestaurants(String mBlurhash,String mImage,String mName,String mDescription,String mDelivery_price,String mLocationTime,String Distance){
        this.mBlurhash=mBlurhash;
       this.mImage=mImage;
        this.mLocationTime=mLocationTime;
        this.mDescription=mDescription;
        this.mDelivery_price=mDelivery_price;
        this.mName=mName;
        this.mDescription=mDescription;
        this.Distance= Distance;
    }
    public void setmLocation(String mLocationTime) {
        this.mLocationTime = mLocationTime;
    }

    public String getmBlurhash() {
        return mBlurhash;
    }

    public String getmCity() {
        return mCity;
    }

    public String getmCurrency() {
        return mCurrency;
    }

    public String getmImage() {
        return mImage;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmLocationTime() {
        return mLocationTime;
    }

    public String getmName() {
        return mName;
    }

    public Boolean getmOnline() {
        return mOnline;
    }

    public void setmBlurhash(String mBlurhash) {
        this.mBlurhash = mBlurhash;
    }

    public String getmTags() {
        return mTags;
    }

    public String getmDelivery_price() {
        return mDelivery_price;
    }

            public String getDistance() {
                return Distance;
            }
        }
