package com.example.example.menu;

import android.content.Context;

public class PropertyMenu {

    private String name=null;
    private String description=null;
    private String clock=null;

    private String textMenu=null;

    private String imageMenu=null;
    private String nameFoodMenu=null;
    private String infoFoodMenu=null;
   private String priceFoodMenu=null;

   private String BlurHashMenu=null;
    private Context context;





    public PropertyMenu(Context context){
        this.context=context;
    }
    public PropertyMenu(String name,String description,String clock ){
        this.name=name;
        this.description=description;
        this.clock=clock;

    }
    public PropertyMenu(String imageMenu,String nameFoodMenu,String infoFoodMenu,String priceFoodMenu,String BlurHashMenu){

        this.imageMenu=imageMenu;
        this.nameFoodMenu=nameFoodMenu;
        this.infoFoodMenu=infoFoodMenu;
        this.priceFoodMenu=priceFoodMenu;
this.BlurHashMenu=BlurHashMenu;
    }

    public PropertyMenu(String textMenu){
        this.textMenu=textMenu;
    }

    public String getBlurHashMenu() {
        return BlurHashMenu;
    }

    public String getNameFoodMenu() {
        return nameFoodMenu;
    }

    public String getInfoFoodMenu() {
        return infoFoodMenu;
    }

    public String getPriceFoodMenu() {
        return priceFoodMenu;
    }

    //
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getClock() {
        return clock;
    }

    //menu ticket
    public String getTextMenu() {
        return textMenu;
    }

    public String getImageMenu() {
        return imageMenu;
    }



    public Context getContext() {
        return context;
    }

}
