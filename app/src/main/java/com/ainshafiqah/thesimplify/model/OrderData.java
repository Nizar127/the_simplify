package com.ainshafiqah.thesimplify.model;

public class OrderData {
    String orderID;
    String name;
    String phone;
    String address;
    String orderDate;
    String shipmentDate;
    String trackingNum;
    String status;

    public OrderData(String orderID,String name, String phone, String address, String orderDate, String shipmentDate, String trackingNum, String status) {
        this.orderID = orderID;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.orderDate = orderDate;
        this.shipmentDate = shipmentDate;
        this.trackingNum = trackingNum;
        this.status = status;
    }

    public OrderData(){

    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(String shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public String getTrackingNum() {
        return trackingNum;
    }

    public void setTrackingNum(String trackingNum) {
        this.trackingNum = trackingNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
