/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hp
 */
public class ListCart {
    private List<Cart> cart;
    private int oID;
    private String date;

    public ListCart() {
        cart = new ArrayList<>();
    }

    public ListCart(List<Cart> cart, int oID, String date) {
        this.cart = cart;
        this.oID = oID;
        this.date = date;
    }

    public List<Cart> getCart() {
        return cart;
    }

    public void setCart(List<Cart> cart) {
        this.cart = cart;
    }

    public int getoID() {
        return oID;
    }

    public void setoID(int oID) {
        this.oID = oID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    public void addListCart (Cart t) {
        cart.add(t);
    }
}
