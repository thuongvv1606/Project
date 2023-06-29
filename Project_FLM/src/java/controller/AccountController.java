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

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        if (req.getParameter("search_btn") != null) {
            String key = req.getParameter("key");
            if (key == null) {
                key = "";
            }
            accList = uDao.searchUser(key);
            count = accList.size();
            endPage = 0;
        }
        req.setAttribute("accList", accList);
        req.setAttribute("count", count);
        req.setAttribute("end", endPage);
        req.setAttribute("index", index);
        RoleDao rDao = new RoleDao();
        List<Role> rList = rDao.getAll();
        req.setAttribute("rList", rList);
        if (req.getParameter("action") == null) {
            if (req.getParameter("filter_btn") != null) {
                String filterByRole = req.getParameter("filterByRole");
                String filterByStatus = req.getParameter("filterByStatus");
                if (filterByRole.equals("0")) {
                    filterByRole = "";
                }
                req.setAttribute("filterByRole", filterByRole);
                req.setAttribute("filterByStatus", filterByStatus);
                if (filterByRole.equals("") && filterByStatus.equals("-1")) {
                    accList = uDao.getAllUser(index);
                    count = accList.size();
                    endPage = 0;
                    req.setAttribute("end", endPage);
                    req.setAttribute("index", index);
                    req.setAttribute("count", count);
                    req.setAttribute("accList", accList);
                } else {
                    accList = uDao.filterUser(filterByRole, filterByStatus);
                    count = accList.size();
                    endPage = 0;
                    req.setAttribute("end", endPage);
                    req.setAttribute("index", index);
                    req.setAttribute("count", count);
                    req.setAttribute("accList", accList);
                }
            }
            req.getRequestDispatcher("/view/admin/accountList.jsp").forward(req, resp);
        } else {
            String id = req.getParameter("userId");
            HttpSession session = req.getSession();
            session.setAttribute("userId", id);
            if (id != null) {
                User acc = uDao.getUser(Integer.parseInt(id));
                session.setAttribute("acc", acc);
            }
            switch (req.getParameter("action")) {
                case "detail":
                    req.getRequestDispatcher("/view/admin/accountDetail.jsp").forward(req, resp);
                    break;
                case "edit":
                    req.getRequestDispatcher("/view/admin/accountEdit.jsp").forward(req, resp);
                    break;
                case "add":
                    req.getRequestDispatcher("/view/admin/accountAdd.jsp").forward(req, resp);
                    break;
                default:
                    if (req.getParameter("action").equals("activate")) {
                        uDao.activateOrDeactiveUser(Integer.parseInt(id), 1);
                    } else {
                        uDao.activateOrDeactiveUser(Integer.parseInt(id), 0);
                    }
                    accList = uDao.getAllUser(index);
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

}
