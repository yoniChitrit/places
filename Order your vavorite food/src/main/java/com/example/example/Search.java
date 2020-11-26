package com.example.example;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.example.menu.MainActivityMenu;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.example.CalculationtTimeDelivery.computeDistanceBetween;

/*
in the beginner check location and than bring the json
this Fragment get onDownloadComplete result and list of FirstFragment from MainActivity and than every time that user search name Restaurants the CheckLocationChanged()
if yes bring the json data again
*/
public class Search extends Fragment implements RecyclerItemClickListener.OnRecylerClickListener{// implements LocationRetriever.SuccessListener,LocationRetriever.FailureListener{


    private static final String TAG = "Search";
    public static final String DATA_TRANSFER_SEARCH="DATA_TRANSFER_S";
    private static final LatLng PRIME_MERIDIAN = new LatLng(0, 0);//start with this

    private static LatLng initialLocation;
    private static LatLng currentLocation = new LatLng(0, 0);
    @SuppressLint("StaticFieldLeak")
    private static Context context;


    private static MyAdapterSearch mAdapter;
    private RecyclerView RecyclerViewSearch;
   // private static List< DataRestaurants > mData;

    private static List< PropertyFirstFragment > mNewDataJson;
    private static String ResultJson =null;
  // private static List< DataRestaurants > filteredList_check = new ArrayList<>();
   private static Boolean flag=true;

  //  private RunForLocation runForLocation;

    public Search() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.search_layout, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e(TAG, "onViewCreated ");
        context = view.getContext().getApplicationContext();


        // Intent intent=  getIntent();

        RecyclerViewSearch = view.findViewById(R.id.RecyclerViewSearch);
        RecyclerViewSearch.setLayoutManager(new LinearLayoutManager(context));
         RecyclerViewSearch.addOnItemTouchListener(new RecyclerItemClickListener(context,RecyclerViewSearch,this));//event handler
        mAdapter = new MyAdapterSearch(context, new ArrayList<  >());
        RecyclerViewSearch.setAdapter(mAdapter);



        SearchView searchView = (SearchView) view.findViewById(R.id.searchView);
        searchView.setQueryHint("Search");
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

           // runForLocation=new RunForLocation();
           // runForLocation.run();
            Log.e(TAG, " onQueryTextChange inter to check location  ");



