/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.SessionDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.Session;
import util.Constants;

/**
 *
 * @author trinh
 */
@WebServlet(name = Constants.URL_SESSIONEDIT, urlPatterns = {Constants.URL_SESSIONEDIT})
public class SessionEditController extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionDao  sessionDao = new SessionDao();
        int id = Integer.parseInt(req.getParameter("sessionId"));
        Session session = sessionDao.getALlSessionById(id);
        req.setAttribute("id", id);
        req.setAttribute("listSession", session);
        req.getRequestDispatcher("view/admin/editSession.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionDao  sessionDao = new SessionDao();
        int id = Integer.parseInt(req.getParameter("sessionId"));
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        int syllabus_id = Integer.parseInt(req.getParameter("syllabus_id"));
        String learning = req.getParameter("learning");
        String materials = req.getParameter("materials");
        String task = req.getParameter("task");
        String mess = "";
        boolean checkUpdate = sessionDao.updateSession(name,description,syllabus_id,learning,materials,task,id);
        if(checkUpdate) {
            mess = "Update sucess!";
            SessionDao  sessionDaoo = new SessionDao();
            Session session = sessionDaoo.getALlSessionById(id);
            req.setAttribute("listSession", session);
        }else {
             mess = "Update false pls update agin"; 
        }
        
        req.setAttribute("mess", mess);
        req.setAttribute("id", id);
        resp.sendRedirect(Constants.URL_PROJECT+Constants.URL_SESSIONLIST+"?syllabusId="+syllabus_id);
        
    }
    
    
    
}
