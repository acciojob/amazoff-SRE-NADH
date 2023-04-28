package com.driver;

import java.util.Arrays;
import java.util.List;

public class Order {
    private String id;
    private int deliveryTime;
    public Order(String id, String deliveryTime) {
     this.id = id;
     this.deliveryTime= convertDeliveryTime(deliveryTime);
        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
    public  void setDeliveryTime(String time){
        this.deliveryTime=convertDeliveryTime(time);
    }
    public static int convertDeliveryTime(String time){
        List<String> arr = Arrays.asList(time.split(":"));
        int HH = Integer.parseInt(arr.get(0));
        int MM = Integer.parseInt(arr.get(1));
        return HH*60+MM;
    }
    public static String getStringDeliveryTime(int deliveryTime){
        int HH = deliveryTime/60;
        int MM = deliveryTime%60;
        String hh = String.valueOf(HH);
        String mm = String.valueOf(MM);
        if(hh.length()==1) hh="0"+hh;
        if(mm.length()==1) mm="0"+mm;
        return hh+":"+mm;
    }
    public int getDeliveryTime() {
        return deliveryTime;
    }

}
