/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author hp
 */
public class Combo {
    private int comboId;
    private int curriculumId;
    private Curriculum curriculum;
    private Subject subject;
    private String group_type;
    private String name;
    private String englishName;

    public Combo(int comboId, int curriculumId, Curriculum curriculum, String group_type, String name, String englishName) {
        this.comboId = comboId;
        this.curriculumId = curriculumId;
        this.curriculum = curriculum;
        this.subject = subject;
        this.group_type = group_type;
        this.name = name;
        this.englishName = englishName;
    }
    

    public Combo(int comboId, int curriculumId, Curriculum curriculum, String group_type, String name) {
        this.comboId = comboId;
        this.curriculumId = curriculumId;
        this.curriculum = curriculum;
        this.group_type = group_type;
        this.name = name;
    }

    public Combo(int comboId, String group_type, String name, String englishName) {
        this.comboId = comboId;
        this.group_type = group_type;
        this.name = name;
        this.englishName = englishName;
    }

    public Combo(int comboId, Curriculum curriculum, String group_type, String name) {
        this.comboId = comboId;
        this.curriculum = curriculum;
        this.group_type = group_type;
        this.name = name;
    }

    public Combo(int curriculumId, String group_type, String name) {
        this.curriculumId = curriculumId;
        this.group_type = group_type;
        this.name = name;
    }
    

    public Combo(int comboId, int curriculumId, String group_type, String name) {
        this.comboId = comboId;
        this.curriculumId = curriculumId;
        this.group_type = group_type;
        this.name = name;
    }

    public Combo(int comboId, int curriculumId, String group_type, String name, String englishName) {
        this.comboId = comboId;
        this.curriculumId = curriculumId;
        this.group_type = group_type;
        this.name = name;
        this.englishName = englishName;
    }
    
    
    
    


    public int getComboId() {
        return comboId;
    }

    public void setComboId(int comboId) {
        this.comboId = comboId;
    }

    public int getCurriculumId() {
        return curriculumId;
    }

    public void setCurriculumId(int curriculumId) {
        this.curriculumId = curriculumId;
    }

    public Curriculum getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getGroup_type() {
        return group_type;
    }

    public void setGroup_type(String group_type) {
        this.group_type = group_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    @Override
    public String toString() {
        return "Combo{" + "comboId=" + comboId + ", curriculumId=" + curriculumId + ", curriculum=" + curriculum + ", subject=" + subject + ", group_type=" + group_type + ", name=" + name + ", englishName=" + englishName + '}';
    }

    
    
}
