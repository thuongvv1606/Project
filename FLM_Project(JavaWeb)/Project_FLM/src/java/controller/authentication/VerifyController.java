/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.authentication;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.User;
import util.Constants;

/**
 *
 * @author hp
 */
@WebServlet(name = Constants.URL_VERIFY, urlPatterns = {Constants.URL_VERIFY})
public class VerifyController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        

        String rawCode = req.getParameter("code");
        String verifyCode = (String) req.getSession().getAttribute("verifyCode");
        if (rawCode.equals(verifyCode)) {
            req.getRequestDispatcher("/view/common/newPassword.jsp").forward(req, resp);
        } else {
            req.setAttribute("mess", "Verification code is not correct. Please try again");
            req.setAttribute("code", rawCode);
            
            req.getRequestDispatcher("/view/common/resetPassword.jsp").forward(req, resp);

        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = "";
        if (req.getParameter("email") != null) {
           email = req.getParameter("email");
        }
        if (req.getParameter("phone") != null) {
            email = req.getParameter("phone");
        }
        req.setAttribute("email", email);
        req.getRequestDispatcher("/view/common/resetPassword.jsp").forward(req, resp);
    }
}
// }

//}
