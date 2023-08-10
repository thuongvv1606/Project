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
import model.Curriculum;
import model.Plo;
import util.Constants;

/**
 *
 * @author Admin
 */
@WebServlet(name = Constants.URL_PLODETAILS, urlPatterns = {Constants.URL_PLODETAILS})

public class PloDetailsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("ploId"));
        int id_curri = Integer.parseInt(req.getParameter("idCurrent"));
        PloDao ploDao = new PloDao();
        Plo plo = ploDao.getPloByID(id);
        Curriculum curriculum = new CurriculumDao().getCurriculumByID(id_curri);
        req.setAttribute("curriculum", curriculum);
        req.setAttribute("a", id_curri);
        req.setAttribute("plo", plo);

        req.getRequestDispatcher("/view/common/ploDetails.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }

}
