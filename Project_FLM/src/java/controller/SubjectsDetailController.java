/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CurriculumDao;
import dao.SubjectDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Curriculum;
import model.Subject;
import util.Constants;

/**
 *
 * @author DungNT
 */
@WebServlet(name = Constants.URL_SUBJECTDETAIL, urlPatterns = {Constants.URL_SUBJECTDETAIL})
public class SubjectsDetailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idSubject ;
        int idCurrent;
        if (request.getParameter("subCode")!= null && request.getParameter("curriculumID") != null) {

            try {
                 idSubject = Integer.parseInt(request.getParameter("subCode"));
                idCurrent = Integer.parseInt(request.getParameter("curriculumID"));

                Subject subject = new SubjectDao().getSubjectById(idSubject);
                Curriculum curriculum = new CurriculumDao().getCurriculumByID(idCurrent);
                request.setAttribute("curriculum", curriculum);
                request.setAttribute("subject", subject);
            } catch (Exception e) {
                response.getWriter().print("Loi: " + e.getMessage());
            }
        }

        if (request.getParameter("subCode") != null) {
            try {
                idSubject = Integer.parseInt(request.getParameter("subCode"));
                Subject subject = new SubjectDao().getSubjectById(idSubject);
                request.setAttribute("subject", subject);
            } catch (Exception e) {
                response.getWriter().print("Loi: " + e.getMessage());
            }
        }
        request.getRequestDispatcher("/view/common/subjectDetail.jsp").forward(request, response);
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

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
