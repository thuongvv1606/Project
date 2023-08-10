/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CloDao;
import dao.SubjectDao;
import dao.SyllabusDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.Clo;
import model.Subject;
import model.Syllabus;
import model.User;
import util.Constants;

/**
 *
 * @author DungNT
 */
@WebServlet(name = Constants.URL_CLOLIST, urlPatterns = {Constants.URL_CLOLIST})
public class CloListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String idsyllabus = request.getParameter("sylId");
        Syllabus syllabus = new SyllabusDao().getAllSyllabusById(Integer.parseInt(idsyllabus));

        String indexPage = request.getParameter("index");
        String nameSearch = request.getParameter("nameSearch");

        // chỉ xem đc clo active
        String active = "1";

        if (indexPage == null) {
            indexPage = "1";
        }
        if (nameSearch == null) {
            nameSearch = "";
        }
        nameSearch = nameSearch.trim();

        int index = Integer.parseInt(indexPage);
        if (user != null && user.getRoleFunction() == 2) {
            // admin xem đc hết
            active = "";
            if (request.getParameter("filterByStatus") != null) {
                active = request.getParameter("filterByStatus");
                if (active.equals("")) {
                    active = "-1";
                }
                request.setAttribute("filterByStatus", active);
                if (active.equals("-1")) {
                    active = "";
                }
            }
        }

        int count = new CloDao().getSizeList(active,nameSearch);
        int endPage = count / Constants.PAGE_SIZE;
        if (count % Constants.PAGE_SIZE != 0) {
            endPage++;
        }

        ArrayList<Clo> listClo = new CloDao().getAllBySyllabusIdAndPage(
                Integer.parseInt(idsyllabus), index, active, nameSearch);
        request.setAttribute("nameSearch", nameSearch);
        request.setAttribute("listClo", listClo);
        request.setAttribute("syllabus", syllabus);
        request.setAttribute("count", count);
        request.setAttribute("end", endPage);
        request.setAttribute("index", index);
        request.getRequestDispatcher("/view/common/cloList.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
