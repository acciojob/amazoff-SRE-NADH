package com.driver;

import java.util.Arrays;
import java.util.List;

public class Timeutils {
    public static int convertDeliveryTime(String time) {
        List<String> arr = Arrays.asList(time.split(":"));
        int HH = Integer.parseInt(arr.get(0));
        int MM = Integer.parseInt(arr.get(1));
        return HH * 60 + MM;
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
}