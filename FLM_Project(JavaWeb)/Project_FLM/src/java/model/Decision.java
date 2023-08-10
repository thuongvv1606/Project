/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.util.ArrayList;


/**
 *
 * @author LanChau
 */
public class Decision {
    private int id;
    private String decision_no;
    private String decision_name;
    private Date decision_date;
    private int creator_id;
    private User creator;
    private ArrayList<Curriculum> curriculumList;
    private ArrayList<Syllabus> syllabusList;
    private boolean is_active;
    private String date;

    public Decision() {
    }

    public Decision(int id, String decision_no, Date decision_date) {
        this.id = id;
        this.decision_no = decision_no;
        this.decision_date = decision_date;
    }
    
    public Decision(String decision_no, Date decision_date) {
        this.decision_no = decision_no;
        this.decision_date = decision_date;
    }

    public Decision(int id, String decision_no, Date decision_date, User creator) {
        this.id = id;
        this.decision_no = decision_no;
        this.decision_date = decision_date;
        this.creator = creator;
    }

    
    public Decision(int id, String decision_no, Date decision_date, User creator, boolean is_active, String decision_name) {
        this.id = id;
        this.decision_no = decision_no;
        this.decision_name = decision_name;
        this.decision_date = decision_date;
        this.creator = creator;
        this.is_active = is_active;
    }
    
    public Decision(int id, String decision_no, String decision_date, User creator, boolean is_active, String decision_name) {
        this.id = id;
        this.decision_no = decision_no;
        this.decision_name = decision_name;
        this.date = decision_date;
        this.creator = creator;
        this.is_active = is_active;
    }

    public Decision(String decision_no, String decision_name, Date decision_date, int creator_id, boolean is_active) {
        this.decision_no = decision_no;
        this.decision_name = decision_name;
        this.decision_date = decision_date;
        this.creator_id = creator_id;
        this.is_active = is_active;
    }
    
    public Decision(int id, String decision_no, String decision_name, Date decision_date, int creator_id, User creator, boolean is_active) {
        this.id = id;
        this.decision_no = decision_no;
        this.decision_name = decision_name;
        this.decision_date = decision_date;
        this.creator_id = creator_id;
        this.creator = creator;
        this.is_active = is_active;
    }
    
    public Decision(int id, String decision_no, String decision_name, String decision_date, int creator_id, User creator, boolean is_active) {
        this.id = id;
        this.decision_no = decision_no;
        this.decision_name = decision_name;
        this.date = decision_date;
        this.creator_id = creator_id;
        this.creator = creator;
        this.is_active = is_active;
    }

    public Decision(int id, String decision_no, String decision_name, Date decision_date, int creator_id, User creator, ArrayList<Curriculum> curriculumList, ArrayList<Syllabus> syllabusList, boolean is_active) {
        this.id = id;
        this.decision_no = decision_no;
        this.decision_name = decision_name;
        this.decision_date = decision_date;
        this.creator_id = creator_id;
        this.creator = creator;
        this.curriculumList = curriculumList;
        this.syllabusList = syllabusList;
        this.is_active = is_active;
    }
    
    public Decision(int id, String decision_no, String decision_name, Date decision_date, boolean is_active, User creator, ArrayList<Curriculum> curriculumList, ArrayList<Syllabus> syllabusList) {
        this.id = id;
        this.decision_no = decision_no;
        this.decision_name = decision_name;
        this.decision_date = decision_date;
        this.creator = creator;
        this.curriculumList = curriculumList;
        this.syllabusList = syllabusList;
        this.is_active = is_active;
    }
    
    public Decision(int id, String decision_no, String decision_name, Date decision_date, boolean is_active) {
        this.id = id;
        this.decision_no = decision_no;
        this.decision_name = decision_name;
        this.decision_date = decision_date;
        this.is_active = is_active;
    }

    public String getDecision_name() {
        return decision_name;
    }

    public void setDecision_name(String decision_name) {
        this.decision_name = decision_name;
    }

    

    public ArrayList<Curriculum> getCurriculumList() {
        return curriculumList;
    }

    public void setCurriculumList(ArrayList<Curriculum> curriculumList) {
        this.curriculumList = curriculumList;
    }

    public ArrayList<Syllabus> getSyllabusList() {
        return syllabusList;
    }

    public void setSyllabusList(ArrayList<Syllabus> syllabusList) {
        this.syllabusList = syllabusList;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }
    

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Decision(int id, String decision_no, Date decision_date, int creator_id) {
        this.id = id;
        this.decision_no = decision_no;
        this.decision_date = decision_date;
        this.creator_id = creator_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDecision_no() {
        return decision_no;
    }

    public void setDecision_no(String decision_no) {
        this.decision_no = decision_no;
    }

    public Date getDecision_date() {
        return decision_date;
    }

    public void setDecision_date(Date decision_date) {
        this.decision_date = decision_date;
    }

    public int getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(int creator_id) {
        this.creator_id = creator_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Decision{" + "id=" + id + ", decision_no=" + decision_no + ", decision_name=" + decision_name + ", decision_date=" + decision_date + ", creator_id=" + creator_id + ", creator=" + creator + ", curriculumList=" + curriculumList + ", syllabusList=" + syllabusList + ", is_active=" + is_active + '}';
    }
    
}
