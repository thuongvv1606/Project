/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author trinh
 */
public class Session {
    private int session_id,syllabus_id;
    private String name, details, learning_type,student_materials,student_task;

    public Session() {
    }

    public Session(int session_id, int syllabus_id, String name, String details, String learning_type, String student_materials, String student_task) {
        this.session_id = session_id;
        this.syllabus_id = syllabus_id;
        this.name = name;
        this.details = details;
        this.learning_type = learning_type;
        this.student_materials = student_materials;
        this.student_task = student_task;
    }

    public Session(int session_id) {
        this.session_id = session_id;
    }

    
    
    public int getSession_id() {
        return session_id;
    }

    public void setSession_id(int session_id) {
        this.session_id = session_id;
    }

    public int getSyllabus_id() {
        return syllabus_id;
    }

    public void setSyllabus_id(int syllabus_id) {
        this.syllabus_id = syllabus_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getLearning_type() {
        return learning_type;
    }

    public void setLearning_type(String learning_type) {
        this.learning_type = learning_type;
    }

    public String getStudent_materials() {
        return student_materials;
    }

    public void setStudent_materials(String student_materials) {
        this.student_materials = student_materials;
    }

    public String getStudent_task() {
        return student_task;
    }

    public void setStudent_task(String student_task) {
        this.student_task = student_task;
    }
    
    
}
