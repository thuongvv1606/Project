/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.SessionDao;
import dao.SyllabusDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import model.Session;
import model.Syllabus;
import model.User;
import util.Constants;

/**
 *
 * @author trinh
 */
@WebServlet(name = Constants.URL_SESSIONEDIT, urlPatterns = {Constants.URL_SESSIONEDIT})
public class SessionEditController extends HttpServlet {

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
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        authorization(req, resp);
        SessionDao sessionDao = new SessionDao();
        int id = Integer.parseInt(req.getParameter("sessionId"));
        Session session = sessionDao.getALlSessionById(id);
        int sylabusId = Integer.parseInt(req.getParameter("syllabusId"));
        SyllabusDao sDao = new SyllabusDao();
        Syllabus syllabus = sDao.getAllSyllabusById(sylabusId);
        req.setAttribute("syllabus", syllabus);
        req.setAttribute("idSylas", sylabusId);
        req.setAttribute("id", id);
        req.setAttribute("listSession", session);
        req.getRequestDispatcher("view/admin/editSession.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        authorization(req, resp);
        SessionDao sessionDao = new SessionDao();
        HttpSession sessionn = req.getSession();
        int sylabusId = Integer.parseInt(req.getParameter("syllabusId"));
        int id = Integer.parseInt(req.getParameter("sessionId"));
        String topic = req.getParameter("topic");
        String materials = req.getParameter("materials");
        String learning = req.getParameter("learning");
        String lo = req.getParameter("lo");
        String itu = req.getParameter("itu");
        String task = req.getParameter("task");
        String success = "";
        String messs = "";
        SyllabusDao sDao = new SyllabusDao();
        Syllabus syllabus = sDao.getAllSyllabusById(sylabusId);
        req.setAttribute("syllabus", syllabus);
        boolean checkUpdate = sessionDao.updateSession(topic, lo, itu, learning, materials, task, id);
        if (checkUpdate) {
            SessionDao sessionDaoo = new SessionDao();
            Session session = sessionDaoo.getALlSessionById(id);
            req.setAttribute("listSession", session);
             sessionn.setAttribute("messSuccess", "Update sucess!");
           
        } else {
            sessionn.setAttribute("messFail", "Update false pls update agin");
        }
        req.setAttribute("idSylas", sylabusId);
        req.setAttribute("messs", messs);
        
        req.setAttribute("id", id);
//        req.getRequestDispatcher("view/admin/editSession.jsp").forward(req, resp);
//        req.getRequestDispatcher("sessionlist").forward(req, resp);
        resp.sendRedirect(Constants.URL_PROJECT + Constants.URL_SESSIONLIST + "?syllabusId=" + sylabusId);

    }

}
