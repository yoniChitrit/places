package com.example.example.menu;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.example.PropertyFirstFragment;
import com.example.example.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.example.FirstFragment.DATA_TRANSFER_MENU;
import static com.example.example.Search.DATA_TRANSFER_SEARCH;
import static com.example.example.menu.ManagerListOfAdapterMenue.getmData_Restaurants_LayoutInflater_in_index_0;
import static com.example.example.menu.ManagerListOfAdapterMenue.getmData_all_Items_LayoutInflater_in_index_after_title;
import static com.example.example.menu.ManagerListOfAdapterMenue.getmData_mProperty_Value_LayoutInflater_in_index_title;
import static com.example.example.menu.ManagerListOfAdapterMenue.setView_user_selected_for_menu;
import static com.example.example.menu.ManagerListOfAdapterMenue.setmData_PAY_ATTENTION_LayoutInflater_in_index_2;
import static com.example.example.menu.ManagerListOfAdapterMenue.setmData_mMy_Recent_Order_LayoutInflater_in_index_1;

public class MainActivityMenu extends AppCompatActivity {
    private final static String TAG = "MainActivityMenu";
    private final static String OpenRestaurant = "Close 12 pm - Open 11:00";
    private final static String NameRestaurantForInputRightData = "Restauran";
    private final static int NumItemsTotalToRestaurant = 20;

    private Context context;
    // private RecyclerViewReadyCallback recyclerViewReadyCallback;

    /// private static ManagerListOfAdapterMenue managerListOfAdapterMenue;
    //private static PropertyMenu propertyMenu;
    private static boolean isAdded = true;
    private static int IndexRemoveSwap = 0;
  private static int i_=0;
  private static HashMap<Integer, String> myLiatV ;



    private ImageView ImgRestaurants;
    private ActionBar actionBar;
    private RecyclerView recyclerView;
    public AdapterMenu mAdapterMenu;
    private ArrayList< PropertyMenu > mDataMenu;
    private  PropertyFirstFragment mDataFirsFragment;

  private  Button ButtonToOrder_For_MainActivityMenu;

  private HashMap<Integer, Integer> ShowToUserWhatSelected_;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        ButtonToOrder_For_MainActivityMenu = (Button) findViewById(R.id.ButtonToOrder);
        ButtonToOrder_For_MainActivityMenu.setVisibility(View.INVISIBLE);//WHEN CLICK TO RIGHT TO ORDER IT BECOME TO VISIBLE
        context = getApplicationContext();
        actionBar = getSupportActionBar();
        ImgRestaurants = (ImageView) findViewById(R.id.menuImageFoodTop);
        if (actionBar == null) {
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

            if (toolbar != null) {
                setSupportActionBar(toolbar);
                actionBar = getSupportActionBar();

            }
        }
        if (actionBar != null) {
            //actionBar.setTitle("hii");
            Objects.requireNonNull(actionBar).setDisplayHomeAsUpEnabled(true);
        }

