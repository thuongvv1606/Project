/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Curriculum;
import model.Subject;
import model.SubjectGroup;
import model.User;
import util.Constants;

/**
 *
 * @author DungNT
 */
@WebServlet(name = Constants.URL_ADDSUBJECT, urlPatterns = {Constants.URL_ADDSUBJECT})
public class AddSubjectListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("idCurrent"));
            Curriculum curriculum = new CurriculumDao().getCurriculumByID(id);
            request.setAttribute("curriculum", curriculum);

            ArrayList<SubjectGroup> listSubjectGroups = new SubjectGroupDao().getAll(id);
            request.setAttribute("listSubjectGroups", listSubjectGroups);
            request.setAttribute("idCurrent", id);
            request.getRequestDispatcher("/view/common/addSubject.jsp").forward(request, response);

        } catch (Exception e) {
            response.getWriter().print("Loi: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Subject subject = new Subject();
            subject.setCode(request.getParameter("subjectCode").trim());
            subject.setName(request.getParameter("subjectName").trim());
            subject.setSemester(Integer.parseInt(request.getParameter("semester").trim()));
            subject.setNo_credit(Integer.parseInt(request.getParameter("noCredit").trim()));
            subject.setPre_condition(request.getParameter("pCondition").trim());

            String subjectGroup = request.getParameter("sGroup");
            int idCurrent = Integer.parseInt(request.getParameter("idCurrent"));
            request.setAttribute("idCurrent", idCurrent);
            request.setAttribute("subjectGroup", subjectGroup);

            SubjectDao subjectsDao = new SubjectDao();
            if (subjectsDao.checkCodeSubject(subject.getCode(), idCurrent)) {
                request.setAttribute("error", "Code is exist");
            } else {
                User user = (User) request.getSession().getAttribute("user");
                int idSubject = subjectsDao.add(subject);
                new CurriculumDao().addCuriculumSubject(idSubject, Integer.parseInt(subjectGroup), idCurrent, user.getUserId());
                request.setAttribute("okSuccc", "Add success");
            }
            ArrayList<SubjectGroup> listSubjectGroups = new SubjectGroupDao().getAll(idCurrent);
            request.setAttribute("listSubjectGroups", listSubjectGroups);
            request.setAttribute("subject", subject);
            request.getRequestDispatcher("/view/common/addSubject.jsp").forward(request, response);

        } catch (Exception e) {
            response.getWriter().print("Loi: " + e.getMessage());
            e.printStackTrace();
        }
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
