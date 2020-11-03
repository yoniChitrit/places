package com.example.example.menu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.example.DataRestaurants;
import com.example.example.R;
import com.example.example.RecyclerItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivityMenu extends AppCompatActivity implements RecyclerItemClickListener.OnRecylerClickListener{
private final static String TAG="MainActivityMenu";
   // private AdapterMenu mAdapterMenu;//class
    private Context context ;
    Toolbar toolbar;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        context=getBaseContext();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) {
            toolbar = (Toolbar) findViewById(R.id.toolbar);

            if (toolbar != null) {
                setSupportActionBar(toolbar);
                actionBar = getSupportActionBar();
              ///  toolbar.setTitleTextColor(getResources().getColor( R.color.Black));
              //  toolbar.setSubtitleTextColor(getResources().getColor( R.color.Black));

            }
        }
        if (actionBar != null) {
            //actionBar.setTitle("hii");
            Objects.requireNonNull(actionBar).setDisplayHomeAsUpEnabled(true);
        }


      //  ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, , toolbar, R.string.app_name, R.string.app_name);
    //    actionBarDrawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.Black));
      //  actionBarDrawerToggle.syncState();


        // bring data from firstFragment
        Intent intent=  getIntent();
        DataRestaurants dataRestaurants=(DataRestaurants)intent.getSerializableExtra("DATA_TRANSFER");
        ImageView ImgRestaurants=(ImageView)findViewById(R.id.menuImageFoodTop);//image top menu
        if(Objects.requireNonNull(dataRestaurants).getmImage()!=null&&actionBar != null){
           // String imgUrl=dataRestaurants.getmImage();
            actionBar.setTitle(dataRestaurants.getmName());

            Log.e(TAG,"onCreat"+dataRestaurants.getmImage());
            Picasso.get().load(dataRestaurants.getmImage()).error(R.drawable.common_full_open_on_phone).placeholder(R.drawable.common_full_open_on_phone).into(ImgRestaurants);//holder.circleimageView




        }

        RecyclerView recyclerView = findViewById(R.id.aRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        PropertyMenu con = new PropertyMenu(context);
        // recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(context,recyclerView,this));//event handler

        AdapterMenu mAdapterMenu = new AdapterMenu(context, new ArrayList< PropertyMenu>());
        recyclerView.setAdapter(mAdapterMenu);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(context, recyclerView, MainActivityMenu.this));
        List< PropertyMenu > mData = new ArrayList< PropertyMenu >();
        Log.e(TAG,"onCreate after"+dataRestaurants.getmImage());

        PropertyMenu info_restaurants = new PropertyMenu(dataRestaurants.getmName(), dataRestaurants.getmDescription(), "Close - Open 11:00");
        mData.add(info_restaurants);

        PropertyMenu menu_data_food = new PropertyMenu("Beef");
        mData.add(menu_data_food);

        for (int i = 0; i < 10; i++) {
            PropertyMenu menu_data = new PropertyMenu(dataRestaurants.getmImage(),"nameFood","infoFood","7 $","UZP$~|R*o~tRyEM{V[f7?wRjVsV@IAozfhae");
            mData.add(menu_data);
        }


        mAdapterMenu.loadNewData(mData);//mAdapter reference to class and holder the data
        recyclerView.setAdapter(mAdapterMenu);






    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart() :");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop() :");
    }

    @Override
    public void OnItemClick(View view, int position) {
        Toast.makeText(this, "OnItemClick"+position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnItemLongClick(View view, int position) {

    }


}