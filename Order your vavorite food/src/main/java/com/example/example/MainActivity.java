package com.example.example;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity implements FirstFragment.myAdapterData {// implements GetFlickrJsonData.OnDataAvailable {
    private static final String TAG = "MainActivity";

    private FragmentManager manager = getSupportFragmentManager();
    private Fragment mDelivery = new FirstFragment(this);
    private Fragment mSearch = new Search();
    private Fragment mProfile = new Profile();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e(TAG,"mDelivery");
        manager.beginTransaction().add(R.id.main_Layout, mDelivery, "delivery").commitAllowingStateLoss();
        Log.e(TAG,"search");
        manager.beginTransaction().add(R.id.main_Layout, mSearch, "search").hide(mSearch).commitAllowingStateLoss();
        Log.e(TAG,"profile");
        manager.beginTransaction().add(R.id.main_Layout, mProfile, "profile").hide(mProfile).commitAllowingStateLoss();


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //   int id = item.getItemId();
                switch (item.getItemId()) {
                    case R.id.Delivery:
                        manager.beginTransaction().hide(mSearch).hide(mProfile).show(mDelivery).commitAllowingStateLoss();
                        break;
                    case R.id.Search:

                        manager.beginTransaction().hide(mDelivery).hide(mProfile).show(mSearch).commitAllowingStateLoss();
                        break;
                    case R.id.Profile:
                        manager.beginTransaction().hide(mDelivery).hide(mSearch).show(mProfile).commitAllowingStateLoss();
                        break;

                }
                return true;
            }
        });

    }
    @Override
    public void resultAdapterList(LatLng latLng, String ResultJson) {
      Search  mSearchData = new Search();
       // List< DataRestaurants >  mData=new ArrayList<>(dataListFirstFragment);

         if(!ResultJson.equals("")){
             mSearchData.updataListFromMainActivity(latLng,ResultJson);
             Log.e(TAG, "resultAdapterList :" );
         }else {
             Log.e(TAG, "resultAdapterList filed move dataList to Fragment Search :" );
         }



    }
  /*  @Override
    protected void onResume() {
        Log.d(TAG, "onResume starts");
        super.onResume();
        GetFlickrJsonData getFlickrJsonData = new GetFlickrJsonData(this, "https://api.flickr.com/services/feeds/photos_public.gne", "en-us", true);
        //getFlickrJsonData.executeOnSameThread("android, nougat");
        getFlickrJsonData.execute("android","nougat");
        Log.d(TAG, "onResume ends");
    }*/
  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }*/

 /*   @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/


}