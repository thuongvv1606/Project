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
import model.Subject;
import util.Constants;

/**
 *
 * @author TT
 */
@WebServlet(name = Constants.URL_ELECTIVEADD, urlPatterns = {Constants.URL_ELECTIVEADD})
public class ElectiveAddController extends HttpServlet {

    protected void processResponse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        List<Curriculum> cList = curiDao.getAllCurriculum();
        req.setAttribute("cList", cList);
        if (req.getParameter("save") != null) {
            if (ename.trim().equals("")) {
                req.setAttribute("messFail", "Empty elective name");
            } else {
                //String cur_id = req.getParameter("cur_id");
                //String[] esubject = req.getParameterValues("esubject");
                if (eDao.checkDuplicateElective(ename, id, 0)) {
                    req.setAttribute("messFail", "Duplicate Elective");
                } else {
                    int eid = eDao.addElective(ename, edes, id);
//                    if (esubject != null) {
//                        for (String s : esubject) {
//                            eDao.addElectiveSubject(eid, Integer.parseInt(s));
//                        }
//                    }
//                    req.setAttribute("cur_id", cur_id);
                    req.setAttribute("messSuccess", "Add successfully");
                }
            }
        }
        List<Subject> sList = sDao.getAllSubject();
        req.setAttribute("sList", sList);
        req.getRequestDispatcher("/view/common/electiveAdd.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processResponse(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/common/electiveAdd.jsp").forward(req, resp);
    }
}
