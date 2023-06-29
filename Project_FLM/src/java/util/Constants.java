/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author LanChau
 */
public class Constants {

    /**
     * Cấu hình DATABASE chỉ cần sửa name, user và pass là đủ
     */
    public static final String DB_NAME = "g5_flm";
    public static final String DB_USER_NAME = "root";
    public static final String DB_PASSWORD = "0888160699"; // sua mk cua b
    public static final String DB_URL = "jdbc:mysql://localhost:3306/"
            + Constants.DB_NAME + "?autoReconnect=true&useSSL=false";
    public static final String DB_CLASSFORNAME = "com.mysql.cj.jdbc.Driver";

    /**
     * Cấu hình URL
     */
    public static final String URL_PROJECT = "/Project_FLM";
    public static final String URL_HOMEPAGE = "/home";
    public static final String URL_LOGIN = "/login";
    public static final String URL_LOGIN_GOOGLE = "/LoginGoogle"; // sau fix lai 
    public static final String URL_REGISTER = "/register";
    public static final String URL_CURRICULAOVERVIEW = "/curriculaoverview";
    public static final String URL_ACCOUNT = "/account";
    public static final String URL_RESETPASSWORDG = "/resetPasswordG";
    public static final String URL_RESETPASSWORDP = "/resetPasswordP";
    public static final String URL_VERIFY = "/verify";
    public static final String URL_NEWPASSWORD = "/newPassword";
    public static final String URL_CURRICULUMLIST = "/curriculum";
    public static final String URL_CURRICULUMADD = "/curriculumAdd";
    public static final String URL_UPDATEUSERPROFILE = "/userprofile";
    public static final String URL_CHANGEPASS = "/changepass";
    public static final String URL_SETTINGLIST = "/settingList";
    public static final String URL_DELETESETTING = "/deleteSettingList";
    public static final String URL_ADDSETTING = "/addSettingList";
    public static final String URL_EDITSETTING = "/editSettingList";
    public static final String URL_DETAILSETTING = "/detailSetting";
    public static final String URL_SUBJECTLIST = "/subjectList";
    public static final String URL_ACCOUNTDETAIL = "/accountDetail";
    public static final String URL_ACCOUNTEDIT = "/accountEdit";
    public static final String URL_ACCOUNTADD = "/accountAdd";
    public static final String URL_CURRICULUMEDIT = "/curriculumedit";
    public static final String URL_ELECTIVE = "/elective";
    public static final String URL_SUBJECTPREDECESSOER = "/subjectPredecessoer";
    public static final String URL_SUBJECTSUCCESSOER = "/subjectSuccessoer";
    public static final String URL_ELECTIVEADD = "/electiveAdd";
    public static final String URL_ELECTIVEEDIT = "/electiveEdit";
    public static final String URL_ELECTIVEDETAIL = "/electiveDetail";
    public static final String URL_CONTENTGROUPLIST = "/contentgroup";
    public static final String URL_ADDSUBJECT = "/addSubjectList";
    public static final String URL_SUBJECTPLOMAPPING = "/SubjectPloMapping";
    public static final String URL_POLIST = "/PoList";
    public static final String URL_ADDPO = "/addPo";
    public static final String URL_UPDATEPO = "/updatePo";
    public static final String URL_EDITPO = "/editPo";
    public static final String URL_DETAILPO = "/detailPo";
    public static final String URL_IMPORTEXCELPO = "/importExcelPo";
    public static final String URL_CONTENTGROUPADD = "/contentgroupAdd";
    public static final String URL_CONTENTGROUPDETAIL = "/contentgroupDetail";
    public static final String URL_CONTENTGROUPEDIT = "/contentgroupEdit";
    public static final String URL_EDITSUBJECT = "/editSubject";
    public static final String URL_SUBJECTDETAIL = "/subjectsDetail";
    public static final String URL_SUBJECTIMPORT = "/subjectImport";
    public static final String URL_PLOLIST = "/plolist";
    public static final String URL_PLOADD = "/ploadd";
    public static final String URL_PLODEDIT = "/ploedit";
    public static final String URL_PLODETAILS = "/plodetails";
    public static final String URL_SYLLABUSLIST = "/syllabus";
    public static final String URL_CLOLIST = "/cloList";
    public static final String URL_UPDATEACTIVECLO = "/updateActiveClo";
    public static final String URL_DETAILCLO = "/detailClo";
    public static final String URL_ADDCLO = "/addClo";
    public static final String URL_EDITCLO = "/editlClo";
    public static final String URL_UPLOADEXCELCLO = "/uploadExcelClo";
    public static final String URL_PLOPOMAPPING = "/plopomapping";
    public static final String URL_SESSIONLIST = "/sessionlist";
    public static final String URL_SESSIONADD = "/sessionadd";
    public static final String URL_SESSIONEDIT = "/sessionedit";
    public static final String URL_SESSIONDETAIL = "/sessiondetail";
    public static final String URL_DECISIONCONTROLLER = "/decisionController";
    public static final String URL_DECISIONDETAIL = "/decisionDetail";

