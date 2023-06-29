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
import java.util.List;
import model.Elective;

/**
 *
 * @author TT
 */
public class ElectiveDao extends BaseDao {

    PreparedStatement ps;
    ResultSet rs;

    public List<Elective> getElectiveByCurId(int id, int index, boolean check) {
        List<Elective> e = new ArrayList<Elective>();
        String query = "select * from elective where curriculum_id = ? limit ?, 5;";
        if (check) {
            query = "select * from elective where curriculum_id = ? and is_active = 1 limit ?, 5;";
        }
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.setInt(2, (index - 1) * 5);
            rs = ps.executeQuery();
            while (rs.next()) {
                e.add(new Elective(rs.getInt("elective_id"), rs.getString("code"), rs.getString("name"),
                        rs.getBoolean("is_active"), new CurriculumDao().getCurriculumByID(rs.getInt("curriculum_id")),
                        new SubjectDao().getSubjectByElectiveId(rs.getInt("elective_id"))));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return e;
    }

    public List<Elective> searchElective(String key, int id, boolean check) {
        List<Elective> e = new ArrayList<Elective>();
        String query = "select * from elective "
                + "where name like ? and curriculum_id = ?";
        if (check) {
            query += " and is_active = 1";
        }
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, "%" + key + "%");
            ps.setInt(2, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                e.add(new Elective(rs.getInt("elective_id"), rs.getString("code"), rs.getString("name"),
                        rs.getBoolean("is_active"), new CurriculumDao().getCurriculumByID(rs.getInt("curriculum_id")),
                        new SubjectDao().getSubjectByElectiveId(rs.getInt("elective_id"))));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return e;
    }

    public int getNumberOfElectiveById(int id, boolean check) {
        String query = "select count(*) from elective where curriculum_id = ?";
        if (check) {
            query += " and is_active = 1";
        }
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return 0;
    }

    public void activateOrDeactiveElective(int status, int id) {
        String query = "update elective set is_active = ? where elective_id = ?";
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

    public void deleteElective(int id) {
        String query = "delete from elective where elective_id = ?";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
    }

    public int addElective(String name, String description, int curId) {
        int generatedId = -1; // Giá trị mặc định nếu không có ID được tạo ra
        String query = "insert into elective (code, name, is_active, curriculum_id) \n"
                + "values (?, ?, 1, ?)";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setInt(3, curId);
            ps.executeUpdate();
            // Lấy các khóa sinh tự động được tạo ra
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                generatedId = rs.getInt(1); // Lấy ID từ kết quả ResultSet
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return generatedId;
    }

    public void addElectiveSubject(int eid, int sid) {
        String query = "insert into elective_subject values (?, ?)";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, eid);
            ps.setInt(2, sid);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
    }

    public boolean checkDuplicateElective(String ename, int id, int eid) {
        String query = "select * from elective where code = ? and curriculum_id = ? and elective_id != ?";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, ename);
            ps.setInt(2, id);
            ps.setInt(3, eid);
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

    public Elective getElectiveById(String id) {
        String query = "select * from elective where elective_id = ?;";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Elective(rs.getInt("elective_id"), rs.getString("code"), rs.getString("name"),
                        rs.getBoolean("is_active"), new CurriculumDao().getCurriculumByID(rs.getInt("curriculum_id")),
                        new SubjectDao().getSubjectByElectiveId(rs.getInt("elective_id")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return null;
    }

    public void updateElective(int eid, String ename, String edes, int id, String status) {
        String query = "update elective set code = ?, name = ?, is_active = ?, curriculum_id = ? where elective_id = ?;";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, ename);
            ps.setString(2, edes);
            ps.setString(3, status);
            ps.setInt(4, id);
            ps.setInt(5, eid);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
    }

    public void deleteElectiveSubject(int eid) {
        String query = "delete from elective_subject where elective_id = ?;";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, eid);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
    }
    
}
