/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CurriculumDao;
import dao.PoDao;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Curriculum;
import model.Po;
import util.Constants;

/**
 *
 * @author LanChau
 */
@WebServlet(name = Constants.URL_POLIST, urlPatterns = {Constants.URL_POLIST})
public class PoListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idCurrent = Integer.parseInt(request.getParameter("idCurrent").trim());
            Curriculum curriculum = new CurriculumDao().getCurriculumByID(idCurrent);
            request.setAttribute("curriculum", curriculum);
            
            String name = "";
            try {
                if (request.getParameter("name") != null) {
                    name = request.getParameter("name").trim();
                }
            } catch (Exception e) {
            }

            ArrayList<Po> listPo = new PoDao().getAllByCurriculumWithName(idCurrent, name);
            request.setAttribute("listPo", listPo);
            request.setAttribute("idCurrent", idCurrent);
            request.setAttribute("name", name);

            request.getRequestDispatcher("/view/common/poList.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

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
