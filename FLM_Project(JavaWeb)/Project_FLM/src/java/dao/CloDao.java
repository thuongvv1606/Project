/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import static dao.BaseDao.getConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.Clo;
import model.Po;
import model.Syllabus;
import util.Constants;

/**
 *
 * @author NgTienDung
 */
public class CloDao extends BaseDao {

    PreparedStatement ps;
    ResultSet rs;

    public boolean ckeckCloByName(String name, int idSyllabus) {

        String sql = ""
                + "SELECT * FROM clo WHERE name = ? AND syllabus_id = ?";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, idSyllabus);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return false;
    }

    public Clo getCloById(int id, String active) {

        String sql = ""
                + "SELECT * FROM clo WHERE clo_id = ? AND status like ?";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, "%" + active + "%");
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Clo(rs.getInt("clo_id"), rs.getString("name"),
                        new SyllabusDao().getAllSyllabusById(rs.getInt("syllabus_id")),
                        rs.getString("description"), rs.getBoolean("status"),rs.getInt("parent_lo_id"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return new Clo();
    }
    
    public static void main(String[] args) {
        System.out.println(new CloDao().getCloById(2, "").getCloName());
    }

    public ArrayList<Clo> getAllBySyllabusId(int idSyllabus) {
        ArrayList<Clo> list = new ArrayList<>();
        String sql = ""
                + "SELECT * FROM clo WHERE syllabus_id = ?";
        try {
            Clo clo = null;
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, idSyllabus);
            rs = ps.executeQuery();
            while (rs.next()) {
                clo = new Clo(rs.getInt("clo_id"), rs.getString("name"),
                        new SyllabusDao().getAllSyllabusById(idSyllabus),
                        rs.getString("description"), rs.getBoolean("status"),rs.getInt("parent_lo_id"));
                list.add(clo);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return list;
    }

    public ArrayList<Clo> getAllBySyllabusIdAndPage(int idSyllabus, int index,
            String active, String nameSearch) {
        ArrayList<Clo> list = new ArrayList<>();
        String sql = ""
                + "SELECT * FROM clo WHERE syllabus_id = ? AND status like ?"
                + " AND name like ? "
                + "limit ?,?";
        try {
            Clo clo = null;
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, idSyllabus);
            ps.setString(2, "%" + active + "%");
            ps.setString(3, "%" + nameSearch + "%");
            ps.setInt(4, Constants.PAGE_SIZE * (index - 1));
            ps.setInt(5, Constants.PAGE_SIZE);
            rs = ps.executeQuery();
            while (rs.next()) {
                clo = new Clo(rs.getInt("clo_id"), rs.getString("name"),
                        new SyllabusDao().getAllSyllabusById(idSyllabus),
                        rs.getString("description"), rs.getBoolean("status"),rs.getInt("parent_lo_id"));
                list.add(clo);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return list;
    }

    public int getSizeList(String status, String nameSearch) {

        String sql = ""
                + "SELECT count(*) as co FROM clo WHERE status like ? AND name like ?";
        try {
            Clo clo = null;
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + status + "%");
            ps.setString(2, "%" + nameSearch + "%");
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("co");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return 0;
    }

    public int addHaveParent(Clo clo) {
        String sql = ""
                + "INSERT INTO `clo`\n"
                + "(`name`,`syllabus_id`,`parent_lo_id`,`description`)\n"
                + "VALUES\n"
                + "(?, ?, ?, ?);";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, clo.getCloName());
            ps.setInt(2, clo.getSyllabusId());

            ps.setInt(3, clo.getCloParentId());
            ps.setString(4, clo.getCloDescription());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return -1;
    }

    public int addNoParent(Clo clo) {
        String sql = ""
                + "INSERT INTO `clo`\n"
                + "(`name`,`syllabus_id`,`description`)\n"
                + "VALUES\n"
                + "(?, ?, ?);";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, clo.getCloName());
            ps.setInt(2, clo.getSyllabusId());

            ps.setString(3, clo.getCloDescription());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return -1;
    }

    public void update(Clo clo) {
        String idParent = clo.getCloParentId()== 0 ?"NULL":clo.getCloParentId()+"";
        String sql = ""
                + "UPDATE `clo`\n"
                + "SET \n"
                + "`name` = ?,\n"
                + "`syllabus_id` = ?,\n"
                + "`parent_lo_id` = "+idParent+" ,\n"
                + "`description` = ?\n"
                + "WHERE `clo_id` = ?";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, clo.getCloName());
            ps.setInt(2, clo.getSyllabusId()); 
            ps.setString(3, clo.getCloDescription());
            ps.setInt(4, clo.getCloId());
            ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }

    }

    public void updateActive(int cloId, boolean active) {
        String sql = ""
                + "UPDATE `clo`\n"
                + "SET \n"
                + "`status` = ?\n"
                + "WHERE `clo_id` = ?";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setBoolean(1, active);
            ps.setInt(2, cloId);
            ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }

    }

}
