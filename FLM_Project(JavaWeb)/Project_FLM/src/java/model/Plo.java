/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author DungNT
 */
public class Plo {
     private int id;
    private String name, description;
    private int curriculum_id;
    private boolean active;

    public Plo() {
    }

    public Plo(int id, String name, String description, int curriculum_id, boolean active) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.curriculum_id = curriculum_id;
        this.active = active;
    }

    public Plo(int id, String name, String description, int curriculum_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.curriculum_id = curriculum_id;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getCurriculum_id() {
        return curriculum_id;
    }

    public void setCurriculum_id(int curriculum_id) {
        this.curriculum_id = curriculum_id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    
}
