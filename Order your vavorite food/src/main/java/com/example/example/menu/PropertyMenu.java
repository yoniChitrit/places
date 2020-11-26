package com.example.example.menu;

import java.io.Serializable;

//***
// it belong to class AdapterMenu and MainActivityMenu
//****
public class PropertyMenu implements Serializable {
    private static final long serialVersionUID = 1L;





    //data buffer and organizer
    private String mProperty_Value_title = null;


////*****************************************

    //index 1 if order already
    private String mMy_Recent_Order = null;

    //index 2 if  exist
    private String PAY_ATTENTION = null;

    //if exist
    public String getPAY_ATTENTION() {
        return PAY_ATTENTION;
    }

    public void setPAY_ATTENTION(String PAY_ATTENTION) {
        this.PAY_ATTENTION = PAY_ATTENTION;
    }

    //////if order already
    public String getmMy_Recent_Order() {
        return mMy_Recent_Order;
    }

    public void setmMy_Recent_Order(String mMy_Recent_Order) {
        this.mMy_Recent_Order = mMy_Recent_Order;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    ///**************************************

    public PropertyMenu() {
    }

    //******************************************************************************************************

    // menu items
    private String mImageMenu = null;
    private String mNameFoodMenu = null;
    private String mInfoFoodMenu = null;
    private String mPriceFoodMenu = null;
    private String mBlurHashMenu = null;
    private String mIsFoodPopularMenu = null;
    private String mMarqueeItem_Menu=null;
    private boolean mIsAvailableItem=true ;

    public PropertyMenu(String imageMenu, String nameFoodMenu, String infoFoodMenu, String priceFoodMenu, String BlurHashMenu, boolean IsAvailableItem, String IsFoodPopularMenu,String MarqueeItem_Menu) {

        this.mImageMenu = imageMenu;
        this.mNameFoodMenu = nameFoodMenu;
        this.mInfoFoodMenu = infoFoodMenu;
        this.mPriceFoodMenu = priceFoodMenu;

        this.mBlurHashMenu = BlurHashMenu;
        this.mIsFoodPopularMenu = IsFoodPopularMenu;
        this.mMarqueeItem_Menu=MarqueeItem_Menu;
        mIsAvailableItem = IsAvailableItem;

    }

    /// ALL THE INDEX menu items

    public void setmIsAvailableItem(Boolean mIsAvailableItem) {
        this.mIsAvailableItem = mIsAvailableItem;
    }

    public void setmMarqueeItem_Menu(String mMarqueeItem_Menu) {// if i know he order i sign him/her
        this.mMarqueeItem_Menu = mMarqueeItem_Menu;
    }

    public String getmMarqueeItem_Menu() {
        return mMarqueeItem_Menu;
    }

    public String getmImageMenu() {
        return mImageMenu;
    }

    public String getmNameFoodMenu() {
        return mNameFoodMenu;
    }

    public String getmInfoFoodMenu() {
        return mInfoFoodMenu;
    }

    public String getmPriceFoodMenu() {
        return mPriceFoodMenu;
    }

    public String getmBlurHashMenu() {
        return mBlurHashMenu;
    }

    public boolean ismIsAvailableItem() {
        return mIsAvailableItem;
    }

    public String getmIsFoodPopularMenu() {
        return mIsFoodPopularMenu;
    }
    ///////////********************************************************************


    //index 0 ****************************************************************************************

    private String mNameRestaurants = null;
    private String mDescription = null;
    private String mClockRestaurantsOpen = null;
    private String mFeedbackRestaurants = null;
    private String mArrivalTimeToDelivery = null;
    private Boolean mIsCloseOrOpenRestaurants ;

    //judt index 0                                                                                      //num about restaurants
    public PropertyMenu(String NameRestaurants, String description, String ClockRestaurantsOpen, String FeedbackRestaurants, boolean IsCloseOrOpen, String ArrivalTimeToDelivery) {
        this.mNameRestaurants = NameRestaurants;
        this.mDescription = description;
        this.mFeedbackRestaurants = FeedbackRestaurants;
        this.mClockRestaurantsOpen = ClockRestaurantsOpen;
        this.mArrivalTimeToDelivery = ArrivalTimeToDelivery;
        mIsCloseOrOpenRestaurants = IsCloseOrOpen;

    }

    public void setmIsCloseOrOpenRestaurants(Boolean mIsCloseOrOpenRestaurants) {
        this.mIsCloseOrOpenRestaurants = mIsCloseOrOpenRestaurants;
    }

    public boolean getmIsCloseOrOpenRestaurants() {
        return mIsCloseOrOpenRestaurants;
    }


    public String getmNameRestaurants() {
        return mNameRestaurants;
    }

    public String getmArrivalTimeToDelivery() {
        return mArrivalTimeToDelivery;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmClockRestaurantsOpen() {
        return mClockRestaurantsOpen;
    }

    public String getmFeedbackRestaurants() {
        return mFeedbackRestaurants;
    }


    // title of data buffer and organizer


    //// right index
    public PropertyMenu(String Property_Value_title) {
        this.mProperty_Value_title = Property_Value_title;
    }

    public String getmProperty_Value_title() {
        return mProperty_Value_title;
    }


    //***************************************************************************
/// SELECTED ITEM AND DySPLaY TO CONFIG ORDER
    private String name1_itemSelected = null;
    private String name2_infoSelected = null;
    private String name3_priceSelected = null;
    private String name4_popularSelected = null;
    private String name5_imageViewFoodSelect = null;
    private String name6_mBlurHashMenuSelected = null;
    private boolean name7_mIsAvailableItemSelcted ;

    public PropertyMenu(String name1_itemSelected, String name2_infoSelected, String name3_priceSelected, String name4_popularSelected,
                        String name5_imageViewFoodSelect, String name6_mBlurHashMenuSelected, boolean name7_mIsAvailableItemSelcted) {

        this.name1_itemSelected = name1_itemSelected;
        this.name2_infoSelected = name2_infoSelected;
        this.name3_priceSelected = name3_priceSelected;
        this.name4_popularSelected = name4_popularSelected;
        this.name5_imageViewFoodSelect = name5_imageViewFoodSelect;
        this.name6_mBlurHashMenuSelected = name6_mBlurHashMenuSelected;
        this.name7_mIsAvailableItemSelcted = name7_mIsAvailableItemSelcted;
    }

    public String getName6_mBlurHashMenuSelected() {
        return name6_mBlurHashMenuSelected;
    }

    public boolean getName7_mIsAvailableItemSelcted() {
        return name7_mIsAvailableItemSelcted;
    }


    public String getName1_itemSelected() {
        return name1_itemSelected;
    }

    public String getName2_infoSelected() {
        return name2_infoSelected;
    }

    public String getName3_priceSelected() {
        return name3_priceSelected;
    }

    public String getName4_popularSelected() {
        return name4_popularSelected;
    }

    public String getName5_imageViewFoodSelect() {
        return name5_imageViewFoodSelect;
    }
}
