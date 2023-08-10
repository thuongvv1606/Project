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
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import model.Po;
import model.Session;
import model.User;
import org.apache.poi.ss.usermodel.DataFormatter;
import util.Constants;

/**
 *
 * @author LanChau
 */
@MultipartConfig
@WebServlet(name = Constants.URL_IMPORTEXCELPO, urlPatterns = {Constants.URL_IMPORTEXCELPO})
public class UpLoadExcelController extends HttpServlet {

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
        int id = Integer.parseInt(request.getParameter("idCurrent"));
        request.setAttribute("id", id);
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
        authorization(request, response);
        InputStream inputStream = null;
        ArrayList<Po> lisPOFail = new ArrayList<>();
        int v = 0;
        try {

            int countOK = 0;
            int countFailFormat = 0;

            inputStream = request.getPart("file").getInputStream();
            System.out.println(v++);
            Workbook workbook = new XSSFWorkbook(inputStream);
            DataFormatter dataFormatter = new DataFormatter();
            System.out.println(v++);
            StringBuilder result = new StringBuilder();
            int count = 0;
            Po po = new Po();
            PoDao poDao = new PoDao();
            System.out.println(v++);
            boolean checkSheet = true;
            // Duyệt qua các sheet và lấy nội dung các ô
            for (Sheet sheet : workbook) {
                System.out.println("she" + v++);
                // result.append("<h3>").append(sheet.getSheetName()).append("</h3>");
                if (sheet.getSheetName().equals("PoList")) {
                    checkSheet = false;
                    for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                        Row row = sheet.getRow(i);
                        count = 1;
                        po = new Po();
                        System.out.println(v++);
                        for (Cell cell : row) {
                            String cellValue = dataFormatter.formatCellValue(cell);
                            try {
                                switch (count) {
                                    case 1:

                                        break;
                                    case 2:
                                        po.setPoName(cellValue);
                                        System.out.println("name: "+po.getPoName());
                                        break;
                                    case 3:
                                        po.setPoDescription(cellValue);
                                        break;
                                    case 4:
                                        po.setCurriculumId(Integer.parseInt(cellValue));
                                        System.out.println("curri : "+po.getCurriculumId());
                                        break;
                                    case 5:
                                        po.setIsActive(cellValue.equals("1"));
                                        break;
                                }
                            } catch (Exception e) {
                            }
                            count++;
                        }
                        if (count >= 5) {
                            if (poDao.checkExitstName(po.getPoName(), po.getCurriculumId())) {
                                System.out.println("name ex" +po.getPoName());
                                countFailFormat++;
                            } else {
                                if (poDao.add(po)) {
                                    countOK++;
                                } else {
                                    countFailFormat++;
                                }
                            }
                        }
                    }
                    
                }

            }
            workbook.close();
            System.out.println(v++);
            request.setAttribute("listPOFail", lisPOFail);
            request.setAttribute("countOk", countOK);
            request.setAttribute("countFaillFormat", countFailFormat);
            request.setAttribute("checkSheet", checkSheet);
        } catch (Exception e) {
            System.out.println(v++);
            System.out.println("Loi " + e.getMessage());
            response.getWriter().print(e.getMessage());
            e.printStackTrace();
        }

        int idCurrent = Integer.parseInt(request.getParameter("idCurrent").trim());
        request.setAttribute("idCurrent", idCurrent);
        request.getRequestDispatcher("/view/common/poList.jsp").forward(request, response);

        //response.sendRedirect(Constants.URL_PROJECT + Constants.URL_POLIST + "?idCurrent=" + idCurrent);
    }
}
