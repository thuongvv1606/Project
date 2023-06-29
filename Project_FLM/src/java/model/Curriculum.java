/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author LanChau
 */
public class Curriculum {
    private int curriculumID;
    private String code;
    private String name;
    private String description;
    private int totalCredit;
    private Decision decision;
    private User user;
    private boolean is_active;
    private User owner;

    public Curriculum() {
    }

    public Curriculum(int curriculumID) {
        this.curriculumID = curriculumID;
    }

    public Curriculum(int curriculumID, String code, String name, String description, int totalCredit, Decision decision, User owner) {
        this.curriculumID = curriculumID;
        this.code = code;
        this.name = name;
        this.description = description;
        this.totalCredit = totalCredit;
        this.decision = decision;
        this.owner = owner;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Curriculum(int curriculumID, String code, String name, String description, int totalCredit, Decision decision, User owner, boolean is_active) {
        this.curriculumID = curriculumID;
        this.code = code;
        this.name = name;
        this.description = description;
        this.totalCredit = totalCredit;
        this.decision = decision;
        this.owner = owner;
        this.is_active = is_active;
    }
    public Curriculum(int curriculumID, String code, String name, String description, int totalCredit, boolean is_active) {
        this.curriculumID = curriculumID;
        this.code = code;
        this.name = name;
        this.description = description;
        this.totalCredit = totalCredit;
        this.is_active = is_active;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public int getCurriculumID() {
        return curriculumID;
    }

    public void setCurriculumID(int curriculumID) {
        this.curriculumID = curriculumID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public int getTotalCredit() {
        return totalCredit;
    }

    public void setTotalCredit(int totalCredit) {
        this.totalCredit = totalCredit;
    }

    public Decision getDecision() {
        return decision;
    }

    public void setDecision(Decision decision) {
        this.decision = decision;
    }

    @Override
    public String toString() {
        return "Curriculum{" + "curriculumID=" + curriculumID + ", code=" + code + ", name=" + name + ", description=" + description + ", totalCredit=" + totalCredit + ", decision=" + decision + ", owner=" + owner + ", is_active=" + is_active + ", owner=" + owner + '}';
    }

    
}
