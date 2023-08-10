/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.SubjectDao;
import dao.SyllabusDao;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Pre_requisite;
import model.Syllabus;
import util.Constants;

/**
 *
 * @author hp
 */
@WebServlet(name = Constants.URL_SUBJECTPREDECESSOER, urlPatterns = {Constants.URL_SUBJECTPREDECESSOER})
public class SubjectPredecessorController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/view/common/subjectPredecessor.jsp").forward(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SyllabusDao sdao = new SyllabusDao();
        SubjectDao subjectDao = new SubjectDao();
        String key = request.getParameter("key");
        boolean check = subjectDao.checkSubject(key);
        if (check == true) {
            Syllabus s = sdao.getPre(key);
            ArrayList<Syllabus> sList = new ArrayList<>();
            if (s == null) {
                request.setAttribute("key", key);
                request.setAttribute("mess", "Syllabus is null");
                request.getRequestDispatcher("/view/common/subjectPredecessor.jsp").forward(request, response);
            } else {
                ArrayList<Pre_requisite> p = s.getPre() == null ? new ArrayList<>() : s.getPre();
                for (Pre_requisite pre_requisite : p) {
                    sList.add(sdao.getPre(String.valueOf(pre_requisite.getPreId())));
                }
                request.setAttribute("s", s);
                request.setAttribute("sList", sList);
                request.setAttribute("key", key);
                request.getRequestDispatcher("/view/common/subjectPredecessor.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("mess", "Subject code " + key + " does not exist");
            request.setAttribute("key", key);
            request.getRequestDispatcher("/view/common/subjectPredecessor.jsp").forward(request, response);
        }

    }
}
