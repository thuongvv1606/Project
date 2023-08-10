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
import java.io.PrintWriter;
import java.util.ArrayList;
import model.Subject;
import model.User;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author hp
 */
@WebServlet(name = Constants.URL_SUBJECTIMPORT, urlPatterns = {Constants.URL_SUBJECTIMPORT})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class SubjectImportController extends HttpServlet {

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
        request.getRequestDispatcher("/view/common/subjectList.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        authorization(request, response);
        SubjectDao subjectDao = new SubjectDao();
        Part filePart = request.getPart("file"); // Lấy đối tượng Part của file đã được gửi từ client
        String messFail = "", messSuccess = "", error = "";
        String code = "", name = "", pre_condition = "";
        int semester = 0, no_credit = 0;
        try ( InputStream fileContent = filePart.getInputStream()) {
            XSSFWorkbook wb = new XSSFWorkbook(fileContent);
            XSSFSheet sheet = wb.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) {
                    continue; // Bỏ qua những dòng trống
                }
                if (row.getCell(0, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL) != null) {
                    code = row.getCell(0, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL).getStringCellValue();
                }
                if (row.getCell(1, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL) != null) {
                    name = row.getCell(1, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL).getStringCellValue();
                }
                if (row.getCell(2, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL) != null) {
                    semester = (int) row.getCell(2, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL).getNumericCellValue();
                }
                if (row.getCell(3, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL) != null) {
                    no_credit = (int) row.getCell(3, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL).getNumericCellValue();
                }
                if (row.getCell(4, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL) != null) {
                    pre_condition = row.getCell(4, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL).getStringCellValue();
                }

                Subject subject = new Subject(code, name, semester, no_credit, pre_condition);

                if (subjectDao.checkCodeSubject(code)) {
                    messFail += "Exist code at: " + i + "<br>";
                } else if (code.equals("")) {
                    messFail += "Empty code at: " + i + "<br>";
                } else if (code.length() > 100 || name.length() > 100 || pre_condition.length() > 500) {
                    messFail += "Code cannot more than 100 characters and name cannot be more than 100 characters and pre_condition cannot be more than 500 characters at: " + i + "<br>";
                } else {
                    if (!subjectDao.InsertData(subject)) {
                        messFail += "Insert into database fail at " + i;
                    } else {
                        messSuccess += i + " ";
                    }
                }
            }
            if (!messFail.equals("")) {
                messFail = "Import fail:<br>" + messFail;
            }
            if (!messSuccess.equals("")) {
                messSuccess = "Insert into database successfully at " + messSuccess;
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
        request.setAttribute("messSuccess", messSuccess);
        request.setAttribute("messFail", messFail);
        request.setAttribute("error", error);
        SubjectDao subjectsDao = new SubjectDao();

        ArrayList<Subject> listSubjects = subjectsDao.getAllSubject();
        for (Subject listSubject : listSubjects) {
            System.out.println(listSubject.getName());
        }
        request.setAttribute("listSubjects2", listSubjects);
        request.getRequestDispatcher("/view/common/subjectList.jsp").forward(request, response);
    }

}
