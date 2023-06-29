/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author DungNT
 */
public class MappingPLOSubject {
    private int plo,subjectId;

    public MappingPLOSubject() {
    }

    public MappingPLOSubject(int plo, int subjectId) {
        this.plo = plo;
        this.subjectId = subjectId;
    }

    public int getPlo() {
        return plo;
    }

    public void setPlo(int plo) {
        this.plo = plo;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }
    
}
