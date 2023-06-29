/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.CurriculumDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.Curriculum;
import util.Constants;

/**
 *
 * @author Admin
 */
@WebServlet(name = Constants.URL_CURRICULUMEDIT, urlPatterns = {Constants.URL_CURRICULUMEDIT})
public class CurriculumEditController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String a = req.getParameter("curId");
        int id =  Integer.parseInt(req.getParameter("curId"));
        req.setAttribute("a", a);
        CurriculumDao curiDao = new CurriculumDao();
        Curriculum curriculum = new Curriculum();
            curriculum = curiDao.getCurriculumByID(id);
            req.setAttribute("curriculum", curriculum);
         req.getRequestDispatcher("view/common/curriculumEdit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("update") != null) {
            String curriculumCode = req.getParameter("code");
            String curriculumName = req.getParameter("name");
            String curriculumDescription = req.getParameter("description");
            String curriculumDecision = req.getParameter("decision");
            HttpSession session = req.getSession();
            int id =  Integer.parseInt(req.getParameter("idSetting"));
            String mess = "";
            CurriculumDao curiDao = new CurriculumDao();
            
            if (curriculumCode == null || curriculumName == null || curriculumDescription == null || curriculumDecision == null) {
                mess = "Please input full data!";
                req.setAttribute("mess", mess);
                req.getRequestDispatcher("view/common/curriculumEdit.jsp").forward(req, resp);
                return;
            } else {
                boolean curri = curiDao.updateCurriculumById(id, curriculumCode, curriculumName, curriculumDescription, curriculumDecision);
                if (curri) {
                    mess = "Update sucess!";
                } else {
                    mess = "Update false pls update agin";
                }
            }
            req.setAttribute("mess", mess);
            
            req.getRequestDispatcher("view/common/curriculumEdit.jsp").forward(req, resp);

        }
    }

}
