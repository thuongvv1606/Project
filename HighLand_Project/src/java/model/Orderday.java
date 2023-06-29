/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author hp
 */
public class Orderday {
    private int oderID;
    private String[] productID;
    private int[] quantity;
    private double price;

    public int getOderID() {
        return oderID;
    }

    public void setOderID(int oderID) {
        this.oderID = oderID;
    }

    public String[] getProductID() {
        return productID;
    }

    public void setProductID(String[] productID) {
        this.productID = productID;
    }

    public int[] getQuantity() {
        return quantity;
    }

    public void setQuantity(int[] quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Orderday{" + "oderID=" + oderID + ", productID=" + productID + ", quantity=" + quantity + ", price=" + price + '}';
    }
    
}
