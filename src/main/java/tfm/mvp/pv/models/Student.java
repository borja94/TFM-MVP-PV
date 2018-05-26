/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tfm.mvp.pv.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author borja
 */
public class Student {

    public Student() {
        subjectCollection = new ArrayList<>();
    }

    public Student(int id, String name, String surname) {
        subjectCollection = new ArrayList<>();
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    private int id;

    private String name;

    private String surname;
    private List<Subject> subjectCollection;

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Subject> getSubjectCollection() {
        return subjectCollection;
    }

    public void setSubjectCollection(List<Subject> subjectCollection) {
        this.subjectCollection = subjectCollection;
    }
}
