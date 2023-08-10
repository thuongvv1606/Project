/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LanChau
 */
public class Syllabus {
    private int syllabusId;
    private String name;
    private String description;
    private boolean status;
    private int decisionId;
    private int designerId;
    private int reviewerId;
    private int subjectId;
    private String degree_level;
    private Subject subject;
    private Decision decision;
    private ArrayList<Pre_requisite> pre;
    private User designer_id;
    private User reviewer;
    private String english_name;
    private int credit;
    private String time;
    private String student_task;
    private String tool;
    private int score;
    private boolean is_approved;
    private String note;
    private int min_mark;
    private String date;
    private List<Subject> subject_pre;

    public Syllabus() {
    }
    
    public Syllabus(int syllabusId, String name, String description, boolean status, int decisionId, int subjectId) {
        this.syllabusId = syllabusId;
        this.name = name;
        this.description = description;
        this.status = status;
        this.decisionId = decisionId;
        this.subjectId = subjectId;
    }

    public Syllabus(int syllabusId, String name, String description, boolean status, int decisionId, int designerId, int reviewerId, int subjectId, String degree_level) {
        this.syllabusId = syllabusId;
        this.name = name;
        this.description = description;
        this.status = status;
        this.decisionId = decisionId;
        this.designerId = designerId;
        this.reviewerId = reviewerId;
        this.subjectId = subjectId;
        this.degree_level = degree_level;
    }

    public Syllabus(int syllabusId, String name, Subject subject, Decision decision, ArrayList<Pre_requisite> pre) {
        this.syllabusId = syllabusId;
        this.name = name;
        this.subject = subject;
        this.decision = decision;
        this.pre = pre;
    }

    public Syllabus(int syllabusId, String name, String english_name, int credit, String time, String student_task,
            String tool, int score, boolean is_approved, String note, int min_mark, String date, List<Subject> subject_pre, String description, 
            boolean status, Decision decision, User designer_id, User reviewer, Subject subject, String degree_level) {
        this.syllabusId = syllabusId;
        this.name = name;
        this.description = description;
        this.status = status;
        this.subject = subject;
        this.designer_id = designer_id;
        this.decision = decision;
        this.degree_level = degree_level;
        this.english_name = english_name;
        this.credit = credit;
        this.time = time;
        this.student_task = student_task;
        this.tool = tool;
        this.score = score;
        this.is_approved = is_approved;
        this.note = note;
        this.min_mark = min_mark;
        this.date = date;
        this.subject_pre = subject_pre;
        this.reviewer = reviewer;
    }

    public int getSyllabusId() {
        return syllabusId;
    }

    public void setSyllabusId(int syllabusId) {
        this.syllabusId = syllabusId;
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

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getDecisionId() {
        return decisionId;
    }

    public void setDecisionId(int decisionId) {
        this.decisionId = decisionId;
    }

    public int getDesignerId() {
        return designerId;
    }

    public void setDesignerId(int designerId) {
        this.designerId = designerId;
    }

    public int getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(int reviewerId) {
        this.reviewerId = reviewerId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getDegree_level() {
        return degree_level;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Decision getDecision() {
        return decision;
    }

    public void setDecision(Decision decision) {
        this.decision = decision;
    }

    public ArrayList<Pre_requisite> getPre() {
        return pre;
    }

    public void setPre(ArrayList<Pre_requisite> pre) {
        this.pre = pre;
    }

    public void setDegree_level(String degree_level) {
        this.degree_level = degree_level;
    }

    public User getDesigner_id() {
        return designer_id;
    }

    public void setDesigner_id(User designer_id) {
        this.designer_id = designer_id;
    }

    public String getEnglish_name() {
        return english_name;
    }

    public void setEnglish_name(String english_name) {
        this.english_name = english_name;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStudent_task() {
        return student_task;
    }

    public void setStudent_task(String student_task) {
        this.student_task = student_task;
    }

    public String getTool() {
        return tool;
    }

    public void setTool(String tool) {
        this.tool = tool;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isIs_approved() {
        return is_approved;
    }

    public void setIs_approved(boolean is_approved) {
        this.is_approved = is_approved;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getMin_mark() {
        return min_mark;
    }

    public void setMin_mark(int min_mark) {
        this.min_mark = min_mark;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Subject> getSubject_pre() {
        return subject_pre;
    }

    public void setSubject_pre(List<Subject> subject_pre) {
        this.subject_pre = subject_pre;
    }

    public User getReviewer() {
        return reviewer;
    }

    public void setReviewer(User reviewer) {
        this.reviewer = reviewer;
    }
    
    @Override
    public String toString() {
        return "Syllabus{" + "syllabusId=" + syllabusId + ", name=" + name + ", description=" + description + ", status=" + status + ", decisionId=" + decisionId + ", designerId=" + designerId + ", reviewerId=" + reviewerId + ", subjectId=" + subjectId + ", degree_level=" + degree_level + ", subject=" + subject + ", decision=" + decision + ", pre=" + pre + '}';
    }
    
    
}
