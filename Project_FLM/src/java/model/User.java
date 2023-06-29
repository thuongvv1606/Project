/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HaPi
 */
public class User {

    private int userId;
    private String fullName;
    private String userName;
    private String email;
    private String mobile;
    private String password;
    private Boolean active;
    private String title;
    private String company;
    private String avatar;
    private List<Role> roles = new ArrayList<>();

    public User() {
    }

    public User(int userId, String fullName, String userName, String email, String mobile, 
            String password, Boolean active, String title, String company, String avatar, List<Role> roles) {
        this.userId = userId;
        this.fullName = fullName;
        this.userName = userName;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.active = active;
        this.title = title;
        this.company = company;
        this.avatar = avatar;
        this.roles = roles;
    }
    
     public User(String fullName, String userName, String email, String mobile, String title, String company) {
        this.fullName = fullName;
        this.userName = userName;
        this.email = email;
        this.mobile = mobile;
        this.title = title;
        this.company = company;
    }

    public User(int userId, String fullName, String userName, String email, String mobile, String password, String title, String company, String avatar, Boolean active) {
        this.userId = userId;
        this.fullName = fullName;
        this.userName = userName;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.active = active;
        this.title = title;
        this.company = company;
        this.avatar = avatar;
    }
    public User(int userId, String fullName, String userName, String email, 
            String mobile, String password, String title, String company, Boolean active, List<Role> roles) {
        this.userId = userId;
        this.fullName = fullName;
        this.userName = userName;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.active = active;
        this.title = title;
        this.company = company;
        this.roles = roles;
    }

    public User(String fullName, String userName, String email, String mobile, String password, Boolean active) {
        this.fullName = fullName;
        this.userName = userName;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.active = active;
    }

    public User(int userId, String fullName, String userName, String email, String mobile, String password, Boolean active) {
        this.userId = userId;
        this.fullName = fullName;
        this.userName = userName;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.active = active;
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getUserId() {
        return userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String printfRole() {
        String result = "";
        for (Role role : roles) {
            result += role.getName() + ", ";
        }
        return result.length() > 0 ? result.trim().substring(0, result.length() - 1) : "";
    }

    public int getRoleFunction() {
        int relust = -1;
        boolean systemAdmin = false;
        boolean cRDDHead = false;
        boolean CRDDStaff = false;
        boolean sysllabusDesigner = false;
        boolean sysllabusReviewer = false;
        boolean teacher = false;
        boolean student = false;
        for (Role role : roles) {
            switch (role.getId()) {
                case 1:
                    systemAdmin = true;
                    break;
                case 2:
                    cRDDHead = true;
                    break;
                case 3:
                    CRDDStaff = true;
                    break;
                case 4:
                    sysllabusDesigner = true;
                    break;
                case 5:
                    sysllabusReviewer = true;
                    break;
                case 6:
                    teacher = true;
                    break;
                case 7:
                    student = true;
                    break;
            }
        }
        if (student || teacher) {
            relust = 0;
        }
        if (sysllabusDesigner || sysllabusReviewer) {
            relust = 1;
        }
        if (systemAdmin || cRDDHead || CRDDStaff) {
            relust = 2;
        }
        return relust;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", fullName=" + fullName + ", userName=" + userName + ", email=" + email + ", mobile=" + mobile + ", password=" + password + ", active=" + active + ", title=" + title + ", company=" + company + ", avatar=" + avatar + ", roles=" + roles + '}';
    }

}
