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
import model.Session;

/**
 *
 * @author trinh
 */
public class SessionDao extends BaseDao{
    PreparedStatement ps;
    ResultSet rs;

    public ArrayList<Session> getAllSessionBy(int id, String search) {
        ArrayList<Session> list = new ArrayList<>();
        String sql = ""
                + "SELECT * FROM session \n"
                + "                where syllabus_id = ? AND name LIKE ? ";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, "%" + search + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Session(rs.getInt("session_id"), rs.getInt("syllabus_id"), rs.getString("name")
                        , rs.getString("details"), rs.getString("learning_type"), rs.getString("student_materials"), rs.getString("student_task")));
            }
        } catch (Exception ex) {
        } finally {
            closeConnection(connection, ps, rs);
        }
        return list;
    }

    public Session getSessionId(int id) {
        Session session = null;
        String query = "SELECT session_id FROM session  where syllabus_id = ?";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                session = new Session();
                session.setSession_id(rs.getInt("session_id"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return session;
    }

    public boolean addSession(String name, int syllabus_id, String detail, String learning, String materials, String task) {
        boolean check = false;
        String query = "INSERT INTO session (syllabus_id, name, details, learning_type, student_materials, student_task)\n"
                + "                VALUES (?, ?, ?, ?, ?, ?);";
        try {

            connection = getConnection();
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, syllabus_id);
            ps.setString(2, name);
            ps.setString(3, detail);
            ps.setString(4, learning);
            ps.setString(5, materials);
            ps.setString(6, task);
            ps.executeUpdate();
            check = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return check;
    }

    public Session getALlSessionById(int id) {
         Session session = null;
        String query = "SELECT * FROM session  where session_id = ?";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                session = new Session();
                session.setSession_id(rs.getInt("session_id"));
                session.setSyllabus_id(rs.getInt("syllabus_id"));
                session.setName(rs.getString("name"));
                session.setDetails(rs.getString("details"));
                session.setLearning_type(rs.getString("learning_type"));
                session.setStudent_materials(rs.getString("student_materials"));
                session.setStudent_task(rs.getString("student_task"));
                
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return session;
    }

    

    public boolean updateSession(String name, String description, int syllabus_id,
            String learning, String materials, String task, int id) {
        boolean f = false;
        String query = "UPDATE session SET name = ?, details = ?, syllabus_id = ?, "
                + "learning_type = ?, student_materials = ?, student_task = ? WHERE session_id = ?;";
        try {

            connection = getConnection();
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, name);
            ps.setString(2, description);
            ps.setInt(3, syllabus_id);
            ps.setString(4, learning);
            ps.setString(5, materials);
            ps.setString(6, task);
            ps.setInt(7, id);
            ps.executeUpdate();
            f = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return f;
    }
    
}
