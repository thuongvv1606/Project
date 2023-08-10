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
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import model.Curriculum;
import model.User;
import util.Constants;

/**
 *
 * @author trinh
 */
@WebServlet(name = Constants.URL_PLOADD, urlPatterns = {Constants.URL_PLOADD})
public class AddPloController extends HttpServlet {

    public void authorization(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        jakarta.servlet.http.HttpSession session = req.getSession();
        if (session.getAttribute("user") == null) {
            resp.setContentType("text/html;charset=UTF-8");
            try ( PrintWriter out = resp.getWriter()) {
                out.print("<!DOCTYPE html>");
                out.print("<html>");
                out.print("<head>");
                out.print("</head>");
                out.print("<body>");
                out.print("<br><br><br><br>");

                out.print("<p style=\"text-align: center\"><img style=\"width: 300px;\" src=\"assets/img/error1.jpg\" alt=\"alt\"/> </p>");
                out.print("<h1 style=\"text-align: center\">YOU HAVEN'T SIGN IN!!!</h1>");
                out.print("<h1><a href=\"home\">Home</a></h1>");
                out.print("</body>");
                out.print("</html>");
            }
        } else {
            resp.setContentType("text/html;charset=UTF-8");
            User user = (User) session.getAttribute("user");
            if (user.getRoleFunction() != 2) {
                try ( PrintWriter out = resp.getWriter()) {
                    out.print("<!DOCTYPE html>");
                    out.print("<html>");
                    out.print("<head>");
                    out.print("</head>");
                    out.print("<body>");
                    out.print("<br><br><br><br>");

                    out.print("<p style=\"text-align: center\"><img style=\"width: 300px;\" src=\"assets/img/error1.jpg\" alt=\"alt\"/> </p>");
                    out.print("<h1 style=\"text-align: center\">YOU DON'T HAVE AUTHORIZATION TO ACCESS THIS SCREEN!!!</h1>");
                    out.print("<h1><a href=\"home\">Home</a></h1>");
                    out.print("</body>");
                    out.print("</html>");
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        authorization(req, resp);
        int curriculum_id = Integer.parseInt(req.getParameter("idCurrent"));
        Curriculum curriculum = new CurriculumDao().getCurriculumByID(curriculum_id);
        req.setAttribute("curriculum", curriculum);
        req.setAttribute("a", curriculum_id);
        req.getRequestDispatcher("/view/admin/addPlo.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        authorization(req, resp);
        if (req.getParameter("add") != null) {
            String name = req.getParameter("name");
            String description = req.getParameter("description");
            int curriculum_id = Integer.parseInt(req.getParameter("curri_id"));
            HttpSession session = req.getSession();
            Curriculum curriculum = new CurriculumDao().getCurriculumByID(curriculum_id);
            req.setAttribute("curriculum", curriculum);
            int active = -1;
            if ("1".equals(req.getParameter("active"))) {
                active = 1;
            } else {
                active = 0;
            }
            String mess = "";
            if (name.isEmpty() || description.isEmpty()
                    || curriculum_id == 0 || active == -1) {
                mess = "Please input full data";
                req.setAttribute("mess", mess);
                session.setAttribute("messFail", mess);
                req.getRequestDispatcher("/view/admin/addPlo.jsp").forward(req, resp);
                return;
            } else {
                PloDao plo = new PloDao();
                if (plo.checkDupicateNamePLO(name, curriculum_id)) {
                    mess = "PLO not duplicate please input PLO new!!";
                    session.setAttribute("messFail", mess);
                    req.setAttribute("a", curriculum_id);
            req.setAttribute("mess", mess);
            req.setAttribute("name", name);
            req.setAttribute("description", description);
            req.setAttribute("curriculum_id", curriculum_id);
            req.setAttribute("active", active);
            req.getRequestDispatcher("/view/admin/addPlo.jsp").forward(req, resp);
                } else {
                    boolean checkAdd = plo.addNewPlo(name, description, curriculum_id, active);
                    if (checkAdd) {
                        mess = "Add success";
                        session.setAttribute("messSuccess", mess);
                        resp.sendRedirect(Constants.URL_PROJECT + Constants.URL_PLOLIST + "?idCurrent=" + curriculum_id);
                        
                    } else {
                        mess = "Add fail please add again";
                        session.setAttribute("messFail", mess);
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
            
            
        }

    }

}
