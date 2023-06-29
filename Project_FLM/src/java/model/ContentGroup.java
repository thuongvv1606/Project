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
    private String type, name;
    private List<Subject> sList;

    public ContentGroup() {
    }

    public ContentGroup(int id, Curriculum curriculum, String type, String name, List<Subject> sList) {
        this.id = id;
        this.curriculum = curriculum;
        this.type = type;
        this.name = name;
        this.sList = sList;
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
    
}
