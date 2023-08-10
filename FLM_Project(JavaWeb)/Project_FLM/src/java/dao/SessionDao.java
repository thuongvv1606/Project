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
public class SessionDao extends BaseDao {

    PreparedStatement ps;
    ResultSet rs;

    public ArrayList<Session> getAllSessionBy(int id, String search, int index) {
        ArrayList<Session> list = new ArrayList<>();
        String sql = ""
                + "SELECT * FROM session \n"
                + "                where syllabus_id = ? AND LO LIKE ? "
                + "limit ?,5 ";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, "%" + search + "%");
            ps.setInt(3, 5 * (index - 1));
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Session(rs.getInt("session_id"), rs.getInt("syllabus_id"), rs.getString("Topic"), rs.getString("learning_type"),
                        rs.getString("student_materials"), rs.getString("student_task"),
                        rs.getString("LO"), rs.getString("ITU"), rs.getString("URL")));
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
                session.setTopic(rs.getString("Topic"));
                session.setLearning_type(rs.getString("learning_type"));
                session.setItu(rs.getString("ITU"));
                session.setLo(rs.getString("LO"));
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

    public boolean addSession(String topic, String learning, int syllabus_id, String lo, String itu, String materials, String task) {
        boolean check = false;
        String selectQuery = "SELECT COUNT(*) FROM session WHERE Topic = ?";
        String insertQuery = "INSERT INTO session (syllabus_id, Topic, learning_type, ITU, LO, student_materials, student_task)\n"
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            connection = getConnection();

            // Kiểm tra sự trùng lặp
            ps = connection.prepareStatement(selectQuery);
            ps.setString(1, topic);
            rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    // Giá trị "LO" đã tồn tại trong cơ sở dữ liệu
                    check = false;
                } else {
                    // Giá trị "LO" chưa tồn tại trong cơ sở dữ liệu
                    // Tiến hành thêm vào cơ sở dữ liệu
                    ps = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
                    ps.setInt(1, syllabus_id);
                    ps.setString(2, topic);
                    ps.setString(3, learning);
                    ps.setString(4, itu);
                    ps.setString(5, lo);
                    ps.setString(6, materials);
                    ps.setString(7, task);
                    ps.executeUpdate();
                    check = true;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return check;
    }

    public int getSizeList() {
        String sql = ""
                + "SELECT count(*) as count FROM session";
        try {
            Session session = null;
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("count");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return 0;
    }

    public boolean updateSession(String topic, String lo, String itu, String learning, String materials, String task, int id) {
        boolean f = false;
        String query = "UPDATE session SET Topic = ?, learning_type = ?, ITU = ?, "
                + "LO = ?, student_materials = ?, student_task = ? WHERE session_id = ?;";
        try {

            connection = getConnection();
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, topic);
            ps.setString(2, learning);
            ps.setString(3, itu);
            ps.setString(4, lo);
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

    public boolean addSession(Session session) {
        boolean a = false;
        System.out.println(session.toString());
        String query = "INSERT INTO session (syllabus_id, Topic,"
                + " learning_type,ITU, LO,student_materials, student_task, URL)\n"
                + "                VALUES ( ?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, session.getSyllabus_id());
            ps.setString(2, session.getTopic());
            ps.setString(3, session.getLearning_type());
            ps.setString(4, session.getItu());
            ps.setString(5, session.getLo());
            ps.setString(6, session.getStudent_materials());
            ps.setString(7, session.getStudent_task());
            ps.setString(8, session.getUrl());
            ps.executeUpdate();
            a = true;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
//            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return a;

    }
    
    public boolean checkExist(String topic) {
        boolean check = false;
        String selectQuery = "SELECT COUNT(*) FROM session WHERE Topic = ?";
        try {
            connection = getConnection();

            // Kiểm tra sự trùng lặp
            ps = connection.prepareStatement(selectQuery);
            ps.setString(1, topic);
            rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    // Giá trị "Topic" đã tồn tại trong cơ sở dữ liệu
                    check = true;
                } else {
                    check = false;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return check;
    }

    public static void main(String[] args) {
        SessionDao a = new SessionDao();
        Session session = new Session();
        session.setSyllabus_id(0);
        session.setTopic("	Topic 1:Introduction to FEWD and the role of HTML/CSS/JS\n"
                + "- Introduction to front-end web development\n"
                + "- The evolution of Dynamic Websites\n"
                + "- The role of HTML/CSS/JS");
        session.setLearning_type("Offline");
        session.setItu("T,u");
        session.setLo("LO1");
        session.setStudent_materials("oke ngon");
        session.setStudent_task("abc oke ");
        session.setUrl("");
        System.out.println(a.addSession(session));
    }

}
