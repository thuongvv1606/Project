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
//        List<Subject> sList = sDao.getAllSubject();
//        req.setAttribute("sList", sList);
        Elective e = eDao.getElectiveById(req.getParameter("electiveId"));
        req.setAttribute("e", e);
        session.setAttribute("e", e);
        switch (action) {
            case "add":
                req.getRequestDispatcher("/view/common/electiveAdd.jsp").forward(req, resp);
                break;
            case "edit":
                req.getRequestDispatcher("/view/common/electiveEdit.jsp").forward(req, resp);
                break;
            case "detail":
                List<Subject> sList = sDao.getSubjectByElectiveId(Integer.parseInt(eId));
                req.setAttribute("sList", sList);
                req.getRequestDispatcher("/view/common/electiveDetail.jsp").forward(req, resp);
                break;
            default:
                if (action.equals("deactivate")) {
                    eDao.activateOrDeactiveElective(0, Integer.parseInt(eId));
                } else if (action.equals("activate")) {
                    eDao.activateOrDeactiveElective(1, Integer.parseInt(eId));
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
}
