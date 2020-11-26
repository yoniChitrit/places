package com.example.example;

import com.google.android.gms.maps.model.LatLng;

import static java.lang.Math.asin;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.Math.toRadians;

 class CalculationtTimeDelivery {

    private static final int SPEED=35;
    private static final int ORDERS_EXIST=15;

   protected   double timeDelivery;


    protected static double  distanceRadians(double lat1, double lng1, double lat2, double lng2) {
        return MathUtil.arcHav(MathUtil.havDistance(lat1, lat2, lng1 - lng2));
    }

    /**
     * Returns the angle between two LatLngs, in radians. This is the same as the distance
     * on the unit sphere.
     */
    static double computeAngleBetween(LatLng from, LatLng to) {
        return distanceRadians(toRadians(from.latitude), toRadians(from.longitude),
                toRadians(to.latitude), toRadians(to.longitude));
    }

    /**
     * Returns the distance between two LatLngs, in meters.
     */
    public static double computeDistanceBetween(LatLng from, LatLng to) {
        return computeAngleBetween(from, to) * MathUtil.EARTH_RADIUS;
    }
public CalculationtTimeDelivery(MathUtil mathUtil){

}

    static class   MathUtil{

     public   static final double EARTH_RADIUS = 6371009;

       static double arcHav(double x) {
           return 2 * asin(sqrt(x));
       }

       static double havDistance(double lat1, double lat2, double dLng) {
           return hav(lat1 - lat2) + hav(dLng) * cos(lat1) * cos(lat2);
       }

       static double hav(double x) {
           double sinHalf = sin(x * 0.5);
           return sinHalf * sinHalf;
       }
   }


public double setDistance(double d){

    return CalculationtTime(d);
}

public double CalculationtTime(double timeDelivery){
return 0;
}


}
