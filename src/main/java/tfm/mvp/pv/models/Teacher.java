package tfm.mvp.pv.models;

import java.util.ArrayList;
import java.util.List;


public class Teacher {

    public Teacher() {
        subjectCollection = new ArrayList<>();
    }

    public Teacher(int id, String name, String surname) {
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
    
    public List<String> getSubjectCollectionString(){
    	List<String> result = new ArrayList<>();
    	
    	for(Subject item : subjectCollection) {
    		result.add(item.toString());
    	}
    	
    	return result;
    }

}
