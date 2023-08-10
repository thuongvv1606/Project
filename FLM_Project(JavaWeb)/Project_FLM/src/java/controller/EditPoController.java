/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CurriculumDao;
import dao.PoDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Curriculum;
import model.Po;
import model.User;
import util.Constants;

/**
 *
 * @author LanChau
 */
@WebServlet(name = Constants.URL_EDITPO, urlPatterns = {Constants.URL_EDITPO})
public class EditPoController extends HttpServlet {

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
        int idCurrent = Integer.parseInt(request.getParameter("idCurrent"));
        request.setAttribute("idCurrent", idCurrent);

        int idPo = Integer.parseInt(request.getParameter("idPo"));
        Po po = new PoDao().getPoById(idPo);

        request.setAttribute("po", po);

        Curriculum curriculum = new CurriculumDao().getCurriculumByID(idCurrent);
        request.setAttribute("curriculum", curriculum);

        request.getRequestDispatcher("/view/common/editPo.jsp").forward(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        authorization(request, response);
        int idCurrent = Integer.parseInt(request.getParameter("idCurrent"));
        int idPo = Integer.parseInt(request.getParameter("idCurrent"));
        Po poOld = new PoDao().getPoById(idPo);
        HttpSession session = request.getSession();
        request.setAttribute("idCurrent", idCurrent);
        Po po = new Po();
        po.setPoId(idPo);
        po.setPoName(request.getParameter("name").trim());
        po.setPoDescription(request.getParameter("description").trim());
        po.setCurriculumId(idCurrent);
        String statusParam = request.getParameter("status");
        boolean isActive;
        if (statusParam.equals("1")) {
            isActive = true;
        } else {
            isActive = false;
        }
        po.setIsActive(isActive);
        PoDao poDao = new PoDao();
        request.setAttribute("po", po);
        Curriculum curriculum = new CurriculumDao().getCurriculumByID(idCurrent);
        request.setAttribute("curriculum", curriculum);
        if (poDao.checkExitstName(po.getPoName(), idCurrent)) {
            String mess = "Name exists";
            session.setAttribute("messFail", mess);
             request.getRequestDispatcher("/view/common/editPo.jsp").forward(request, response);
        } else {
            poDao.update(po);
            String mess = "Add successsfully";
            session.setAttribute("messSuccess", mess);
            response.sendRedirect(Constants.URL_PROJECT + Constants.URL_POLIST + "?idCurrent=" + idCurrent);
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