        //   Log.e(TAG,"64");
// get data for first fragment
        Intent intent = getIntent();
        PropertyFirstFragment dataFirstFragment_Search = (PropertyFirstFragment) intent.getSerializableExtra(DATA_TRANSFER_SEARCH);
        PropertyFirstFragment dataFirstFragment = (PropertyFirstFragment) intent.getSerializableExtra(DATA_TRANSFER_MENU);
        if (dataFirstFragment != null) {
            //   Log.e(TAG,"71");
          boolean  isEmpty=  createListMenu();//create ref not chenge loction
           if(isEmpty) {
               buildRecyclerView(dataFirstFragment);
           }
        } else {
            if(dataFirstFragment_Search!=null) {
                Log.e(TAG, "DATA_TRANSFER_SEARC" + dataFirstFragment_Search.getmNameRestaurnt());
                boolean  isEmpty=  createListMenu();//create ref not chenge loction
                if(isEmpty) {
                    buildRecyclerView(dataFirstFragment_Search);
                }
            }

        }



    }


    public  void setButtonToOrder_For_MainActivityMenu(Button buttonToOrder_For_MainActivityMenu) {
        ButtonToOrder_For_MainActivityMenu = buttonToOrder_For_MainActivityMenu;
    }

    public  Button getButtonToOrder_For_MainActivityMenu() {
        return ButtonToOrder_For_MainActivityMenu;
    }
    @Override
    protected void onStart() {
        super.onStart();


    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() :");
    }

    private void buildRecyclerView(PropertyFirstFragment dataFirsFragment) {

            mDataFirsFragment = dataFirsFragment;

        // ImageView ImgRestaurants=(ImageView)findViewById(R.id.menuImageFoodTop);//image top menu
        if (Objects.requireNonNull(dataFirsFragment).getmImageRestaurnt() != null && actionBar != null) {
            // String imgUrl=dataRestaurants.getmImage();
            actionBar.setTitle(mDataFirsFragment.getmNameRestaurnt());
            //   Log.e(TAG,"onCreat"+dataRestaurants.getmImage());
            Picasso.get().load(mDataFirsFragment.getmImageRestaurnt()).error(R.drawable.common_full_open_on_phone).placeholder(R.drawable.common_full_open_on_phone).into(ImgRestaurants);//holder.circleimageView

            createListFromReferenceFirstActivity();
        }

      //  manegaeLayout_In_after_title();// full the items that get ref json
     //   Log.e(TAG, "after 120" + recyclerView);


       // recyclerView.setAdapter(mAdapterMenu);


     /*  recyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                recyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                Log.e(TAG," finish ");
               // setmData_all_Items_LayoutInflater_in_index_after_title(false);
               // setmData_Restaurants_LayoutInflater_in_index_0(false);
               // setmData_mProperty_Value_LayoutInflater_in_index_title(false);


            }
        });*/


    }




    private boolean createListMenu() {
        //  Log.e(TAG,"createListMenu() 175 line");
        recyclerView = findViewById(R.id.aRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapterMenu = new AdapterMenu(this, new ArrayList<>(), this);
        recyclerView.setAdapter(mAdapterMenu);
        mDataMenu = new ArrayList<>();
        myLiatV = new HashMap<>();
        ShowToUserWhatSelected_=new HashMap<>();//keep selects of user or some change that made
        // managerListOfAdapterMenue = new ManagerListOfAdapterMenue();
        //mDataFirsFragment = new PropertyFirstFragment();

        return true;

    }







    public void payAttention() {//if  myRecentOrder exist than put it 2 index else 1 index== boolean
        setmData_PAY_ATTENTION_LayoutInflater_in_index_2(true);
        //  PropertyMenu info_titel = new PropertyMenu("Title Food");
        //  mDataMenu.add(1, info_titel);
        mAdapterMenu.notifyItemRangeInserted(2, 1);

    }

    public void myRecentOrder() {
        setmData_mMy_Recent_Order_LayoutInflater_in_index_1(true);
        //  mDataMenu.add(1, info_titel);
        mAdapterMenu.notifyItemRangeInserted(1, 1);
    }

    //  positionOnSwap
    public void positionOnSwap(PropertyMenu propertyMenuSwap, int swap) {// put this data in the world end selected
        Log.e(TAG, " swap 262  " + swap);


        if (swap > 0) {//every time swap one time, check if  swap already

            Log.e(TAG, " swap 264 Available Item ? ->"+propertyMenuSwap.ismIsAvailableItem());

            // keep the data of item clicked for send order
            PropertyMenu propertyMenuSelect = new PropertyMenu(propertyMenuSwap.getmNameFoodMenu()
                    , propertyMenuSwap.getmInfoFoodMenu(), propertyMenuSwap.getmPriceFoodMenu(), propertyMenuSwap.getmIsFoodPopularMenu(),
                    propertyMenuSwap.getmImageMenu(), propertyMenuSwap.getmBlurHashMenu(), propertyMenuSwap.ismIsAvailableItem());
            //   public PropertyMenu( String name1_itemSelected, String name2_infoSelected, String name3_priceSelected, String name4_popularSelected,
            //     String name5_imageViewFoodSelect, String name6_mBlurHashMenuSelected, Boolean name7_mIsAvailableItemSelcted) {
            setView_user_selected_for_menu(true);
            mDataMenu.add(swap, propertyMenuSelect);
            mAdapterMenu.notifyItemRangeInserted(swap, 1);//POSITION SELRCTED
        } else {
            Log.e(TAG, "not swap in this index 274 line, or swap open already");
        }
    }

    public void removeSwap(int remove) {

      //  ButtonToOrder_For_MainActivityMenu.setBackgroundColor(getResources().getColor(R.color.Purple));
        if(ButtonToOrder_For_MainActivityMenu.getVisibility()== View.VISIBLE){
            ButtonToOrder_For_MainActivityMenu.setVisibility(View.GONE);
        }
        mDataMenu.remove(remove);

        mAdapterMenu.notifyItemRangeRemoved(remove,1);
      /*  recyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                Log.e(TAG, "onGlobalLayout()");

                recyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }

        });*/


    }

public void ShowToUserWhatSelected(int IndexOrder,int amount){//if get the value from AdapterMenu fresh the list ans marked the item selected

    removeSwap(IndexOrder);//
    createListFromReferenceFirstActivity();//refresh but keep the selects
    if(amount>0) {
        ShowToUserWhatSelected_.put(IndexOrder, amount);
    }
        try {
            Thread.sleep(200);

            // Log.e(TAG,"IndexOrder= "+IndexOrder+" Amoust= "+amount);
            //  PropertyMenu propertyMenu = mAdapterMenu.getmData(IndexOrder);//bring thr index of list and up data
            //   propertyMenu.setmMarqueeItem_Menu(String.format("%s %s ", "x", amount));

            for (int i : ShowToUserWhatSelected_.keySet()) {
                PropertyMenu propertyMenu = mAdapterMenu.getmData(i);//bring thr index of list and up data
                propertyMenu.setmMarqueeItem_Menu(String.format("%s %s ", "x", ShowToUserWhatSelected_.get(i)));
                Log.e(TAG, "IndexOrder= " + i + " Amoust= " + ShowToUserWhatSelected_.get(i) + "    -----------------------------------\n");

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    getButtonToOrder_For_MainActivityMenu().setBackgroundColor(Color.parseColor("#E12A1947"));
    if( getButtonToOrder_For_MainActivityMenu().getVisibility()==View.VISIBLE){

        getButtonToOrder_For_MainActivityMenu().setVisibility(View.INVISIBLE);
    }



 //MarqueeItem_
}


    public void createListFromReferenceFirstActivity(){
        getButtonToOrder_For_MainActivityMenu().setBackgroundColor(Color.parseColor("#E12A1947"));
        if( getButtonToOrder_For_MainActivityMenu().getVisibility()==View.VISIBLE){

            getButtonToOrder_For_MainActivityMenu().setVisibility(View.INVISIBLE);
        }

        mDataMenu.clear();

        for(  i_=0;i_< NumItemsTotalToRestaurant;i_++){

           if(i_==0) {

             // setmData_Restaurants_LayoutInflater_in_index_0(true);
              myLiatV.put(i_,"index_0");

               Log.e(TAG,"333 -> I == "+i_+" IN "+ getmData_Restaurants_LayoutInflater_in_index_0());
               // if(timeO) time clock is open than true after false and is affect on the button order //data coming from json
               PropertyMenu info_restaurants = new PropertyMenu(mDataFirsFragment.getmNameRestaurnt(), mDataFirsFragment.getmDistance(),
                       "Close 12 pm - Open 11:00", mDataFirsFragment.getmFeedbackRestaurants(), mDataFirsFragment.ismIsOpenOrClose(), mDataFirsFragment.getmLocationTime());
               //  PropertyMenu( NameRestaurants,  description,  ClockRestaurantsOpen , FeedbackRestaurants,boolean IsCloseOrOpen, ArrivalTimeToDelivery)
              mDataMenu.add(info_restaurants);


             //  mAdapterMenu.loadNewData(mDataMenu);
             //  recyclerView.setAdapter(mAdapterMenu);
              // WithingToConfig();

           }else {

               for (;i_<NumItemsTotalToRestaurant;i_++)
                 //  setmData_all_Items_LayoutInflater_in_index_after_title(true);
               if(i_>0){//NumItemsTotalToRestaurant

                 //  Log.e(TAG, "before" + recyclerView.getAdapter());


                       //json
                       if (i_ != 8 && i_!=11) {//10 difrent not 10 difrent for 15 10 yes

                           if(i_==6){// item not Available

                               Log.e(TAG," -> I == "+i_ +" IN "+ getmData_all_Items_LayoutInflater_in_index_after_title());

                               PropertyMenu menu_data = new PropertyMenu(mDataFirsFragment.getmImageRestaurnt(), "nameFoodType"
                                       , "thisInfoFood", "7", mDataFirsFragment.getmBlurhash(), false, "popular","");

                               mDataMenu.add(menu_data);
                               myLiatV.put(i_,"after_index_0");
                             //  mAdapterMenu.loadNewData(mDataMenu);
                             //  recyclerView.setAdapter(mAdapterMenu);
                            //   WithingToConfig();
                               continue;
                           }

                           if(i_>15){///add to list drink

                               Log.e(TAG," -> I == "+i_ +" IN "+ getmData_all_Items_LayoutInflater_in_index_after_title());
                               PropertyMenu menu_data = new PropertyMenu(mDataFirsFragment.getmImageRestaurnt(), "drink"
                                       , "thisInfoDrink", "2", mDataFirsFragment.getmBlurhash(), true, null,"");

                               mDataMenu.add(menu_data);
                               myLiatV.put(i_,"after_index_0");
                             //  mAdapterMenu.loadNewData(mDataMenu);
                             //  recyclerView.setAdapter(mAdapterMenu);
                             //  WithingToConfig();
                               continue;
                           }
                           if(i_==2||i_==4){///popular food

                               Log.e(TAG," -> I == "+i_ +" IN "+ getmData_all_Items_LayoutInflater_in_index_after_title());
                               PropertyMenu menu_data = new PropertyMenu(mDataFirsFragment.getmImageRestaurnt(), "nameFoodType"
                                       , "thisInfoFood", "7", mDataFirsFragment.getmBlurhash(), true, "popular","");

                               mDataMenu.add(menu_data);
                               myLiatV.put(i_,"after_index_0");
                              // mAdapterMenu.loadNewData(mDataMenu);
                             //  recyclerView.setAdapter(mAdapterMenu);
                             //  WithingToConfig();
                               continue;

                           }
                          // setmData_all_Items_LayoutInflater_in_index_after_title(true);
                           Log.e(TAG," -> I == "+i_ +" IN "+ getmData_all_Items_LayoutInflater_in_index_after_title());
                           PropertyMenu menu_data = new PropertyMenu(mDataFirsFragment.getmImageRestaurnt(), "nameFoodType"
                                   , "thisInfoFood", "7", mDataFirsFragment.getmBlurhash(), true, null,"");

                           mDataMenu.add(menu_data);
                           myLiatV.put(i_,"after_index_0");
                         //  mAdapterMenu.loadNewData(mDataMenu);
                        //  recyclerView.setAdapter(mAdapterMenu);
                        //   WithingToConfig();

                       } else {

                         //    setmData_mProperty_Value_LayoutInflater_in_index_title(true);
                             myLiatV.put(i_,"title_2");
                             Log.e(TAG, "387 title  -> I == " + i_ + " IN " + getmData_mProperty_Value_LayoutInflater_in_index_title());
                             PropertyMenu info_titel = new PropertyMenu("Title Food");

                            mDataMenu.add(info_titel);

                           //  mAdapterMenu.loadNewData(mDataMenu);
                          //   recyclerView.setAdapter(mAdapterMenu);
                           //  WithingToConfig();





                       }

                   }


               }

           }

      //  setmData_Restaurants_LayoutInflater_in_index_0(true);
        mAdapterMenu.loadNewData(mDataMenu,myLiatV);//mAdapter reference to class and holder the data
        recyclerView.setAdapter(mAdapterMenu);

        }



    }







