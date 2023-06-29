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
 * @author LanChau
 */
@WebServlet(name = Constants.URL_ADDPO, urlPatterns = {Constants.URL_ADDPO})
public class PoAddController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idCurrent = Integer.parseInt(request.getParameter("idCurrent"));
        request.setAttribute("idCurrent", idCurrent);
        Curriculum curriculum = new CurriculumDao().getCurriculumByID(idCurrent);
        request.setAttribute("curriculum", curriculum);
        request.getRequestDispatcher("/view/common/addPo.jsp").forward(request, response);
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
        request.setAttribute("idCurrent", idCurrent);
        Po po = new Po();
        po.setPoName(request.getParameter("name").trim());
        po.setPoDescription(request.getParameter("description").trim());
        po.setCurriculumId(idCurrent);
        PoDao poDao = new PoDao();
        if (poDao.checkCode(po.getPoName(), idCurrent)) {
            request.setAttribute("po", po);
            request.setAttribute("error", "Name exists");

        } else {
            poDao.add(po);
            request.setAttribute("okSuccc", "Add successfully");
        }
        Curriculum curriculum = new CurriculumDao().getCurriculumByID(idCurrent);
        request.setAttribute("curriculum", curriculum);
        request.getRequestDispatcher("/view/common/addPo.jsp").forward(request, response);

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
