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
import model.Role;
import model.Syllabus;
import model.User;
import util.Constants;

/**
 *
 * @author TT
 */
@WebServlet(name = Constants.URL_SYLLABUSREVIEW, urlPatterns = {Constants.URL_SYLLABUSREVIEW})
public class SyllabusReviewerController extends HttpServlet {

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
            if(user.getRoleFunction() != 2 && user.getRoleFunction() != 1){
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
        authorization(req, resp);
        HttpSession session = req.getSession();
        User a = (User) session.getAttribute("user");
        SyllabusDao sDao = new SyllabusDao();
        String indexPage = req.getParameter("index");
        if (indexPage == null) {
            indexPage = "1";
        }
        int index = Integer.parseInt(indexPage);
        int count = sDao.getNumberOfSyllabusReview();
        int endPage = count / 8;
        if (count % 8 != 0) {
            endPage++;
        }
        req.setAttribute("end", endPage);
        req.setAttribute("index", index);
        String id = req.getParameter("sylId");
        if (req.getParameter("action") != null) {
            sDao.approveSyllabus(Integer.parseInt(id), 1, a.getUserId());
            req.setAttribute("messSuccess", "Approve successfully syllabus has id = " + Integer.parseInt(id));
        }
        List<Syllabus> sList = sDao.getReviewSyllabus(index);
        count = sDao.getNumberOfSyllabusReview();
        req.setAttribute("sList", sList);
        req.setAttribute("count", count);
        req.getRequestDispatcher("/view/common/syllabusReview.jsp").forward(req, resp);
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
