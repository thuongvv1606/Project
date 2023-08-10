/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import util.Constants;
import dao.*;
import model.Curriculum;
import model.Subject;

/**
 *
 * @author DungNT
 */
@WebServlet(name = Constants.URL_SUBJECTLIST, urlPatterns = {Constants.URL_SUBJECTLIST})
public class SubjectListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SubjectDao subjectsDao = new SubjectDao();
        int searchBy = -1;
        String inputSearch = "";
        String code = "";
        String name = "";
        if (request.getParameter("inputSearch") != null) {
            inputSearch = request.getParameter("inputSearch").trim();
        }

        try {
            searchBy = Integer.parseInt(request.getParameter("searchBy"));
            if (searchBy == 1) {
                code = inputSearch;
            }
            if (searchBy == 0) {
                name = inputSearch;
            }
            request.setAttribute("inputSearch", inputSearch);
            request.setAttribute("searchBy", searchBy);
        } catch (Exception e) {
        }
        try {
            
             ArrayList<Subject> listSubjects = subjectsDao.getAllListSubject(code, name);
            for (Subject listSubject : listSubjects) {
                System.out.println(listSubject.getName());
            }
            request.setAttribute("listSubjects2", listSubjects);
        } catch (Exception e) {
        }
        request.getRequestDispatcher("/view/common/subjectList.jsp").forward(request, response);
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
