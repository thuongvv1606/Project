/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.oracle.wls.shaded.org.apache.bcel.generic.AALOAD;
import dao.DecisionDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import model.Decision;
import model.User;
import static org.apache.el.lang.ELArithmetic.add;

/**
 *
 * @author hp
 */
@WebServlet(name = "decisionController", urlPatterns = {"/decisionController"})
public class DecisionController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        jakarta.servlet.http.HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.setContentType("text/html;charset=UTF-8");
            try ( PrintWriter out = response.getWriter()) {
                out.print("You need to login again");
            }
        }

        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }
        switch (action) {
            case "active": {
                active(request, response);
                break;
            }
            case "list": {
                list(request, response);
                break;
            }
            case "filter": {
                filter(request, response);
                break;
            }
            case "add": {
                request.getRequestDispatcher("/view/common/decisionAdd.jsp").forward(request, response);
                break;
            }
            case "edit": {
                request.getRequestDispatcher("/view/common/decisionDetail.jsp").forward(request, response);
                break;
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(DecisionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(DecisionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void active(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, Exception {
        DecisionDao decisionDao = new DecisionDao();
        int id = Integer.parseInt(request.getParameter("id"));
        String is_active = request.getParameter("is_active");
        decisionDao.updateDecisionActive(id, is_active);
        list(request, response);
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        HttpSession session = (HttpSession) request.getSession();
        User user = (User) session.getAttribute("user");

        String decision_no = request.getParameter("decisionNo");
        String decision_name = request.getParameter("decisionName");
        String decision_date = request.getParameter("decisionDate");

        // Convert ngày từ kiểu String sang kiểu Date để lưu vào cơ sở dữ liệu
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = df.parse(decision_date);

        // Lấy giá trị của biến active và chuyển đổi sang kiểu boolean
        Boolean is_active = request.getParameter("active").equals("1");

        DecisionDao decisionDao = new DecisionDao();
        String message = "";
        if (decisionDao.checkNo(decision_no)) {
            message = "Code Duplicate!";
        } else {
            int id = user.getUserId();
            Decision decision = new Decision(decision_no, decision_name, new java.sql.Date(date.getTime()), id, is_active);
            boolean check = decisionDao.addDecision(decision);
            if (!check) {
                message = "Add fails";
            } else {
                message = "success";
            }
        }

        request.setAttribute("message", message);
        request.getRequestDispatcher("/view/common/decisionAdd.jsp").forward(request, response);
    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {
        String message = "";
        String indexPage = request.getParameter("index");
        if (indexPage == null) {
            indexPage = "1";
        }
        int index = Integer.parseInt(indexPage);
        DecisionDao decisionDao = new DecisionDao();
        ArrayList<Decision> decisionList = new ArrayList<>();
        int count = 0;
        int endPage = 0;

        String raw_inputSearch = request.getParameter("inputSearch");

        try {
            if (request.getParameter("filter_btn") != null) {
                String filterByStatus = request.getParameter("filterByStatus");
                request.setAttribute("filterByStatus", filterByStatus);

                if (filterByStatus.equals("-1")) {
                    String inputSearch = (raw_inputSearch != null && raw_inputSearch.length() > 0) ? raw_inputSearch : null;
                    decisionList = decisionDao.getDecisionList(inputSearch, index);
                    count = decisionList.size();
                    endPage = count / 5;
                    if (count % 5 != 0) {
                        endPage++;
                    }
                } else {
                    decisionList = decisionDao.getDecisionByStatus(filterByStatus, index);
                        count = decisionList.size();
                    endPage = count / 5;
                    if (count % 5 != 0) {
                        endPage++;
                    }
                }
            } else {
                String inputSearch = (raw_inputSearch != null && raw_inputSearch.length() > 0) ? raw_inputSearch : null;
                decisionList = decisionDao.getDecisionList(inputSearch, index);
                count = decisionDao.getNumberOfDecision();
                endPage = count / 5;
                if (count % 5 != 0) {
                    endPage++;
                }
            }
        } catch (Exception ex) {
            message = "decisions error";
        }
        request.setAttribute("end", endPage);
        request.setAttribute("index", index);
        request.setAttribute("count", count);
        request.setAttribute("decisionList", decisionList);
        request.getRequestDispatcher("/view/common/decisionList.jsp").forward(request, response);
    }

    private void filter(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
