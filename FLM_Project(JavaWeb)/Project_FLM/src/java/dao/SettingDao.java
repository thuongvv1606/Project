/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Setting;

/**
 *
 * @author HaPi
 */
public class SettingDao extends BaseDao {

    PreparedStatement ps = null;
    ResultSet rs = null;

    public String lastQueryString;

    //query everything
    public List<Setting> querySetting(String title, String type, int status, int index) {

        List<Setting> listSetting = new ArrayList<>();
        int offset = (index - 1) * 5;
        String query = "select * from setting where title LIKE '%" + title + "%'";
        if (!type.equalsIgnoreCase("*")) {
            query = query + " and type = '" + type + "'";
        }
        if (status != -1) {
            query = query + " and status = " + status;
        }
        query = query + " LIMIT " + offset + ",5;";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                listSetting.add(new Setting(rs.getInt("setting_id"), rs.getString("type"), rs.getString("title"), rs.getString("value"), rs.getString("details"), rs.getInt("status"), rs.getInt("display_order")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        lastQueryString = query;
        return listSetting;
    }

    //get total result based on last executed query
    public int getCount() {
        //remove Limit and 'SELECT *' from last query string
        System.out.println("last: " + lastQueryString);

        String query = lastQueryString.substring(8);
        query = query.substring(0, query.indexOf("LIMIT") - 1);
        query = "select count(*) as number" + query;
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("count:" + query);
                return rs.getInt("number");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        System.out.println("count:" + query);
        return 0;
    }

    //update status
    public void updateStatus(int id, int status) {

        String query = "UPDATE `setting` SET status = ? WHERE setting_id = ?";
        try {

            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, status);
            ps.setInt(2, id);
            ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
    }

    public Setting getSettingById(int id) {

        Setting setting = null;
        String query = "select * from setting where setting_id = ? ";
        try {

            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                setting = new Setting();
                setting.setId(id);
                setting.setType(rs.getString("type"));
                setting.setValue(rs.getString("value"));
                setting.setTitle(rs.getString("title"));
                setting.setDetails(rs.getString("details"));
                setting.setStatus(rs.getInt("status"));
                setting.setDisplayOrder(rs.getInt("display_order"));
            };
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return setting;
    }

    // update 
    public void updateSetting(int id, String title, String value, int status, String details, int displayOrder) {

        String query = "UPDATE `setting` SET title = ? , value = ?, status = ? , details = ?, display_order = ? WHERE setting_id = ?";
        try {

            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, title);
            ps.setString(2, value);
            ps.setInt(3, status);
            ps.setString(4, details);
            ps.setInt(5, displayOrder);
            ps.setInt(6, id);
            ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
    }

    public void addNew(String title, String type, String value, int status, String details, int displayOrder) {

        String query = "INSERT INTO `setting`(`type`, `title`, `value`, `details`, `status` , `display_order`) VALUES (?, ?, ?, ?, ?, ?)";
        try {

            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, type);
            ps.setString(2, title);
            ps.setString(3, value);
            ps.setString(4, details);
            ps.setInt(5, status);
            ps.setInt(6, displayOrder);
            ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
    }

    public List<String> getTypes() {

        String query = "SELECT distinct `type` from setting";
        List<String> types = new ArrayList<>();
        try {

            connection = getConnection();
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                types.add(rs.getString("type"));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return types;
    }

    public List<String> getValuesByType(String type) {

        List<String> values = new ArrayList<>();
        String query = "select value from setting where type = '" + type + "'";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                values.add(rs.getString("value"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return values;
    }

    public void updateSetting(int id, String title, String value, int status, String details, String type, int displayOrder) {

        String query = "UPDATE `setting` "
                + "SET title = ? , value = ?, status = ? , details = ? , type = ? , display_order = ? "
                + "WHERE setting_id = ?";
        try {

            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, title);
            ps.setString(2, value);
            ps.setInt(3, status);
            ps.setString(4, details);
            ps.setString(5, type);
             ps.setInt(6, displayOrder);
            ps.setInt(7, id);
            ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
    }

public List<Setting> getAll(String title, String type, int status) {

        List<Setting> listSetting = new ArrayList<>();
       
        String query = "select * from setting where title LIKE '%" + title + "%'";
        if (!type.equalsIgnoreCase("")) {
            query = query + " and type = '" + type + "'";
        }
        if (status != -1) {
            query = query + " and status = " + status;
        }
      
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                listSetting.add(
                        new Setting(rs.getInt("setting_id"),
                                rs.getString("type"),
                                rs.getString("title"),
                                rs.getString("value"),
                                rs.getString("details"), 
                                rs.getInt("status"), rs.getInt("display_order")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        lastQueryString = query;
        return listSetting;
    }

}
