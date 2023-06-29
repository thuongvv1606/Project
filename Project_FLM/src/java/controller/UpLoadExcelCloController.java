/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CloDao;
import dao.PoDao;
import dao.SyllabusDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.util.ArrayList;
import model.Clo;
import model.Po;
import model.Syllabus;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import util.Constants;

/**
 *
 * @author NgTienDung
 */
@MultipartConfig
@WebServlet(name = Constants.URL_UPLOADEXCELCLO, urlPatterns = {Constants.URL_UPLOADEXCELCLO})
public class UpLoadExcelCloController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        InputStream inputStream = null;
        try {
            ArrayList<Clo> listClosFail = new ArrayList<>();
            int countOk = 0;
            int countFaillFormat = 0;
            inputStream = request.getPart("file").getInputStream();
            
            Workbook workbook = new XSSFWorkbook(inputStream);
            DataFormatter dataFormatter = new DataFormatter();
            
            StringBuilder result = new StringBuilder();
            int count = 0;
            Clo clo = new Clo();
            CloDao cloDao = new CloDao();
            boolean checkSheet = true; // true la ko thay ten sheeet 
            // Duyệt qua các sheet và lấy nội dung các ô
            for (Sheet sheet : workbook) {
                // result.append("<h3>").append(sheet.getSheetName()).append("</h3>");
                if (sheet.getSheetName().equals("CloList")) {
                    checkSheet = false;
                    
                    for (Row row : sheet) {
                        count = 1;
                        clo = new Clo();
                        for (Cell cell : row) {
                            String cellValue = dataFormatter.formatCellValue(cell);
                            try {
                                switch (count) {
                                    case 1:
                                        break;
                                    case 2:
                                        clo.setCloName(cellValue);
                                        break;
                                    case 3:
                                        clo.setCloDescription(cellValue);
                                        
                                        break;
                                    case 4:
                                        if (!cellValue.trim().equals("0")) {
                                            clo.setCloParentId(Integer.parseInt(cellValue));
                                        }
                                        break;
                                    case 5:
                                        clo.setStatus(cellValue.equals("1"));
                                        break;
                                    case 6:
                                        clo.setSyllabusId(Integer.parseInt(cellValue));
                                        clo.setSyllabus(new SyllabusDao().getAllSyllabusById(Integer.parseInt(cellValue)));
                                        break;
                                }
                                if (count == 6) {
                                    // kiem tra xem co trung name clo va trung Syllabus
                                    if (!cloDao.ckeckCloByName(clo.getCloName(), clo.getSyllabusId())) {
                                        if (clo.getCloParentId() == 0) {
                                            // ko parent
                                            cloDao.addNoParent(clo);
                                        } else {
                                            // co parent
                                            cloDao.addHaveParent(clo);
                                        }
                                        countOk++;
                                    } else {
                                        // trung thi add vao list faill
                                        listClosFail.add(clo);
                                    }
                                }
                                
                            } catch (Exception e) {
                                countFaillFormat++;
                            }
                            
                            count++;
                        }
                    }
                } else {
                    
                }
                
            }
            workbook.close();
            request.setAttribute("listClosFail", listClosFail);
            request.setAttribute("countOk", countOk);
            request.setAttribute("countFaillFormat", countFaillFormat);
            request.setAttribute("checkSheet", checkSheet);
        } catch (Exception e) {
            response.getWriter().print(e.getMessage());
            e.printStackTrace();
        }
        String idsyllabus = request.getParameter("sylId");
        Syllabus syllabus = new SyllabusDao().getAllSyllabusById(Integer.parseInt(idsyllabus));
        request.setAttribute("syllabus", syllabus);
        request.getRequestDispatcher("/view/common/cloList.jsp").forward(request, response);
        
    }
    
}
