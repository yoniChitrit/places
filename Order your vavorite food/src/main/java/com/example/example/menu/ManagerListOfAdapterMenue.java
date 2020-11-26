package com.example.example.menu;

class   ManagerListOfAdapterMenue {
    //it organizer the LayoutInflater  onCreateViewHolder in class AdapterMenu

    ///   organizer the RIGHT INDEX LIST
    private static boolean mData_Restaurants_LayoutInflater_in_index_0=false;
    private static boolean mData_mMy_Recent_Order_LayoutInflater_in_index_1=false;
    private static boolean mData_PAY_ATTENTION_LayoutInflater_in_index_2=false;
    private static boolean mData_mProperty_Value_LayoutInflater_in_index_title=false;
    private static boolean mData_all_Items_LayoutInflater_in_index_after_title=false;
    private static boolean mView_user_selected_for_menu=false;
///public ManagerListOfAdapterMenue(){}

 /*   public ManagerListOfAdapterMenue(Boolean mData_Restaurants_LayoutInflater_in_index_0, Boolean mData_mMy_Recent_Order_LayoutInflater_in_index_1, Boolean mData_PAY_ATTENTION_LayoutInflater_in_index_2, Boolean mData_mProperty_Value_LayoutInflater_in_index_title, Boolean mData_all_Items_LayoutInflater_in_index_after_title) {
        this.mData_Restaurants_LayoutInflater_in_index_0 = mData_Restaurants_LayoutInflater_in_index_0;
        this.mData_mMy_Recent_Order_LayoutInflater_in_index_1 = mData_mMy_Recent_Order_LayoutInflater_in_index_1;
        this.mData_PAY_ATTENTION_LayoutInflater_in_index_2 = mData_PAY_ATTENTION_LayoutInflater_in_index_2;
        this.mData_mProperty_Value_LayoutInflater_in_index_title = mData_mProperty_Value_LayoutInflater_in_index_title;
        this.mData_all_Items_LayoutInflater_in_index_after_title = mData_all_Items_LayoutInflater_in_index_after_title;
    }*/

    static void setmData_Restaurants_LayoutInflater_in_index_0(boolean Data_Restaurants_LayoutInflater_in_index_0) {
       mData_Restaurants_LayoutInflater_in_index_0 = Data_Restaurants_LayoutInflater_in_index_0;
    }

    static void setmData_mMy_Recent_Order_LayoutInflater_in_index_1(Boolean Data_mMy_Recent_Order_LayoutInflater_in_index_1) {
        mData_mMy_Recent_Order_LayoutInflater_in_index_1 = Data_mMy_Recent_Order_LayoutInflater_in_index_1;
    }

     static void setmData_PAY_ATTENTION_LayoutInflater_in_index_2(Boolean Data_PAY_ATTENTION_LayoutInflater_in_index_2) {
      mData_PAY_ATTENTION_LayoutInflater_in_index_2 = Data_PAY_ATTENTION_LayoutInflater_in_index_2;
    }

    static void setmData_mProperty_Value_LayoutInflater_in_index_title(Boolean Data_mProperty_Value_LayoutInflater_in_index_title) {
        mData_mProperty_Value_LayoutInflater_in_index_title = Data_mProperty_Value_LayoutInflater_in_index_title;
    }

    static void setmData_all_Items_LayoutInflater_in_index_after_title(Boolean Data_all_Items_LayoutInflater_in_index_after_title) {
       mData_all_Items_LayoutInflater_in_index_after_title = Data_all_Items_LayoutInflater_in_index_after_title;
    }

     static void setView_user_selected_for_menu(boolean view_user_selected_for_menu) {
       mView_user_selected_for_menu = view_user_selected_for_menu;
    }
    /////////


    static boolean getmData_Restaurants_LayoutInflater_in_index_0() {
        return mData_Restaurants_LayoutInflater_in_index_0;
    }

     static boolean getmData_mMy_Recent_Order_LayoutInflater_in_index_1() {
        return mData_mMy_Recent_Order_LayoutInflater_in_index_1;
    }

     static boolean getmData_PAY_ATTENTION_LayoutInflater_in_index_2() {
        return mData_PAY_ATTENTION_LayoutInflater_in_index_2;
    }

     static boolean getmData_mProperty_Value_LayoutInflater_in_index_title() {
        return mData_mProperty_Value_LayoutInflater_in_index_title;
    }

    static boolean getmData_all_Items_LayoutInflater_in_index_after_title() {
        return mData_all_Items_LayoutInflater_in_index_after_title;
    }

    public static boolean ismView_user_selected_for_menu() {
        return mView_user_selected_for_menu;
    }
}
