package com.example.example.menu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.example.BlurHashDecoder;
import com.example.example.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.example.menu.ManagerListOfAdapterMenue.getmData_Restaurants_LayoutInflater_in_index_0;
import static com.example.example.menu.ManagerListOfAdapterMenue.getmData_all_Items_LayoutInflater_in_index_after_title;
import static com.example.example.menu.ManagerListOfAdapterMenue.getmData_mProperty_Value_LayoutInflater_in_index_title;
import static com.example.example.menu.ManagerListOfAdapterMenue.ismView_user_selected_for_menu;
import static com.example.example.menu.ManagerListOfAdapterMenue.setView_user_selected_for_menu;
import static com.example.example.menu.ManagerListOfAdapterMenue.setmData_Restaurants_LayoutInflater_in_index_0;
import static com.example.example.menu.ManagerListOfAdapterMenue.setmData_all_Items_LayoutInflater_in_index_after_title;
import static com.example.example.menu.ManagerListOfAdapterMenue.setmData_mProperty_Value_LayoutInflater_in_index_title;

public class AdapterMenu extends RecyclerView.Adapter< AdapterMenu.MyViewHolder > {
    private static final String TAG = "AdapterMenu";
    private static MainActivityMenu mainActivityMenu;
    private static ManagerListOfAdapterMenue managerListOfAdapterMenue;
    private Context context;
    private ArrayList< PropertyMenu > mData;
    private static HashMap<Integer, String> myLiatAdapter ;


    //keep the selected of user if click item
    private static int AddOrderCount = 0;
    private static int positionOrderSelected=0;
  // private static  PropertyMenu propertyMenuSwap;
    private static int flugList=0;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        //imge_ticket
        TextView name = null;
        TextView description = null;
        TextView Show_info_button_time_open = null;
        Button info_button_time_open = null;
        TextView Show_Change_time_Delivery = null;
        Button Change_time_Delivery = null;
        TextView feedbackIndex_0_ = null;
        //items
        TextView menuNameFood_ = null;
        TextView menuInfoFood_ = null;
        TextView menuPrice_ = null;
        ImageView menuImageFood_ = null;
        TextView menu_kind_of_food_ = null;
        TextView menuIsItemPopular_ = null;//?
        ConstraintLayout Menue_ToOrder_ = null;
        TextView textUpdateunavailableItem_ = null;//?
        TextView MarqueeItem_=null;//?

        //swap
        TextView itemSelected_, infoSelected_, priceSelected_, popularSelected_=null, countSelected_, statySelected_, toppingSelected_, ChooseItemSelected_;

        ImageView underPriceSelected_, imageViewFoodSelect_;
        Button bCountSelected_, bToppingSelected_, bChooseSelected_, bChengeItemSelected_;

        public MyViewHolder(@NonNull View itemView) {//display index 0 of adapter
            super(itemView);
            name = itemView.findViewById(R.id.name);
            description = itemView.findViewById(R.id.description);
            Show_info_button_time_open = itemView.findViewById(R.id.time_open);
            info_button_time_open = itemView.findViewById(R.id.menuButtonInfo1);
            Show_Change_time_Delivery = itemView.findViewById(R.id.Show_time_delivery);
            Change_time_Delivery = itemView.findViewById(R.id.menuButtonInfo2);
            feedbackIndex_0_ = itemView.findViewById(R.id.feedbackIndex_0);
        }


