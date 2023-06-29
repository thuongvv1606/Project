/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.SyllabusDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import model.Syllabus;
import model.User;
import util.Constants;

/**
 *
 * @author TT
 */
@WebServlet(name = Constants.URL_SYLLABUSLIST, urlPatterns = {Constants.URL_SYLLABUSLIST})
public class SyllabusController extends HttpServlet {
    protected void processResponse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User a = (User) session.getAttribute("user");
        boolean check = true;
        if (a != null && a.getRoleFunction() == 2) {
            check = false;
        }
        SyllabusDao sDao = new SyllabusDao();
        String indexPage = req.getParameter("index");
        if (indexPage == null) {
            indexPage = "1";
        }
        int index = Integer.parseInt(indexPage);
        int count = sDao.getNumberOfSyllabus(check);
        int endPage = count / 8;
        if (count % 8 != 0) {
            endPage++;
        }
        List<Syllabus> sList = sDao.getAllSyllabus(index, check);
        if (req.getParameter("search_btn") != null) {
            String key = req.getParameter("key");
            if (key == null) {
                key = "";
            }
            sList = sDao.searchSyllabus(key, check);
            count = sList.size();
            endPage = 0;
        }
        List<User> owner = sDao.getAllOwner();
        req.setAttribute("sList", sList);
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
                    sList = sDao.getAllSyllabus(index, check);
                    count = sDao.getNumberOfSyllabus(check);
                    req.setAttribute("count", count);
                    req.setAttribute("sList", sList);
                } else {
                    sList = sDao.filterSyllabus(in_charge, filterByStatus);
                    count = sList.size();
                    endPage = 0;
                    req.setAttribute("end", endPage);
                    req.setAttribute("index", index);
                    req.setAttribute("count", count);
                    req.setAttribute("sList", sList);
                }
            }
            req.getRequestDispatcher("/view/common/syllabusList.jsp").forward(req, resp);
        } else {
            String id = req.getParameter("sylId");
            switch (req.getParameter("action")) {
                case "detail":
                    Syllabus syllabus = sDao.getAllSyllabusById(Integer.parseInt(id));
                    session.setAttribute("syllabus", syllabus);
                    req.setAttribute("syllabus", syllabus);
                    req.getRequestDispatcher("/view/common/syllabusDetail.jsp").forward(req, resp);
                    break;
                default:
                    if (req.getParameter("action").equals("active")) {
                        sDao.activateOrDeactiveSyllabus(Integer.parseInt(id), 1);
                    } else {
                        sDao.activateOrDeactiveSyllabus(Integer.parseInt(id), 0);
                    }
                    sList = sDao.getAllSyllabus(index, check);
                    count = sDao.getNumberOfSyllabus(check);
                    req.setAttribute("sList", sList);
                    req.setAttribute("count", count);
                    req.getRequestDispatcher("/view/common/syllabusList.jsp").forward(req, resp);
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
