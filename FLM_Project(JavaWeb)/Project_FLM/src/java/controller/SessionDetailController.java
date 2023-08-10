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
import java.io.IOException;
import model.Session;
import model.Syllabus;
import util.Constants;

/**
 *
 * @author trinh
 */
@WebServlet(name = Constants.URL_SESSIONDETAIL, urlPatterns = {Constants.URL_SESSIONDETAIL})
public class SessionDetailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionDao sessionDao = new SessionDao();

        int id = Integer.parseInt(req.getParameter("sessionId"));
        Session session = sessionDao.getALlSessionById(id);
        SyllabusDao sDao = new SyllabusDao();
        Syllabus syllabus = sDao.getAllSyllabusById(session.getSyllabus_id());
        req.setAttribute("syllabus", syllabus);
        req.setAttribute("listSession", session);
        req.getRequestDispatcher("view/common/sessionDetail.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}
