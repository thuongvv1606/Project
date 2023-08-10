/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.CurriculumDao;
import dao.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import model.Curriculum;
import model.User;
import util.Constants;

/**
 *
 * @author TT
 */
@WebServlet(name = Constants.URL_CURRICULUMLIST, urlPatterns = {Constants.URL_CURRICULUMLIST})
public class CurriculumController extends HttpServlet {

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
        } else {
            resp.setContentType("text/html;charset=UTF-8");
            User user = (User) session.getAttribute("user");
            if (user.getRoleFunction() != 2) {
                try ( PrintWriter out = resp.getWriter()) {
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

    protected void processResponse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User a = (User) session.getAttribute("user");
        boolean check = true;
        if (a != null && a.getRoleFunction() == 2) {
            check = false;
        }
        CurriculumDao cDao = new CurriculumDao();
        String indexPage = req.getParameter("index");
        if (indexPage == null) {
            indexPage = "1";
        }
        int index = Integer.parseInt(indexPage);
        int count = cDao.getNumberOfCurriculum(check);
        int endPage = count / 5;
        if (count % 5 != 0) {
            endPage++;
        }
        List<Curriculum> cList = cDao.getAllCurriculum(index, check);
        List<User> owner = cDao.getAllOwner();
        req.setAttribute("cList", cList);
        req.setAttribute("count", count);
        req.setAttribute("end", endPage);
        req.setAttribute("index", index);
        req.setAttribute("owner", owner);
        if (req.getParameter("action") == null) {
            if (req.getParameter("search_btn") != null) {
                String filterByStatus = req.getParameter("filterByStatus");
                req.setAttribute("filterByStatus", filterByStatus);
                String in_charge = req.getParameter("in_charge");
                req.setAttribute("in_charge", in_charge);
                String key = req.getParameter("key");
                if (key == null) {
                    key = "";
                }
                if (in_charge == null) {
                    in_charge = "0";
                }
                req.setAttribute("key", key);
                cList = cDao.filterCurriculum(key, in_charge, filterByStatus, check);
                count = cList.size();
                endPage = 0;
                req.setAttribute("end", endPage);
                req.setAttribute("index", index);
                req.setAttribute("count", count);
                req.setAttribute("cList", cList);
            }
            req.getRequestDispatcher("/view/common/curriculumList.jsp").forward(req, resp);
        } else {
            switch (req.getParameter("action")) {
                case "detail":
                    req.getRequestDispatcher("/view/common/curriculumOverview.jsp").forward(req, resp);
                    break;
                case "edit":
                    req.getRequestDispatcher("/view/common/curriculumEdit.jsp").forward(req, resp);
                    break;
                case "add":
                    UserDao userDao = new UserDao();
                    ArrayList<User> userList = userDao.getAllListUser();
                    req.setAttribute("userList", userList);
                    req.getRequestDispatcher("/view/common/curriculumAdd.jsp").forward(req, resp);
                    break;
                default:
                    if (req.getParameter("action").equals("active")) {
                        authorization(req, resp);
                        cDao.activateOrDeactiveCurriculum(Integer.parseInt(req.getParameter("curId")), 1);
                        req.setAttribute("messSuccess", "Activate successfully curriculum has id = " + Integer.parseInt(req.getParameter("curId")));
                    } else {
                        authorization(req, resp);
                        cDao.activateOrDeactiveCurriculum(Integer.parseInt(req.getParameter("curId")), 0);
                        req.setAttribute("messSuccess", "Deactivate successfully curriculum has id = " + Integer.parseInt(req.getParameter("curId")));
                    }
                    cList = cDao.getAllCurriculum(index, check);
                    req.setAttribute("cList", cList);
                    req.getRequestDispatcher("/view/common/curriculumList.jsp").forward(req, resp);
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processResponse(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processResponse(req, resp);
    }

}
