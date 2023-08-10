/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author HaPi
 */
public class Setting {

    private int id;
    private String type;
    private String title;
    private String value;
    private String details;
    private int status;
    private int displayOrder;

    public Setting() {
    }

    public Setting(int id, String type, String title, String value, String details, int status) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.value = value;
        this.details = details;
        this.status = status;
    }

    public Setting(int id, String type, String title, String value, String details, int status, int displayOrder) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.value = value;
        this.details = details;
        this.status = status;
        this.displayOrder = displayOrder;
    }
    

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Setting{" + "id=" + id + ", type=" + type + ", title=" + title + ", value=" + value + ", details=" + details + ", status=" + status + '}';
    }

}
