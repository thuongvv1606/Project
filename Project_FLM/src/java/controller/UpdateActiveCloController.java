/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CloDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.Constants;

/**
 *
 * @author NgTienDung
 */
@WebServlet(name = Constants.URL_UPDATEACTIVECLO, urlPatterns = {Constants.URL_UPDATEACTIVECLO})
public class UpdateActiveCloController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idsyllabus = request.getParameter("sylId");
        String cloId = request.getParameter("cloId");
        String index = request.getParameter("index");
        String nameSearch = request.getParameter("nameSearch");
        String filterByStatus = request.getParameter("filterByStatus");
        boolean active = request.getParameter("active").equals("1");
        new CloDao().updateActive(Integer.parseInt(cloId), active);

        response.sendRedirect(Constants.URL_PROJECT + Constants.URL_CLOLIST + "?sylId="
                + idsyllabus + "&index=" + index + "&nameSearch=" + nameSearch
        + "&filterByStatus=" + filterByStatus);

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
