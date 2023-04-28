package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class OrderRepocitory {
HashMap<String,Order> orderMap = new HashMap<>();
HashMap<String,DeliveryPartner> partnerMap= new HashMap<>();
Map<String,List<String>> PartnerOrderMap = new HashMap<>();
Map<String,String> OrderPartnerMap = new HashMap<>();

    public OrderRepocitory() {
        orderMap = new HashMap<>();
        partnerMap = new HashMap<>();
        PartnerOrderMap = new HashMap<>();
        OrderPartnerMap = new HashMap<>();
    }
    public void addOrder(Order order) {
        orderMap.put(order.getId(),order);
    }

    public void addPartner(DeliveryPartner deliveryPartner) {
        partnerMap.put(deliveryPartner.getId(), deliveryPartner);
    }

    public Optional<Order> checkOrderId(String orderId) {
    if(orderMap.containsKey(orderId)){
        return Optional.of(orderMap.get(orderId));
    }
    return Optional.empty();
    }

    public Optional<DeliveryPartner> checkPartnerId(String partnerId) {
        if(partnerMap.containsKey(partnerId)){
            return Optional.of(partnerMap.get(partnerId));
        }
        return Optional.empty();
    }

    public void addOrderPartner(String orderId, String partnerId) {
        if(PartnerOrderMap.containsKey(partnerId)){
            if(orderispresent(orderId,partnerId)){
                return;
            }
           PartnerOrderMap.get(partnerId).add(orderId);
        }
        else {
            List<String> tmp = new ArrayList<>();
            tmp.add(orderId);
            PartnerOrderMap.put(partnerId,tmp);
        }
        partnerMap.get(partnerId).setNumberOfOrders(PartnerOrderMap.get(partnerId).size());
    }

    private boolean orderispresent(String orderId,String partnerId) {
        List<String> tmp =getOrdersByPartnerId(partnerId);
        for(String x:tmp){
            if(x.equals(orderId)) return true;
        }
        return false;
    }

    public boolean checkPartnerinPair(String id) {

        return PartnerOrderMap.containsKey(id);
    }

    public Integer findlength(String partnerId) {
        return PartnerOrderMap.get(partnerId).size();
    }

    public List<String> getOrdersByPartnerId(String id) {
        return PartnerOrderMap.get(id);
    }

    public List<String> getAllOrders() {
        if(orderMap.isEmpty()){
            return null;
        }
        return new ArrayList<>(orderMap.keySet());
    }

    public void addOrderWithPartner(String orderId, String partnerId) {
        OrderPartnerMap.put(orderId,partnerId);
    }

    public int findSizeOfOrderpartner() {
        return OrderPartnerMap.size();
    }

    public int findsizeOfOrder() {
        return orderMap.size();
    }

    public void removepartnermap(String partnerId) {
        partnerMap.remove(partnerId);
    }

    public void removeinOrderPartnerPair(String x) {
        OrderPartnerMap.remove(x);
    }

    public void removepartnerpair(String partnerId) {
        PartnerOrderMap.remove(partnerId);
    }

    public String getPartnerinOrderMap(String orderId) {
        return OrderPartnerMap.get(orderId);
    }

    public void removeOrderIdinpartnerPair(String partnerId, String orderId) {
        List<String> tmp = PartnerOrderMap.get(partnerId);
        for(String x:tmp){
            if(x.equals(orderId)){
                tmp.remove(x);
            }
        }
        PartnerOrderMap.put(partnerId,tmp);
    }

    public void removeOrder(String orderId) {
        orderMap.remove(orderId);
    }

    public boolean idcontainsorderpartner(String orderId) {
        return OrderPartnerMap.containsKey(orderId);
    }
}
