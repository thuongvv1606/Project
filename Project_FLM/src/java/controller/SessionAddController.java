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
import util.Constants;

/**
 *
 * @author trinh
 */
@WebServlet(name = Constants.URL_SESSIONADD, urlPatterns = {Constants.URL_SESSIONADD})
public class SessionAddController extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            req.getRequestDispatcher("view/admin/addSession.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            SessionDao sessionDao = new SessionDao();
            String mess = "";
            String name = req.getParameter("name");
            String detail = req.getParameter("description");
            int syllabus_id = Integer.parseInt(req.getParameter("syllabus_id"));
            String learning = req.getParameter("learning");
            String materials = req.getParameter("materials");
            String task = req.getParameter("task");
            boolean checkAdd = sessionDao.addSession(name,syllabus_id,detail,learning,materials,task);
            if(checkAdd) {
                mess = "Add success";
            }else {
                mess = "Add fail please add again";
            }
            req.setAttribute("name", name);
            req.setAttribute("detail", detail);
            req.setAttribute("syllabus_id", syllabus_id);
            req.setAttribute("learning", learning);
            req.setAttribute("materials", materials);
            req.setAttribute("task", task);
            req.setAttribute("mess", mess);
            req.getRequestDispatcher("view/admin/addSession.jsp").forward(req, resp);
    }
    
    
    
    
}
