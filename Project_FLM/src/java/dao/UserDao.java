package dao;

import static controller.authentication.LoginGoogleController.getToken;
import static controller.authentication.LoginGoogleController.getUserInfo;
import static dao.BaseDao.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.User;
import model.UserGoogleDto;
import util.BCryptCoder;

/**
 *
 * @author hp
 */
public class UserDao extends BaseDao {

    PreparedStatement ps;
    ResultSet rs;

    public static void main(String[] args) {
        UserDao u = new UserDao();
        String email = "thuongvvhs160715@fpt.edu.vn";
        u.registerUser(email);
        int index = email.indexOf("@");
        String name = email.substring(0, index);
        System.out.println(name);
    }
    
    public void registerUser(String email){
        int index = email.indexOf("@");
        String name = email.substring(0, index);
        String encrypted = BCryptCoder.encoder("admin123@");
        String query = "INSERT INTO  `user`"
                + " (`full_name` , `user_name` , `email` , `password`, `status`) \n"
                + "VALUES (?, ?, ?, ?, 1)";
        try {
             connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, name);
            ps.setString(3, email);
            ps.setString(4, encrypted);
            ps.executeUpdate();
            rs = ps.executeQuery();
        } catch (Exception ex) {
            System.out.println("lỗi");
        } finally {
            closeConnection(connection, ps, rs);
        }
        //return generatedId;
    }
    
    public boolean checkStatus(User user) {
        String query = "select status from user where user_name = ?";
        try {

            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getUserName());
            rs = ps.executeQuery();
            while (rs.next()) {
                if(rs.getBoolean("status") == true){
                    return true;
                }else {
                    return false;
                }
                        
            }
        } catch (Exception ex) {
            System.out.println("Loi: " + ex.getMessage());
        } finally {
            closeConnection(connection, ps, rs);
        }
        return false;
    }

    public ArrayList<User> getListUser() {
        ArrayList<User> data = new ArrayList<>();

        String query = "select * from user";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                data.add(new User(rs.getString("user_name"), rs.getString("mobile"),
                        rs.getString("full_name"), rs.getString(4), rs.getString(5), rs.getBoolean("status")));
            }
        } catch (Exception ex) {
        } finally {
            closeConnection(connection, ps, rs);
        }
        return data;
    }

    public User getUser(String userName, String passwordInput) {
        //User user = null;
        String password = BCryptCoder.encoder(passwordInput);
        String query = "select * from user where user_name = ? and password = ? or mobile = ? and password = ? or email = ? and password = ?";
        try {

            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, userName);
            ps.setString(2, password);
            ps.setString(3, userName);
            ps.setString(4, password);
            ps.setString(5, userName);
            ps.setString(6, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setFullName(rs.getString("full_name"));
                user.setUserName(rs.getString("user_name"));
                user.setEmail(rs.getString("email"));
                user.setMobile(rs.getString("mobile"));
                user.setPassword(rs.getString("password"));
                user.setActive(rs.getBoolean("status"));
                user.setTitle(rs.getString("title"));
                user.setCompany(rs.getString("company"));
                user.setAvatar(rs.getString("avatar_id"));
                user.setRoles(new RoleDao().getRolesByUserId(user.getUserId()));
                return user;
            };
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return null;
    }

    public User getUser(int id) {
        Connection conn = null;
        User user = null;
        String query = "select * from user where user_id = ?";
        try {
            conn = getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUserName(rs.getString("user_name"));
                user.setMobile(rs.getString("mobile"));
                user.setEmail(rs.getString("email"));
                user.setFullName(rs.getString("full_name"));
                user.setActive(rs.getBoolean("status"));
                user.setCompany(rs.getString("company"));
                user.setTitle(rs.getString("title"));
                user.setRoles(new RoleDao().getRolesByUserId(user.getUserId()));
            }
        } catch (Exception ex) {
        } finally {
            closeConnection(conn, ps, rs);
        }
        return user;
    }

    public User getUserByPhone(String mobile) {

        //User user = null;
        String query = "select * from user where mobile = ? ";
        try {

            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, mobile);
            rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setFullName(rs.getString("full_name"));
                user.setUserName(rs.getString("user_name"));
                user.setEmail(rs.getString("email"));
                user.setMobile(rs.getString("mobile"));
                user.setPassword(rs.getString("password"));
                user.setActive(rs.getBoolean("status"));
                user.setTitle(rs.getString("title"));
                user.setCompany(rs.getString("company"));
                user.setAvatar(rs.getString("avatar_id"));
                user.setRoles(new RoleDao().getRolesByUserId(user.getUserId()));
                return user;
            }
        } catch (Exception ex) {
        } finally {
            closeConnection(connection, ps, rs);
        }
        return null;
    }

    //get user by email
    public User getUserByEmail(String email) {

        //User user = null;
        String query = "select * from user where email = ? ";
        try {

            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setFullName(rs.getString("full_name"));
                user.setUserName(rs.getString("user_name"));
                user.setEmail(rs.getString("email"));
                user.setMobile(rs.getString("mobile"));
                user.setPassword(rs.getString("password"));
                user.setActive(rs.getBoolean("status"));
                user.setTitle(rs.getString("title"));
                user.setCompany(rs.getString("company"));
                user.setAvatar(rs.getString("avatar_id"));
                user.setRoles(new RoleDao().getRolesByUserId(user.getUserId()));
                return user;
            }
        } catch (Exception ex) {
        } finally {
            closeConnection(connection, ps, rs);
        }
        return null;
    }

    public void resetPassword(int email, String password) {
        String encrypted = BCryptCoder.encoder(password);
        String query = "UPDATE g5_flm.user SET password = ? WHERE user_id = ?;";
        try {

            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, encrypted);
            ps.setInt(2, email);
            ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
    }

    public int getNumberOfUser() {
        try {
            String query = "select count(*) from user;";
            connection = getConnection();
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("getNumberOfUser: " + e.getMessage());
        }
        return 0;
    }

    public ArrayList<User> searchUser(String key) {
        ArrayList<User> uList = new ArrayList<>();
        try {
            String query = "select distinct user.*, file_name from user left join avatar on user.avatar_id = avatar.avatar_id\n"
                    + "join user_role on user.user_id = user_role.user_id join role on role.role_id = user_role.role_id\n"
                    + "where user_name like ? or full_name like ?\n"
                    + "or email like ? or mobile like ?;";
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, "%" + key + "%");
            ps.setString(2, "%" + key + "%");
            ps.setString(3, "%" + key + "%");
            ps.setString(4, "%" + key + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                uList.add(new User(rs.getInt("user_id"), rs.getString("full_name"), rs.getString("user_name"),
                        rs.getString("email"), rs.getString("mobile"), rs.getString("password"),
                        rs.getBoolean("status"), rs.getString("title"), rs.getString("company"),
                        rs.getString("file_name"), new RoleDao().getRolesByUserId(rs.getInt("user_id"))));
            }
        } catch (Exception e) {
            System.out.println("searchUser:" + e.getMessage());
        }
        return uList;
    }

    public List<User> getAllUser(int index) {
        ArrayList<User> uList = new ArrayList<>();
        try {
            String query = "select user.*, file_name from user left join avatar on user.avatar_id = avatar.avatar_id"
                    + " limit ?, 5;";
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, (index - 1) * 5);
            rs = ps.executeQuery();
            while (rs.next()) {
                uList.add(new User(rs.getInt("user_id"), rs.getString("full_name"), rs.getString("user_name"),
                        rs.getString("email"), rs.getString("mobile"), rs.getString("password"),
                        rs.getBoolean("status"), rs.getString("title"), rs.getString("company"),
                        rs.getString("file_name"), new RoleDao().getRolesByUserId(rs.getInt("user_id"))));
            }
        } catch (Exception e) {
            System.out.println("getAllUser: " + e.getMessage());
        }
        return uList;
    }

    public boolean checkUserExit(User user) {
        String query = "select * from user"
                + " where email = ? OR user_name = ? ";
        try {

            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getUserName());
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

    public int registerUser(User user) {
        int generatedId = -1; // Giá trị mặc định nếu không có ID được tạo ra
        String encrypted = BCryptCoder.encoder(user.getPassword());
        String query = "INSERT INTO  `user`"
                + " (`full_name` , `user_name` , `email` , `password`, `status`, mobile) \n"
                + "VALUES (?, ?, ?, ?, 1, ?)";
        try {

            connection = getConnection();
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, user.getFullName());
            ps.setString(2, user.getUserName());
            ps.setString(3, user.getEmail());
            ps.setString(4, encrypted);
            ps.setString(5, user.getMobile());
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

    public void activateOrDeactiveUser(int userId, int status) {
        User u = getUser(userId);
        String query = "update user \n"
                + "set status = ?\n"
                + "where user_id = ?";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, status);
            ps.setInt(2, userId);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
    }

    public boolean updateUserProfile(User user) {
        boolean update = false;
        String query = "update user set full_name = ?"
                + ", user_name = ?, email = ?, mobile = ?"
                + ", title = ?, company = ? \n"
                + "where user_id = ?";
        try {

            connection = getConnection();
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, user.getFullName());
            ps.setString(2, user.getUserName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getMobile());
            ps.setString(5, user.getTitle());
            ps.setString(6, user.getCompany());
            ps.setInt(7, user.getUserId());
            ps.executeUpdate();
            update = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return update;
    }

    public String getPassword(int user) {
        String pass = "";
        String query = "select password from user where user_id= ?";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, user);
            rs = ps.executeQuery();
            while (rs.next()) {
                pass = rs.getString("password");
            }

        } catch (Exception ex) {
            System.out.println("getPassword " + ex.getMessage());
        } finally {
            closeConnection(connection, ps, rs);
        }
        return pass;
    }

    public boolean changePassWord(User user, String pass) {
        boolean f = false;
        String password = BCryptCoder.encoder(pass);
        String query = "update  user "
                + "set password = ? "
                + " where user_id= ? "
                + " ";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, password);
            ps.setInt(2, user.getUserId());
            ps.executeUpdate();
            f = true;
        } catch (Exception e) {
            System.out.println("changePassWord " + e.getMessage());
        }
        return f;
    }

    public List<User> getUserByRole(int role) {
        ArrayList<User> uList = new ArrayList<>();
        try {
            String query = "select distinct user.*, file_name from user left join avatar on user.avatar_id = avatar.avatar_id \n"
                    + "join user_role on user.user_id = user_role.user_id where role_id = ?";
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, role);
            rs = ps.executeQuery();
            while (rs.next()) {
                uList.add(new User(rs.getInt("user_id"), rs.getString("full_name"), rs.getString("user_name"),
                        rs.getString("email"), rs.getString("mobile"), rs.getString("password"),
                        rs.getBoolean("status"), rs.getString("title"), rs.getString("company"),
                        rs.getString("file_name"), new RoleDao().getRolesByUserId(rs.getInt("user_id"))));
            }
        } catch (Exception e) {
            System.out.println("getUserByRole: " + e.getMessage());
        }
        return uList;
    }

    public List<User> getUserByStatus(int status) {
        ArrayList<User> uList = new ArrayList<>();
        try {
            String query = "select user.*, file_name from user left join avatar on user.avatar_id = avatar.avatar_id \n"
                    + "where status = ?";
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, status);
            rs = ps.executeQuery();
            while (rs.next()) {
                uList.add(new User(rs.getInt("user_id"), rs.getString("full_name"), rs.getString("user_name"),
                        rs.getString("email"), rs.getString("mobile"), rs.getString("password"),
                        rs.getBoolean("status"), rs.getString("title"), rs.getString("company"),
                        rs.getString("file_name"), new RoleDao().getRolesByUserId(rs.getInt("user_id"))));
            }
        } catch (Exception e) {
            System.out.println("getUserByStatus: " + e.getMessage());
        }
        return uList;
    }

    public List<User> sortUserList(int order) {
        ArrayList<User> uList = new ArrayList<>();
        try {
            String query = "select user.*, file_name from user left join avatar on user.avatar_id = avatar.avatar_id \n"
                    + "order by user_id";
            if (order == 2) {
                query += " desc";
            }
            connection = getConnection();
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                uList.add(new User(rs.getInt("user_id"), rs.getString("full_name"), rs.getString("user_name"),
                        rs.getString("email"), rs.getString("mobile"), rs.getString("password"),
                        rs.getBoolean("status"), rs.getString("title"), rs.getString("company"),
                        rs.getString("file_name"), new RoleDao().getRolesByUserId(rs.getInt("user_id"))));
            }
        } catch (Exception e) {
            System.out.println("sortUserList: " + e.getMessage());
        }
        return uList;
    }

    public User getUserById(User user) {
        User u = null;
        String query = "select * from user where user_id = ? ";
        try {

            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, user.getUserId());
            rs = ps.executeQuery();
            if (rs.next()) {
                String fullName = rs.getString("full_name");
                String userName = rs.getString("user_name");
                String email = rs.getString("email");
                String mobile = rs.getString("mobile");
                String title = rs.getString("title");
                String company = rs.getString("company");
                u = new User(fullName, userName, email, mobile, title, company);
            }
        } catch (Exception ex) {
        } finally {
            closeConnection(connection, ps, rs);
        }
        return u;
    }

    public boolean checkOldPass(User user, String oldPass) {
        boolean f = false;
        String password = BCryptCoder.encoder(oldPass);
        String query = "select password from user where user_id = ? and password = ?";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, user.getUserId());
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                f = true;
            }
        } catch (Exception e) {
            System.out.println("checkOldPass " + e.getMessage());
        }
        return f;
    }

    public List<User> filterUser(String role, String status) {
        ArrayList<User> uList = new ArrayList<>();
        try {
            String query = "select distinct user.*, file_name from user left join avatar on user.avatar_id = avatar.avatar_id \n"
                    + "join user_role on user.user_id = user_role.user_id where";
            if (!role.equals("")) {
                query += " role_id = ?";
            }
            if (!status.equals("-1")) {
                if (!role.equals("")) {
                    query += " and status = ?";
                } else {
                    query += " status = ?";
                }
            }
            if (role.equals("") && status.equals("-1")) {
                query = "select distinct user.*, file_name from user left join avatar on user.avatar_id = avatar.avatar_id \n"
                        + "join user_role on user.user_id = user_role.user_id";
            }
            connection = getConnection();
            ps = connection.prepareStatement(query);
            if (!role.equals("")) {
                ps.setInt(1, Integer.parseInt(role));
            }
            if (!status.equals("-1")) {
                if (!role.equals("")) {
                    ps.setInt(2, Integer.parseInt(status));
                } else {
                    ps.setInt(1, Integer.parseInt(status));
                }
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                uList.add(new User(rs.getInt("user_id"), rs.getString("full_name"), rs.getString("user_name"),
                        rs.getString("email"), rs.getString("mobile"), rs.getString("password"),
                        rs.getBoolean("status"), rs.getString("title"), rs.getString("company"),
                        rs.getString("file_name"), new RoleDao().getRolesByUserId(rs.getInt("user_id"))));
            }
        } catch (Exception e) {
            System.out.println("filterUser: " + e.getMessage());
        }
        return uList;
    }

    public boolean checkDuplicateEmail(String email, int i) {
        try {
            String query = "select * from user where email = ? and user_id != ?";
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ps.setInt(2, i);
            rs = ps.executeQuery();
            if (rs.next()) {
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return true;
    }

    public boolean checkDuplicateUsername(String username, int i) {
        try {
            String query = "select * from user where user_name = ? and user_id != ?";
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ps.setInt(2, i);
            rs = ps.executeQuery();
            if (rs.next()) {
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return true;
    }

    public boolean checkDuplicatePhone(String phone, int i) {
        try {
            String query = "select * from user where mobile = ? and user_id != ?";
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, phone);
            ps.setInt(2, i);
            rs = ps.executeQuery();
            if (rs.next()) {
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection, ps, rs);
        }
        return true;
    }

    public int addUser(String fullname, String username, String email, String phone, String title, String company) {
        int generatedId = -1; // Giá trị mặc định nếu không có ID được tạo ra
        String encrypted = BCryptCoder.encoder("acc@1234");
        String query = "INSERT INTO user"
                + " (full_name , user_name , email , password, mobile, title, company) \n"
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, fullname);
            ps.setString(2, username);
            ps.setString(3, email);
            ps.setString(4, encrypted);
            ps.setString(5, phone);
            ps.setString(6, title);
            ps.setString(7, company);
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

    public void deleteUserRole(int userId) {
        String query = "delete from user_role where user_id = ?;";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, userId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("deleteUserRole " + e.getMessage());
        }
    }

    public void updateUserRole(int userId, int roleId) {
        String query = "insert into user_role values (?, ?, 1);";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, roleId);
            ps.setInt(2, userId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("updateUserRole " + e.getMessage());
        }
    }
}
