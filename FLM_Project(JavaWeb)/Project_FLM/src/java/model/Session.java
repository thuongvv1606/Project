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
    private String topic, learning_type,student_materials,student_task,lo,itu,url;

    public Session() {
    }

    public Session(int session_id, int syllabus_id, String topic, String details, String learning_type, String student_materials, String student_task) {
        this.session_id = session_id;
        this.syllabus_id = syllabus_id;
        this.topic = topic;
        this.learning_type = learning_type;
        this.student_materials = student_materials;
        this.student_task = student_task;
    }

    public Session(int session_id, int syllabus_id, String topic, String learning_type, String student_materials, String student_task, String lo, String itu, String url) {
        this.session_id = session_id;
        this.syllabus_id = syllabus_id;
        this.topic = topic;
        this.learning_type = learning_type;
        this.student_materials = student_materials;
        this.student_task = student_task;
        this.lo = lo;
        this.itu = itu;
        this.url = url;
    }
    
    

    public Session(int session_id) {
        this.session_id = session_id;
    }

    public String getLo() {
        return lo;
    }

    public void setLo(String lo) {
        this.lo = lo;
    }

    public String getItu() {
        return itu;
    }

    public void setItu(String itu) {
        this.itu = itu;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
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

    @Override
    public String toString() {
        return "Session{" + "session_id=" + session_id + ", syllabus_id=" + syllabus_id + ", topic=" + topic + ", learning_type=" + learning_type + ", student_materials=" + student_materials + ", student_task=" + student_task + ", lo=" + lo + ", itu=" + itu + ", url=" + url + '}';
    }
    
    
}
