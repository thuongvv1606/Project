/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.CurriculumDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
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
        if (req.getParameter("search_btn") != null) {
            String key = req.getParameter("key");
            if (key == null) {
                key = "";
            }
            cList = cDao.searchCurriculum(key, check);
            count = cList.size();
            endPage = 0;
        }
        List<User> owner = cDao.getAllOwner();
        req.setAttribute("cList", cList);
        req.setAttribute("count", count);
        req.setAttribute("end", endPage);
        req.setAttribute("index", index);
        req.setAttribute("owner", owner);
        if (req.getParameter("action") == null) {
            if (req.getParameter("filter_btn") != null) {
                String filterByStatus = req.getParameter("filterByStatus");
                req.setAttribute("filterByStatus", filterByStatus);
                String in_charge = req.getParameter("in_charge");
                req.setAttribute("in_charge", in_charge);
                if (filterByStatus.equals("-1") && in_charge.equals("0")) {
                    cList = cDao.getAllCurriculum(index, check);
                    count = cDao.getNumberOfCurriculum(check);
                    req.setAttribute("count", count);
                    req.setAttribute("cList", cList);
                } else {
                    cList = cDao.filterCurriculum(in_charge, filterByStatus);
                    count = cList.size();
                    endPage = 0;
                    req.setAttribute("end", endPage);
                    req.setAttribute("index", index);
                    req.setAttribute("count", count);
                    req.setAttribute("cList", cList);
                }
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
                    req.getRequestDispatcher("/view/common/curriculumAdd.jsp").forward(req, resp);
                    break;
                default:
                    if (req.getParameter("action").equals("active")) {
                        cDao.activateOrDeactiveCurriculum(Integer.parseInt(req.getParameter("curId")), 1);
                    } else {
                        cDao.activateOrDeactiveCurriculum(Integer.parseInt(req.getParameter("curId")), 0);
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
