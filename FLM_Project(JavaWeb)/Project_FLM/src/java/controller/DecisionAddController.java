/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DecisionDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Decision;
import model.User;

/**
 *
 * @author hp
 */
@WebServlet(name = "DecisionAdd", urlPatterns = {"/decisionAdd"})
public class DecisionAddController extends HttpServlet {

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
        request.getRequestDispatcher("/view/common/decisionAdd.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        authorization(request, response);
        jakarta.servlet.http.HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.setContentType("text/html;charset=UTF-8");
            try ( PrintWriter out = response.getWriter()) {
                out.print("You need to login again");
            }
        }
        User user = (User) session.getAttribute("user");
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

        Boolean is_active = false;
        if (request.getParameter("active") == null) {
            is_active = false;
        } else {
            if (request.getParameter("active").equals("1")) {
                is_active = true;
            } else if (request.getParameter("active").equals("0")) {
                is_active = false;
            } else {
                is_active = true;
            }
        }

        DecisionDao decisionDao = new DecisionDao();
        String messFail = "";
        if (decision_no.isEmpty() || decision_no == null) {
            request.setAttribute("messFail", "Decision No not empty");
            request.setAttribute("decisionNo", decision_no);
            request.setAttribute("decisionName", decision_name);
            request.setAttribute("decisionDate", decision_date);
            request.setAttribute("active", request.getParameter("active"));
            request.getRequestDispatcher("/view/common/decisionAdd.jsp").forward(request, response);
        } else if (decision_no.length() > 70 || decision_name.length() > 70) {
            request.setAttribute("messFail", "Decision no or name cannot have more than 70 characters");
            request.setAttribute("decisionNo", decision_no);
            request.setAttribute("decisionName", decision_name);
            request.setAttribute("decisionDate", decision_date);
            request.setAttribute("active", request.getParameter("active"));
            request.getRequestDispatcher("/view/common/decisionAdd.jsp").forward(request, response);
        } else {
            if (decisionDao.checkNo(decision_no)) {
                messFail = "Duplicate Code!";
                request.setAttribute("messFail", messFail);
                request.setAttribute("decisionNo", decision_no);
                request.setAttribute("decisionName", decision_name);
                request.setAttribute("decisionDate", decision_date);
                request.setAttribute("active", request.getParameter("active"));
                request.getRequestDispatcher("/view/common/decisionAdd.jsp").forward(request, response);
            } else {
                int id = user.getUserId();
                Decision decision = new Decision(decision_no, decision_name, sqlDate, id, is_active);
                boolean check = decisionDao.addDecision(decision);
                if (!check) {
                    messFail = "Add failed";
                    request.setAttribute("messFail", messFail);
                    request.getRequestDispatcher("/view/common/decisionAdd.jsp").forward(request, response);
                } else {
                    request.setAttribute("messSuccess", "Add successfully new elective has code = " + decision_no);
                    request.getRequestDispatcher("decisionController").forward(request, response);
                }
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
    }

}
