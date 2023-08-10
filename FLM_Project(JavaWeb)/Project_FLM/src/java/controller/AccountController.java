/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.RoleDao;
import dao.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import model.Role;
import model.User;
import util.Constants;

/**
 *
 * @author TT
 */
@WebServlet(name = Constants.URL_ACCOUNT, urlPatterns = {Constants.URL_ACCOUNT})
public class AccountController extends HttpServlet {

    public void authorization(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        jakarta.servlet.http.HttpSession session = req.getSession();
        if (session.getAttribute("user") == null) {
            resp.setContentType("text/html;charset=UTF-8");
            try ( PrintWriter out = resp.getWriter()) {
                out.print("<!DOCTYPE html>");
                out.print("<html>");
                out.print("<head>");
                out.print("</head>");
                out.print("<body>");
                out.print("<br><br><br><br>");
                
                out.print("<p style=\"text-align: center\"><img style=\"width: 300px;\" src=\"assets/img/error1.jpg\" alt=\"alt\"/> </p>");
                out.print("<h1 style=\"text-align: center\">YOU HAVEN'T SIGN IN!!!</h1>");
                out.print("<h1><a href=\"home\">Home</a></h1>");
                out.print("</body>");
                out.print("</html>");
            }
        }else{
            resp.setContentType("text/html;charset=UTF-8");
            User user = (User) session.getAttribute("user");
            if(user.getRoleFunction() != 2){
                try (PrintWriter out = resp.getWriter()) {
                out.print("<!DOCTYPE html>");
                out.print("<html>");
                out.print("<head>");
                out.print("</head>");
                out.print("<body>");
                out.print("<br><br><br><br>");
                
                out.print("<p style=\"text-align: center\"><img style=\"width: 300px;\" src=\"assets/img/error1.jpg\" alt=\"alt\"/> </p>");
                out.print("<h1 style=\"text-align: center\">YOU DON'T HAVE AUTHORIZATION TO ACCESS THIS SCREEN!!!</h1>");
                out.print("<h1><a href=\"home\">Home</a></h1>");
                out.print("</body>");
                out.print("</html>");
            }
            }
        }
    }
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        jakarta.servlet.http.HttpSession session = req.getSession();
        authorization(req, resp);
        UserDao uDao = new UserDao();
        String indexPage = req.getParameter("index");
        if (indexPage == null) {
            indexPage = "1";
        }
        int index = Integer.parseInt(indexPage);
        List<User> accList = uDao.getAllUser(index);
        int count = uDao.getNumberOfUser();
        int endPage = count / 5;
        if (count % 5 != 0) {
            endPage++;
        }
        RoleDao rDao = new RoleDao();
        List<Role> rList = rDao.getAll();
        req.setAttribute("rList", rList);
        if (req.getParameter("action") == null) {
            if (req.getParameter("search_btn") != null) {
                String filterByRole = req.getParameter("filterByRole");
                String filterByStatus = req.getParameter("filterByStatus");
                String key = req.getParameter("key");
                if (key == null) {
                    key = "";
                }
                req.setAttribute("key", key);
                req.setAttribute("filterByRole", filterByRole);
                req.setAttribute("filterByStatus", filterByStatus);
                endPage = 0;
                if (filterByRole.equals("0") && filterByStatus.equals("-1")) {
                    accList = uDao.getAllUser(index);
                    count = accList.size();
                } else {
                    accList = uDao.filterUser(key, filterByRole, filterByStatus);
                    count = accList.size();
                }
            }
            req.setAttribute("end", endPage);
            req.setAttribute("index", index);
            req.setAttribute("count", count);
            req.setAttribute("accList", accList);
            req.getRequestDispatcher("/view/admin/accountList.jsp").forward(req, resp);
        } else {
            String id = req.getParameter("userId");
            session.setAttribute("userId", id);
            if (id != null) {
                User acc = uDao.getUser(Integer.parseInt(id));
                session.setAttribute("acc", acc);
            }
            switch (req.getParameter("action")) {
                case "detail":
                    String userId = (String) session.getAttribute("userId");
                    User acc = uDao.getUser(Integer.parseInt(userId));
                    req.setAttribute("acc", acc);
                    req.getRequestDispatcher("/view/admin/accountDetail.jsp").forward(req, resp);
                    break;
                case "edit":
                    req.getRequestDispatcher("/view/admin/accountEdit.jsp").forward(req, resp);
                    break;
                case "add":
                    add(req, resp);
                    break;
                default:
                    if (req.getParameter("action").equals("activate")) {
                        uDao.activateOrDeactiveUser(Integer.parseInt(id), 1);
                        req.setAttribute("messSuccess", "Activate successfully account has id = " + Integer.parseInt(id));
                    } else {
                        uDao.activateOrDeactiveUser(Integer.parseInt(id), 0);
                        req.setAttribute("messSuccess", "Deactivate successfully account has id = " + Integer.parseInt(id));
                    }
                    accList = uDao.getAllUser(index);
                    req.setAttribute("end", endPage);
                    req.setAttribute("index", index);
                    req.setAttribute("count", count);
                    req.setAttribute("accList", accList);
                    req.getRequestDispatcher("/view/admin/accountList.jsp").forward(req, resp);
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        authorization(req, resp);
        UserDao uDao = new UserDao();
        RoleDao rDao = new RoleDao();
        List<Role> rList = rDao.getAll();
        req.setAttribute("rList", rList);
        if (req.getParameter("save") != null) {
            String fullname = req.getParameter("fullname");
            String username = req.getParameter("username");
            String company = req.getParameter("company");
            String[] role = req.getParameterValues("role");
            String title = req.getParameter("title");
            String email = req.getParameter("email");
            String phone = req.getParameter("phone");
            req.setAttribute("fullname", fullname);
            req.setAttribute("username", username);
            req.setAttribute("company", company);
            req.setAttribute("email", email);
            req.setAttribute("phone", phone);
            String messFail = "";
            if (!uDao.checkDuplicateEmail(email, -1)) {
                messFail += "Duplicate Email <br>";
            }
            if (!uDao.checkDuplicateUsername(username, -1)) {
                messFail += "Duplicate Username <br>";
            }
            if (!uDao.checkDuplicatePhone(phone, -1)) {
                messFail += "Duplicate Phone";
            }
            if (uDao.checkDuplicateEmail(email, -1) && uDao.checkDuplicateUsername(username, -1)
                    && uDao.checkDuplicatePhone(phone, -1)) {
                int id = uDao.addUser(fullname, username, email, phone, title, company);
                if (role == null) {
                    uDao.updateUserRole(id, 7);
                } else {
                    for (String role1 : role) {
                        uDao.updateUserRole(id, Integer.parseInt(role1));
                    }
                }
                req.setAttribute("messSuccess", "Add successfully new account has id = " + id);
                int count = uDao.getNumberOfUser();
                req.setAttribute("count", count);
                String indexPage = req.getParameter("index");
                if (indexPage == null) {
                    indexPage = "1";
                }
                int index = Integer.parseInt(indexPage);
                List<User> accList = uDao.getAllUser(index);
                int endPage = count / 5;
                if (count % 5 != 0) {
                    endPage++;
                }
                req.setAttribute("end", endPage);
                req.setAttribute("index", index);
                req.setAttribute("count", count);
                req.setAttribute("accList", accList);
                req.getRequestDispatcher("/view/admin/accountList.jsp").forward(req, resp);
            } else {
                req.setAttribute("messFail", messFail);
                req.getRequestDispatcher("/view/admin/accountAdd.jsp").forward(req, resp);
            }
        } else {
            req.getRequestDispatcher("/view/admin/accountAdd.jsp").forward(req, resp);
        }
    }

}
