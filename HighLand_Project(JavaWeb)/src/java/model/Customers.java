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
public class Customers {
    private int customerID;
    private String customerName;
    private String phone;
    private boolean gender;
    private String Address;
    private Date birthdate;

    public Customers() {
    }

    public Customers(int customerID) {
        this.customerID = customerID;
    }
    

    public Customers(int customerID, String customerName, String phone, boolean gender, String Address, Date birthdate) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.phone = phone;
        this.gender = gender;
        this.Address = Address;
        this.birthdate = birthdate;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        return "Customers{" + "customerID=" + customerID + ", customerName=" + customerName + ", phone=" + phone + ", gender=" + gender + ", Address=" + Address + ", birthdate=" + birthdate + '}';
    }
    
}
