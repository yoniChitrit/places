package com.example.example;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerItemClickListener extends RecyclerView.SimpleOnItemTouchListener {

    private static final String TAG = "RecyclerItemClickListener";

    public interface OnRecylerClickListener {
        void OnItemClick(View view, int position);

        void OnItemLongClick(View view, int position);
    }

    private final OnRecylerClickListener mListener;
    private final GestureDetectorCompat mGestureDetector;

    public RecyclerItemClickListener(Context context, final RecyclerView recyclerView, final OnRecylerClickListener listener) {
        mListener = listener;
        mGestureDetector = new GestureDetectorCompat(context, new GestureDetector.SimpleOnGestureListener() {
            @SuppressLint("LongLogTag")
            @Override
            public boolean onSingleTapUp(MotionEvent e) {//return super.onSingleTapUp(e);
                Log.d(TAG, "onSingleTapUp: starts");
                View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (childView != null && mListener != null) {
                    Log.d(TAG, "onSingleTapUp: calling listener OnItemClick");
                    listener.OnItemClick(childView, recyclerView.getChildAdapterPosition(childView));
                }
                return true;
            }

            @SuppressLint("LongLogTag")
            @Override
            public void onLongPress(MotionEvent e) {// super.onLongPress(e);
                Log.d(TAG, "onLongPress: starts");//if tag clicked long and scrolling not nothing happen
                View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (childView != null && mListener != null) {
                    Log.d(TAG, "onSingleTapUp: calling listener OnItemLongClick");
                    listener.OnItemLongClick(childView, recyclerView.getChildAdapterPosition(childView));
                }
            }
        });
    }


    @SuppressLint("LongLogTag")
    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {//return super.onInterceptTouchEvent(rv, e);

        if (mGestureDetector != null) {
            boolean result = mGestureDetector.onTouchEvent(e);
            Log.d(TAG, "onInterceptTouchEvent : returned " + result);
            return result;
        } else {
       //     Log.d(TAG, "onInterceptTouchEvent : false");
            return false;
        }

    }

}
