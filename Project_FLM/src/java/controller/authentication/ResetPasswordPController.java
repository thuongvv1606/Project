/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.authentication;

import com.twilio.sdk.TwilioRestException;
import dao.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import util.Constants;
import verify.RandomCode;

/**
 *
 * @author hp
 */
@WebServlet(name = Constants.URL_RESETPASSWORDP, urlPatterns = {Constants.URL_RESETPASSWORDP})
public class ResetPasswordPController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String phone = req.getParameter("phone");
        //activate 6-digit code
        RandomCode rc=new RandomCode();
        String verifyCode=rc.activateCode();
        
        if (phone.isEmpty()) {
            req.setAttribute("mess", "not empty");
            req.getRequestDispatcher("/view/common/resetPassword.jsp").forward(req, resp);
        } else {
            UserDao dao = new UserDao();
            User u = dao.getUserByPhone(phone);

            if (u == null) {
                req.setAttribute("mess", "Account does not exist");
                req.setAttribute("phone", phone);
                req.getRequestDispatcher("/view/common/resetPassword.jsp").forward(req, resp);
            } else {
                SendPhoneController se = new SendPhoneController();
                String code = "+84" + phone.substring(1, 10);
                try {
                    se.sendMessage(code, verifyCode);
                } catch (TwilioRestException ex) {
                    Logger.getLogger(ResetPasswordPController.class.getName()).log(Level.SEVERE, null, ex);
                }
                req.getSession().setAttribute("verifyCode", verifyCode);
                req.getSession().setAttribute("status", "resetP");
                req.getSession().setAttribute("email", code);
                req.getRequestDispatcher("/view/common/resetPassword.jsp").forward(req, resp);
            }
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/common/resetPassword.jsp").forward(req, resp);
    }
}
