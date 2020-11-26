package com.example.example;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapterSearch extends RecyclerView.Adapter<MyAdapterSearch.MyViewHolder>{// implements Filterable {

    //  ArrayList< SlideModel >  slideModels;
    private Context context;
    private static List<PropertyFirstFragment> mData;
  //  private List<DataRestaurants> mDataFull;



    // ImageSlider image_Slider;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        // final  LinearLayout linearLayout;
        // final TextView textView;
        //  View v;

     private    Context context;
        TextView name=null;
        TextView description=null;
        TextView time=null;
         TextView priceDelivery=null;
       // TextView  RestaurantsDistance=null;
        ImageView img=null;


        public MyViewHolder(View v) {//ImageView k
            super(v);// reference to ticket
            img=(ImageView)v.findViewById(R.id.SearchImageFood);
            name=(TextView) v.findViewById(R.id.SearchNameFood);
            description=(TextView)v.findViewById(R.id.SearchDescriptionFood);
            time=(TextView) v.findViewById(R.id.SearchDeliveryTime);
            priceDelivery=(TextView)v.findViewById(R.id.SearchPrice);


        }



    }
    void loadNewData(List<PropertyFirstFragment> Data){
        mData=Data;// get the list value
      //  mDataFull=new ArrayList<>(mData);//get the copy
        notifyDataSetChanged();
    }

    public MyAdapterSearch(Context contex, List<PropertyFirstFragment> Data){
        context=contex;
        mData=Data;// get the list value

    }


    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public  MyAdapterSearch.MyViewHolder  onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view

        //    Log.d(TAG,"onCreateViewHolder"+dataRestaurants.getmName());
        View v =  LayoutInflater.from(parent.getContext()).inflate(R.layout.search_restaurants, parent, false);
        this.context=parent.getContext();
        // MViewHolder vh = new MyViewHolder(v);
        return new MyAdapterSearch.MyViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {//get the value

        PropertyFirstFragment dataRestaurants =mData.get(position);

        Bitmap bitmap=BlurHashDecoder.decode(dataRestaurants.getmBlurhash(),20,10);
        holder.img.setImageBitmap(bitmap);

        holder.name.setText(dataRestaurants.getmNameRestaurnt());
        holder.time.setText(dataRestaurants.getmLocationTime());
        holder.description.setText(dataRestaurants.getmDescription());
        holder.priceDelivery.setText(String.format("%s %s","$$ - Delivery ",dataRestaurants.getmDelivery_price() ));//"$$ - Delivery "+dataRestaurants.getmDelivery_price()
     //   holder.RestaurantsDistance.setText(dataRestaurants.getDistance()+" km");
        Picasso.get().load(dataRestaurants.getmImageRestaurnt()).error(R.drawable.food_delivery).placeholder(holder.img.getDrawable()).into(holder.img);//holder.circleimageView





    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {

        return ((this.mData!=null &&this.mData.size()!=0)?  this.mData.size(): 0);
    }

    public List<PropertyFirstFragment> getmDataList(){
        return ((this.mData!=null &&this.mData.size()!=0)?  this.mData : null);
    }

    public   PropertyFirstFragment getmData(int position) {

        return ((mData!=null && this.mData.size()!=0)?  this.mData.get(position) : null);
    }




  /*  @Override
    public Filter getFilter() {
        return DataRestaurantsFilter;
    }
    private final Filter DataRestaurantsFilter= new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<DataRestaurants> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(mDataFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (DataRestaurants item : mDataFull) {
                    if (item.getmName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mData.clear();
            mData.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };*/
}
