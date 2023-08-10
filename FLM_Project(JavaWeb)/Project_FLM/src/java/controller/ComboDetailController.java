/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import dao.ComboDao;
import dao.CurriculumDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Combo;
import model.Curriculum;
import model.Subject;

/**
 *
 * @author hp
 */
@WebServlet(urlPatterns = {"/comboDetail"})
public class ComboDetailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String curId = request.getParameter("curId");
        String comboId = request.getParameter("comboId");
        String subjectId = request.getParameter("subjectId");
        CurriculumDao curiDao = new CurriculumDao();
        ComboDao comboDao = new ComboDao();

        if (comboId != null && subjectId != null) {
            comboDao.deleteSubjectCombo(Integer.parseInt(comboId), Integer.parseInt(subjectId));
            request.setAttribute("messSuccess", "Delete successfully subject combo has code = " + comboId);
        }

        Curriculum curriculum = curiDao.getCurriculumByID(Integer.parseInt(curId));
        ArrayList<Subject> subjectList = comboDao.getSubjectByCombo(Integer.parseInt(curId), Integer.parseInt(comboId));
        Combo combo = comboDao.getCombo(Integer.parseInt(comboId));

        request.setAttribute("curId", curId);
        request.setAttribute("comboId", comboId);
        request.setAttribute("combo", combo);
        request.setAttribute("curriculum", curriculum);
        request.setAttribute("subjectList", subjectList);
        request.getRequestDispatcher("/view/common/comboDetail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String curId = request.getParameter("curId");
        String comboId = request.getParameter("comboId");
        String subjectId = request.getParameter("subjectId");
        String comboName = request.getParameter("comboName");
        String comboEnglishName = request.getParameter("comboEnglishName");
        CurriculumDao curiDao = new CurriculumDao();
        ComboDao comboDao = new ComboDao();

        boolean check = comboDao.updateCombo(new Combo(Integer.parseInt(comboId), Integer.parseInt(curId), "", comboName, comboEnglishName));
        if (check) {
            request.setAttribute("messSuccess", "Edit successfully ");

        } else {
            String messFail = "Add failed";
            request.setAttribute("messFail", messFail);

        }
        Curriculum curriculum = curiDao.getCurriculumByID(Integer.parseInt(curId));
        ArrayList<Subject> subjectList = comboDao.getSubjectByCombo(Integer.parseInt(curId), Integer.parseInt(comboId));
        Combo combo = comboDao.getCombo(Integer.parseInt(comboId));

        request.setAttribute("curId", curId);
        request.setAttribute("comboId", comboId);
        request.setAttribute("combo", combo);
        request.setAttribute("curriculum", curriculum);
        request.setAttribute("subjectList", subjectList);
        request.getRequestDispatcher("/view/common/comboDetail.jsp").forward(request, response);
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