        /* //menu_ticket
         TextView menuNameFood=null;
         TextView menuInfoFood=null;
         TextView menuPrice=null;
         ImageView menuImageFood=null;
         TextView menu_kind_of_food_=null;
         TextView menuIsItemPopular_=null;
         LinearLayout Menue_ToOrder=null;
         TextView textUpdateunavailableItem_=null;
         public MyViewHolder(@NonNull View itemView, int viewI) {
             super(itemView);

             menuNameFood= itemView.findViewById(R.id.menuNameFood);
             menuImageFood = itemView.findViewById(R.id.menuImageFood);
             menuInfoFood=itemView.findViewById(R.id.menuInfoFood);
             menuPrice=itemView.findViewById(R.id.menuPrice);
             Menue_ToOrder= itemView.findViewById(R.id.MenueToOrder);
             menuIsItemPopular_=itemView.findViewById(R.id.menuIsItemPopular);
         }*/

//ITEM
        public MyViewHolder(@NonNull View itemView, int viewI) {// items
            super(itemView);

            menuNameFood_ = itemView.findViewById(R.id.menuNameFood);
            menuImageFood_ = itemView.findViewById(R.id.menuImageFood);
            menuInfoFood_ = itemView.findViewById(R.id.menuInfoFood);
            menuPrice_ = itemView.findViewById(R.id.menuPrice);
            Menue_ToOrder_ = itemView.findViewById(R.id.MenueToOrder);

            menuIsItemPopular_ = itemView.findViewById(R.id.menuIsItemPopular);//DISPLY IM ITEM
            menuIsItemPopular_.setVisibility(View.INVISIBLE);

            textUpdateunavailableItem_ = itemView.findViewById(R.id.textUpdateunavailableItem);//display in item unavailable
            textUpdateunavailableItem_.setVisibility(View.INVISIBLE);

            MarqueeItem_= itemView.findViewById(R.id.MarqueeItem);
          //  MarqueeItem_.setVisibility(View.INVISIBLE);
        }



        public MyViewHolder(@NonNull View itemView, boolean viewI) {//king food
            super(itemView);
            menu_kind_of_food_ = itemView.findViewById(R.id.menu_kind_of_food);
        }




        public MyViewHolder(@NonNull View itemView, double justHear) {//after select for menu it desplay
            super(itemView);
            imageViewFoodSelect_ = itemView.findViewById(R.id.imageViewFoodSelect);
            underPriceSelected_ = itemView.findViewById(R.id.underPriceSelected);

            bCountSelected_ = itemView.findViewById(R.id.bCountSelected);
            bToppingSelected_ = itemView.findViewById(R.id.bToppingSelected);
            bChooseSelected_ = itemView.findViewById(R.id.bChooseSelected);
            bChengeItemSelected_ = itemView.findViewById(R.id.bChengeItemSelected);

            itemSelected_ = itemView.findViewById(R.id.itemSelected);
            infoSelected_ = itemView.findViewById(R.id.infoSelected);
            priceSelected_ = itemView.findViewById(R.id.priceSelected);
            popularSelected_ = itemView.findViewById(R.id.popularSelected);
            countSelected_ = itemView.findViewById(R.id.countSelected);
            statySelected_ = itemView.findViewById(R.id.statySelected);
            toppingSelected_ = itemView.findViewById(R.id.toppingSelected);
            ChooseItemSelected_ = itemView.findViewById(R.id.ChooseItemSelected);

            popularSelected_.setVisibility(View.INVISIBLE);
        }

    }

    public void loadNewData(ArrayList< PropertyMenu > DataFirst,HashMap<Integer, String> LiatAdapter) {

        this.mData = DataFirst;// get the list value
        myLiatAdapter = new HashMap<Integer, String>();
        myLiatAdapter=LiatAdapter;
        flugList=0;
        AddOrderCount=0;
        positionOrderSelected=0;


        //  stop=(getItemCount()-1);
        notifyDataSetChanged();
        //  Log.e(TAG, "finsh 92");
    }

    public void insertItem(PropertyMenu DataTopping) {
        setmData_Restaurants_LayoutInflater_in_index_0(true);
        mData.add(0, DataTopping);
        notifyItemRangeInserted(0, 1);
    }


    public AdapterMenu(Context context_, ArrayList< PropertyMenu > mData, final MainActivityMenu ainActivityMenu) {
        mainActivityMenu = new MainActivityMenu();
        mainActivityMenu = ainActivityMenu;
        // managerListOfAdapterMenue=new ManagerListOfAdapterMenue();
        context = context_;
        this.mData = mData;// get the list value

    }

    @NonNull
    @Override
    public AdapterMenu.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        Log.e(TAG, "1 ");
        menateh(flugList);

        //get the refernace from mainActivityMenu that change
        // the boolean

        // Log.e(TAG, "onCreateViewHolder after title  :" + getmData_all_Items_LayoutInflater_in_index_after_title());
        if (getmData_Restaurants_LayoutInflater_in_index_0()) {//viewType == 0
            Log.e(TAG, "index 0");
            //   Log.e(TAG, "onCreateViewHolder index after title  :" + getmData_Restaurants_LayoutInflater_in_index_0());
            //  Log.e(TAG,"onCreateViewHolder ::"+dataRestaurants.getIndex());
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_design_menu, parent, false);

            return new MyViewHolder(v);
        }


        if (getmData_mProperty_Value_LayoutInflater_in_index_title()) {
            //   Log.e(TAG, "onCreateViewHolder index after title  :" + getmData_mProperty_Value_LayoutInflater_in_index_title());
            View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.kind_of_food_in_menu, parent, false);
            return new MyViewHolder(v, true);

        }

        if (getmData_all_Items_LayoutInflater_in_index_after_title()) {
            //  Log.e(TAG, "onCreateViewHolder index after title  :" + getmData_all_Items_LayoutInflater_in_index_after_title());
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_activity_menu_for_adapter, parent, false);
            // MViewHolder vh = new MyViewHolder(v);
            return new MyViewHolder(v, 1);

        }

        if (ismView_user_selected_for_menu()) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_selected_user_for_menu, parent, false);
            // MViewHolder vh = new MyViewHolder(v);
            return new MyViewHolder(v, 2.0);
        }
      return null;


    }



    /*  @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        }*/
