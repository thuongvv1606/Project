/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CloDao;
import dao.SyllabusDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Clo;
import model.Syllabus;
import model.User;
import util.Constants;

/**
 *
 * @author DungNT
 */
@WebServlet(name = Constants.URL_DETAILCLO, urlPatterns = {Constants.URL_DETAILCLO})
public class DetailCloController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        String active = "1";
        if (user != null && user.getRoleFunction() == 2) {
            active = "";
        }

        String idsyllabus = request.getParameter("sylId");
        Syllabus syllabus = new SyllabusDao().getAllSyllabusById(Integer.parseInt(idsyllabus));
        int idClo = Integer.parseInt(request.getParameter("cloId"));
        
        Clo clo = new CloDao().getCloById(idClo, active);
        
        if (clo.getCloParentId() != 0) {
            clo.setCloParent(new CloDao().getCloById(clo.getCloParentId(), ""));
        }
        request.setAttribute("syllabus", syllabus);
        request.setAttribute("clo", clo);

        request.getRequestDispatcher("/view/common/detailClo.jsp").forward(request, response);

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
