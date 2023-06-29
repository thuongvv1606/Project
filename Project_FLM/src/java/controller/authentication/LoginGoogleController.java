/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.authentication;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dao.RoleDao;
import dao.SettingDao;
import dao.UserDao;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Role;
import model.User;
import model.UserGoogleDto;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import util.Constants;

/**
 *
 * @author hp
 */
@WebServlet(name = Constants.URL_LOGIN_GOOGLE, urlPatterns = {Constants.URL_LOGIN_GOOGLE})
public class LoginGoogleController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String code = request.getParameter("code");
        if (code == null || code.isEmpty()) {
            request.getRequestDispatcher(Constants.URL_LOGIN).forward(request, response);
        } else {
            String accessToken = getToken(code);
            UserGoogleDto u = getUserInfo(accessToken);
            String email = u.getEmail();
            UserDao userDao = new UserDao();
            User user = userDao.getUserByEmail(email);
            jakarta.servlet.http.HttpSession session = request.getSession();
            session.removeAttribute("user");
            SettingDao settingDao = new SettingDao();
            System.out.println(user);
            if (user == null) {
                //check setting email domain
                List<String> emailDomains = settingDao.getValuesByType("Email Domain");
                boolean schoolEmailDomain = false;
                for (String emailDomain : emailDomains) {
                    System.out.println(emailDomain);
                    if (email.endsWith(emailDomain)) {
                        schoolEmailDomain = true;
                        break;
                    }
                }
                if (schoolEmailDomain) {
                    userDao.registerUser(email);
                    user = userDao.getUserByEmail(email);
                    RoleDao roleDao = new RoleDao();
                    List<Role> roleList = roleDao.getRolesByUserId(user.getUserId());
                    user.setRoles(roleList);
                    session.setAttribute("user", user);
                    request.getRequestDispatcher(Constants.URL_HOMEPAGE).forward(request, response);

                } else {
                    request.setAttribute("mess", "This Google email is not authorized");
                    request.getRequestDispatcher(Constants.URL_LOGIN).forward(request, response);
                }
            } else {
                RoleDao roleDao = new RoleDao();
                List<Role> roleList = roleDao.getRolesByUserId(user.getUserId());
                user.setRoles(roleList);
                session.setAttribute("user", user);
                request.getRequestDispatcher(Constants.URL_HOMEPAGE).forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public static String getToken(String code) throws ClientProtocolException, IOException {
        // call api to get token
        String response = Request.Post(Constants.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form().add("client_id", Constants.GOOGLE_CLIENT_ID)
                        .add("client_secret", Constants.GOOGLE_CLIENT_SECRET)
                        .add("redirect_uri", Constants.GOOGLE_REDIRECT_URI).add("code", code)
                        .add("grant_type", Constants.GOOGLE_GRANT_TYPE).build())
                .execute().returnContent().asString();

        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

    public static UserGoogleDto getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = Constants.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();

        UserGoogleDto googlePojo = new Gson().fromJson(response, UserGoogleDto.class);

        return googlePojo;
    }
}
