/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author NgTienDung
 */
public class Clo {

    private int cloId;
    private String cloName;
    private Syllabus syllabus;
    private int syllabusId;
    private Clo cloParent;
    private int cloParentId;
    private String cloDescription;
    private boolean status;
    public Clo() {
    }

    public Clo(int cloId, String cloName, Syllabus syllabus, String cloDescription, boolean status, int cloParentId) {
        this.cloId = cloId;
        this.cloName = cloName;
        this.syllabus = syllabus;
        this.cloDescription = cloDescription;
        this.status = status;
         this.cloParentId = cloParentId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    
    public int getCloId() {
        return cloId;
    }

    public void setCloId(int cloId) {
        this.cloId = cloId;
    }

    public String getCloName() {
        return cloName;
    }

    public void setCloName(String cloName) {
        this.cloName = cloName;
    }

    public Syllabus getSyllabus() {
        return syllabus;
    }

    public void setSyllabus(Syllabus syllabus) {
        this.syllabus = syllabus;
    }

    public int getSyllabusId() {
        return syllabusId;
    }

    public void setSyllabusId(int syllabusId) {
        this.syllabusId = syllabusId;
    }

    public Clo getCloParent() {
        return cloParent;
    }

    public void setCloParent(Clo cloParent) {
        this.cloParent = cloParent;
    }

    public int getCloParentId() {
        return cloParentId;
    }

    public void setCloParentId(int cloParentId) {
        this.cloParentId = cloParentId;
    }

    public String getCloDescription() {
        return cloDescription;
    }

    public void setCloDescription(String cloDescription) {
        this.cloDescription = cloDescription;
    }
    
}
