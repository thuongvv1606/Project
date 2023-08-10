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
public class Elective {
    private int id;
    private String code;
    private String name;
    private String description;
    private boolean active;
    private Curriculum curriculum;
    private List<Subject> subject; 

    public Elective() {
    }

    public Elective(int id, String code, String name, String description, boolean active, Curriculum curriculum, List<Subject> subject) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.active = active;
        this.curriculum = curriculum;
        this.subject = subject;
    }
    
    public Elective(int id, String name, String description, boolean active, Curriculum curriculum) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.active = active;
        this.curriculum = curriculum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Curriculum getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
    }

    public List<Subject> getSubject() {
        return subject;
    }

    public void setSubject(List<Subject> subject) {
        this.subject = subject;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
}
