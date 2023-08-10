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
import java.io.PrintWriter;
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
            if(user.getRoleFunction() == 3){
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
    protected void processResponse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User a = (User) session.getAttribute("user");
        boolean check = true;
        if (a != null && (a.getRoleFunction() == 2 || a.getRoleFunction() == 1)) {
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
        List<User> owner = sDao.getAllOwner();
        req.setAttribute("sList", sList);
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
                String filterByApprove = req.getParameter("filterByApprove");
                req.setAttribute("filterByApprove", filterByApprove);
                String key = req.getParameter("key");
                if (in_charge == null) {
                    in_charge = "0";
                }
                if (filterByApprove == null) {
                    filterByApprove = "-1";
                }
                if (key == null) {
                    key = "";
                }
                req.setAttribute("search", key);
                sList = sDao.searchSyllabus(key, check, in_charge, filterByStatus, filterByApprove);
                count = sList.size();
                endPage = 0;
                req.setAttribute("sList", sList);
                req.setAttribute("count", count);
                req.setAttribute("end", endPage);
            }
            req.getRequestDispatcher("/view/common/syllabusList.jsp").forward(req, resp);
        } else {
            String id = req.getParameter("sylId");
            Syllabus syllabus = null;
            if (id != null) {
                syllabus = sDao.getAllSyllabusById(Integer.parseInt(id));
            }
            switch (req.getParameter("action")) {
                case "add":
                    add(req, resp);
                    break;
                case "edit":
                    edit(req, resp);
                    break;
                case "detail":
                    req.setAttribute("sylId", id);
                    req.setAttribute("syllabus", syllabus);
                    req.getRequestDispatcher("/view/common/syllabusDetail.jsp").forward(req, resp);
                    break;
                default:
                    if (req.getParameter("action").equals("active")) {
                        sDao.activateOrDeactiveSyllabus(Integer.parseInt(id), 1);
                        req.setAttribute("messSuccess", "Activate successfully syllabus has id = " + Integer.parseInt(id));
                    } else {
                        sDao.activateOrDeactiveSyllabus(Integer.parseInt(id), 0);
                        req.setAttribute("messSuccess", "Deactivate successfully syllabus has id = " + Integer.parseInt(id));
                    }
                    sList = sDao.getAllSyllabus(index, check);
                    count = sDao.getNumberOfSyllabus(check);
                    req.setAttribute("sList", sList);
                    req.setAttribute("count", count);
                    req.getRequestDispatcher("/view/common/syllabusList.jsp").forward(req, resp);
                    break;
            }
            req.setAttribute("messSuccess", "");
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

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        authorization(req, resp);
        HttpSession session = req.getSession();
        User a = (User) session.getAttribute("user");
        SyllabusDao sDao = new SyllabusDao();
        String name = req.getParameter("name");
        req.setAttribute("name", name);
        String des = req.getParameter("des");
        req.setAttribute("des", des);
        String status = req.getParameter("status");
        req.setAttribute("status", status);
        String degree = req.getParameter("degree");
        req.setAttribute("degree", degree);
        String engname = req.getParameter("engname");
        req.setAttribute("engname", engname);
        String time = req.getParameter("time");
        req.setAttribute("time", time);
        String score = req.getParameter("score");
        req.setAttribute("score", score);
        String mark = req.getParameter("mark");
        req.setAttribute("mark", mark);
        String credit = req.getParameter("credit");
        req.setAttribute("credit", credit);
        String task = req.getParameter("task");
        req.setAttribute("task", task);
        String note = req.getParameter("note");
        req.setAttribute("note", note);
        String tool = req.getParameter("tool");
        req.setAttribute("tool", tool);
        if (req.getParameter("save") != null) {
            if (Integer.parseInt(mark) > Integer.parseInt(score)) {
                req.setAttribute("messFail", "Min mark to pass must be smaller or equals to scale score");
                req.getRequestDispatcher("/view/admin/syllabusAdd.jsp").forward(req, resp);
            } else if (name.trim().equals("")) {
                req.setAttribute("messFail", "Empty syllabus name");
                req.getRequestDispatcher("/view/admin/syllabusAdd.jsp").forward(req, resp);
            } else if (sDao.checkDuplicateSyllabus(name, "-1")) {
                req.setAttribute("messFail", "Duplicate syllabus name");
                req.getRequestDispatcher("/view/admin/syllabusAdd.jsp").forward(req, resp);
            } else {
                if (sDao.checkDuplicateSyllabus(name, "-1")) {
                    req.setAttribute("messFail", "Duplicate syllabus name");
                    req.getRequestDispatcher("/view/admin/syllabusAdd.jsp").forward(req, resp);
                } else {
                    int id = sDao.addSyllabus(name, engname, credit, time, des, task, tool, score, note, 
                            mark, status, degree, a.getUserId());
                    req.setAttribute("messSuccess", "Add successfully syllabus has id = " + id);
                    String indexPage = req.getParameter("index");
                    boolean check = true;
                    if (a != null && a.getRoleFunction() == 2) {
                        check = false;
                    }
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
                    List<User> owner = sDao.getAllOwner();
                    req.setAttribute("sList", sList);
                    req.setAttribute("count", count);
                    req.setAttribute("end", endPage);
                    req.setAttribute("index", index);
                    req.setAttribute("owner", owner);
                    req.getRequestDispatcher("/view/common/syllabusList.jsp").forward(req, resp);
                }
            }
        } else {
            req.getRequestDispatcher("/view/admin/syllabusAdd.jsp").forward(req, resp);
        }
    }

    protected void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        authorization(req, resp);
        HttpSession session = req.getSession();
        User a = (User) session.getAttribute("user");
        SyllabusDao sDao = new SyllabusDao();
        String name = req.getParameter("name");
        String des = req.getParameter("des");
        req.setAttribute("name", name);
        req.setAttribute("des", des);
        String status = req.getParameter("status");
        String id = req.getParameter("sylId");
        Syllabus syllabus = sDao.getAllSyllabusById(Integer.parseInt(id));
        req.setAttribute("status", status);
        req.setAttribute("sylId", id);
        req.setAttribute("syllabus", syllabus);
        String degree = req.getParameter("degree");
        req.setAttribute("degree", degree);
        String engname = req.getParameter("engname");
        req.setAttribute("engname", engname);
        String time = req.getParameter("time");
        req.setAttribute("time", time);
        String score = req.getParameter("score");
        req.setAttribute("score", score);
        String mark = req.getParameter("mark");
        req.setAttribute("mark", mark);
        String credit = req.getParameter("credit");
        req.setAttribute("credit", credit);
        String task = req.getParameter("task");
        req.setAttribute("task", task);
        String note = req.getParameter("note");
        req.setAttribute("note", note);
        String tool = req.getParameter("tool");
        req.setAttribute("tool", tool);
        if (req.getParameter("save") != null) {
            if (Integer.parseInt(mark) > Integer.parseInt(score)) {
                req.setAttribute("messFail", "Min mark to pass must be smaller or equals to scale score");
                req.getRequestDispatcher("/view/admin/syllabusEdit.jsp").forward(req, resp);
            } else if (sDao.checkDuplicateSyllabus(name, id)) {
                req.setAttribute("messFail", "Duplicate syllabus name");
                req.getRequestDispatcher("/view/admin/syllabusEdit.jsp").forward(req, resp);
            } else if (name.trim().equals("")) {
                req.setAttribute("messFail", "Empty syllabus name");
                req.getRequestDispatcher("/view/admin/syllabusEdit.jsp").forward(req, resp);
            } else {
                sDao.updateSyllabus(name, engname, des, credit, time, task, tool, 
                        score, note, mark, status, degree, id);
                req.setAttribute("messSuccess", "Update successfully syllabus has id = " + id);
                String indexPage = req.getParameter("index");
                boolean check = true;
                if (a != null && a.getRoleFunction() == 2) {
                    check = false;
                }
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
                List<User> owner = sDao.getAllOwner();
                req.setAttribute("sList", sList);
                req.setAttribute("count", count);
                req.setAttribute("end", endPage);
                req.setAttribute("index", index);
                req.setAttribute("owner", owner);
                req.getRequestDispatcher("/view/common/syllabusList.jsp").forward(req, resp);
            }
        } else {
            req.getRequestDispatcher("/view/admin/syllabusEdit.jsp").forward(req, resp);
        }
    }
}
