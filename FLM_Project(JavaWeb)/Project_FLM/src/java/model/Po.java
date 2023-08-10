/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author LanChau
 */
public class Po {
    private int poId;
    private String poName;
    private String poDescription;
    private Curriculum curriculum;
    private int curriculumId;
    private boolean isActive;

    public Po() {
    }

    public Po(int poId, String poName, String poDescription, Curriculum curriculum, boolean isActive) {
        this.poId = poId;
        this.poName = poName;
        this.poDescription = poDescription;
        this.curriculum = curriculum;
        this.isActive = isActive;
    }
    
    public Po(int poId, String poName, String poDescription, int curriculumId) {
        this.poId = poId;
        this.poName = poName;
        this.poDescription = poDescription;
        this.curriculumId = curriculumId;
    }


    public Po(int poId, String poName, String poDescription, int curriculumId, boolean isActive) {
        this.poId = poId;
        this.poName = poName;
        this.poDescription = poDescription;
        this.curriculumId = curriculumId;
        this.isActive = isActive;
    }

    public Po(int poId, String poName, String poDescription, Curriculum curriculum, int curriculumId, boolean isActive) {
        this.poId = poId;
        this.poName = poName;
        this.poDescription = poDescription;
        this.curriculum = curriculum;
        this.curriculumId = curriculumId;
        this.isActive = isActive;
    }

    public int getPoId() {
        return poId;
    }

    public void setPoId(int poId) {
        this.poId = poId;
    }

    public String getPoName() {
        return poName;
    }

    public void setPoName(String poName) {
        this.poName = poName;
    }

    public String getPoDescription() {
        return poDescription;
    }

    public void setPoDescription(String poDescription) {
        this.poDescription = poDescription;
    }

    public int getCurriculumId() {
        return curriculumId;
    }

    public void setCurriculumId(int curriculumId) {
        this.curriculumId = curriculumId;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

 
  
    public Curriculum getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
    }

    @Override
    public String toString() {
        return "Po{" + "poId=" + poId + ", poName=" + poName + ", poDescription=" + poDescription + ", curriculum=" + curriculum + ", curriculumId=" + curriculumId + ", isActive=" + isActive + '}';
    }


    
    
}
