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
@WebServlet(name = Constants.URL_EDITSUBJECT, urlPatterns = {Constants.URL_EDITSUBJECT})
public class EditSubjectController extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            int idSubject = Integer.parseInt(request.getParameter("id"));
            int idCurrent = Integer.parseInt(request.getParameter("curriculumID"));

            Subject subject = new SubjectDao().getSubjectById(idSubject);
            Curriculum curriculum = new CurriculumDao().getCurriculumByID(idCurrent);
            request.setAttribute("curriculum", curriculum);
            request.setAttribute("subject", subject);

            request.getRequestDispatcher("/view/common/editSubject.jsp").forward(request, response);

        } catch (Exception e) {
            response.getWriter().print("Loi: " + e.getMessage());
        }
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        int idCurrent = Integer.parseInt(request.getParameter("idCurrent"));
        Subject subject = new SubjectDao().getSubjectById(Integer.parseInt(request.getParameter("idSubject")));
        SubjectDao subjectDao = new SubjectDao();

        subject.setName(request.getParameter("subjectName").trim());
        subject.setSemester(Integer.parseInt(request.getParameter("semester").trim()));
        subject.setNo_credit(Integer.parseInt(request.getParameter("noCredit").trim()));
        subject.setPre_condition(request.getParameter("pCondition"));
        if (!subject.getCode().equals(request.getParameter("subjectCode").trim())) {
            if (subjectDao.checkCodeSubject(request.getParameter("subjectCode").trim())) {
                subject.setCode(request.getParameter("subjectCode").trim());
                request.setAttribute("subject", subject);
                request.setAttribute("error", "Code is exist");

                Curriculum curriculum = new CurriculumDao().getCurriculumByID(idCurrent);
                request.setAttribute("curriculum", curriculum);

                request.getRequestDispatcher("/view/common/editSubject.jsp").forward(request, response);
                return;
            }
        }else {
            subject.setCode(request.getParameter("subjectCode").trim());
            subjectDao.update(subject);
            response.sendRedirect(Constants.URL_PROJECT + Constants.URL_SUBJECTLIST+ "?idCurrent="+idCurrent);
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
