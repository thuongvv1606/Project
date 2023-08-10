/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CurriculumDao;
import dao.UserDao;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import model.Curriculum;
import model.User;
import util.Constants;

/**
 *
 * @author hp
 */
@WebServlet(name = Constants.URL_CURRICULUMADD, urlPatterns = {Constants.URL_CURRICULUMADD})
public class CurriculumAddController extends HttpServlet {

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
        UserDao userDao = new UserDao();
        ArrayList<User> userList = userDao.getAllListUser();
        request.setAttribute("userList", userList);
        request.getRequestDispatcher("/view/common/curriculumAdd.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        authorization(request, response);
        String code = request.getParameter("code");
        String na = request.getParameter("name");
        String engname = request.getParameter("engname");
        String name = engname + "(" + na;
        String assignee = request.getParameter("assignee");
        String description = request.getParameter("description");
        String error = null;
        String success;
        CurriculumDao curriculumDao = new CurriculumDao();
        UserDao userDao = new UserDao();
        ArrayList<User> userList = userDao.getAllListUser();

        if (code.isEmpty() || name.isEmpty()) {
            error = "Not Empty";
            request.setAttribute("code", code);
            request.setAttribute("name", na);
            request.setAttribute("engname", engname);
            request.setAttribute("assignee", request.getParameter("assignee"));
            request.setAttribute("description", description);
            request.setAttribute("userList", userList);
            request.setAttribute("error", error);
            request.getRequestDispatcher("/view/common/curriculumAdd.jsp").forward(request, response);
        } else {
            if (code.length() > 50) {
                error = "code cannot be blank and less than 100 characters";
                request.setAttribute("code", code);
                request.setAttribute("name", na);
                request.setAttribute("engname", engname);
                request.setAttribute("assignee", request.getParameter("assignee"));
                request.setAttribute("description", description);
                request.setAttribute("userList", userList);
                request.setAttribute("error", error);
                request.getRequestDispatcher("/view/common/curriculumAdd.jsp").forward(request, response);
            } else if (name.length() > 100) {
                error = "name cannot be blank and less than 100 characters";
                request.setAttribute("code", code);
                request.setAttribute("name", na);
                request.setAttribute("engname", engname);
                request.setAttribute("assignee", request.getParameter("assignee"));
                request.setAttribute("description", description);
                request.setAttribute("userList", userList);
                request.setAttribute("error", error);
                request.getRequestDispatcher("/view/common/curriculumAdd.jsp").forward(request, response);
            } else if (description.length() > 1000) {
                error = "description  less than 100 characters";
                request.setAttribute("code", code);
                request.setAttribute("name", na);
                request.setAttribute("engname", engname);
                request.setAttribute("assignee", request.getParameter("assignee"));
                request.setAttribute("description", description);
                request.setAttribute("userList", userList);
                request.setAttribute("error", error);
                request.getRequestDispatcher("/view/common/curriculumAdd.jsp").forward(request, response);
            } else {
                boolean check = curriculumDao.checkCode(code);
                if (check) {
                    error = "duplicate code!!!!";
                    request.setAttribute("code", code);
                    request.setAttribute("name", na);
                    request.setAttribute("engname", engname);
                    request.setAttribute("assignee", request.getParameter("assignee"));
                    request.setAttribute("description", description);
                    request.setAttribute("userList", userList);
                    request.setAttribute("error", error);
                    request.getRequestDispatcher("/view/common/curriculumAdd.jsp").forward(request, response);
                } else {
                    success = "Successfully!!!!";
                    curriculumDao.addCuriculum(new Curriculum(code, name, description, Integer.parseInt(assignee)));
                    request.setAttribute("messSuccess", success);
                    request.getRequestDispatcher("curriculum").forward(request, response);
                }
            }

        }
    }

}
