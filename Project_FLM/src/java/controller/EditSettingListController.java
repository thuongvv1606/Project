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
import model.Setting;
import util.Constants;

/**
 *
 * @author DungNT
 */
@WebServlet(name = Constants.URL_EDITSETTING, urlPatterns = {Constants.URL_EDITSETTING})
public class EditSettingListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Setting setting = new SettingDao().getSettingById(id);
            request.setAttribute("setting", setting);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/view/common/editSetting.jsp").forward(request, response);

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
        try {
            int id = Integer.parseInt(request.getParameter("idSetting"));
            String type = request.getParameter("type");
            String title = request.getParameter("title");
            String value = request.getParameter("value");
            String details = request.getParameter("details");
            int status = Integer.parseInt(request.getParameter("status"));
            int displayOrder = Integer.parseInt(request.getParameter("displayOrder"));
            new SettingDao().updateSetting(id, title, value, status, details, type,displayOrder );

        } catch (Exception e) {
             e.printStackTrace();
        }
        response.sendRedirect(Constants.URL_PROJECT + Constants.URL_SETTINGLIST);
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
