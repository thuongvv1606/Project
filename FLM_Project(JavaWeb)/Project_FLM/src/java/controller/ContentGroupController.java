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
import java.io.PrintWriter;
import java.util.List;
import model.ContentGroup;
import model.Curriculum;
import model.Subject;
import model.User;
import util.Constants;

/**
 *
 * @author TT
 */
@WebServlet(name = Constants.URL_CONTENTGROUPLIST, urlPatterns = {Constants.URL_CONTENTGROUPLIST})
public class ContentGroupController extends HttpServlet {

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
            if(user.getRoleFunction() != 2){
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
        ContentGroup cg = cDao.getContentgroupById(req.getParameter("contentgroupId"));
        req.setAttribute("cg", cg);
        req.setAttribute("count", count);
        req.setAttribute("end", endPage);
        req.setAttribute("cgList", cgList);
        switch (action) {
            case "add":
                req.getRequestDispatcher("/view/common/contentgroupAdd.jsp").forward(req, resp);
                break;
            case "edit":
            case "remove":
                edit(req, resp, cg);
                break;
            case "detail":
                req.getRequestDispatcher("/view/common/contentgroupDetail.jsp").forward(req, resp);
                break;
            default:
                if (action.equals("delete")) {
                    authorization(req, resp);
                    cDao.deleteContentgroupSubject(Integer.parseInt(cId));
                    cDao.deleteContentgroup(Integer.parseInt(cId));
                    cgList = cDao.getContentgroupByCurId(id, index);
                    req.setAttribute("messSuccess", "Delete successfaully content group has id = " + cId);
                }
                count = cDao.getNumberOfContentGroupById(id);
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
    
    protected void edit(HttpServletRequest req, HttpServletResponse resp, ContentGroup g) throws ServletException, IOException {
        authorization(req, resp);
        HttpSession session = req.getSession();
        int id = (int) session.getAttribute("curId");
        CurriculumDao curiDao = new CurriculumDao();
        Curriculum curriculum = curiDao.getCurriculumByID(id);
        SubjectDao sDao = new SubjectDao();
        ContentGroupDao cDao = new ContentGroupDao();
        String cname = req.getParameter("cname");
        String ename = req.getParameter("ename");
        String order = req.getParameter("order");
        req.setAttribute("cname", cname);
        req.setAttribute("ename", ename);
        req.setAttribute("order", order);
        String contentgroup = req.getParameter("contentgroupId");
        req.setAttribute("contentgroupId", contentgroup);
        ContentGroup cg = cDao.getContentgroupById(contentgroup);
        String subject = req.getParameter("subjectId");
        req.setAttribute("subjectId", subject);
        if (req.getParameter("save") != null) {
            if (cname.trim().equals("") || ename.trim().equals("")) {
                req.setAttribute("messFail", "Empty content group name");
                req.getRequestDispatcher("/view/common/contentgroupEdit.jsp").forward(req, resp);
            } else {
                String cur_id = req.getParameter("cur_id");
//                String[] subject = req.getParameterValues("subject");
                if (cDao.checkDuplicateContentgroup(cname, cg.getId(), id)) {
                    req.setAttribute("messFail", "Duplicate Content Group. Please enter another content group name.");
                    req.getRequestDispatcher("/view/common/contentgroupEdit.jsp").forward(req, resp);
                } else if (cDao.checkDuplicateDisplayOrder(order, cg.getId(), id)) {
                    req.setAttribute("messFail", "Duplicate Content Group display order.");
                    req.getRequestDispatcher("/view/common/contentgroupEdit.jsp").forward(req, resp);
                } else {
                    cDao.updateContentgroup(cname, ename, order, cg.getId());
//                    if (subject != null) {
//                        cDao.deleteContentgroupSubject(cg.getId(), id);
//                        for (String s : subject) {
//                            cDao.addContentgroupSubject(Integer.parseInt(cur_id), Integer.parseInt(s), cg.getId());
//                        }
//                    } else {
//                        cDao.updateContentgroupSubject(cg.getId(), id);
//                    }
                    req.setAttribute("messSuccess", "Update successfully content group has id = " + cg.getId());
                    req.setAttribute("curriculum", curriculum);
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
                    List<ContentGroup> cgList = cDao.getContentgroupByCurId(id, index);
                    req.setAttribute("count", count);
                    req.setAttribute("end", endPage);
                    req.setAttribute("cgList", cgList);
                    req.getRequestDispatcher("/view/common/contentgroupList.jsp").forward(req, resp);
                }
            }     
        } else {
            if (req.getParameter("action").equals("remove")) {
                cDao.deleteContentgroupSubject(Integer.parseInt(subject), id, cg.getId());
                req.setAttribute("messSuccess", "Delete successfully subject has id = " + subject + " from this content group");
            }
            cg = cDao.getContentgroupById(contentgroup);
            req.setAttribute("cg", cg);
            req.getRequestDispatcher("/view/common/contentgroupEdit.jsp").forward(req, resp);
        }
    }
}
