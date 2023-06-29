/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.SettingDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Setting;
import util.Constants;

/**
 *
 * @author NgTienDung
 */
@WebServlet(name = Constants.URL_SETTINGLIST, urlPatterns = {Constants.URL_SETTINGLIST})
public class SettingListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String title = "";
        if (request.getParameter("title") != null) {
            title = request.getParameter("title").trim();
        }
        String type = "";
        if (request.getParameter("type") != null) {
            type = request.getParameter("type").trim();
        }
        
        List<Setting> listSettings = new SettingDao().getAll(title, type, -1);
        
        request.setAttribute("listSettingss", listSettings);
        request.setAttribute("type", type);
        request.setAttribute("title", title);
        request.getRequestDispatcher("/view/common/settingList.jsp").forward(request, response);

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
