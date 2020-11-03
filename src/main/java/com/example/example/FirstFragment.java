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

//import static androidx.recyclerview.widget.RecyclerView.Adapter;

public  class FirstFragment extends Fragment implements DownloadTask.DownloadComplete, RecyclerItemClickListener.OnRecylerClickListener
       {// LocationRetriever.SuccessListener





private static final String TAG="FirstFragment";
    private static final LatLng PRIME_MERIDIAN = new LatLng(32.065116, 34.774285);//start with this
    private LatLng  initialLocation=null;
    private  LatLng  currentLocation = new LatLng(0, 0);

   // private static Boolean  Myprovider=false;
    private RecyclerView recyclerView;
    @SuppressLint("StaticFieldLeak")
  static   private  Context context;
  //  private DownloadTask downloadTask;
  private   MyAdapter  mAdapter;

           private final FirstFragment.myAdapterData myAdapterData;


public FirstFragment(FirstFragment.myAdapterData myAdapterData){

    this.myAdapterData = myAdapterData;
}
           interface myAdapterData{
               void resultAdapterList(List<DataRestaurants> dataList);
           }



  //  private   CalculationtTimeDelivery calculationtTimeDelivery;
    @SuppressLint("ObsoleteSdkInt")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.e(TAG,"FirstFragment A");



        return inflater.inflate(R.layout.fragment_first, container, false);
    }
    //  layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);//LinearLayoutManager.HORIZONTAL
    //  recyclerView.setLayoutManager(layoutManager);
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context=view.getContext().getApplicationContext();

        Log.e(TAG,"FirstFragment B");
        currentLocation = new LatLng(0, 0);
        recyclerView=view.findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(context,recyclerView,this));//event handler
        mAdapter = new MyAdapter(context,new ArrayList< DataRestaurants >());

        recyclerView.setAdapter(mAdapter);
        LocationCheck();

      /*  DownloadTask  downloadTask = new DownloadTask(context,this);//DownloadTask.DownloadComplete
        Log.e(TAG,"startDownload :-");
        String urlString=" https://yonichitrit.github.io/places/res.json";
         downloadTask.execute(urlString);*/


    }


    @Override
    public void onDownloadComplete(String result) {


        try {
           //LocationCheck();
            Log.e(TAG, "onDownloadComplete:" +currentLocation.latitude+","+currentLocation.longitude );
            if(currentLocation.latitude!=0.0&& currentLocation.longitude!=0.0) {

              //  CalculationtTimeDelivery calculationtTimeDelivery = new CalculationtTimeDelivery();
                List< DataRestaurants > mData = new ArrayList<>();
                JSONObject response = new JSONObject(result);
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
                   // Log.e(TAG, "my location from:" + currentLocation.latitude+""+currentLocation.longitude);
                  //  Log.e(TAG, "to location the restaurants :" +lan+""+lon );

                    LatLng from =new LatLng(currentLocation.latitude, currentLocation.longitude);
                   LatLng to=new LatLng(lan, lon);


                    double  Distance = computeDistanceBetween(from,to); //32.065116,34.774285//initialLocation.latitude,initialLocation.longitude
                    Distance=(Distance/1000);// become from meter to km
                    Distance = Math.floor(Distance * 100) / 100;//"#.##"


                    if (Distance < 4.0&&Distance>0.0) {//LASS THAN 4KM
                        Log.e(TAG, "result Distance  :" +( Distance));
                        mData.add(new DataRestaurants(Blurhash, image, name, description, delivery_price, "15-25",String.valueOf(Distance)));
                    }

                    mAdapter.loadNewData(mData);//mAdapter reference to class and holder the data
                    recyclerView.setAdapter(mAdapter);
                    this.myAdapterData.resultAdapterList(mData);//implement to MainActivity

                    // textView.append(image + "," + String.valueOf(lon) +","+String.valueOf(lan)+ "\n\n");
                }
            }else {
                Log.e(TAG,"FirstFragment : field to get location 126 line   ");

            }
        }catch (JSONException e ){
            Log.e(TAG,"FirstFragment : field to Download json  ");
            e.printStackTrace();
        }

    }

    @Override
    public void OnItemClick(View view, int position) {
        Log.d(TAG,"OnItemClick");
      //  Toast.makeText(getContext(), "normal tag  "+position, Toast.LENGTH_SHORT).show();
        Intent intent =new Intent(getContext(), MainActivityMenu.class);//RestaurantMenu.class
        intent.putExtra( "DATA_TRANSFER" ,mAdapter.getmData(position));//REFERENCE FROM THE
        startActivity(intent);
    }

    @Override
    public void OnItemLongClick(View view, int position) {
       // MyAdapter myAdapterData=new MyAdapter();
   //    ArrayList< DataRestaurants > mData = new ArrayList<>();
       // mData.add( mAdapter.getmData(position));
    //  Log.d(TAG,"OnItemLongClick :"+ mData.get(position).getmImage());


        //Toast.makeText(getContext(), "long tag  "+ position, Toast.LENGTH_SHORT).show();

    }


public void  LocationCheck(){

    if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
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


        try {
           // Thread.sleep(500);
            initialLocation=latLng;
            Log.e(TAG, " setInitialLocation location." +latLng);
            if(initialLocation.latitude!=0.0&& initialLocation.longitude!=0.0){
                setCurrentLocation(initialLocation);


               // LocationCheck();
            }else {
                Log.e(TAG, " setInitialLocation location is filed." );
            }



        } catch (Exception e) {
            e.printStackTrace();
        }




      // Intent intent =new Intent(getContext(),Search.class);


    }
    private void setCurrentLocation(LatLng location) {
        currentLocation = location;
        DownloadTask  downloadTask = new DownloadTask(context,this);//DownloadTask.DownloadComplete
        Log.e(TAG,"startDownload :-");
        String urlString=" https://yonichitrit.github.io/places/res.json";
        downloadTask.execute(urlString);
     //   Log.e(TAG, " setCurrentLocation location." + currentLocation.latitude + "," + currentLocation.longitude);
    }
   /* @Override
    public void onSuccess(@NonNull Location location) {
        Log.e(TAG, "[onSuccess] get location." +location);
    }*/

  /*  private void enableMyLocationButtonIfHaveThePermission(GoogleMap googleMap) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)   == PackageManager.PERMISSION_GRANTED ||
                checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            googleMap.setMyLocationEnabled(true);
        }
    }*/
/*

An InputStream is a readable source of bytes. Once you get an InputStream, it's common to decode or
 convert it into a target data type. For example, if you were downloading image data,
  you might decode and display it like this:

 */
/*InputStream inputStream = null;

    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
    ImageView imageView = (ImageView) findViewById(R.id.image_view);
imageView.setImageBitmap(bitmap);
*/






}