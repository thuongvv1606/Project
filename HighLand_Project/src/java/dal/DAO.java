/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.Cart;
import model.Category;
import model.Item;
import model.Orderday;
import model.Orders;
import model.Product;
import model.Staff;

/**
 *
 * @author hp
 */
public class DAO extends DBContext {

    PreparedStatement pstm;
    ResultSet rs;

    public DAO() {
    }

    public ArrayList<Product> getListProduct() {
        ArrayList<Product> data = new ArrayList<>();
        try {
            String strSelect = "select * from Products ";
            pstm = connection.prepareStatement(strSelect);
            rs = pstm.executeQuery();
            while (rs.next()) {

                data.add(new Product(rs.getString(1), rs.getString(2),
                        rs.getDouble(3), rs.getInt(4),
                        rs.getString(5), rs.getInt(6), rs.getBoolean(7)));
            }
        } catch (SQLException e) {
            System.out.println("getListProduct:" + e.getMessage());
        }
        return data;
    }

    public ArrayList<Product> getListProductByCID(String key) {
        ArrayList<Product> list = new ArrayList<>();
        try {
            String strSelect = "select * from Products "
                    + "where CategoryID = ? ";
            pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, key);
            rs = pstm.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getString(1), rs.getString(2),
                        rs.getDouble(3), rs.getInt(4),
                        rs.getString(5), rs.getInt(6), rs.getBoolean(7)));
            }
        } catch (SQLException e) {
            System.out.println("getListProductByCID:" + e.getMessage());
        }
        return list;
    }

    public Product getProductByID(String ID) {
        try {
            String strSelect = "select * from Products "
                    + "where ProductID = ? ";
            pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, ID);
            rs = pstm.executeQuery();
            while (rs.next()) {
                return new Product(rs.getString(1), rs.getString(2),
                        rs.getDouble(3), rs.getInt(4),
                        rs.getString(5), rs.getInt(6), rs.getBoolean(7));
            }
        } catch (SQLException e) {
            System.out.println("ProductByID:" + e.getMessage());
        }
        return null;
    }

    public String getCatgoryID(String ID) {
        try {
            String strSelect = "select * from Products "
                    + "where ProductID = ? ";
            pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, ID);
            rs = pstm.executeQuery();
            while (rs.next()) {
                return rs.getString(6);

            }
        } catch (SQLException e) {
            System.out.println("ProductByID:" + e.getMessage());
        }
        return null;
    }

    public ArrayList<Product> BanChay() {
        ArrayList<Product> list = new ArrayList<>();
        try {
            String strSelect = "select  distinct  TOP(16) Products.ProductID\n"
                    + "                    ,Products.ProductName\n"
                    + "					,Products.UnitPrice \n"
                    + "					,Products.Discontinued, \n"
                    + "                    sum(Quantity * UnitPrice) as TotalAmount\n"
                    + "                    from OrderItems \n"
                    + "					join Products on Products.ProductID = OrderItems.ProductID\n"
                    + "                    group by Products.ProductID\n"
                    + "					,Products.ProductName\n"
                    + "					,Products.UnitPrice\n"
                    + "					,Products.Discontinued\n"
                    + "                    order by TotalAmount DESC";
            pstm = connection.prepareStatement(strSelect);
            rs = pstm.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getString(1), rs.getString(2),
                        rs.getDouble(3), rs.getBoolean(4)));
            }
        } catch (SQLException e) {
            System.out.println("BanChay()" + e.getMessage());
        }
        return list;

    }

    public ArrayList<Category> getListCategory() {
        ArrayList<Category> data = new ArrayList<>();
        try {
            String strSelect = "select * from Categories ";
            pstm = connection.prepareStatement(strSelect);
            rs = pstm.executeQuery();
            while (rs.next()) {

                data.add(new Category(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            System.out.println("getListCategory:" + e.getMessage());
        }
        return data;
    }

    public ArrayList<Product> searchProduct(String key) {
        ArrayList<Product> data = new ArrayList<>();
        try {
            String strSelect = "select TOP(16) * from Products where 1=1 ";
            if (key != null && !key.equals("")) {
                strSelect += " and ProductID like ? or ProductName like ?";
            }
            pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, "%" + key + "%");
            pstm.setString(2, "%" + key + "%");
            rs = pstm.executeQuery();
            while (rs.next()) {
                data.add(new Product(rs.getString(1), rs.getString(2),
                        rs.getDouble(3), rs.getInt(4),
                        rs.getString(5), rs.getInt(6), rs.getBoolean(7)));
            }
        } catch (SQLException e) {
            System.out.println("searchProduct:" + e.getMessage());
        }
        return data;
    }

    public Staff getStaff(String account) {
        try {
            String strSelect = "select * from Staff "
                    + "where account =? ";
            pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, account);
            rs = pstm.executeQuery();
            while (rs.next()) {
                return new Staff(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getBoolean(5), rs.getString(6), rs.getString(7));

            }
        } catch (SQLException e) {
            System.out.println("getStaff:" + e.getMessage());
        }
        return null;
    }

    public Staff login(String account, String password) {
        try {
            String strSelect = "select * from [dbo].[Staff] "
                    + "where account =? "
                    + "and Password =? ";
            pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, account);
            pstm.setString(2, password);
            rs = pstm.executeQuery();
            while (rs.next()) {
                return new Staff(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getBoolean(5),
                        rs.getString(6),
                        rs.getString(7));
            }
        } catch (SQLException e) {
            System.out.println("checkUser:" + e.getMessage());
        }
        return null;
    }

    public void addStaff(String account, String password, String name, String phone,
            String address, boolean gender, String dob) {
        try {
            String strUpdate = "INSERT INTO  Staff "
                    + "VALUES ( ?,?,?,?,?,?,?)";
            pstm = connection.prepareStatement(strUpdate);
            //pstm.setInt(1, Integer.parseInt(productID));
            pstm.setString(1, account);
            pstm.setString(2, password);
            pstm.setString(3, name);
            pstm.setString(4, phone);
            pstm.setBoolean(5, gender);
            pstm.setString(6, address);
//            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            pstm.setString(7, dob);
            pstm.execute();
        } catch (SQLException e) {
            System.out.println("addStaff:" + e.getMessage());
        }
    }

    public boolean checkDuplicatetStaff(String account) {
        try {
            String strSelect = "select * from Staff "
                    + "where account =? ";
            pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, account);
            rs = pstm.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("checkStaff:" + e.getMessage());
        }
        return false;
    }

    public void deleteProduct(String productID) {
        String strSelect = "DELETE FROM [dbo].[Products]\n"
                + "      WHERE ProductID = ?";
        try {
            pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, productID);
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("deleteProduct: " + e);
        }
    }

    public void addProduct(String productID, String productName, double price,
            int stock, String image, int categoryID, boolean discontinued) {
        String strSelect = "INSERT INTO [dbo].[Products]\n"
                + "           ([ProductID]\n"
                + "           ,[ProductName]\n"
                + "           ,[UnitPrice]\n"
                + "           ,[UnitsInStock]\n"
                + "           ,[Image]\n"
                + "           ,[CategoryID]\n"
                + "           ,[Discontinued])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?)";
        try {
            pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, productID);
            pstm.setString(2, productName);
            pstm.setDouble(3, price);
            pstm.setInt(4, stock);
            pstm.setString(5, image);
            pstm.setInt(6, categoryID);
            pstm.setBoolean(7, discontinued);
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("AddProduct: " + e);
        }
    }

    public void EditProduct(String productID, String productName, double price,
            int stock, String image, boolean discontinued) {
        String strSelect = "UPDATE [dbo].[Products]\n"
                + "   SET [ProductName] = ?\n"
                + "      ,[UnitPrice] = ?\n"
                + "      ,[UnitsInStock] = ?\n"
                + "      ,[Image] = ?\n"
                + "      ,[Discontinued] = ?\n"
                + " WHERE  [ProductID] = ?";
        try {
            pstm = connection.prepareStatement(strSelect);
            pstm.setString(6, productID);
            pstm.setString(1, productName);
            pstm.setDouble(2, price);
            pstm.setInt(3, stock);
            pstm.setString(4, image);
            pstm.setBoolean(5, discontinued);
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("EditProduct: " + e);
        }
    }

    public ArrayList<Staff> getAllStaff() {
        ArrayList<Staff> list = new ArrayList<>();
        String StrSelect = "SELECT [account]\n"
                + "      ,[Password]\n"
                + "      ,[name]\n"
                + "      ,[phone]\n"
                + "      ,[gender]\n"
                + "      ,[address]\n"
                + "      ,[dateofbirth]\n"
                + "  FROM [dbo].[Staff]";
        try {
            pstm = connection.prepareStatement(StrSelect);
            rs = pstm.executeQuery();
            while (rs.next()) {
                list.add(new Staff(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getBoolean(5), rs.getString(6), rs.getString(7)));
            }
        } catch (SQLException e) {
            System.out.println("getAllStaff(): " + e);
        }
        return list;
    }

    public static void main(String[] args) {
        DAO dao = new DAO();
        //ArrayList<Product> list = dao.getListProductByCID("1");
//        ArrayList<Category> list = dao.getListCategory();
//        for (Category category : list) {
//            System.out.println(category);
//        }
        ArrayList<Product> P = dao.BanChay();
        for (Product product : P) {
            System.out.println(product);
        }
        dao.editStaff("admin", "pass", "name", "phone", true, "address", "2002-02-02");
        //System.out.println(s);
        //dao.EditProduct("BP002", "productName", 0, 0, "image", true);
        //System.out.println(p);
        int count = dao.getTotalProduct();
        System.out.println(count);

        ArrayList<Staff> list = dao.getAllStaff();
        for (Staff staff : list) {
            System.out.println(staff);
        }
        //dao.addStaff("nhn", "123", "nhuca", "0888160699", "hanoi", false, "02-02-2002");
//        Product p = dao.getListProductByID("TT004");
//        System.out.println(p);
        int a = dao.getOrderlast("2023-03-17");
        System.out.println(a);
    }

    public int getTotalProduct() {
        String strSelect = "select count(*) from [dbo].[Products]";
        try {
            pstm = connection.prepareStatement(strSelect);
            rs = pstm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("getTotalProduct" + e);
        }
        return 0;
    }

    public ArrayList<Product> pagingProduct(int index) {
        ArrayList<Product> list = new ArrayList<>();
        String strSelect = "select * from [dbo].[Products]\n"
                + "ORDER BY [dbo].[Products].[ProductID]\n"
                + "OFFSET ?  ROWS FETCH NEXT 20 ROWS ONLY";
        try {
            pstm = connection.prepareStatement(strSelect);
            pstm.setInt(1, (index - 1) * 20);
            rs = pstm.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getString(1), rs.getString(2),
                        rs.getDouble(3), rs.getInt(4),
                        rs.getString(5), rs.getInt(6), rs.getBoolean(7)));
            }
        } catch (SQLException e) {
            System.out.println("PaginProduct: " + e);
        }
        return list;
    }

    public void editStaff(String account, String pass, String name, String phone,
            boolean gender, String address, String dob) {
        String strUpdate = "UPDATE [dbo].[Staff]\n"
                + "   SET \n"
                + "      [Password] = ?\n"
                + "      ,[name] = ?\n"
                + "      ,[phone] =  ?\n"
                + "      ,[gender] = ?\n"
                + "      ,[address] = ?\n"
                + "      ,[dateofbirth] = ?\n"
                + " WHERE [account] = ?";
        try {
            pstm = connection.prepareStatement(strUpdate);
            pstm.setString(1, pass);
            pstm.setString(2, name);
            pstm.setString(3, phone);
            pstm.setBoolean(4, gender);
            pstm.setString(5, address);
            pstm.setString(6, dob);
//            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            pstm.setString(7, account);
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("updateStaff" + e);
        }
    }

    public void deleteStaff(String account) {
        String strdelete = "DELETE FROM [dbo].[Staff]\n"
                + "      WHERE [account] = ? ";
        try {
            pstm = connection.prepareStatement(strdelete);
            pstm.setString(1, account);
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("deleteStaff: " + e);
        }
    }

    public Orderday listOrderDay(String date) {
        try {
            String str = "SELECT distinct [dbo].OrderItems.OrderID,[dbo].Products.ProductID, [dbo].OrderItems.Quantity, [dbo].Products.UnitPrice,[dbo].Orders.OrderDate\n"
                    + "  FROM [dbo].[Orders]\n"
                    + "  join [dbo].Customers on [dbo].Customers.CustomerID =   [dbo].Orders.CustomerID\n"
                    + "  join [dbo].OrderItems on [dbo].OrderItems.OrderID = [dbo].Orders.OrderID\n"
                    + "  join [dbo].Products on [dbo].Products.ProductID = [dbo].OrderItems.ProductID\n"
                    + "  WHERE [OrderDate] = ? and [dbo].Customers.CustomerID =   [dbo].Orders.CustomerID and [dbo].OrderItems.OrderID = [dbo].Orders.OrderID and  [dbo].Products.ProductID = [dbo].OrderItems.ProductID";
            pstm = connection.prepareStatement(str);
            pstm.setString(1, date);
            rs = pstm.executeQuery();
            while (rs.next()) {
                int orderID = rs.getInt(1);
                String product = rs.getString(2);
                String[] listProductID = null;
                for (String productID : listProductID) {

                }

            }
        } catch (Exception e) {
        }
        return null;
    }

    public ArrayList<Orders> getListOrdersByDay(String day) {
        ArrayList<Orders> list = new ArrayList<>();
        try {
            String str = "SELECT *\n"
                    + "  FROM [HighLand].[dbo].[Orders]\n"
                    + "  WHERE [HighLand].[dbo].[Orders].OrderDate = ?";
            pstm = connection.prepareStatement(str);
            pstm.setString(1, day);
            rs = pstm.executeQuery();
            while (rs.next()) {
                list.add(new Orders(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException e) {
            System.out.println("getListOrdersByDay: " + e);
        }
        return list;
    }

    public int getOrderfirst(String day) {
        try {
            String str = "SELECT TOP(1) [OrderID]\n"
                    + "  FROM [dbo].[Orders]\n"
                    + "  WHERE [HighLand].[dbo].[Orders].OrderDate = ?";
            pstm = connection.prepareStatement(str);
            pstm.setString(1, day);
            rs = pstm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("getListOrdersByDay: " + e);
        }
        return 0;
    }

    public int getOrderlast(String day) {
        try {
            String str = "SELECT TOP(1) [OrderID]\n"
                    + "  FROM [dbo].[Orders]\n"
                    + "  WHERE [HighLand].[dbo].[Orders].OrderDate = ?\n"
                    + "  ORDER BY [OrderID] DESC";
            pstm = connection.prepareStatement(str);
            pstm.setString(1, day);
            rs = pstm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("getListOrdersByDay: " + e);
        }
        return 0;
    }

    public ArrayList<Product> getListProductByDay(int orderID) {
        ArrayList<Product> list = new ArrayList<>();
        try {
            String str = "SELECT [HighLand].[dbo].Products.ProductID,\n"
                    + "       [HighLand].[dbo].Products .ProductName,\n"
                    + "	   [HighLand].[dbo].Products.CategoryID,\n"
                    + "	   [HighLand].[dbo].Products .UnitPrice\n"
                    + "FROM [HighLand].[dbo].OrderItems\n"
                    + "  JOIN [HighLand].[dbo].Products on [HighLand].[dbo].OrderItems.ProductID = [HighLand].[dbo].Products.ProductID\n"
                    + "  WHERE [HighLand].[dbo].OrderItems.OrderID = ?";
            pstm = connection.prepareStatement(str);
            pstm.setInt(1, orderID);
            rs = pstm.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDouble(4)));
            }
        } catch (SQLException e) {
            System.out.println("getListProductByDay " + e);
        }
        return list;
    }

    public Cart getCart(int id) {
        Cart cart = new Cart();
        String str = "SELECT [HighLand].[dbo].Products.ProductID,\n"
                + "       [HighLand].[dbo].Products .ProductName,\n"
                + "	   [HighLand].[dbo].Products.CategoryID,\n"
                + "	   [HighLand].[dbo].Products .UnitPrice\n"
                + "FROM [HighLand].[dbo].OrderItems\n"
                + "  JOIN [HighLand].[dbo].Products on [HighLand].[dbo].OrderItems.ProductID = [HighLand].[dbo].Products.ProductID\n"
                + "  WHERE [HighLand].[dbo].OrderItems.OrderID = ?";
        try {
            pstm = connection.prepareStatement(str);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            while (rs.next()) {
                cart.addItem((Item) new Item(new Product(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDouble(4)), rs.getInt(3), rs.getDouble(4)));
            }
        } catch (SQLException e) {
        }
        return cart;
    }

}
