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
@WebServlet(name = Constants.URL_CONTENTGROUPLIST, urlPatterns = {Constants.URL_CONTENTGROUPLIST})
public class ContentGroupController extends HttpServlet {

    protected void processResponse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int id = (int) session.getAttribute("curId");
        CurriculumDao curiDao = new CurriculumDao();
        Curriculum curriculum = curiDao.getCurriculumByID(id);
        req.setAttribute("curriculum", curriculum);
        ContentGroupDao cDao = new ContentGroupDao();
        String indexPage = req.getParameter("index");
        if (indexPage == null) {
            indexPage = "1";
        }
        int index = Integer.parseInt(indexPage);
        int count = cDao.getNumberOfContentGroupById(id);
        int endPage = count / 5;
        if (count % 5 != 0) {
            endPage++;
        }
        String action = req.getParameter("action");
        String cId = req.getParameter("contentgroupId");
        List<ContentGroup> cgList = cDao.getContentgroupByCurId(id, index);
        if (req.getParameter("search_btn") != null) {
            String key = req.getParameter("key");
            if (key == null) {
                key = "";
            }
            cgList = cDao.searchContentgroup(key, id);
            count = cgList.size();
            endPage = 0;
        }
        req.setAttribute("count", count);
        req.setAttribute("end", endPage);
        req.setAttribute("cgList", cgList);
        SubjectDao sDao = new SubjectDao();
        List<Subject> sList = sDao.getAllSubject();
        req.setAttribute("sList", sList);
        List<Curriculum> cList = curiDao.getAllCurriculum();
        req.setAttribute("cList", cList);
        ContentGroup cg = cDao.getContentgroupById(req.getParameter("contentgroupId"));
        session.setAttribute("cg", cg);
        switch (action) {
            case "add":
                req.getRequestDispatcher("/view/common/contentgroupAdd.jsp").forward(req, resp);
                break;
            case "edit":
                req.getRequestDispatcher("/view/common/contentgroupEdit.jsp").forward(req, resp);
                break;
            case "detail":
                req.getRequestDispatcher("/view/common/contentgroupDetail.jsp").forward(req, resp);
                break;
            default:
                if (action.equals("delete")) {
                    cDao.deleteContentgroupSubject(Integer.parseInt(cId));
                    cDao.deleteContentgroup(Integer.parseInt(cId));
                    cgList = cDao.getContentgroupByCurId(id, index);
                }
                req.setAttribute("count", count);
                req.setAttribute("end", endPage);
                req.setAttribute("cgList", cgList);
                req.getRequestDispatcher("/view/common/contentgroupList.jsp").forward(req, resp);
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
