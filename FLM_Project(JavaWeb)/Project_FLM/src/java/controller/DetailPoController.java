/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CurriculumDao;
import dao.PoDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Curriculum;
import model.Po;
import util.Constants;

/**
 *
 * @author NCC
 */
@WebServlet(name = Constants.URL_DETAILPO, urlPatterns = {Constants.URL_DETAILPO})
public class DetailPoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idCurrent = Integer.parseInt(request.getParameter("idCurrent"));
        request.setAttribute("idCurrent", idCurrent);

        int idPo = Integer.parseInt(request.getParameter("idPo"));
        Po po = new PoDao().getPoById(idPo);

        request.setAttribute("po", po);

        Curriculum curriculum = new CurriculumDao().getCurriculumByID(idCurrent);
        request.setAttribute("curriculum", curriculum);
        request.getRequestDispatcher("/view/common/detailPo.jsp").forward(request, response);

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
