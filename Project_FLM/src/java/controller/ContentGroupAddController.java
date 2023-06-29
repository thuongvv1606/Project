/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ContentGroupDao;
import dao.CurriculumDao;
import dao.SubjectDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.Curriculum;
import model.Subject;
import util.Constants;

/**
 *
 * @author TT
 */
@WebServlet(name = Constants.URL_CONTENTGROUPADD, urlPatterns = {Constants.URL_CONTENTGROUPADD})
public class ContentGroupAddController extends HttpServlet {

    protected void processResponse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SubjectDao sDao = new SubjectDao();
        ContentGroupDao cDao = new ContentGroupDao();
        CurriculumDao curiDao = new CurriculumDao();
        String cname = req.getParameter("cname");
        String ctype = req.getParameter("ctype");
        String cur_id = req.getParameter("cur_id");
        req.setAttribute("cname", cname);
        req.setAttribute("ctype", ctype);
        req.setAttribute("cur_id", cur_id);
        if (req.getParameter("save") != null) {
            if (cname.trim().equals("")) {
                req.setAttribute("messFail", "Empty content group name");
            } else {
                String[] subject = req.getParameterValues("subject");
                if (cDao.checkDuplicateContentgroup(cname, Integer.parseInt(cur_id))) {
                    req.setAttribute("messFail", "Duplicate Content Group. <br> Please enter another content group name.");
                } else {
                    int eid = cDao.addContentgroup(cname, ctype, cur_id);
                    System.out.println(eid);
                    if (subject != null) {
                        for (String s : subject) {
                            cDao.addContentgroupSubject(Integer.parseInt(cur_id), Integer.parseInt(s), eid);
                        }
                    }
                    req.setAttribute("messSuccess", "Add successfully!");
                }
            }
        }
        List<Subject> sList = sDao.getAllSubject();
        req.setAttribute("sList", sList);
        List<Curriculum> cList = curiDao.getAllCurriculum();
        req.setAttribute("cList", cList);
        req.getRequestDispatcher("/view/common/contentgroupAdd.jsp").forward(req, resp);
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
