/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.CurriculumDao;
import dao.PloDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.Curriculum;
import model.Plo;
import util.Constants;

/**
 *
 * @author trinh
 */
@WebServlet(name = Constants.URL_PLODEDIT, urlPatterns = {Constants.URL_PLODEDIT})
public class EditPloController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String a = req.getParameter("ploId");
        int id = Integer.parseInt(req.getParameter("ploId"));
        int id_curi = Integer.parseInt(req.getParameter("idCurrent"));
        req.setAttribute("a", a);
        PloDao ploDao = new PloDao();
        Plo plo = new Plo();
        plo = ploDao.getPloByID(id);
        Curriculum curriculum = new CurriculumDao().getCurriculumByID(id_curi);
//        req.setAttribute("b", id_curi);
        req.setAttribute("curriculum", curriculum);
        req.setAttribute("plo", plo);
        req.getRequestDispatcher("view/admin/editPlo.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("update") != null) {
            String ploName = req.getParameter("name");
            String ploDescription = req.getParameter("description");
            int curriculum_id = Integer.parseInt(req.getParameter("curri_id"));
            int id = Integer.parseInt(req.getParameter("idSetting"));
            String mess = "";
            PloDao curiDao = new PloDao();
            

            if (ploName.isEmpty() || ploDescription.isEmpty() || curriculum_id == 0) {
                mess = "Please input full data!";
                req.setAttribute("mess", mess);
                req.setAttribute("a", id);
//                Plo ploa = new Plo();
//                    ploa = curiDao.getPloByID(id);
//                    req.setAttribute("plo", ploa);
                req.getRequestDispatcher("view/admin/editPlo.jsp").forward(req, resp);
                return;
            } else {
                boolean plo = curiDao.updatePloById(ploName, ploDescription, curriculum_id, id);
                if (plo) {
                    mess = "Update sucess!";
                    Plo ploa = new Plo();
                    ploa = curiDao.getPloByID(id);
                    req.setAttribute("plo", ploa);
                } else {
                    mess = "Update false pls update agin";
                }
            }
            Curriculum curriculum = new CurriculumDao().getCurriculumByID(curriculum_id);
        req.setAttribute("curriculum", curriculum);
            req.setAttribute("mess", mess);
            req.setAttribute("a", id);

            req.getRequestDispatcher("view/admin/editPlo.jsp").forward(req, resp);

        }
    }

}