        // searchView.getInputType();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @SuppressLint("ShowToast")
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Log.e(TAG, "Search"+query); mame.contains(query):searchView.clearFocus();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {//if i input search box and changed , deleted char or add char than is going to method

                if(mNewDataJson.size() == 0){//if data empty
                    Log.e(TAG, "  ResultJson is empty line 134 ");
                    return false;

                }

                List< PropertyFirstFragment > filteredList = new ArrayList<>();
                if (newText.equals("")&&newText.isEmpty()) {
                //    filteredList = new ArrayList<>();

                    Log.e(TAG, "newText .equals empty  filteredList.clear()  "+filteredList.size());
                    // filteredList.clear();
                    //  Toast.makeText(context,"onQuery null :"+searchView.getQuery(),Toast.LENGTH_LONG).show();
                    RecyclerViewSearch.clearFocus();
                    mAdapter = new MyAdapterSearch(context, new ArrayList< >());
                    RecyclerViewSearch.setAdapter(mAdapter);

                } else {
                    mAdapter = new MyAdapterSearch(context, new ArrayList< >());
                    RecyclerViewSearch.setAdapter(mAdapter);

                    String[] arrOfName=null;
                        String filterPattern = newText.toString().toLowerCase().trim();
                        for (PropertyFirstFragment item : mNewDataJson) {
                             arrOfName = item.getmNameRestaurnt().split(" ", 2);

                            if (arrOfName[0].toLowerCase().contains(filterPattern)) {
                                       // public PropertyFirstFragment(String LocationTime, String Description, String ImageRestaurnt, String NameRestaurnt,
                                       // String Blurhash, String Delivery_price, String Distance,String FeedbackRestaurants,boolean isOpenOrClose) {
                                Log.e(TAG, "Search \n" + arrOfName[0]);
                                filteredList.add(new PropertyFirstFragment(item.getmLocationTime(), item.getmDescription(), item.getmImageRestaurnt(),
                                        item.getmNameRestaurnt(), item.getmBlurhash(), item.getmDelivery_price(),
                                        String.valueOf(item.getmDistance()),item.getmFeedbackRestaurants(),item.ismIsOpenOrClose()));
                                mAdapter.loadNewData(filteredList);
                                RecyclerViewSearch.setAdapter(mAdapter);
                             //   filteredList_check.add(new DataRestaurants(item.getmBlurhash(), item.getmImage(), item.getmName(), item.getmDescription(), item.getmDelivery_price(), "15-25", String.valueOf(item.getDistance())));

                            }


                        }
                       // if(newText.length()>arrOfName[0].length()&&filteredList.size()==0){
                         //   Toast.makeText(context,"not found",Toast.LENGTH_SHORT);
                        //}


                }

                // Toast.makeText(context,""+newText,Toast.LENGTH_LONG).show();//every char input is it going here
                return false;
            }
        });

    }


     void updataListFromMainActivity(LatLng latLng, String result) {//get data from MainActivity line 60
        currentLocation = latLng;// location from FirstFragment
        ResultJson = result;//get all the value from json
        if (currentLocation.latitude != 0 && !ResultJson.equals("")) {
            NewDownloadJson();



            // mData=new ArrayList<>(Data);
            Log.e(TAG, "155 line DataJson and list from MainActivity by implements S");


        } else {
            Log.e(TAG, "Search filed to get data from MainActivity line 202" );
        }

    }

  /*  public void updataListFromMainActivity(List< DataRestaurants > Data, String result) {//get data from MainActivity line 60
        mData = new ArrayList<>(Data);
        ResultJson = result;//get all the value from json
        if (mData.size() != 0 && !ResultJson.equals("")) {
            NewDownloadJson();
            // mData=new ArrayList<>(Data);
            Log.e(TAG, "155 line DataJson and list from MainActivity by implements S");
            Log.e(TAG, "Search " + mData.size());

        } else {
            Log.e(TAG, "Search filed to get data from MainActivity" + mData.size());
        }

    }*/

   /* class RunForLocation implements Runnable{
      Handler  handler = new Handler();
         public RunForLocation(){}

        @Override
        public void run() {
            LocationCheck();
            Log.e(TAG, "run line 255 ---------------------------------------------");
            handler.postDelayed(this,(1000*30));
           // LocationCheck();
        }


    }
    @Override
    public void onStop() {
        super.onStop();
        runForLocation.handler.removeCallbacks(runForLocation);
       // runForLocation.handler.getLooper();

        Log.e(TAG, "onStop() line 255 ---------------------------------------------");


    }*/

    @Override
    public void onStart() {
        super.onStart();
      //  LocationCheck();
    }

    public void LocationCheck() {

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
        // LocationRetriever locationRetriever=   new LocationRetriever(context,this,this,this);
            new LocationRetriever(context, this, location -> {
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
        initialLocation = latLng;// get my location now for check with location that got from FirstFragment if changed or not
        if (initialLocation.latitude == 0&& initialLocation.longitude == 0) {

            Toast.makeText(context, "Failed to \n get location line 247 \n location random", Toast.LENGTH_SHORT).show();
            setCurrentLocation(initialLocation);
        }else {
          //  if(CheckLocationChanged()){// if location changed //CheckLocationChanged()
                setCurrentLocation(initialLocation);
          //  }else {
                Log.e(TAG, " location not changed line 243.");
         //   }

        }
      //

        // Intent intent =new Intent(getContext(),Search.class);


    }

    private void setCurrentLocation(LatLng location) {
        currentLocation = location;
        NewDownloadJson(); //bring new data the location changed
       // Toast.makeText(context, "get location Successfully \n" + currentLocation.latitude + "\n" + currentLocation.longitude, Toast.LENGTH_SHORT).show();
        Log.e(TAG, " setCurrentLocation location changed line 267." + currentLocation.latitude + "," + currentLocation.longitude);

    }

   /* private Boolean CheckLocationChanged() {

        LatLng from = new LatLng(currentLocation.latitude, currentLocation.longitude);
        LatLng to = new LatLng(initialLocation.latitude, initialLocation.longitude);
        double Distance = computeDistanceBetween(from, to);
        Distance = (Distance / 1000);// become from meter to km
        Distance = Math.floor(Distance * 100) / 100;//"#.##"
        if (Distance > 0.3) {
            Log.e(TAG, "CheckLocationChanged  \n" + Distance);
        }

        return ((Distance > 0.2) ? true : false);//if the user changed location than more 200 meter bring again the data json
    }*/

    public void NewDownloadJson() {
        //  mNewDataJson=new ArrayList<>();
        //  Log.e(TAG, "onDownloadComplete:" +ResultJson );
        try {


            if (currentLocation.latitude != 0.0 && currentLocation.longitude != 0.0&&ResultJson!=null) {

                //  CalculationtTimeDelivery calculationtTimeDelivery = new CalculationtTimeDelivery();
                List< PropertyFirstFragment > mDataJson = new ArrayList<>();
                JSONObject response = new JSONObject(ResultJson);
                JSONArray temp = response.getJSONArray("restaurants");

                for (int i = 0; i < 10; i++) {//jsonArray.length()
                    JSONObject data_restaurant = temp.getJSONObject(i);
                    String Blurhash = data_restaurant.getString("blurhash");
                    String image = data_restaurant.getString("image");
                    JSONArray location = data_restaurant.getJSONArray("location");
                    String name = data_restaurant.getString("name");
                    String description = data_restaurant.getString("description");
                    String delivery_price = data_restaurant.getString("delivery_price");

                    // textView.append(  + "\n\n");
                    double lan = location.getDouble(0);
                    double lon = location.getDouble(1);

                    LatLng from = new LatLng(currentLocation.latitude, currentLocation.longitude);
                    LatLng to = new LatLng(lan, lon);

                    double Distance = computeDistanceBetween(from, to); //32.065116,34.774285//initialLocation.latitude,initialLocation.longitude
                    Distance = (Distance / 1000);// become from meter to km
                    Distance = Math.floor(Distance * 100) / 100;//"#.##"


                    if (Distance < 4.0 && Distance > 0.0) {//LASS THAN 4KM
                        // Log.e(TAG, "result Distance  :" +( Distance));
                        mDataJson.add(new PropertyFirstFragment("15-25",description, image, name, Blurhash, delivery_price,
                                String.valueOf(Distance),String.valueOf(0),true));// ->FEEDBACK
                  //  filteredList.add(new PropertyFirstFragment(item.getmLocationTime(), item.getmDescription(), item.getmImageRestaurnt(),
                        //                                        item.getmNameRestaurnt(), item.getmBlurhash(), item.getmDelivery_price(),
                        //                                        String.valueOf(item.getmDistance()),item.getmFeedbackRestaurants(),item.ismIsOpenOrClose()));
                    }
                    if(i==3&&Distance < 4.0&&Distance>0.0){
                        mDataJson.add(new PropertyFirstFragment(     "15-25",description,image,name,Blurhash,delivery_price,
                                String.valueOf(Distance) ,String.valueOf(1),false));//feedback
                    }

                    // textView.append(image + "," + String.valueOf(lon) +","+String.valueOf(lan)+ "\n\n");
                }
                mNewDataJson = new ArrayList<>(mDataJson);//GET REFERENCE IN SEARCH
                Log.e(TAG, " Download Successfully json" + mNewDataJson.size());
            } else {
                Log.e(TAG, "Search : field to get location 126 line   ");

            }
        } catch (JSONException e) {
            Log.e(TAG, "Search : field to Download json  ");
            e.printStackTrace();
        }

    }

    @Override
    public void OnItemClick(View view, int position) {
        Log.e(TAG, "OnItemClick  "+mAdapter.getmData(position).getmNameRestaurnt());
        if(mAdapter.getmData(position)!=null) {
            Intent intent = new Intent(getActivity(), MainActivityMenu.class);//RestaurantMenu.class
            intent.putExtra(DATA_TRANSFER_SEARCH, mAdapter.getmData(position));//REFERENCE FROM THE
            startActivity(intent);
        }else {
            Log.e(TAG, "filed bring data  ");
        }
    }

    @Override
    public void OnItemLongClick(View view, int position) {

    }


  /*  @Override
    public void onSuccess(@NonNull Location location) {
        Toast.makeText(context, "onSuccess "+location.getLatitude()+","+location.getLongitude() , Toast.LENGTH_LONG).show();
       Log.e(TAG, "Search : onSuccess  ");
    }

    @Override
    public void onFailure() {
        Toast.makeText(context, "onFailure() Location  ", Toast.LENGTH_LONG).show();
    }*/


        /*    if (newText.length()>1) {//&&newText.length()>3
                        int count=0;
                        for (DataRestaurants item : filteredList_check ) {
                            String[] arrOfName = item.getmName().split(" ", 2);
                            if(arrOfName[0].length()==newText.length()&&!arrOfName[0].equals(newText)){
                                //Toast.makeText(context, "remuve ", Toast.LENGTH_LONG).show();
                                mAdapter.removeAt(count);

                                if(filteredList.size()==count){
                                    Toast.makeText(context, "We did not find \n that name..", Toast.LENGTH_LONG).show();
                                }
                            }
                            count++;
                        }
                        filteredList_check.clear();
                        //Toast.makeText(context, "We did not find \n that name..", Toast.LENGTH_LONG).show();

                    }*/
}
