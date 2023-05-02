package com.driver;

import java.util.Arrays;
import java.util.List;

public class Order {
    private String id;
    private int deliveryTime;
    public Order(String id, String deliveryTime) {
     this.id = id;
     this.deliveryTime= Timeutils.convertDeliveryTime(deliveryTime);
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
        this.deliveryTime=Timeutils.convertDeliveryTime(time);
    }
    public int getDeliveryTime() {
        return this.deliveryTime;
    }

}
