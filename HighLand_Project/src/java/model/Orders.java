/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author hp
 */
public class Orders {
    private int orderID;
    private String CustomerID;
    private String OrderDate;

    public Orders() {
    }

    public Orders(int orderID, String CustomerID, String OrderDate) {
        this.orderID = orderID;
        this.CustomerID = CustomerID;
        this.OrderDate = OrderDate;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String CustomerID) {
        this.CustomerID = CustomerID;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String OrderDate) {
        this.OrderDate = OrderDate;
    }

    @Override
    public String toString() {
        return "Orders{" + "orderID=" + orderID + ", CustomerID=" + CustomerID + ", OrderDate=" + OrderDate + '}';
    }

   
    

    
}
