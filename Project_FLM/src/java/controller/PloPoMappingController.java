/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.CurriculumDao;
import dao.MappingPloPoDao;
import dao.PloDao;
import dao.PoDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.Curriculum;
import model.MappingPloPo;
import model.Plo;
import model.Po;
import util.Constants;

/**
 *
 * @author trinh
 */
@WebServlet(name = Constants.URL_PLOPOMAPPING, urlPatterns = {Constants.URL_PLOPOMAPPING})
public class PloPoMappingController extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idCurrent = Integer.parseInt(req.getParameter("idCurrent"));

            ArrayList<MappingPloPo> listMappingPLOPo = new MappingPloPoDao().getAll(idCurrent);
            ArrayList<Plo> listPlo = new PloDao().getAllByCurrent(idCurrent);
             
            ArrayList<Po> listPo = new PoDao().getAllByCurrent(idCurrent);

            req.setAttribute("idCurrent", idCurrent);
            req.setAttribute("listMappingPLOPo", listMappingPLOPo);
            req.setAttribute("listPlo", listPlo);
            req.setAttribute("listPo", listPo);

            Curriculum curriculum = new CurriculumDao().getCurriculumByID(idCurrent);
            req.setAttribute("curriculum", curriculum);
            req.getRequestDispatcher("/view/common/ploPoMapping.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idCurrent = Integer.parseInt(req.getParameter("idCurrent"));

            MappingPloPoDao mappingPloPo = new MappingPloPoDao();
            ArrayList<Plo> listPlo = new PloDao().getAllByCurrent(idCurrent);
             
            ArrayList<Po> listPo = new PoDao().getAllByCurrent(idCurrent);
            boolean check = false;
        String name = "";

        for (Po b : listPo) {
            for (Plo a : listPlo) {
                name = "mapping" + b.getPoId()+ "_" + a.getId() + "";
                check = mappingPloPo.check(a.getId(), b.getPoId());
                if (req.getParameter(name) == null && check == true) {
                    mappingPloPo.delete(a.getId(), b.getPoId());
                }
                if (req.getParameter(name) != null && check == false) {
                    mappingPloPo.add(a.getId(), b.getPoId());
                }
            }
        }
        
        resp.sendRedirect(Constants.URL_PROJECT+Constants.URL_PLOPOMAPPING+"?idCurrent="+idCurrent);

    }
    
    
}
