/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author hp
 */
public class Pre_requisite {
    private int subjectId;
    private int preId;
    private String code;

    public Pre_requisite() {
    }

    public Pre_requisite(int subjectId, int preId, String code) {
        this.subjectId = subjectId;
        this.preId = preId;
        this.code = code;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getPreId() {
        return preId;
    }

    public void setPreId(int preId) {
        this.preId = preId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Pre_requisite{" + "subjectId=" + subjectId + ", preId=" + preId + ", code=" + code + '}';
    }
    
    
}
