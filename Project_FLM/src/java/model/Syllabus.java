/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

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

    public Syllabus(int syllabusId, String name, String description, boolean status, Decision decision, User designer_id, Subject subject) {
        this.syllabusId = syllabusId;
        this.name = name;
        this.description = description;
        this.status = status;
        this.subject = subject;
        this.designer_id = designer_id;
        this.decision = decision;
    }

    public Syllabus(int syllabusId, String name, String description, boolean status) {
        this.syllabusId = syllabusId;
        this.name = name;
        this.description = description;
        this.status = status;
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
    
    @Override
    public String toString() {
        return "Syllabus{" + "syllabusId=" + syllabusId + ", name=" + name + ", description=" + description + ", status=" + status + ", decisionId=" + decisionId + ", designerId=" + designerId + ", reviewerId=" + reviewerId + ", subjectId=" + subjectId + ", degree_level=" + degree_level + ", subject=" + subject + ", decision=" + decision + ", pre=" + pre + '}';
    }
    
    
}
