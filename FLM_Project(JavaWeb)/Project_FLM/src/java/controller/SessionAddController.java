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
import model.Syllabus;
import model.User;
import util.Constants;

/**
 *
 * @author trinh
 */
@WebServlet(name = Constants.URL_SESSIONADD, urlPatterns = {Constants.URL_SESSIONADD})
public class SessionAddController extends HttpServlet{

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
        int id = Integer.parseInt(req.getParameter("syllabusId"));
            String topic = req.getParameter("topic");
            String materials = req.getParameter("materials");
            String learning = req.getParameter("learning");
            String lo = req.getParameter("lo");
            String itu = req.getParameter("itu");
            String task = req.getParameter("task");
            SyllabusDao sDao = new SyllabusDao();
            Syllabus syllabus = sDao.getAllSyllabusById(id);
            req.setAttribute("id", id);
            req.setAttribute("syllabus", syllabus);
            req.setAttribute("topic", topic);
            req.setAttribute("materials", materials);
            req.setAttribute("lo", lo);
            req.setAttribute("learning", learning);
            req.setAttribute("itu", itu);
            req.setAttribute("task", task);
        req.getRequestDispatcher("view/admin/addSession.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        authorization(req, resp);
        SessionDao sessionDao = new SessionDao();
            String mess = "";
            String messs = "";
            String topic = req.getParameter("topic");
            String materials = req.getParameter("materials");
            String learning = req.getParameter("learning");
            int syllabus_id = Integer.parseInt(req.getParameter("syllabus_id"));
            String lo = req.getParameter("lo");
            String itu = req.getParameter("itu");
            String task = req.getParameter("task");
            boolean checkAdd = sessionDao.addSession(topic,learning,syllabus_id
                    ,lo,itu,materials,task);
            if(checkAdd) {
                mess = "Add success";
                int id = Integer.parseInt(req.getParameter("syllabusId"));
            
            HttpSession session = req.getSession();
            session.setAttribute("messSuccess", mess);
            resp.sendRedirect(Constants.URL_PROJECT + Constants.URL_SESSIONLIST + "?syllabusId=" + id);
            }else {
                messs = "Add fail please add again";
                int id = Integer.parseInt(req.getParameter("syllabusId"));
            req.setAttribute("id", id);
            req.setAttribute("topic", topic);
            req.setAttribute("materials", materials);
            req.setAttribute("lo", lo);
            req.setAttribute("learning", learning);
            req.setAttribute("itu", itu);
            req.setAttribute("task", task);
            HttpSession session = req.getSession();
            session.setAttribute("messFail", messs);
            resp.sendRedirect(Constants.URL_PROJECT + Constants.URL_SESSIONADD + "?syllabusId=" + id);
            }
            
    }
    
    
    
    
}
