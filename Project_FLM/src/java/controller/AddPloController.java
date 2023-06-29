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
import util.Constants;

/**
 *
 * @author trinh
 */
@WebServlet(name = Constants.URL_PLOADD, urlPatterns = {Constants.URL_PLOADD})
public class AddPloController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int curriculum_id = Integer.parseInt(req.getParameter("idCurrent"));
            Curriculum curriculum = new CurriculumDao().getCurriculumByID(curriculum_id);
            req.setAttribute("curriculum", curriculum);
            req.setAttribute("a", curriculum_id);
        req.getRequestDispatcher("/view/admin/addPlo.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("add") != null) {
            String name = req.getParameter("name");
            String description = req.getParameter("description");
            int curriculum_id = Integer.parseInt(req.getParameter("curri_id"));
            Curriculum curriculum = new CurriculumDao().getCurriculumByID(curriculum_id);
            req.setAttribute("curriculum", curriculum);
            int active = -1;
            if (req.getParameter("active") == "1") {
                active = 1;
            } else {
                active = 0;
            }
            String mess = "";
            if (name.isEmpty() || description.isEmpty()
                    || curriculum_id == 0 || active == -1) {
                mess = "Please input full data";
                req.setAttribute("mess", mess);
                req.getRequestDispatcher("/view/admin/addPlo.jsp").forward(req, resp);
                return;
            } else {
                PloDao plo = new PloDao();
                boolean checkAdd = plo.addNewPlo(name, description, curriculum_id, active);
                if (checkAdd) {
                    mess = "Add success";
                } else {
                    mess = "Add fail please add again";
                }
            }
            req.setAttribute("a", curriculum_id);
            req.setAttribute("mess", mess);
            req.setAttribute("name", name);
            req.setAttribute("description", description);
            req.setAttribute("curriculum_id", curriculum_id);
            req.setAttribute("active", active);
            req.getRequestDispatcher("/view/admin/addPlo.jsp").forward(req, resp);
        }

    }

}
