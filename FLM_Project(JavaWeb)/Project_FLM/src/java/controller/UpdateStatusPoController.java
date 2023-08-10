/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.CurriculumDao;
import dao.PoDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.Curriculum;
import util.Constants;

/**
 *
 * @author trinh
 */

@WebServlet(name = Constants.URL_UPDATEPO, urlPatterns = {Constants.URL_UPDATEPO})
public class UpdateStatusPoController extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id_current = Integer.parseInt(req.getParameter("idCurrent"));
        Curriculum curriculum = new CurriculumDao().getCurriculumByID(id_current);
        req.setAttribute("curriculum", curriculum);
        req.setAttribute("idCurrent", id_current);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id_current = Integer.parseInt(req.getParameter("idCurrent"));
        int id_po = Integer.parseInt((req.getParameter("idPo")));
        PoDao poDao = new PoDao();
        Curriculum curriculum = new CurriculumDao().getCurriculumByID(id_current);
        req.setAttribute("curriculum", curriculum);
        req.setAttribute("idCurrent", id_current);
        poDao.updateStatus(id_po);
        resp.sendRedirect(Constants.URL_PROJECT + Constants.URL_POLIST + "?idCurrent=" + id_current);
    }
    
    
    
}
