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
import java.util.ArrayList;
import java.util.List;
import model.Combo;
import model.Curriculum;
import model.Subject;
import model.User;
import static org.apache.el.lang.ELArithmetic.add;

/**
 *
 * @author hp
 */
@WebServlet(name = "ComboListController", urlPatterns = {"/combo"})
public class ComboListController extends HttpServlet {

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
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }
        switch (action) {
            case "list": {
                list(request, response);
                break;
            }
            case "add": {
                add(request, response);
                break;
            }
            case "edit": {
                edit(request, response);
                break;
            }
            case "delete": {
                delete(request, response);
                break;
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
        processRequest(request, response);
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

    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String curId = request.getParameter("curId");
        String key = request.getParameter("key");
        String indexPage = request.getParameter("index");
        if (indexPage == null) {
            indexPage = "1";
        }
        int index = Integer.parseInt(indexPage);
        int count;
        int endPage;
        CurriculumDao curiDao = new CurriculumDao();
        ComboDao comboDao = new ComboDao();
        Curriculum curriculum = curiDao.getCurriculumByID(Integer.parseInt(curId));

        if (request.getParameter("search_btn") != null) {
            List<Combo> comboList = comboDao.getComboList(Integer.parseInt(curId), key, index);
            count = comboDao.getComboListSize(Integer.parseInt(curId), key, index).size();
            endPage = count / 5;
            if (count % 5 != 0) {
                endPage++;
            }
            request.setAttribute("end", endPage);
            request.setAttribute("key", key);
            request.setAttribute("index", index);
            request.setAttribute("count", count);
            request.setAttribute("curId", curId);
            request.setAttribute("curriculum", curriculum);
            request.setAttribute("comboList", comboList);
            request.getRequestDispatcher("/view/common/comboList.jsp").forward(request, response);
        } else {
            List<Combo> comboList = comboDao.getComboList(Integer.parseInt(curId), key, index);
            count = comboDao.getComboListByCurriculumId(Integer.parseInt(curId)).size();
            endPage = count / 5;
            if (count % 5 != 0) {
                endPage++;
            }
            request.setAttribute("end", endPage);
            request.setAttribute("key", key);
            request.setAttribute("index", index);
            request.setAttribute("count", count);
            request.setAttribute("curId", curId);
            request.setAttribute("curriculum", curriculum);
            request.setAttribute("comboList", comboList);
            request.getRequestDispatcher("/view/common/comboList.jsp").forward(request, response);
        }
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        authorization(request, response);
        String curId = request.getParameter("curId");
        CurriculumDao curiDao = new CurriculumDao();
        Curriculum curriculum = curiDao.getCurriculumByID(Integer.parseInt(curId));
        request.setAttribute("curId", curId);
        request.setAttribute("curriculum", curriculum);
        request.getRequestDispatcher("/view/common/comboAdd.jsp").forward(request, response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        authorization(request, response);
        String comboId = request.getParameter("comboId");
        ComboDao comboDao = new ComboDao();
        comboDao.deleteCombo(Integer.parseInt(comboId));
        list(request, response);
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String curId = request.getParameter("curId");
        String comboId = request.getParameter("comboId");
        String subjectId = request.getParameter("subjectId");
        CurriculumDao curiDao = new CurriculumDao();
        ComboDao comboDao = new ComboDao();
        Curriculum curriculum = curiDao.getCurriculumByID(Integer.parseInt(curId));
        ArrayList<Subject> subjectList = comboDao.getSubjectByCombo(Integer.parseInt(curId), Integer.parseInt(comboId));
        Combo combo = comboDao.getCombo(Integer.parseInt(comboId));
        request.setAttribute("curId", curId);
        request.setAttribute("comboId", comboId);
        request.setAttribute("combo", combo);
        request.setAttribute("curriculum", curriculum);
        request.setAttribute("subjectList", subjectList);
        request.getRequestDispatcher("/view/common/comboDetail.jsp").forward(request, response);
    }

}
