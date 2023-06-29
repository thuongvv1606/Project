/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.PoDao; 
import java.io.InputStream;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import jakarta.servlet.http.Part;
import java.util.Iterator;
import java.util.List;
import model.Po;
import org.apache.poi.ss.usermodel.DataFormatter;
import util.Constants;

/**
 *
 * @author LanChau
 */
@MultipartConfig
@WebServlet(name = Constants.URL_IMPORTEXCELPO, urlPatterns = {Constants.URL_IMPORTEXCELPO})
public class UpLoadExcelController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    private static final long serialVersionUID = 1L;

    // Phương thức hỗ trợ để lấy tên file từ Part
    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] elements = contentDisposition.split(";");
        for (String element : elements) {
            if (element.trim().startsWith("filename")) {
                return element.substring(element.indexOf("=") + 1).trim().replace("\"", "");
            }
        }
        return "";
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        InputStream inputStream = null;
        try {

            inputStream = request.getPart("file").getInputStream();

            Workbook workbook = new XSSFWorkbook(inputStream);
            DataFormatter dataFormatter = new DataFormatter();

            StringBuilder result = new StringBuilder();
            int count = 0;
            Po po = new Po();
            // Duyệt qua các sheet và lấy nội dung các ô
            for (Sheet sheet : workbook) {
                // result.append("<h3>").append(sheet.getSheetName()).append("</h3>");
                if (sheet.getSheetName().equals("PoList")) {
                    for (Row row : sheet) {
                        count = 1;
                        for (Cell cell : row) {
                            String cellValue = dataFormatter.formatCellValue(cell);
                            try {
                                switch (count) {
                                    case 1:
                                        po.setPoId(Integer.parseInt(cellValue));
                                        break;
                                    case 2:
                                        po.setPoName(cellValue);
                                        break;
                                    case 3:
                                        po.setPoDescription(cellValue);
                                        break;
                                    case 4:
                                        po.setCurriculumId(Integer.parseInt(cellValue));
                                        break;
                                    case 5:
                                        po.setIsActive(cellValue.equals("1"));
                                        break;
                                }
                                if (count == 5) {
                                    new PoDao().add(po);
                                }

                            } catch (Exception e) {
                            }

                            count++;
                        }
                    }
                }

            }
            workbook.close();

        } catch (Exception e) {
            response.getWriter().print(e.getMessage());
            e.printStackTrace();
        }
        int idCurrent = Integer.parseInt(request.getParameter("idCurrent").trim());
        response.sendRedirect(Constants.URL_PROJECT + Constants.URL_POLIST + "?idCurrent=" + idCurrent);

    }
}
