/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.SessionDao;
import dao.SyllabusDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import model.Session;
import model.Syllabus;
import model.User;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import util.Constants;

/**
 *
 * @author trinh
 */
@MultipartConfig
@WebServlet(name = Constants.URL_UPLOADEXCELSESSION, urlPatterns = {Constants.URL_UPLOADEXCELSESSION})
public class UpLoadExcelSessionController extends HttpServlet {

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
            if(user.getRoleFunction() != 2){
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        authorization(req, resp);
        int id = Integer.parseInt(req.getParameter("syllabusId"));
        req.setAttribute("id", id);
    }

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        authorization(req, resp);
        InputStream inputStream = null;
        try {
            ArrayList<Session> listClosFail = new ArrayList<>();
            int countOk = 0;
            int countFaillFormat = 0;
            inputStream = req.getPart("file").getInputStream();

            Workbook workbook = new XSSFWorkbook(inputStream);
            DataFormatter dataFormatter = new DataFormatter();

            StringBuilder result = new StringBuilder();
            int count = 0;
            Session session = new Session();
            SessionDao sessionDao = new SessionDao();
            boolean checkSheet = true; // true la ko thay ten sheeet 
            for (Sheet sheet : workbook) {
                if (sheet.getSheetName().equals("SessionList")) {
                    checkSheet = false;

                    for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                        Row row = sheet.getRow(i);
                        count = 1;
                        session = new Session();
                        for (Cell cell : row) {
                            String cellValue = dataFormatter.formatCellValue(cell);
                            try {
                                switch (count) {
                                    case 1:
                                        //session.setSession_id(Integer.parseInt(cellValue));
                                        break;
                                    case 2:
                                        session.setSyllabus_id(Integer.parseInt(cellValue));
                                        break;
                                    case 3:
                                        session.setTopic(cellValue);
                                        break;
                                    case 4:
                                        session.setLearning_type(cellValue);
                                        break;
                                    case 5:
                                        session.setItu(cellValue);
                                        break;
                                    case 6:
                                        session.setLo(cellValue);
                                        break;
                                    case 7:
                                        session.setStudent_materials(cellValue);
                                        break;
                                    case 8:
                                        session.setStudent_task(cellValue);
                                        break;
                                    case 9:
                                        session.setUrl(cellValue);
                                        break;
                                }
                            } catch (Exception e) {
                                System.out.println(e.getMessage());

                            }

                            count++;
                        }
                        if (count >= 8) {
                            if (sessionDao.checkExist(session.getTopic())) {
                                countFaillFormat++;
                            } else {
                                if (sessionDao.addSession(session)) {
                                    countOk++;
                                } else {
                                    countFaillFormat++;
                                }
                            }
                        }
                    }
                } else {

                }

            }
            workbook.close();
            req.setAttribute("listClosFail", listClosFail);
            req.setAttribute("countOk", countOk);
            req.setAttribute("countFaillFormat", countFaillFormat);
            req.setAttribute("checkSheet", checkSheet);
        } catch (Exception e) {
            resp.getWriter().print(e.getMessage());
            e.printStackTrace();
        }
        int id = Integer.parseInt(req.getParameter("syllabusId"));
        req.setAttribute("id", id);
        req.getRequestDispatcher("/view/common/sessionList.jsp").forward(req, resp);

    }

}
