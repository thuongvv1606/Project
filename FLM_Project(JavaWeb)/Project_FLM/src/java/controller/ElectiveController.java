/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.CurriculumDao;
import dao.ElectiveDao;
import dao.SubjectDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import model.Curriculum;
import model.Elective;
import model.Subject;
import model.User;
import util.Constants;

/**
 *
 * @author TT
 */
@WebServlet(name = Constants.URL_ELECTIVE, urlPatterns = {Constants.URL_ELECTIVE})
public class ElectiveController extends HttpServlet {

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
        int id = (int) session.getAttribute("curId");
        User a = (User) session.getAttribute("user");
        boolean check = true;
        if (a != null && a.getRoleFunction() == 2) {
            check = false;
        }
        CurriculumDao curiDao = new CurriculumDao();
        Curriculum curriculum = curiDao.getCurriculumByID(id);
        req.setAttribute("curriculum", curriculum);
        ElectiveDao eDao = new ElectiveDao();
        String indexPage = req.getParameter("index");
        if (indexPage == null) {
            indexPage = "1";
        }
        int index = Integer.parseInt(indexPage);
        int count = eDao.getNumberOfElectiveById(id, check);
        int endPage = count / 5;
        if (count % 5 != 0) {
            endPage++;
        }
        String action = req.getParameter("action");
        String eId = req.getParameter("electiveId");
        SubjectDao sDao = new SubjectDao();
        List<Subject> sList = sDao.getAllElectiveSubject(id);
        req.setAttribute("sList", sList);
        Elective e = eDao.getElectiveById(req.getParameter("electiveId"));
        req.setAttribute("e", e);
        switch (action) {
            case "add":
                add(req, resp);
                break;
            case "edit":
            case "remove":
                edit(req, resp);
                break;
            case "detail":
                sList = sDao.getSubjectByElectiveId(Integer.parseInt(eId));
                req.setAttribute("sList", sList);
                req.getRequestDispatcher("/view/common/electiveDetail.jsp").forward(req, resp);
                break;
            default:
                if (action.equals("deactivate")) {
                    authorization(req, resp);
                    eDao.activateOrDeactiveElective(0, Integer.parseInt(eId));
                    req.setAttribute("messSuccess", "Deactivate successfully elective has code = " + eId);
                } else if (action.equals("activate")) {
                    authorization(req, resp);
                    eDao.activateOrDeactiveElective(1, Integer.parseInt(eId));
                    req.setAttribute("messSuccess", "Activate successfully elective has code = " + eId);
                }
                List<Elective> eList = eDao.getElectiveByCurId(id, index, check);
                count = eDao.getNumberOfElectiveById(id, check);
                if (req.getParameter("search_btn") != null) {
                    String key = req.getParameter("key");
                    if (key == null) {
                        key = "";
                    }
                    eList = eDao.searchElective(key, id, check);
                    count = eList.size();
                    endPage = 0;
                }
                req.setAttribute("count", count);
                req.setAttribute("end", endPage);
                req.setAttribute("eList", eList);
                req.getRequestDispatcher("/view/common/electiveList.jsp").forward(req, resp);
                break;
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
    
    protected void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        authorization(req, resp);
        ElectiveDao eDao = new ElectiveDao();
        SubjectDao sDao = new SubjectDao();
        CurriculumDao curiDao = new CurriculumDao();
        HttpSession session = req.getSession();
        int id = (int) session.getAttribute("curId");
        String electiveId = req.getParameter("electiveId");
        Elective e = eDao.getElectiveById(electiveId);
        req.setAttribute("e", e);
        String ecode = req.getParameter("ecode");
        req.setAttribute("ecode", ecode);
        String ename = req.getParameter("ename");
        String edes = req.getParameter("edes");
        req.setAttribute("ename", ename);
        req.setAttribute("edes", edes);
        String status = req.getParameter("status");
        req.setAttribute("status", status);
        List<Subject> sList = sDao.getAllElectiveSubject(id);
        req.setAttribute("sList", sList);
        req.setAttribute("id", id);
        if (req.getParameter("save") != null) {
            if (ename.trim().equals("")) {
                req.setAttribute("messFail", "Empty elective name");
                req.getRequestDispatcher("/view/common/electiveEdit.jsp").forward(req, resp);
            } else {
                //String cur_id = req.getParameter("cur_id");
                //String[] esubject = req.getParameterValues("esubject");
                if (eDao.checkDuplicateElective(ecode, id, e.getId())) {
                    req.setAttribute("messFail", "Duplicate Elective, please enter another code");
                    req.getRequestDispatcher("/view/common/electiveEdit.jsp").forward(req, resp);
                } else {
                    eDao.updateElective(e.getId(), ecode, ename, edes, status);
//                    if (esubject != null) {
//                        eDao.deleteElectiveSubject(e.getId());
//                        for (String s : esubject) {
//                            eDao.addElectiveSubject(e.getId(), Integer.parseInt(s));
//                        }
//                    } 
                    //req.setAttribute("cur_id", cur_id);
                    req.setAttribute("messSuccess", "Update successfully elective has id = " + e.getId());
                    Curriculum curriculum = curiDao.getCurriculumByID(id);
                    req.setAttribute("curriculum", curriculum);
                    String indexPage = req.getParameter("index");
                    if (indexPage == null) {
                        indexPage = "1";
                    }
                    int index = Integer.parseInt(indexPage);
                    int count = eDao.getNumberOfElectiveById(id, false);
                    int endPage = count / 5;
                    if (count % 5 != 0) {
                        endPage++;
                    }
                    Elective elective = eDao.getElectiveById(req.getParameter("electiveId"));
                    List<Elective> eList = eDao.getElectiveByCurId(id, index, false);
                    req.setAttribute("e", elective);
                    req.setAttribute("count", count);
                    req.setAttribute("end", endPage);
                    req.setAttribute("eList", eList);
                    req.getRequestDispatcher("/view/common/electiveList.jsp").forward(req, resp);
                }
            }
        } else {
            if (req.getParameter("action").equals("remove")) {
                String subject = req.getParameter("subjectId");
                eDao.deleteElectiveSubject(e.getId(), subject);
                req.setAttribute("messSuccess", "Remove successfully subject has id = " + subject + " from this elective");
            }
            e = eDao.getElectiveById(electiveId);
            req.setAttribute("e", e);
            req.getRequestDispatcher("/view/common/electiveEdit.jsp").forward(req, resp);
        }
    }
    
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        authorization(req, resp);
        SubjectDao sDao = new SubjectDao();
        ElectiveDao eDao = new ElectiveDao();
        CurriculumDao curiDao = new CurriculumDao();
        HttpSession session = req.getSession();
        int id = (int) session.getAttribute("curId");
        Curriculum curriculum = curiDao.getCurriculumByID(id);
        req.setAttribute("curriculum", curriculum);
        String ename = req.getParameter("ename");
        String edes = req.getParameter("edes");
        req.setAttribute("ename", ename);
        req.setAttribute("edes", edes);
        String ecode = req.getParameter("ecode");
        req.setAttribute("ecode", ecode);
        List<Curriculum> cList = curiDao.getAllCurriculum();
        req.setAttribute("cList", cList);
        if (req.getParameter("save") != null) {
            if (ename.trim().equals("")) {
                req.setAttribute("messFail", "Empty elective code or name");
                req.getRequestDispatcher("/view/common/electiveAdd.jsp").forward(req, resp);
            } else {
                //String cur_id = req.getParameter("cur_id");
                //String[] esubject = req.getParameterValues("esubject");
                if (eDao.checkDuplicateElective(ecode, id, 0)) {
                    req.setAttribute("messFail", "Duplicate Elective, please enter another code");
                    req.getRequestDispatcher("/view/common/electiveAdd.jsp").forward(req, resp);
                } else {
                    int eid = eDao.addElective(ecode, ename, edes, id);
//                    if (esubject != null) {
//                        for (String s : esubject) {
//                            eDao.addElectiveSubject(eid, Integer.parseInt(s));
//                        }
//                    }
//                    req.setAttribute("cur_id", cur_id);
                    req.setAttribute("messSuccess", "Add successfully new elective has code = " + ecode);
                    String indexPage = req.getParameter("index");
                    if (indexPage == null) {
                        indexPage = "1";
                    }
                    int index = Integer.parseInt(indexPage);
                    int count = eDao.getNumberOfElectiveById(id, false);
                    int endPage = count / 5;
                    if (count % 5 != 0) {
                        endPage++;
                    }
                    Elective elective = eDao.getElectiveById(req.getParameter("electiveId"));
                    List<Elective> eList = eDao.getElectiveByCurId(id, index, false);
                    req.setAttribute("e", elective);
                    req.setAttribute("count", count);
                    req.setAttribute("end", endPage);
                    req.setAttribute("eList", eList);
                    req.getRequestDispatcher("/view/common/electiveList.jsp").forward(req, resp);
                }
            }
        } else {
            req.getRequestDispatcher("/view/common/electiveAdd.jsp").forward(req, resp);
        }
    }
}
