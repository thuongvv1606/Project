/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CurriculumDao;
import dao.DecisionDao;
import dao.SyllabusDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Curriculum;
import model.Decision;
import model.Syllabus;

/**
 *
 * @author hp
 */
@WebServlet(name = "DecisionDetailController", urlPatterns = {"/decisionDetail"})
public class DecisionDetailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id_raw = request.getParameter("decisionId");
        int id = Integer.parseInt(id_raw);
        DecisionDao decisionDao = new DecisionDao();
        SyllabusDao syllabusDao = new SyllabusDao();
        CurriculumDao curriculumDao = new CurriculumDao();
        ArrayList<Syllabus> syllabusList = new ArrayList<>();
        syllabusList = syllabusDao.getAllSyllabus();
        ArrayList<Curriculum> curriculumList = new ArrayList<>();
        curriculumList = curriculumDao.getAllCurriculumList();
        Decision decision = decisionDao.getDecisionByID(id);
        request.setAttribute("syllabusList", syllabusList);
        request.setAttribute("curriculumList", curriculumList);
        request.setAttribute("decision", decision);
        request.setAttribute("decisionId", id);
        request.getRequestDispatcher("/view/common/decisionDetail.jsp").forward(request, response);

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
        String decision_Id_raw = request.getParameter("decisionId");
        String decision_no = request.getParameter("decisionNo");
        String decision_name = request.getParameter("decisionName");
        String decision_date = request.getParameter("decisionDate");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = new Date();
        try {
            date = df.parse(decision_date);
        } catch (ParseException ex) {
            Logger.getLogger(DecisionAddController.class.getName()).log(Level.SEVERE, null, ex);
        }

        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        boolean is_active = true;
        if (request.getParameter("active").equals("1")) {
            is_active = true;
        } else if (request.getParameter("active").equals("0")) {
            is_active = false;
        }
        DecisionDao decisionDao = new DecisionDao();
        SyllabusDao syllabusDao = new SyllabusDao();
        CurriculumDao curriculumDao = new CurriculumDao();
        ArrayList<Syllabus> syllabusList = new ArrayList<>();
        ArrayList<Curriculum> curriculumList = new ArrayList<>();

        boolean checkNo = decisionDao.checkNo(decision_no);
        String message;
        String error = null;
        int decision_Id = Integer.parseInt(decision_Id_raw);
        boolean check = decisionDao.updateDecision(new Decision(decision_Id, decision_no, decision_name, sqlDate, is_active));
        if (!check) {
            error = "Unsuccessful!";
            Decision decision = decisionDao.getDecisionByID(decision_Id);
            syllabusList = syllabusDao.getAllSyllabus();
            curriculumList = curriculumDao.getAllCurriculumList();
            request.setAttribute("decision", decision);
            request.setAttribute("syllabusList", syllabusList);
            request.setAttribute("curriculumList", curriculumList);
            request.getRequestDispatcher("/view/common/decisionDetail.jsp").forward(request, response);
        }
        String[] curriculum = request.getParameterValues("curriculum");
        boolean checkDecision = true;
        if (curriculum != null) {
            for (String string : curriculum) {
                if(string != null){
                checkDecision = curriculumDao.updateDecision(Integer.parseInt(string), decision_Id);
                }
            }
        }
        if (!checkDecision) {
            error = "Unsuccessful!";
            Decision decision = decisionDao.getDecisionByID(decision_Id);
            syllabusList = syllabusDao.getAllSyllabus();
            curriculumList = curriculumDao.getAllCurriculumList();
            request.setAttribute("decision", decision);
            request.setAttribute("syllabusList", syllabusList);
            request.setAttribute("curriculumList", curriculumList);
            request.getRequestDispatcher("/view/common/decisionDetail.jsp").forward(request, response);
        }

        String[] syllabus = request.getParameterValues("syllabus");
        boolean checkSyllabus = true;
        if (syllabus != null) {
            for (String syllabu : syllabus) {
                if(syllabu != null){
                checkSyllabus = syllabusDao.updateDecision(Integer.parseInt(syllabu), decision_Id);
                }
            }
        }
        if (!checkSyllabus) {
            error = "Unsuccessful!";
            Decision decision = decisionDao.getDecisionByID(decision_Id);
            syllabusList = syllabusDao.getAllSyllabus();
            curriculumList = curriculumDao.getAllCurriculumList();
            request.setAttribute("decision", decision);
            request.setAttribute("syllabusList", syllabusList);
            request.setAttribute("curriculumList", curriculumList);
            request.getRequestDispatcher("/view/common/decisionDetail.jsp").forward(request, response);
        }

        message = "Success";
        Decision decision = decisionDao.getDecisionByID(decision_Id);
        syllabusList = syllabusDao.getAllSyllabus();
        curriculumList = curriculumDao.getAllCurriculumList();
        request.setAttribute("decision", decision);
        request.setAttribute("syllabusList", syllabusList);
        request.setAttribute("curriculumList", curriculumList);
        request.setAttribute("message", message);
        request.setAttribute("error", error);

        request.getRequestDispatcher("/view/common/decisionDetail.jsp").forward(request, response);

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
