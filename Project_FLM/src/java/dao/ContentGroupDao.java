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
import model.ContentGroup;

/**
 *
 * @author TT
 */
public class ContentGroupDao extends BaseDao {

    PreparedStatement ps;
    ResultSet rs;

    public int getNumberOfContentGroupById(int id) {
        String query = "select count(*) from subject_group where curriculum_id = ?;";
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

    public List<ContentGroup> getContentgroupByCurId(int id, int index) {
        List<ContentGroup> e = new ArrayList<ContentGroup>();
        String query = "select * from subject_group where curriculum_id = ? limit ?, 5;";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.setInt(2, (index - 1) * 5);
            rs = ps.executeQuery();
            while (rs.next()) {
                e.add(new ContentGroup(rs.getInt("subject_group_id"), new CurriculumDao().getCurriculumByID(rs.getInt("subject_group_id")),
                        rs.getString("group_type"), rs.getString("name"), new SubjectDao().getSubjectByContentgroup(rs.getInt("subject_group_id"))));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return e;
    }

    public void deleteContentgroup(int id) {
        setForeignKeyCheck(0);
        String query = "delete from subject_group where subject_group_id = ?;";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            setForeignKeyCheck(1);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
    }
    
    public void deleteContentgroupSubject(int id) {
        setForeignKeyCheck(0);
        String query = "delete from curriculum_subject where subject_group_id = ?;";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            setForeignKeyCheck(1);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
    }

    public boolean checkDuplicateContentgroup(String cname, int parseInt) {
        String query = "select * from subject_group where name = ? and curriculum_id = ?";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, cname);
            ps.setInt(2, parseInt);
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return false;
    }

    public int addContentgroup(String cname, String ctype, String cur_id) {
        int generatedId = -1; // Giá trị mặc định nếu không có ID được tạo ra
        String query = "insert into subject_group (curriculum_id, group_type, name) values (?, ?, ?)";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, cur_id);
            ps.setString(2, ctype);
            ps.setString(3, cname);
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

    public void addContentgroupSubject(int cur_id, int parseInt, int eid) {
        String query = "insert into curriculum_subject (curriculum_id, subject_id, subject_group_id)\n"
                + "values (?, ?, ?)";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, cur_id);
            ps.setInt(2, parseInt);
            ps.setInt(3, eid);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
    }

    public ContentGroup getContentgroupById(String id) {
        String query = "select * from subject_group where subject_group_id = ?;";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new ContentGroup(rs.getInt("subject_group_id"),
                        new CurriculumDao().getCurriculumByID(rs.getInt("subject_group_id")),
                        rs.getString("group_type"), rs.getString("name"),
                        new SubjectDao().getSubjectByContentgroup(rs.getInt("subject_group_id")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return null;
    }

    public boolean checkDuplicateContentgroup(String cname, int id, int curriculumID) {
        String query = "select * from subject_group where name = ? and subject_group_id != ? and curriculum_id = ?;";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, cname);
            ps.setInt(2, id);
            ps.setInt(3, curriculumID);
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return false;
    }

    public void updateContentgroup(String cname, String ctype, int curriculumID, int id) {
        setForeignKeyCheck(0);
        String query = "update subject_group set name = ?, group_type = ?, curriculum_id = ? where subject_group_id = ?;";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, cname);
            ps.setString(2, ctype);
            ps.setInt(3, curriculumID);
            ps.setInt(4, id);
            ps.executeUpdate();
            setForeignKeyCheck(1);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
    }

    public void deleteContentgroupSubject(int id, int curid) {
        setForeignKeyCheck(0);
        String query = "delete from curriculum_subject where subject_group_id = ? and curriculum_id = ?;";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.setInt(2, curid);
            ps.executeUpdate();
            setForeignKeyCheck(1);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
    }

    public void updateContentgroupSubject(int id, int curid) {
        setForeignKeyCheck(0);
        String query = "update curriculum_subject set curriculum_id = ? where subject_group_id = ?;";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(2, id);
            ps.setInt(1, curid);
            ps.executeUpdate();
            setForeignKeyCheck(1);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
    }

    public void setForeignKeyCheck(int i) {
        String query = "SET FOREIGN_KEY_CHECKS = ?;";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, i);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
    }

    public List<ContentGroup> searchContentgroup(String key, int id) {
        List<ContentGroup> e = new ArrayList<ContentGroup>();
        String query = "select * from subject_group where curriculum_id = ? and (group_type like ? or name like ?);";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.setString(2, "%" + key + "%");
            ps.setString(3, "%" + key + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                e.add(new ContentGroup(rs.getInt("subject_group_id"), new CurriculumDao().getCurriculumByID(rs.getInt("subject_group_id")),
                        rs.getString("group_type"), rs.getString("name"), new SubjectDao().getSubjectByContentgroup(rs.getInt("subject_group_id"))));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return e;
    }
    public static void main(String[] args) {
        ContentGroupDao cDao = new ContentGroupDao();
        List<ContentGroup> e = cDao.searchContentgroup("Gen", 1);
        for (ContentGroup c: e) {
            System.out.println(c.getName());
        }
    }
}
