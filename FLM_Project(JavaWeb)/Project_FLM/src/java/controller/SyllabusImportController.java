/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.SyllabusDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import model.Syllabus;
import model.User;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import util.Constants;

/**
 *
 * @author TT
 */
@WebServlet(name = Constants.URL_SYLLABUSIMPORT, urlPatterns = {Constants.URL_SYLLABUSIMPORT})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB

public class SyllabusImportController extends HttpServlet {

    private String isNull(String a) {
        return (a == null) ? "" : a;
    }

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
            if(user.getRoleFunction() == 3){
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        authorization(request, response);
        request.setAttribute("messSuccess", "");
        request.setAttribute("messFail", "");
        request.setAttribute("error", "");
        request.getRequestDispatcher("/view/common/syllabusList.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        authorization(req, resp);
        HttpSession session = req.getSession();
        User a = (User) session.getAttribute("user");
        Part filePart = req.getPart("file"); // Lấy đối tượng Part của file đã được gửi từ client
        SyllabusDao sDao = new SyllabusDao();
        String messFail = "", messSuccess = "", error = "";
        try ( InputStream fileContent = filePart.getInputStream()) {
            XSSFWorkbook wb = new XSSFWorkbook(fileContent);
            XSSFSheet sheet = wb.getSheetAt(0);
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                String name = "", engname = "", description = "", time = "", task = "", tool = "", note = "", degree_lvl = "";
                int credit = 1, score = 0, mark = 0, active = 1;
                Row row = sheet.getRow(i);
                if (row == null) {
                    continue; // Bỏ qua những dòng trống
                }
                if (row.getCell(0, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL) != null) {
                    name = isNull(row.getCell(0, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL).getStringCellValue());
                }
                if (row.getCell(1, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL) != null) {
                    engname = isNull(row.getCell(1, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL).getStringCellValue());
                }
                if (row.getCell(2, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL) != null) {
                    credit = (int) row.getCell(2, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL).getNumericCellValue();
                }
                if (row.getCell(3, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL) != null) {
                    time = isNull(row.getCell(3, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL).getStringCellValue());
                }
                if (row.getCell(4, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL) != null) {
                    task = isNull(row.getCell(4, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL).getStringCellValue());
                }
                if (row.getCell(5, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL) != null) {
                    tool = isNull(row.getCell(5, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL).getStringCellValue());
                }
                if (row.getCell(6, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL) != null) {
                    score = (int) row.getCell(6, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL).getNumericCellValue();
                }
                if (row.getCell(7, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL) != null) {
                    note = isNull(row.getCell(7, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL).getStringCellValue());
                }
                if (row.getCell(8, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL) != null) {
                    mark = (int) row.getCell(8, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL).getNumericCellValue();
                }
                if (row.getCell(9, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL) != null) {
                    description = isNull(row.getCell(9, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL).getStringCellValue());
                }
                if (row.getCell(10, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL) != null) {
                    active = (int) row.getCell(10, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL).getNumericCellValue();
                }
                if (row.getCell(11, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL) != null) {
                    degree_lvl = isNull(row.getCell(11, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL).getStringCellValue());
                }
                String no_credit = String.valueOf(credit);
                String max = String.valueOf(score);
                String min = String.valueOf(mark);
                String status = String.valueOf(active);
                if (sDao.checkDuplicateSyllabus(name, "-1")) {
                    messFail += "Exist name at: " + i + "<br>";
                } else if (mark > score) {
                    messFail += "Min mark to pass is more than scale score at: " + i + "<br>";
                } else if (name.trim().equals("")) {
                    messFail += "Empty name at: " + i + "<br>";
                } else {
                    if (sDao.addSyllabus(name, engname, no_credit, time, description, task, tool, max, note,
                            min, status, degree_lvl, a.getUserId()) == -1) {
                        messFail += "Insert into database fail at " + i + "<br>";
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
            if (e.getMessage() != "null") {
                error = "Exception: " + e.getMessage();
            }
        }
        req.setAttribute("messSuccess", messSuccess);
        req.setAttribute("messFail", messFail);
        req.setAttribute("error", error);
        String indexPage = req.getParameter("index");
        if (indexPage == null) {
            indexPage = "1";
        }
        int index = Integer.parseInt(indexPage);
        int count = sDao.getNumberOfSyllabus(false);
        int endPage = count / 8;
        if (count % 8 != 0) {
            endPage++;
        }
        List<Syllabus> sList = sDao.getAllSyllabus(index, false);
        List<User> owner = sDao.getAllOwner();
        req.setAttribute("sList", sList);
        req.setAttribute("count", count);
        req.setAttribute("end", endPage);
        req.setAttribute("index", index);
        req.setAttribute("owner", owner);
        req.getRequestDispatcher("/view/common/syllabusList.jsp").forward(req, resp);
    }
}