    /**
     * Cấu hình Page
     */
    public static final int PAGE_SIZE = 10;

    /**
     * Regex cho html
     */
    public static final String HTML_REGEX__FULLNAME = "^[a-zA-ZÀ-ỹ\\s]{10,}$";
    public static final String HTML_REGEX_USERNAME = "^[a-zA-Z1-9]{5,50}$";
    public static final String HTML_REGEX_NAME = "^[a-zA-Z1-9À-ỹ\\s]{5,100}$";
    public static final String HTML_REGEX_EMAIL = "^[\\w.-]+@[a-zA-Z_-]+?(?:\\.[a-zA-Z]{2,6})+$";
    public static final String HTML_REGEX_MOBILE = "[+]{0,1}[0-9]{10,12}";
    public static final String HTML_REGEX_VERIFY = "[+]{0,1}[0-9]{6}";
    public static final String HTML_REGEX_NUMBER = "[+]{0,1}[0-9]";
    public static final String HTML_REGEX_PASSWORD = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$";
    public static final String HTML_TITLE_OF_REGEX__FULLNAME = "Enter letters and at least 10 and no more than 100 characters";
    public static final String HTML_TITLE_OF_REGEX__VERIFY = "you need to enter 6 numbers";
    public static final String HTML_TITLE_OF_REGEX__USERNAME = "Enter at least 5 and no more than 50 characters";
    public static final String HTML_TITLE_OF_REGEX__NAME = "Enter at least 5 and no more than 100 characters";
    public static final String HTML_TITLE_OF_REGEX__EMAIL = "Email must be in the correct format";
    public static final String HTML_TITLE_OF_REGEX__MOBILE = "Phone number must be 10 to 12 digits";
    public static final String HTML_TITLE_OF_REGEX__PASSWORD = "Password must be at least eight characters, at least one letter, one number and one special character";

    // chuyển đổi
    public static void getURL(String url, String url_jsp, HttpServletRequest request, HttpServletResponse response) {
        try {
            String reURL = "";
            String[] arrURL = url.split("/");
            if (arrURL.length > 1) {
                for (int i = 0; i < arrURL.length - 2; i++) {
                    reURL += "../";
                }
            }
            reURL = reURL + url_jsp + ".jsp";
            request.getRequestDispatcher(reURL).forward(request, response);
        } catch (Exception e) {
        }

    }

    public static void main(String[] args) {
        // System.out.println(convertURL("/homepage", "home"));
    }

    /**
     * Cấu hình login google
     */
    public static final String GOOGLE_CLIENT_ID = "1041616500303-1hmohb3usnr25vbno0hk97m94qfon1e4.apps.googleusercontent.com";
    public static final String GOOGLE_CLIENT_SECRET = "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";
    public static final String GOOGLE_REDIRECT_URI = "http://localhost:9999/Project_FLM/LoginGoogle";
    public static final String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";
    public static final String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";
    public static final String GOOGLE_GRANT_TYPE = "authorization_code";

}
