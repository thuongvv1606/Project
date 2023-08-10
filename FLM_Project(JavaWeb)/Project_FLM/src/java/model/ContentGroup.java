/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;

/**
 *
 * @author TT
 */
public class ContentGroup {
    private int id;
    private Curriculum curriculum;
    private String type, ename, name;
    private List<Subject> sList;
    private int order;

    public ContentGroup() {
    }

    public ContentGroup(int id, Curriculum curriculum, String type, String name, String ename, List<Subject> sList, int order) {
        this.id = id;
        this.curriculum = curriculum;
        this.type = type;
        this.name = name;
        this.ename = ename;
        this.sList = sList;
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Curriculum getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Subject> getsList() {
        return sList;
    }

    public void setsList(List<Subject> sList) {
        this.sList = sList;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }
    
}
