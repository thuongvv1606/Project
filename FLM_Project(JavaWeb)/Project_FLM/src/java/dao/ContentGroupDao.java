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
        String query = "select count(*) from subject_group where curriculum_id = ? and group_type = 'content group';";
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
        String query = "select * from subject_group where curriculum_id = ?  and group_type = 'content group' order by display_order limit ?, 5;";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.setInt(2, (index - 1) * 5);
            rs = ps.executeQuery();
            while (rs.next()) {
                e.add(new ContentGroup(rs.getInt("subject_group_id"), new CurriculumDao().getCurriculumByID(rs.getInt("subject_group_id")),
                        rs.getString("group_type"), rs.getString("name"), rs.getString("english_name"), new SubjectDao().getSubjectByContentgroup(rs.getInt("subject_group_id")),
                        rs.getInt("display_order")));
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
        String query = "update curriculum_subject set subject_group_id = null where subject_group_id = ?;";
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

    public int addContentgroup(String cname, String ename, String order, String cur_id) {
        int generatedId = -1; // Giá trị mặc định nếu không có ID được tạo ra
        String query = "insert into subject_group (curriculum_id, group_type, english_name, name, display_order) values (?, 'content group', ?, ?, ?)";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, cur_id);
            ps.setString(2, ename);
            ps.setString(3, cname);
            ps.setString(4, order);
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
        String query = "select * from subject_group where subject_group_id = ?  and group_type = 'content group';";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new ContentGroup(rs.getInt("subject_group_id"), new CurriculumDao().getCurriculumByID(rs.getInt("subject_group_id")),
                        rs.getString("group_type"), rs.getString("name"), rs.getString("english_name"), new SubjectDao().getSubjectByContentgroup(rs.getInt("subject_group_id")),
                        rs.getInt("display_order"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return null;
    }

    public boolean checkDuplicateContentgroup(String cname, int id, int curriculumID) {
        String query = "select * from subject_group where name = ? and subject_group_id != ? and curriculum_id = ?  and group_type = 'content group';";
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

    public void updateContentgroup(String cname, String ename, String order, int id) {
        setForeignKeyCheck(0);
        String query = "update subject_group set name = ?, english_name = ?, display_order = ? where subject_group_id = ?;";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, cname);
            ps.setString(2, ename);
            ps.setString(3, order);
            ps.setInt(4, id);
            ps.executeUpdate();
            setForeignKeyCheck(1);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
    }

    public void deleteContentgroupSubject(int id, int curid, int cgid) {
        setForeignKeyCheck(0);
        String query = "update curriculum_subject set subject_group_id = null where subject_id = ? and "
                + "curriculum_id = ? and subject_group_id = ?;";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.setInt(2, curid);
            ps.setInt(3, cgid);
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
        String query = "select * from subject_group where curriculum_id = ? and (group_type like ? or name like ?) and group_type = 'content group' order by display_order;";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.setString(2, "%" + key + "%");
            ps.setString(3, "%" + key + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                e.add(new ContentGroup(rs.getInt("subject_group_id"), new CurriculumDao().getCurriculumByID(rs.getInt("subject_group_id")),
                        rs.getString("group_type"), rs.getString("name"), rs.getString("english_name"), new SubjectDao().getSubjectByContentgroup(rs.getInt("subject_group_id")),
                        rs.getInt("display_order")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return e;
    }

    public boolean checkDuplicateDisplayOrder(String order, int id, int cur_id) {
        String query = "select * from subject_group where display_order = ? and subject_group_id != ? and curriculum_id = ?  and group_type = 'content group';";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, order);
            ps.setInt(2, id);
            ps.setInt(3, cur_id);
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
}
