/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author NgTienDung
 */
public class Subject {

    private int id, semester, no_credit;
    private String name, code, pre_condition;

    public Subject() {
    }

    public Subject(String code) {
        this.code = code;
    }

    public Subject(int id, int semester, int no_credit, String name, String code) {
        this.id = id;
        this.semester = semester;
        this.no_credit = no_credit;
        this.name = name;
        this.code = code;
    }

    public Subject(int id, int semester, int no_credit, String name, String code, String pre_condition) {
        this.id = id;
        this.semester = semester;
        this.no_credit = no_credit;
        this.name = name;
        this.code = code;
        this.pre_condition = pre_condition;
    }

    public Subject(int id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public Subject(int subject_id, String code, String name, int semester, int no_credit, String pre_condition) {
        this.id = subject_id;
        this.semester = semester;
        this.no_credit = no_credit;
        this.name = name;
        this.code = code;
        this.pre_condition = pre_condition;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getNo_credit() {
        return no_credit;
    }

    public void setNo_credit(int no_credit) {
        this.no_credit = no_credit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPre_condition() {
        return pre_condition;
    }

    public void setPre_condition(String pre_condition) {
        this.pre_condition = pre_condition;
    }

    @Override
    public String toString() {
        return "Subject{" + "id=" + id + ", semester=" + semester + ", no_credit=" + no_credit + ", name=" + name + ", code=" + code + ", pre_condition=" + pre_condition + '}';
    }

}
