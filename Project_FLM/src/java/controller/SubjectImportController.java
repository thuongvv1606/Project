/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.SubjectDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import util.Constants;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.IOException;
import jakarta.servlet.annotation.WebServlet;
import java.io.FileNotFoundException;
import java.io.InputStream;
import model.Subject;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author hp
 */
@WebServlet(name = Constants.URL_SUBJECTIMPORT , urlPatterns = {Constants.URL_SUBJECTIMPORT })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class SubjectImportController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/view/common/subjectList.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SubjectDao subjectDao = new SubjectDao();
        String message = "";
        String error = "";

        Part filePart = request.getPart("file"); // Lấy đối tượng Part của file đã được gửi từ client
        try ( InputStream fileContent = filePart.getInputStream()) {
            XSSFWorkbook wb = new XSSFWorkbook(fileContent);
            XSSFSheet sheet = wb.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) {
                    continue; // Bỏ qua những dòng trống
                }
                int subject_id = (int) row.getCell(0, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL).getNumericCellValue();
                String code = row.getCell(1, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL).getStringCellValue();
                String name = row.getCell(2, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL).getStringCellValue();
                int semester = (int) row.getCell(3, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL).getNumericCellValue();
                int no_credit = (int) row.getCell(4, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL).getNumericCellValue();
                String pre_condition = row.getCell(5, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL).getStringCellValue();

                Subject subject = new Subject(subject_id, code, name, semester, no_credit, pre_condition);
                boolean result = subjectDao.InsertData(subject);
                if (result == true) {
                    message = "Data import successful!";
                } else {
                    error = "Data import failed!";
                }
            }
        } catch (FileNotFoundException e) {
            // Xử lý ngoại lệ file not found
            error = "File Not Found: " + e.getMessage();
        } catch (IOException e) {
            // Xử lý ngoại lệ IO
            error = "IOException IO: " + e.getMessage();
        } catch (Exception e) {
            // Xử lý các ngoại lệ khác
            error = "Exception: " + e.getMessage();
        }
        request.setAttribute("message", message);
        request.setAttribute("error", error);
        request.getRequestDispatcher("/view/common/subjectList.jsp").forward(request, response);
    }

}
