/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author DungNT
 */
public class SubjectGroup {

    private int subject_group_id;
    private int curriculumID;
    private Curriculum curriculum;
    private String group_type;
    private String name;

    public SubjectGroup() {
    }

    public SubjectGroup(int curriculumID, String group_type, String name) {
        this.curriculumID = curriculumID;
        this.group_type = group_type;
        this.name = name;
    }

    public SubjectGroup(int subject_group_id, Curriculum curriculum, String group_type, String name) {
        this.subject_group_id = subject_group_id;
        this.curriculum = curriculum;
        this.group_type = group_type;
        this.name = name;
    }

    public int getCurriculumID() {
        return curriculumID;
    }

    public void setCurriculumID(int curriculumID) {
        this.curriculumID = curriculumID;
    }

    public int getSubject_group_id() {
        return subject_group_id;
    }

    public void setSubject_group_id(int subject_group_id) {
        this.subject_group_id = subject_group_id;
    }

    public Curriculum getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
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

}

