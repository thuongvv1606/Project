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
import model.Plo;
import model.User;
import util.Constants;

/**
 *
 * @author trinh
 */
@WebServlet(name = Constants.URL_PLODEDIT, urlPatterns = {Constants.URL_PLODEDIT})
public class EditPloController extends HttpServlet {

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
        String a = req.getParameter("ploId");
        int id = Integer.parseInt(req.getParameter("ploId"));
        int id_curi = Integer.parseInt(req.getParameter("idCurrent"));
        req.setAttribute("a", a);
        PloDao ploDao = new PloDao();
        Plo plo = new Plo();
        plo = ploDao.getPloByID(id);
        Curriculum curriculum = new CurriculumDao().getCurriculumByID(id_curi);
        req.setAttribute("b", id_curi);
        req.setAttribute("curriculum", curriculum);
        req.setAttribute("plo", plo);
        req.getRequestDispatcher("view/admin/editPlo.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        authorization(req, resp);
        if (req.getParameter("update") != null) {
            String ploName = req.getParameter("name");
            String ploDescription = req.getParameter("description");
            int curriculum_id = Integer.parseInt(req.getParameter("idCurri"));
            int id = Integer.parseInt(req.getParameter("idSetting"));
            HttpSession session = req.getSession();
            String mess = "";
            PloDao ploDao = new PloDao();

            if (ploName.isEmpty() || ploDescription.isEmpty() || curriculum_id == 0) {
                mess = "Please input full data!";
                session.setAttribute("messFail", mess);
                req.setAttribute("a", id);
//                Plo ploa = new Plo();
//                    ploa = curiDao.getPloByID(id);
//                    req.setAttribute("plo", ploa);
                Curriculum curriculum = new CurriculumDao().getCurriculumByID(curriculum_id);
                req.setAttribute("curriculum", curriculum);
                req.setAttribute("a", id);
                req.getRequestDispatcher("view/admin/editPlo.jsp").forward(req, resp);
                return;
            } else {

                boolean plo = ploDao.updatePloById(ploName, ploDescription, curriculum_id, id);
                if (plo) {
                    Plo ploa = new Plo();
                    mess = "Edit sucesss!";
                    ploa = ploDao.getPloByID(id);
                    session.setAttribute("messSuccess", mess);
                    req.setAttribute("plo", ploa);
                    resp.sendRedirect(Constants.URL_PROJECT + Constants.URL_PLOLIST + "?idCurrent=" + curriculum_id);
                } else {
                    mess = "Update false please update agin";
                    Curriculum curriculum = new CurriculumDao().getCurriculumByID(curriculum_id);
                    req.setAttribute("curriculum", curriculum);
                    req.setAttribute("a", id);
                    session.setAttribute("messFail", mess);
                    req.getRequestDispatcher("view/admin/editPlo.jsp").forward(req, resp);
                }
            }

        }
    }

}
