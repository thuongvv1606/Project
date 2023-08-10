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
import java.util.ArrayList;
import model.Session;
import model.Syllabus;
import util.Constants;

/**
 *
 * @author trinh
 */
@WebServlet(name = Constants.URL_SESSIONLIST, urlPatterns = {Constants.URL_SESSIONLIST})

public class SessionListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionDao sessionDao = new SessionDao();
        String search = "";

        String indexPage = req.getParameter("index");

        int count = sessionDao.getSizeList();
        if (indexPage == null) {
            indexPage = "1";
        }
        int index = Integer.parseInt(indexPage);
        int endPage = count / 5;
        if (count % 5 != 0) {
            endPage++;
        }
        if (req.getParameter("key") != null) {
            search = req.getParameter("key").trim();
        }

        try {
            int id = Integer.parseInt(req.getParameter("syllabusId"));
            ArrayList<Session> listSession = sessionDao.getAllSessionBy(id, search, index);
            SyllabusDao sDao = new SyllabusDao();
            Syllabus syllabus = sDao.getAllSyllabusById(id);
            Session session = sessionDao.getSessionId(id);
            req.setAttribute("syllabus", syllabus);
            req.setAttribute("id", id);
            req.setAttribute("end", endPage);
            req.setAttribute("index", index);
            req.setAttribute("count", count);
            req.setAttribute("listSession", listSession);
            req.setAttribute("sessionId", session);
        } catch (Exception e) {
        }

        req.getRequestDispatcher("view/common/sessionList.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}
