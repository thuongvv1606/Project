/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hp
 */
public class Cart {
    private List<Item> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public Cart(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
    
    
    private Item getItemByID(String id) {
        for (Item i : items) {
            if(i.getProduct().getProductID().equalsIgnoreCase(id)){
                return i;
            }
        }
        return null;
    }
    
    public int getQuantityByID(String id) {
        return getItemByID(id).getQuantity();
    }
    
    //thêm vào giỏ
    public void addItem(Item t) {
        //có o cart rooif
        if(getItemByID(t.getProduct().getProductID()) != null) {
            Item i = getItemByID(t.getProduct().getProductID());
            i.setQuantity(i.getQuantity()+t.getQuantity());
        } else {
            //chưa có
            items.add(t);
        }
    }
    
    public void removeItem(String id) {
        if(getItemByID(id) != null) {
            items.remove(getItemByID(id));
        }
    }
    
    public double getTotalMoney() {
        double t = 0;
        for (Item i : items) {
            t += i.getQuantity() * i.getPrice();
        }
        return t;
    }

    public boolean getQuantityByID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
