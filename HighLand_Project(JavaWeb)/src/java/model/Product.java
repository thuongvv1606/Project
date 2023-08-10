/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author hp
 */
public class Product {
//    [ProductID] [varchar](5) NOT NULL,
//	[ProductName] [nvarchar](50) NULL,
//	[UnitPrice] [money] NULL,
//	[UnitsInStock] [int] NULL,
//	[Image] [nvarchar](max),
//	[CategoryID] [int] NULL,
//	[Discontinued] [bit] NULL,

    private String productID;
    private String productName;
    private double unitPrice;
    private int unitsInStock;
    private String image;
    private int categoryID;
    private boolean discontinued;

    public Product() {
    }

    public Product(String productID, String productName, double unitPrice, boolean discontinued) {
        this.productID = productID;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.discontinued = discontinued;
    }

    public Product(String productID, String productName, double unitPrice, int unitsInStock, String image, int categoryID, boolean discontinued) {
        this.productID = productID;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.unitsInStock = unitsInStock;
        this.image = image;
        this.categoryID = categoryID;
        this.discontinued = discontinued;
    }

    public Product(String productID, String productName, int categoryID, double unitPrice) {
        this.productID = productID;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.categoryID = categoryID;
    }

    public Product(String productID, String productName, double unitPrice) {
        this.productID = productID;
        this.productName = productName;
        this.unitPrice = unitPrice;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(int unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public boolean isDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(boolean discontinued) {
        this.discontinued = discontinued;
    }

    @Override
    public String toString() {
        return "Product{" + "productID=" + productID + ", productName=" + productName + ", unitPrice=" + unitPrice + ", unitsInStock=" + unitsInStock + ", image=" + image + ", categoryID=" + categoryID + ", discontinued=" + discontinued + '}';
    }

    
}
