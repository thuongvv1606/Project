/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.*;
import util.Constants;

/**
 *
 * @author DungNT
 */
@WebServlet(name = Constants.URL_SUBJECTPLOMAPPING, urlPatterns = {Constants.URL_SUBJECTPLOMAPPING})
public class SubjectPloMappingController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           int idCurrent = Integer.parseInt(request.getParameter("idCurrent"));

            ArrayList<MappingPLOSubject> listMappingPLOSubject = new MappingPLOSubjectDao().getAll(idCurrent);
            ArrayList<Plo> listPlo = new PloDao().getAllByCurrent(idCurrent);
             
            ArrayList<Subject> listSubjects = new SubjectDao().getAllByCurrent(idCurrent);

            request.setAttribute("idCurrent", idCurrent);
            request.setAttribute("listMappingPLOSubject", listMappingPLOSubject);
            request.setAttribute("listPlo", listPlo);
            request.setAttribute("listSubjects", listSubjects);

            Curriculum curriculum = new CurriculumDao().getCurriculumByID(idCurrent);
            request.setAttribute("curriculum", curriculum);
            request.getRequestDispatcher("/view/common/subjectPloMapping.jsp").forward(request, response);


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
        int idCurrent = Integer.parseInt(request.getParameter("idCurrent"));

        MappingPLOSubjectDao mappingPLOSubjectDao = new MappingPLOSubjectDao();

        ArrayList<Plo> listPlo = new PloDao().getAllByCurrent(idCurrent);
        ArrayList<Subject> listSubjects = new SubjectDao().getAllByCurrent(idCurrent);

        boolean check = false;
        String name = "";

        for (Subject b : listSubjects) {
            for (Plo a : listPlo) {
                name = "mapping" + b.getId() + "_" + a.getId() + "";
                check = mappingPLOSubjectDao.check(a.getId(), b.getId());
                if (request.getParameter(name) == null && check == true) {
                    mappingPLOSubjectDao.delete(a.getId(), b.getId());
                }
                if (request.getParameter(name) != null && check == false) {
                    mappingPLOSubjectDao.add(a.getId(), b.getId());
                }

                //response.getWriter().println(idCurrent+"--"+ b.getId()+"--"+ a.getId()+"--"+check +"                  ");
            }
        }
        
        response.sendRedirect(Constants.URL_PROJECT+Constants.URL_SUBJECTPLOMAPPING+"?idCurrent="+idCurrent);

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
