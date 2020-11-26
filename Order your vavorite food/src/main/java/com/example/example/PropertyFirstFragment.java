package com.example.example;

import java.io.Serializable;

public class PropertyFirstFragment implements Serializable {
    private static final long serialVersionUID = 1L;

    private String mLocationTime=null;
    private String mDescription=null;
    private  String mImageRestaurnt=null;
    private  String mNameRestaurnt=null;
    private String mBlurhash=null;
    private  String mDelivery_price=null;
    private  String mDistance=null;
    private String mFeedbackRestaurants=null;
    private boolean mIsOpenOrClose=false;
public PropertyFirstFragment(){}
    public PropertyFirstFragment(String LocationTime, String Description, String ImageRestaurnt, String NameRestaurnt,
                                 String Blurhash, String Delivery_price, String Distance,String FeedbackRestaurants,boolean isOpenOrClose) {
        this.mLocationTime = LocationTime;
        this.mDescription = Description;
        this.mImageRestaurnt = ImageRestaurnt;
        this.mNameRestaurnt = NameRestaurnt;
        this.mBlurhash = Blurhash;
        this.mDelivery_price = Delivery_price;
       this.mDistance = Distance;
       this.mFeedbackRestaurants=FeedbackRestaurants;
       this.mIsOpenOrClose=isOpenOrClose;
    }

    public boolean ismIsOpenOrClose() {
        return mIsOpenOrClose;
    }

    public String getmLocationTime() {
        return mLocationTime;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmImageRestaurnt() {
        return mImageRestaurnt;
    }

    public String getmNameRestaurnt() {
        return mNameRestaurnt;
    }

    public String getmBlurhash() {
        return mBlurhash;
    }

    public String getmDelivery_price() {
        return mDelivery_price;
    }

    public String getmDistance() {
        return mDistance;
    }

    public String getmFeedbackRestaurants() {
        return mFeedbackRestaurants;
    }
}
