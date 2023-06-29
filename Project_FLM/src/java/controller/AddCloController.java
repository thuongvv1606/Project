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
import java.util.ArrayList;
import model.Clo;
import model.Syllabus;
import util.Constants;

/**
 *
 * @author NgTienDung
 */
@WebServlet(name = Constants.URL_ADDCLO, urlPatterns = {Constants.URL_ADDCLO})
public class AddCloController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idsyllabus = request.getParameter("sylId");
        Syllabus syllabus = new SyllabusDao().getAllSyllabusById(Integer.parseInt(idsyllabus));

        ArrayList<Clo> listClos = new CloDao().getAllBySyllabusId(Integer.parseInt(idsyllabus));

        request.setAttribute("syllabus", syllabus);
        request.setAttribute("listClos", listClos);
        request.getRequestDispatcher("/view/common/addClo.jsp").forward(request, response);
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
        String idsyllabus = request.getParameter("sylId");
        Clo clo = new Clo();
        clo.setCloName(request.getParameter("name"));
        clo.setCloDescription(request.getParameter("description"));
        clo.setSyllabusId(Integer.parseInt(idsyllabus));
        clo.setCloParentId(Integer.parseInt(request.getParameter("idCloParent")));
        request.setAttribute("clo", clo);
        if (new CloDao().ckeckCloByName(clo.getCloName(), clo.getSyllabusId())) {
            request.setAttribute("error", "name is exist");
        } else {
            request.setAttribute("okSuccc", "add clo successful");
            int idClo = clo.getCloParentId() == 0 ? new CloDao().addNoParent(clo) : new CloDao().addHaveParent(clo);

        }
        Syllabus syllabus = new SyllabusDao().getAllSyllabusById(Integer.parseInt(idsyllabus));
        request.setAttribute("syllabus", syllabus);
        request.getRequestDispatcher("/view/common/addClo.jsp").forward(request, response);

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
