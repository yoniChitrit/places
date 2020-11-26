package com.example.example;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{// implements Filterable {

private  static final   String TAG="MyAdapter";
    //  ArrayList< SlideModel >  slideModels;
    private Context context;
    private List<PropertyFirstFragment> mData;
  //  private List<DataRestaurants> mDataFull;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        // final  LinearLayout linearLayout;
        // final TextView textView;
      //  View v;

         Context context;
         TextView name=null;
       TextView description=null;
        TextView time=null;
       TextView priceDelivery=null;
         TextView  RestaurantsDistance=null;
       ImageView img=null;

TextView textUpdateUnavailableRestaurants_=null;//display massage restaurants open or not
        public MyViewHolder(View v) {//ImageView k
            super(v);// reference to ticket
            img=(ImageView)v.findViewById(R.id.img);
        name=(TextView) v.findViewById(R.id.name_res);
            description=(TextView)v.findViewById(R.id.description);
        time=(TextView) v.findViewById(R.id.time);
            priceDelivery=(TextView)v.findViewById(R.id.price_delivery);
            RestaurantsDistance=(TextView)v.findViewById(R.id.RestaurantsDistance);
            textUpdateUnavailableRestaurants_=(TextView) v.findViewById(R.id.textUpdateUnavailableRestaurants);
            textUpdateUnavailableRestaurants_.setVisibility(View.GONE);
          //  this.v=v;

        }



    }
    void loadNewData(List<PropertyFirstFragment> mData){
          this.mData=mData;// get the list value

          notifyDataSetChanged();
        }

    public MyAdapter(Context context, List<PropertyFirstFragment> mData){

       this.context=context;
        this.mData=mData;// get the list value

    }
public MyAdapter(Context context){
        this.context=context;
}

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public  MyAdapter.MyViewHolder  onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view

        //    Log.d(TAG,"onCreateViewHolder"+dataRestaurants.getmName());
        View v =  LayoutInflater.from(parent.getContext()).inflate(R.layout.first_fragment_to_my_adapter, parent, false);
        this.context=parent.getContext();
        // MViewHolder vh = new MyViewHolder(v);
        return new MyViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {//get the value

        PropertyFirstFragment propertyFirstFragment =this.mData.get(position);

        Bitmap bitmap=BlurHashDecoder.decode(propertyFirstFragment.getmBlurhash(),20,10);
        holder.img.setImageBitmap(bitmap);

        holder.name.setText(propertyFirstFragment.getmNameRestaurnt());
        holder.time.setText(propertyFirstFragment.getmLocationTime());
        holder.description.setText(propertyFirstFragment.getmDescription());
        holder.priceDelivery.setText("$$ - Delivery "+propertyFirstFragment.getmDelivery_price());
       holder.RestaurantsDistance.setText(propertyFirstFragment.getmDistance()+" km");
        Picasso.get().load(propertyFirstFragment.getmImageRestaurnt()).error(R.drawable.food_delivery).placeholder(holder.img.getDrawable()).into(holder.img);//holder.circleimageView
        Log.e(TAG,"109"+propertyFirstFragment.ismIsOpenOrClose());
           if(!propertyFirstFragment.ismIsOpenOrClose()){// in the firstFragment calculation time oprn or close and than uptade the list with false or true
               Log.e(TAG,"110"+propertyFirstFragment.ismIsOpenOrClose());
               holder.textUpdateUnavailableRestaurants_.setVisibility(View.VISIBLE);
           }




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
