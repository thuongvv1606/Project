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
@WebServlet(name = Constants.URL_EDITCLO, urlPatterns = {Constants.URL_EDITCLO})
public class EditCloController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idsyllabus = request.getParameter("sylId");
        String cloId = request.getParameter("cloId");

        Clo clo = new CloDao().getCloById(Integer.parseInt(cloId), "");
        clo.setCloParent(new CloDao().getCloById(clo.getCloParentId(), ""));
        Syllabus syllabus = new SyllabusDao().getAllSyllabusById(Integer.parseInt(idsyllabus));

        ArrayList<Clo> listClos = new CloDao().getAllBySyllabusId(Integer.parseInt(idsyllabus));

        request.setAttribute("clo", clo);
        request.setAttribute("syllabus", syllabus);
        request.setAttribute("listClos", listClos);
        request.getRequestDispatcher("/view/common/editClo.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idsyllabus = request.getParameter("sylId");
        int id = Integer.parseInt(request.getParameter("cloId"));
        Clo clo = new Clo();
        clo.setCloId(id);
        clo.setCloName(request.getParameter("name").trim());
        clo.setCloDescription(request.getParameter("description").trim());
        clo.setSyllabusId(Integer.parseInt(idsyllabus));
        clo.setCloParentId(Integer.parseInt(request.getParameter("idCloParent")));
        request.setAttribute("clo", clo);
        
        Clo cloOld = new CloDao().getCloById(id, "");

        if (!cloOld.getCloName().equals(clo.getCloName())
                && new CloDao().ckeckCloByName(clo.getCloName(), clo.getSyllabusId())) {
            request.setAttribute("error", "name is exist");
        } else {
            request.setAttribute("okSuccc", "update clo successful");
            new CloDao().update(clo);

        }
        Syllabus syllabus = new SyllabusDao().getAllSyllabusById(Integer.parseInt(idsyllabus));
        request.setAttribute("syllabus", syllabus);
        request.getRequestDispatcher("/view/common/editClo.jsp").forward(request, response);

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
