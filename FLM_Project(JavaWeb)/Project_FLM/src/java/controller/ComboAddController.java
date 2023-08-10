/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.ComboDao;
import dao.CurriculumDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Combo;
import model.Curriculum;
import model.Role;
import model.User;

/**
 *
 * @author hp
 */
@WebServlet(name = "ComboAddController", urlPatterns = {"/comboAdd"})
public class ComboAddController extends HttpServlet {

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        authorization(request, response);
        String curId = request.getParameter("curId");
        CurriculumDao curiDao = new CurriculumDao();
        Curriculum curriculum = curiDao.getCurriculumByID(Integer.parseInt(curId));
        request.setAttribute("curId", curId);
        request.setAttribute("curriculum", curriculum);
        request.getRequestDispatcher("/view/common/comboAdd.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        authorization(request, response);
        String curId = request.getParameter("curId");
        String comboType = request.getParameter("comboType");
        String comboName = request.getParameter("comboName");
        String comboEnglishName = request.getParameter("comboEnglishName");
        String message;
        if (comboType.length() < 0 || comboType.length() > 50) {
            message = "Combo type must be in range (0; 50)";
            CurriculumDao curiDao = new CurriculumDao();
            Curriculum curriculum = curiDao.getCurriculumByID(Integer.parseInt(curId));
            request.setAttribute("message", message);
            request.setAttribute("curId", curId);
            request.setAttribute("comboType", comboType);
            request.setAttribute("comboName", comboName);
            request.setAttribute("comboEnglishName", comboEnglishName);
            request.setAttribute("curriculum", curriculum);
            request.getRequestDispatcher("/view/common/comboAdd.jsp").forward(request, response);
        } else if (comboName.length() <= 0 || comboName.length() > 100) {
            message = "Combo name must be in range (0; 50)";
            CurriculumDao curiDao = new CurriculumDao();
            Curriculum curriculum = curiDao.getCurriculumByID(Integer.parseInt(curId));
            request.setAttribute("message", message);
            request.setAttribute("curId", curId);
            request.setAttribute("comboType", comboType);
            request.setAttribute("comboName", comboName);
            request.setAttribute("comboEnglishName", comboEnglishName);
            request.setAttribute("curriculum", curriculum);
            request.getRequestDispatcher("/view/common/comboAdd.jsp").forward(request, response);
        } else if (comboEnglishName.length() < 0 || comboEnglishName.length() > 100) {
            message = "Combo English name must be in range (0; 50)";
            CurriculumDao curiDao = new CurriculumDao();
            Curriculum curriculum = curiDao.getCurriculumByID(Integer.parseInt(curId));
            request.setAttribute("message", message);
            request.setAttribute("curId", curId);
            request.setAttribute("comboType", comboType);
            request.setAttribute("comboName", comboName);
            request.setAttribute("comboEnglishName", comboEnglishName);
            request.setAttribute("curriculum", curriculum);
            request.getRequestDispatcher("/view/common/comboAdd.jsp").forward(request, response);
        } else {
            ComboDao comboDao = new ComboDao();
            boolean check = comboDao.addCombo(Integer.parseInt(curId), comboType, comboName, comboEnglishName);
            if (check) {
                CurriculumDao curiDao = new CurriculumDao();
                Curriculum curriculum = curiDao.getCurriculumByID(Integer.parseInt(curId));
                request.setAttribute("messSuccess", "Add successfully new Combo ");
                request.setAttribute("curId", curId);
                request.setAttribute("curriculum", curriculum);
                request.getRequestDispatcher("combo").forward(request, response);
            } else {
                message = "Add fails!!!";
                CurriculumDao curiDao = new CurriculumDao();
                Curriculum curriculum = curiDao.getCurriculumByID(Integer.parseInt(curId));
                request.setAttribute("message", message);
                request.setAttribute("curId", curId);
                request.setAttribute("comboType", comboType);
                request.setAttribute("comboName", comboName);
                request.setAttribute("comboEnglishName", comboEnglishName);
                request.setAttribute("curriculum", curriculum);
                request.getRequestDispatcher("/view/common/comboAdd.jsp").forward(request, response);
            }

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