private void menateh(int position){

    //Log.e(TAG, "become ");
    Log.e(TAG, "become   "+position);
    for (int i : myLiatAdapter.keySet()) {

       if(i==position){
           if(myLiatAdapter.get(i).equals("index_0")){
               setmData_Restaurants_LayoutInflater_in_index_0(true);
               break;
           }
           if(myLiatAdapter.get(i).equals("after_index_0")){
               setmData_all_Items_LayoutInflater_in_index_after_title(true);
         break;
           }
           if(myLiatAdapter.get(i).equals("title_2")){
               setmData_mProperty_Value_LayoutInflater_in_index_title(true);
               break;
           }
         break;

       }

    }


}



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        flugList=position;
        Log.e(TAG, "3 "+position+"=="+flugList);
        flugList++;
        Log.e(TAG, "4 "+position+" == "+flugList);
        PropertyMenu propertyMenu = this.mData.get(position);// it keep all the data,every  index other data
        if (getmData_mProperty_Value_LayoutInflater_in_index_title()) {//&&position!=(getItemCount()-2) buffer btween drink to king food
            Log.e(TAG, " title  -> po  == " + position + " IN " + getmData_mProperty_Value_LayoutInflater_in_index_title());

            holder.menu_kind_of_food_.setText(propertyMenu.getmProperty_Value_title());//propertyMenu.getmProperty_Value_title()
            //  return;
            setmData_mProperty_Value_LayoutInflater_in_index_title(false);
            //  holder.menu_kind_of_food.setText("food");

        }

        if (getmData_Restaurants_LayoutInflater_in_index_0()) {// only one in the list
            Log.e(TAG, "index 0  pos " + position + " in " + getmData_Restaurants_LayoutInflater_in_index_0());
            setmData_Restaurants_LayoutInflater_in_index_0(false);

            holder.name.setText(propertyMenu.getmNameRestaurants());
            holder.description.setText(propertyMenu.getmDescription());
            holder.Show_info_button_time_open.setText(propertyMenu.getmClockRestaurantsOpen());
            holder.Show_Change_time_Delivery.setText(propertyMenu.getmArrivalTimeToDelivery());
            holder.feedbackIndex_0_.setText(String.format("%s  %s", "Excellent ", propertyMenu.getmFeedbackRestaurants()));


            if (!propertyMenu.getmIsCloseOrOpenRestaurants()) {

                mainActivityMenu.getButtonToOrder_For_MainActivityMenu().setBackgroundColor(Color.parseColor("#DF0B090C"));
                mainActivityMenu.getButtonToOrder_For_MainActivityMenu().setVisibility(View.VISIBLE);
                mainActivityMenu.getButtonToOrder_For_MainActivityMenu().setText(String.format("%S    ", "Not Available"));
            }else {
               if(mainActivityMenu.getButtonToOrder_For_MainActivityMenu().getVisibility()==View.VISIBLE){
                   mainActivityMenu.getButtonToOrder_For_MainActivityMenu().setBackgroundColor(Color.parseColor("#E12A1947"));
                   mainActivityMenu.getButtonToOrder_For_MainActivityMenu().setVisibility(View.INVISIBLE);
               }

            }
//return;

        }

        if (getmData_all_Items_LayoutInflater_in_index_after_title()) {// items select
            setmData_all_Items_LayoutInflater_in_index_after_title(false);
            Log.e(TAG, "after title  -> po  == " + position + " IN " + getmData_all_Items_LayoutInflater_in_index_after_title());

            Bitmap bitmap = BlurHashDecoder.decode(propertyMenu.getmBlurHashMenu(), 20, 10);
                  if(bitmap!=null) {
                      holder.menuImageFood_.setImageBitmap(bitmap);
                      Picasso.get().load(propertyMenu.getmImageMenu()).placeholder(holder.menuImageFood_.getDrawable()).into(holder.menuImageFood_);
                  }else {

                          Picasso.get().load(propertyMenu.getmImageMenu()).into(holder.menuImageFood_);

                  }



            holder.menuNameFood_.setText(propertyMenu.getmNameFoodMenu());//dataRestaurants.getTextMenu()
            holder.menuInfoFood_.setText(propertyMenu.getmInfoFoodMenu());
            holder.menuPrice_.setText(propertyMenu.getmPriceFoodMenu());
            holder.MarqueeItem_.setText(propertyMenu.getmMarqueeItem_Menu());// for this get just null and if user selected the item will marked


            if (propertyMenu.getmIsFoodPopularMenu()!=null) {
                holder.menuIsItemPopular_.setVisibility(View.VISIBLE);
                holder.menuIsItemPopular_.setText(propertyMenu.getmIsFoodPopularMenu());
            }

          /*  if(holder.popularSelected_.getVisibility()==View.VISIBLE){
                holder.textUpdateunavailableItem_.setText(" ");
                holder.popularSelected_.setVisibility(View.GONE);
            }*/
            if(!propertyMenu.ismIsAvailableItem()){
                holder.textUpdateunavailableItem_.setVisibility(View.VISIBLE);
                holder.textUpdateunavailableItem_.setText(String.format("%S    ", "UnAvailable"));
            }



            View.OnClickListener swapItem = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e(TAG, " 243 click swap position" + position);
                    switch (v.getId()) {
                        case R.id.MenueToOrder:

                            if (position > 0 && position < getItemCount()&&positionOrderSelected==0) {
                                Log.e(TAG, " 243 click swap");

                             //   PropertyMenu propertyMenuSwap = getmData(position);// send the data abut this item
                                PropertyMenu  propertyMenuSwap = getmData(position);// send the data abut this item
                                Log.e(TAG, " 243 click swap" + propertyMenuSwap);

                                positionOrderSelected = position;
                                Log.e(TAG, "  swap before" + positionOrderSelected + "==" + position);
                                mainActivityMenu.positionOnSwap(propertyMenuSwap, position);

                                try {
                                    Thread.sleep(200);
                                    mainActivityMenu.removeSwap((position+1));
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }



                                //  mainActivityMenu.positionOnSwap(position);
                            }else {
                                Log.e(TAG, " 379 line , positionOrderSelected = "+ positionOrderSelected+"position= "+ position);

                            }
                            holder.Menue_ToOrder_.clearFocus();
                            Log.e(TAG, " 283 clearFocus()");


                            break;

                        default:
                            Log.d(TAG, "mnmnm");

                    }
                }
            };

            holder.Menue_ToOrder_.setOnClickListener(swapItem);

           // if (position == stop) {
                // setmData_all_Items_LayoutInflater_in_index_after_title(false);
          //  }

        }


        if (ismView_user_selected_for_menu()) {// if swap cliced some item
            setView_user_selected_for_menu(false);
            if(holder.popularSelected_.getVisibility()==View.VISIBLE) {
                holder.popularSelected_.setVisibility(View.INVISIBLE);
            }

            Bitmap bitmap = BlurHashDecoder.decode(propertyMenu.getName6_mBlurHashMenuSelected(), 20, 10);
            if (bitmap != null) {
                holder.imageViewFoodSelect_.setImageBitmap(bitmap);
                Picasso.get().load(propertyMenu.getName5_imageViewFoodSelect()).placeholder(holder.imageViewFoodSelect_.getDrawable()).into(holder.imageViewFoodSelect_);
            }else {
                Picasso.get().load(propertyMenu.getName5_imageViewFoodSelect()).into(holder.imageViewFoodSelect_);
               // Picasso.get().load(propertyMenu.getName5_imageViewFoodSelect()).error(R.drawable.googleg_standard_color_18).placeholder(holder.imageViewFoodSelect_.get).into(holder.imageViewFoodSelect_);
            }

            holder.underPriceSelected_.setImageResource(R.drawable.ic_baseline_delete_24_item);  //underPriceSelected_
            holder.itemSelected_.setText(propertyMenu.getName1_itemSelected());
            holder.infoSelected_.setText(propertyMenu.getName2_infoSelected());
            holder.priceSelected_.setText(propertyMenu.getName3_priceSelected());

            holder.bCountSelected_.setText(String.format("%s    ","Add to order"));
            holder.countSelected_.setText(String.format("%s   ","Count: "));
            if (propertyMenu.getName4_popularSelected()!=null) {// if not popular or drink
                holder.popularSelected_.setVisibility(View.VISIBLE);
                holder.popularSelected_.setText(propertyMenu.getName4_popularSelected());
            }
            if (!propertyMenu.getName7_mIsAvailableItemSelcted()) {// if item unavailabl
                if(mainActivityMenu.getButtonToOrder_For_MainActivityMenu().getVisibility()!=View.VISIBLE) {
                    mainActivityMenu.getButtonToOrder_For_MainActivityMenu().setBackgroundColor(Color.parseColor("#DF0B090C"));
                    mainActivityMenu.getButtonToOrder_For_MainActivityMenu().setText(String.format("%S    ", "Not Available"));
                    mainActivityMenu.getButtonToOrder_For_MainActivityMenu().setVisibility(View.VISIBLE);
                }

            }



            // AddOrderCount
            //   setView_user_selected_for_menu(false);
            View.OnClickListener ButtonListen = new View.OnClickListener() {
                @Override
                public void onClick(@NonNull View v) {
                    switch (v.getId()) {

                        case R.id.bCountSelected:


                            AddOrderCount++;

                            //   PropertyMenu keepRefernceToSelectedUser= getmData((positionOrderSelected+1));
                        //    Log.e(TAG,  " ==" + propertyMenu.getName7_mIsAvailableItemSelcted());//**FALSE NOT Available ,TRUE Available
                           // if (propertyMenu.getName7_mIsAvailableItemSelcted() && AddOrderCount > 0) {// boolean
                                holder.countSelected_.setText(String.format("%s  %s", "Count: ", AddOrderCount));
                                int price = Integer.parseInt(propertyMenu.getName3_priceSelected());
                                mainActivityMenu.getButtonToOrder_For_MainActivityMenu().setText(String.format("%s  %s %s", "Go to checkout  ", String.valueOf(price * AddOrderCount), "$"));
                                holder.bCountSelected_.setText(String.format("%S    "," - | + "));

                        //    }
                            if ( mainActivityMenu.getButtonToOrder_For_MainActivityMenu().getVisibility()!=View.VISIBLE&& AddOrderCount > 0) {
                                // JUST ONE TIME and check if line 201 become it
                                // to visible become restaurant closed
                                mainActivityMenu.getButtonToOrder_For_MainActivityMenu().setVisibility(View.VISIBLE);

                            }

                            holder.bCountSelected_.clearFocus();

                            break;

                        case R.id.imageViewFoodSelect:// IF REMOVE RESET

                            if(holder.popularSelected_.getVisibility()==View.VISIBLE) {
                                holder.popularSelected_.setVisibility(View.INVISIBLE);
                            }

                            Log.e(TAG, " 331 swap after positionOrderSelected = " + positionOrderSelected + "  position =  " + position);
                            if (position == positionOrderSelected) {//if equals what i cliced before than remove
                                int ColorListener=  ((ColorDrawable) mainActivityMenu.getButtonToOrder_For_MainActivityMenu().getBackground()).getColor();
                                if(ColorListener!=-517334713){
                                    Log.e(TAG, "Cant Order");
                                }else {
                                    if(ColorListener==-517334713) {
                                          mainActivityMenu.ShowToUserWhatSelected(positionOrderSelected, AddOrderCount);// up data and Display to user the item selected ->like x1+




                                        Log.e(TAG, "You Can Order");
                                    }

                                }
                                AddOrderCount = 0;
                                positionOrderSelected = 0;


                              //  restAllValue(holder);

                           //    mainActivityMenu.removeSwap(position);



                              /*  try {
                                    Thread.sleep(200);
                                    mainActivityMenu.removeSwap(position);
                                    mainActivityMenu.createListFromReferenceFirstActivity();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }*/


                            }
                            holder.imageViewFoodSelect_.clearFocus();
                            break;
                        default:
                            Log.d(TAG, "mnmnm");


                    }

                    // setView_user_selected_for_menu(false);
                }
            };
            holder.bCountSelected_.setOnClickListener(ButtonListen);
            holder.imageViewFoodSelect_.setOnClickListener(ButtonListen);


        }


        //  holder.menu_kind_of_food.setText("HELLO");

       // Log.e(TAG, "finsh 225");

        mainActivityMenu.getButtonToOrder_For_MainActivityMenu().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               int ColorListener=  ((ColorDrawable) mainActivityMenu.getButtonToOrder_For_MainActivityMenu().getBackground()).getColor();
                Log.e(TAG, "LINE   513  "+ColorListener);
                if(ColorListener!=-517334713){
                    Log.e(TAG, "Cant Order");//Color.parseColor("#DF0B090C")
                }else {
                    if(ColorListener==-517334713) {
                      //  mainActivityMenu.ShowToUserWhatSelected(positionOrderSelected, AddOrderCount);// up data and Display to user the item selected ->like x1+
                        Log.e(TAG, "You Can Order");
                    }
                }
            }
        });






    }
    void   restAllValue(MyViewHolder holder){
        AddOrderCount = 0;
        positionOrderSelected = 0;

        //  ButtonToOrder_For_MainActivityMenu.setText("");


     /*   if(holder.textUpdateunavailableItem_.getVisibility()==View.VISIBLE){

          // holder.textUpdateunavailableItem_.setVisibility(View.INVISIBLE);
        }
        if(holder.menuIsItemPopular_.getVisibility()==View.VISIBLE){
           // holder.menuIsItemPopular_.setVisibility(View.INVISIBLE);
        }*/


    }

    @Override
    public int getItemViewType(int position) {///call here many time

        return super.getItemViewType(position);

    }

    @Override
    public int getItemCount() {

        //  Log.e(TAG, " getItemCount():" + this.mData.size());
        return ((this.mData != null && this.mData.size() != 0) ? this.mData.size() : 0);
    }

    public PropertyMenu getmData(int position) {

        return ((this.mData != null && this.mData.size() != 0) ? this.mData.get(position) : null);
    }

    public List< PropertyMenu > getmData() {
        return mData;
    }


}


