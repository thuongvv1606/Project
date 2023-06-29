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
 * @author NgTienDung
 */
@WebServlet(name = Constants.URL_ADDSETTING, urlPatterns = {Constants.URL_ADDSETTING})
public class AddSettingListController extends HttpServlet {
   
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
           request.getRequestDispatcher("/view/common/addSetting.jsp").forward(request, response);

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
        try {
            String type = request.getParameter("type");
            String title = request.getParameter("title");
            String value = request.getParameter("value");
            String details = request.getParameter("details"); 
             int displayOrder = Integer.parseInt(request.getParameter("displayOrder"));
            new SettingDao().addNew(title, type, value, 1, details, displayOrder);
            
        } catch (Exception e) {
        }
        response.sendRedirect(Constants.URL_PROJECT + Constants.URL_SETTINGLIST);
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
