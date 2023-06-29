/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CurriculumDao;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import util.Constants;

/**
 *
 * @author hp
 */
@WebServlet(name = Constants.URL_CURRICULUMADD, urlPatterns = {Constants.URL_CURRICULUMADD})
public class CurriculumAddController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/view/common/curriculumAdd.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        String code = request.getParameter("code");
        String name = request.getParameter("name");
        String total =request.getParameter("total");
        String description = request.getParameter("description");
        int decisionId;
        if (null == request.getParameter("decisionId")) {
            decisionId = 3;
        } else switch (request.getParameter("decisionId")) {
            case "1":
                decisionId = 1;
                break;
            case "2":
                decisionId = 2;
                break;
            default:
                decisionId = 3;
                break;
        }
        int active;
        if ("1".equals(request.getParameter("active"))) {
            active = 1;
        } else {
            active = 2;
        }
        if (code.isEmpty() || name.isEmpty()|| description.isEmpty() || total.isEmpty()) {
            request.setAttribute("mess", "Not Empty");
            request.getRequestDispatcher("/view/common/curriculumAdd.jsp").forward(request, response);
        } else {
            int num = Integer.parseInt(total);
            CurriculumDao dao = new CurriculumDao();
            int curriculumId = dao.getCurriculumIdLast();
            curriculumId = curriculumId + 1;
            dao.addCuriculum(curriculumId, code, name, description, decisionId, num, active, u.getUserId());
            request.setAttribute("code", code);
            request.setAttribute("name", name);
            request.setAttribute("total", request.getParameter("total"));
            request.setAttribute("description", description);
            request.setAttribute("d", request.getParameter("decisionId"));
            request.setAttribute("a", request.getParameter("active"));
            request.setAttribute("messs", "Successfully");
            request.getRequestDispatcher("/view/common/curriculumAdd.jsp").forward(request, response);
        }
    }

}
