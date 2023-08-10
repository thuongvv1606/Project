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
import java.io.PrintWriter;
import model.Curriculum;
import model.User;
import util.Constants;

/**
 *
 * @author Admin
 */
@WebServlet(name = Constants.URL_CURRICULUMEDIT, urlPatterns = {Constants.URL_CURRICULUMEDIT})
public class CurriculumEditController extends HttpServlet {

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
        }else{
            resp.setContentType("text/html;charset=UTF-8");
            User user = (User) session.getAttribute("user");
            if(user.getRoleFunction() != 2){
                try (PrintWriter out = resp.getWriter()) {
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
        authorization(req, resp);
        if (req.getParameter("update") != null) {
            String curriculumCode = req.getParameter("code");
            String name = req.getParameter("name");
            String engname = req.getParameter("engname");
            String curriculumName = engname + "(" + name;
            String curriculumDescription = req.getParameter("description");
            HttpSession session = req.getSession();
            int id =  Integer.parseInt(req.getParameter("idSetting"));
            String mess = "";
            String success = "";
            CurriculumDao curiDao = new CurriculumDao();
            req.setAttribute("code", curriculumCode);
            req.setAttribute("name", name);
            req.setAttribute("engname", engname);
            req.setAttribute("description", curriculumDescription);
            if (curriculumCode == "" || curriculumName == "") {
                mess = "Please input full data!";
                req.setAttribute("messfail", mess);
                req.getRequestDispatcher("view/common/curriculumEdit.jsp").forward(req, resp);
                return;
            } else {
                boolean curri = curiDao.updateCurriculumById(id, curriculumCode, curriculumName, curriculumDescription);
                if (curri) {
                    success = "Update sucess!";
                     req.setAttribute("messSuccess", success);
            req.getRequestDispatcher("curriculum").forward(req, resp);
                } else {
                    mess = "Update false pls update agin";
                    req.setAttribute("messfail", mess);
                req.getRequestDispatcher("view/common/curriculumEdit.jsp").forward(req, resp);
                }
            }
           

        }
    }

}
