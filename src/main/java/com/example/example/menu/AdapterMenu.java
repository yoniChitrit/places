package com.example.example.menu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
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

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterMenu  extends RecyclerView.Adapter< AdapterMenu.MyViewHolder > {
        private static final String TAG = "AdapterMenu";
        private  Context context;
        private List< PropertyMenu > mData;

        private static Boolean indexView = true;
        private static Boolean indexView2 = true;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        //imge_ticket
        TextView name = null;
        TextView description=null;

        TextView Show_info_button_time_open=null;
        Button info_button_time_open=null;

        TextView Show_Change_time_Delivery=null;
        Button Change_time_Delivery=null;

        //menu_ticket
        TextView menuNameFood=null;
        TextView menuInfoFood=null;
        TextView menuPrice=null;
        ImageView menuImageFood=null;

        TextView menu_kind_of_food=null;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            description = itemView.findViewById(R.id.description);
            Show_info_button_time_open = itemView.findViewById(R.id.time_open);
            info_button_time_open = itemView.findViewById(R.id.menuButtonInfo1);

            Show_Change_time_Delivery = itemView.findViewById(R.id.Show_time_delivery);
            Change_time_Delivery = itemView.findViewById(R.id.menuButtonInfo2);
        }

        public MyViewHolder(@NonNull View itemView, int viewI) {
            super(itemView);
            int viewItem=viewI;
            menuNameFood= itemView.findViewById(R.id.menuNameFood);
            menuImageFood = itemView.findViewById(R.id.menuImageFood);
            menuInfoFood=itemView.findViewById(R.id.menuInfoFood);
            menuPrice=itemView.findViewById(R.id.menuPrice);
        }
        public MyViewHolder(@NonNull View itemView, Boolean viewI){
            super(itemView);
            menu_kind_of_food=itemView.findViewById(R.id.menu_kind_of_food);
        }
    }

    public void loadNewData(List< PropertyMenu > mData) {

        this.mData = mData;// get the list value
        indexView=true;
        indexView2 = true;
        notifyDataSetChanged();
    }

    public AdapterMenu() {
    }

    public AdapterMenu(Context context_, List< PropertyMenu > mData) {
        context = context_;
        this.mData = mData;// get the list value

    }

    @NonNull
    @Override
    public AdapterMenu.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();

        Log.e(TAG, "onCreateViewHolder :" + viewType);
        if (indexView) {//viewType == 0
            //  Log.e(TAG,"onCreateViewHolder ::"+dataRestaurants.getIndex());
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_design_menu, parent, false);

            return new MyViewHolder(v);
        }else {

            if (!indexView && indexView2) {

                View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.kind_of_food_in_menu, parent, false);
                return new MyViewHolder(v,true);


            }else {

                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_design_menu_second, parent, false);
                // MViewHolder vh = new MyViewHolder(v);
                return new MyViewHolder(v, viewType);
            }
        }


    }

  /*  @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }*/

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        PropertyMenu propertyMenu = this.mData.get(position);
       // Log.e(TAG, " onBindViewHolder :" + position + "==" + (getItemCount() - 2));
        ///  if(position==(getItemCount()-2)){
        //  return ;
        // }

        if (indexView) {
            indexView = false;
            //  Log.e(TAG," onBindViewHolder ::"+dataRestaurants.getIndex());
            holder.name.setText(propertyMenu.getName());
            holder.description.setText(propertyMenu.getDescription());
            holder.Show_info_button_time_open.setText(propertyMenu.getClock());
            holder.Show_Change_time_Delivery.setText("@string/delivery_in_min");

        } else {

            if (!indexView&&indexView2) {//&&position!=(getItemCount()-2)
                indexView2=false;
                holder.menu_kind_of_food.setText(propertyMenu.getTextMenu());

            }else {



                Bitmap bitmap= BlurHashDecoder.decode(propertyMenu.getBlurHashMenu(),20,10);
                holder.menuImageFood.setImageBitmap(bitmap);

                holder.menuNameFood.setText(propertyMenu.getNameFoodMenu());//dataRestaurants.getTextMenu()
                holder.menuInfoFood.setText(propertyMenu.getInfoFoodMenu());
                holder.menuPrice.setText(propertyMenu.getPriceFoodMenu());
                Picasso.get().load(propertyMenu.getImageMenu()).error(R.drawable.googleg_standard_color_18).placeholder(holder.menuImageFood.getDrawable()).into(holder.menuImageFood);
            }

        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        Log.e(TAG, " getItemCount():" + this.mData.size());
        return ((this.mData != null && this.mData.size() != 0) ? this.mData.size() : 0);
    }

    public PropertyMenu getmData(int position) {

        return ((this.mData != null && this.mData.size() != 0) ? this.mData.get(position) : null);
    }

    public List< PropertyMenu > getmData() {
        return mData;
    }



}


