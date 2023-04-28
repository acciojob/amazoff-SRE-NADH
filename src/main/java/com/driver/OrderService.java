package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
@Autowired
OrderRepocitory repo;
    public void addOrder(Order order) {
        repo.addOrder(order);
    }

    public void addPartner(String partnerId) {
        repo.addPartner(new DeliveryPartner(partnerId));
    }

    public void addOrderPartner(String orderId, String partnerId) {
        Optional<Order> optOrder = repo.checkOrderId(orderId);
        Optional<DeliveryPartner> optPartner = repo.checkPartnerId(partnerId);
        if(optOrder.isPresent() && optPartner.isPresent()){
            repo.addOrderWithPartner(orderId,partnerId);
            repo.addOrderPartner(orderId,partnerId);
        }
    }

    public Order getOrderById(String orderId) {
        Optional<Order> opt = repo.checkOrderId(orderId);
        if(opt.isPresent()){
            return opt.get();
        }
        return null;
    }

    public DeliveryPartner getPartnerById(String partnerId) {
        Optional<DeliveryPartner> opt = repo.checkPartnerId(partnerId);
        if(opt.isPresent()){
            return opt.get();
        }
        return null;
    }

    public Integer getOrderCountByPartnerId(String partnerId) {
        if(repo.checkPartnerinPair(partnerId)){
            return repo.findlength(partnerId);
        }
        return 0;
    }


    public List<String> getOrdersByPartnerId(String partnerId) {
        if (repo.checkPartnerinPair(partnerId)) {
            List<String> tmp = repo.getOrdersByPartnerId(partnerId);
        }
        return null;
    }

    public List<String> getAllOrders() {
        List<String> tmp =repo.getAllOrders();
        return tmp;
    }

    public Integer getCountOfUnassignedOrders() {
        int n = repo.findSizeOfOrderpartner();
        int m =repo.findsizeOfOrder();
        return m-n;
    }

    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId) {
        if(repo.checkPartnerinPair(partnerId)){
            return 0;
        }
        List<String> tmp = repo.getOrdersByPartnerId(partnerId);
        int count=0;
        int Time=Order.convertDeliveryTime(time);
        for(String x:tmp){
            Optional<Order> opt=repo.checkOrderId(x);
            Order order = opt.get();
            if(order.getDeliveryTime()>Time){
                count++;
            }
        }
        return count;
    }

    public String getLastDeliveryTimeByPartnerId(String partnerId) {
        List<String> tmp = repo.getOrdersByPartnerId(partnerId);
        int max=0;
        for(String x:tmp){
            Optional<Order> opt=repo.checkOrderId(x);
            Order order = opt.get();
            int time =order.getDeliveryTime();
            if(time>max){
                max=time;
            }
        }
        return Order.getStringDeliveryTime(max);
    }

    public void deletePartnerById(String partnerId) {
        List<String> tmp = repo.getOrdersByPartnerId(partnerId);
        for(String x:tmp){
            repo.removeinOrderPartnerPair(x);
        }
        repo.removepartnermap(partnerId);
        repo.removepartnerpair(partnerId);
    }

    public void deleteOrderById(String orderId) {
        String partnerId = repo.getPartnerinOrderMap(orderId);
         repo.removeOrderIdinpartnerPair(partnerId,orderId);
         repo.removeinOrderPartnerPair(orderId);
         repo.removeOrder(orderId);
    }




//    private int convertDeliveryTime(String time){
//        List<String> arr = Arrays.asList(time.split(":"));
//        int HH = Integer.parseInt(arr.get(0));
//        int MM = Integer.parseInt(arr.get(1));
//        return HH*60+MM;
//    }
}
