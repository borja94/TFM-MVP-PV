/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tfm.mvp.pv.Models;

import java.util.Objects;

/**
 *
 * @author borja
 */
public class Subject {

    public Subject() {
    }

    public Subject(int id) {
        this.id = id;
    }

    public Subject(int id, String name, Integer course) {
        this.id = id;
        this.title = name;
        this.course = course;
    }

    private Integer id;
    private String title;
    private Integer course;

    public Integer getId() {
        return id;
    }

    public void setId(Integer Id) {
        this.id = Id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public Integer getCourse() {
        return course;
    }

    public void setCourse(Integer course) {
        this.course = course;
    }

    @Override
    public boolean equals(Object v) {
        boolean retVal = false;
        if (v instanceof Subject) {
            retVal = Objects.equals(this.id, ((Subject) v).id);
        }
        return retVal;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

	@Override
	public String toString() {
		return this.getId() + "#" + this.getTitle() + "(" + this.getCourse() + ")";
	}
    
    

}
