/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.SubjectDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import model.Elective;
import model.Subject;
import util.Constants;

/**
 *
 * @author TT
 */
@WebServlet(name = Constants.URL_ELECTIVEDETAIL, urlPatterns = {Constants.URL_ELECTIVEDETAIL})
public class ElectiveDetailController extends HttpServlet {
    protected void processResponse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SubjectDao sDao = new SubjectDao();
        HttpSession session = req.getSession();
        Elective e = (Elective) session.getAttribute("e");
        req.setAttribute("e", e);
        String status = req.getParameter("status");
        req.setAttribute("status", status);
        List<Subject> sList = sDao.getSubjectByElectiveId(e.getId());
        req.setAttribute("sList", sList);
        req.getRequestDispatcher("/view/common/electiveDetail.jsp").forward(req, resp);
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
