package com.example.example;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Search extends Fragment {


    private static final String TAG = "Search";
    private static final LatLng PRIME_MERIDIAN = new LatLng(32.0722903, 34.7856782);//start with this
    private TextView getLocation;
    private Button Click;
    private LatLng initialLocation;
    private   LatLng currentLocation = new LatLng(0, 0);
    private Context context;
    String mame="yoni";

    private    MyAdapter  mAdapter;
    private RecyclerView RecyclerViewSearch;
    private static List<DataRestaurants> mData;


public Search(){

}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.search_layout, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context=view.getContext().getApplicationContext();

       // Intent intent=  getIntent();

        RecyclerViewSearch=view.findViewById(R.id.RecyclerViewSearch);
        RecyclerViewSearch.setLayoutManager(new LinearLayoutManager(context));
       // RecyclerViewSearch.addOnItemTouchListener(new RecyclerItemClickListener(context,recyclerView,this));//event handler
        mAdapter = new MyAdapter(context,new ArrayList< DataRestaurants >());
        RecyclerViewSearch.setAdapter(mAdapter);
        LocationCheck();

      SearchView  searchView=(SearchView)view.findViewById(R.id.searchView);
        searchView.setQueryHint("Search");
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
       // searchView.getInputType();

      searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
          @SuppressLint("ShowToast")
          @Override
          public boolean onQueryTextSubmit(String query) {
             // Log.e(TAG, "Search"+query);
              searchView.clearFocus();
              if(mame.contains(query)){
                  Log.e(TAG, "Search"+query);
                  Toast.makeText(context,"found"+query,Toast.LENGTH_LONG).show();//+searchView.getInputType()
              }

              return false;
          }

          @Override
          public boolean onQueryTextChange(String newText) {//if i input search box and changed , deleted char or add char than is going to method
              List< DataRestaurants > filteredList = new ArrayList<>();
              if(newText.equals("")){
                 // filteredList.clear();
                //  Toast.makeText(context,"onQuery null :"+searchView.getQuery(),Toast.LENGTH_LONG).show();
                  RecyclerViewSearch.clearFocus();
                  mAdapter = new MyAdapter(context,new ArrayList< DataRestaurants >());
             //    mAdapter.loadNewData(filteredList);
                  RecyclerViewSearch.setAdapter(mAdapter);

              }else {
                 // Toast.makeText(context, "onQueryTextChange :" + searchView.getQuery(), Toast.LENGTH_LONG).show();
                  if (mData.size() != 0) {
                      Log.e(TAG, "onQueryTextChange" + mData.size());
                      //  mData= new ArrayList<>();// come for activity
                      String filterPattern = newText.toString().toLowerCase().trim();
                      for (DataRestaurants item : mData) {
                          String[] arrOfName = item.getmName().split(" ", 2);

                          if (arrOfName[0].toLowerCase().contains(filterPattern)) {

                              Log.e(TAG, "Search \n" + arrOfName[0]);
                              filteredList.add(new DataRestaurants(item.getmBlurhash(), item.getmImage(), item.getmName(), item.getmDescription(), item.getmDelivery_price(), "15-25", String.valueOf(item.getDistance())));
                              mAdapter.loadNewData(filteredList);
                              RecyclerViewSearch.setAdapter(mAdapter);

                          }
                      }
                      if(filteredList.size()==0){
                          Toast.makeText(context,"We did not find \n that name..",Toast.LENGTH_LONG).show();
                      }

                  } else {
                      Log.e(TAG, "filed get data from MainActivity :"+mData.size());
                      //return false;
                  }
              }

             // Toast.makeText(context,""+newText,Toast.LENGTH_LONG).show();//every char input is it going here
              return false;
          }
      });



      /*  view.findViewById(R.id.searchView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // NavHostFragment.findNavController(FirstFragment.this).navigate(R.id.action_FirstFragment_to_SecondFragment);
                Log.e(TAG, "Search");
            }
        });*/


    }

    public void updataListFromMainActivity(List<DataRestaurants> Data){
        mData=new ArrayList<>(Data);
       // DataRestaurants dataRestaurants=mData.get(0);
       // Log.e(TAG,"Search"+mData.size());
        if(mData.size()!=0){
           // mData=new ArrayList<>(Data);

                Log.e(TAG,"Search"+mData.size());

        }else {
            Log.e(TAG,"Search filed to get data from MainActivity"+mData.size());
        }

    }

    public void  LocationCheck(){

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            new LocationRetriever(context, Search.this, location -> {
                setInitialLocation(new LatLng(location.getLatitude(), location.getLongitude()));
                //  input.setText(location.getLatitude() + "," + location.getLongitude());

            }, () -> {
                Log.w(TAG, "Failed to get location.");
                setInitialLocation(PRIME_MERIDIAN);
            });
        } else {
            Log.w(TAG, "No location permissions");
            setInitialLocation(PRIME_MERIDIAN);
        }


    }

    public void setInitialLocation(@NonNull LatLng latLng) {
        this.initialLocation=latLng;
       if(this.initialLocation.latitude==32.0722903 && this.initialLocation.longitude==34.7856782){
           LocationCheck();//try again to find setCurrentLocation
       }
        setCurrentLocation(this.initialLocation);
        // Intent intent =new Intent(getContext(),Search.class);


    }
    private void setCurrentLocation(LatLng location) {
        this.currentLocation = location;
        Log.e(TAG, " get location." + this.currentLocation.latitude + "," + this.currentLocation.longitude);
    }
    @SuppressLint("SetTextI18n")
    private void onClickButton(){
        getLocation.setText(initialLocation.latitude + "," + initialLocation.longitude);

    }


}
