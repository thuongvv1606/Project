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
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import model.ContentGroup;
import model.Curriculum;
import model.Subject;
import util.Constants;

/**
 *
 * @author TT
 */
@WebServlet(name = Constants.URL_CONTENTGROUPEDIT, urlPatterns = {Constants.URL_CONTENTGROUPEDIT})
public class ContentGroupEditController extends HttpServlet {

    protected void processResponse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SubjectDao sDao = new SubjectDao();
        ContentGroupDao cDao = new ContentGroupDao();
        CurriculumDao curiDao = new CurriculumDao();
        HttpSession session = req.getSession();
        int id = (int) session.getAttribute("curId");
        String cname = req.getParameter("cname");
        String ctype = req.getParameter("ctype");
        req.setAttribute("cname", cname);
        req.setAttribute("ctype", ctype);
        ContentGroup cg = (ContentGroup) session.getAttribute("cg");
        if (req.getParameter("save") != null) {
            if (cname.trim().equals("")) {
                req.setAttribute("messFail", "Empty content group name");
            } else {
                String cur_id = req.getParameter("cur_id");
                String[] subject = req.getParameterValues("subject");
                if (cDao.checkDuplicateContentgroup(cname, cg.getId(), Integer.parseInt(cur_id))) {
                    req.setAttribute("messFail", "Duplicate Content Group.<br>Please enter another content group name.");
                } else {
                    cDao.updateContentgroup(cname, ctype, Integer.parseInt(cur_id), cg.getId());
                    if (subject != null) {
                        cDao.deleteContentgroupSubject(cg.getId(), id);
                        for (String s : subject) {
                            cDao.addContentgroupSubject(Integer.parseInt(cur_id), Integer.parseInt(s), cg.getId());
                        }
                    } else {
                        cDao.updateContentgroupSubject(cg.getId(), id);
                    }
                    req.setAttribute("cur_id", cur_id);
                    req.setAttribute("messSuccess", "Update successfully");
                }
            }
        }
        List<Subject> sList = sDao.getAllSubject();
        req.setAttribute("sList", sList);
        List<Curriculum> cList = curiDao.getAllCurriculum();
        req.setAttribute("cList", cList);
        req.getRequestDispatcher("/view/common/contentgroupEdit.jsp").forward(req, resp);
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
