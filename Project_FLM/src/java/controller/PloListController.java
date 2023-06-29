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
import java.io.IOException;
import java.util.ArrayList;
import model.Curriculum;
import model.Plo;
import util.Constants;

/**
 *
 * @author trinh
 */
@WebServlet(name = Constants.URL_PLOLIST, urlPatterns = {Constants.URL_PLOLIST})
public class PloListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PloDao plo = new PloDao();
        String search = "";
        String indexPage = req.getParameter("index");

        int count = plo.getSizeList();
        if (indexPage == null) {
            indexPage = "1";
        }
        int index = Integer.parseInt(indexPage);
        int endPage = count / 5;
        if (count % 5 != 0) {
            endPage++;
        }
        if(req.getParameter("key") != null) {
             search = req.getParameter("key").trim();
        }
        
       

        try {
            int id = Integer.parseInt(req.getParameter("idCurrent"));
            ArrayList<Plo> listPlo = plo.getAllPloById(id, search, index);
            Curriculum curriculum = new CurriculumDao().getCurriculumByID(id);
        req.setAttribute("curriculum", curriculum);
            Plo ploP = plo.getPloByID(id);
            req.setAttribute("end", endPage);
            req.setAttribute("index", index);
            req.setAttribute("count", count);
            req.setAttribute("ploId", ploP);
            req.setAttribute("id", id);
            req.setAttribute("plo", listPlo);
        } catch (Exception e) {
        }

        req.getRequestDispatcher("view/common/ploList.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}
