package tfm.mvp.pv.presenters;

import java.util.List;

import javax.swing.DefaultListModel;

import tfm.mvp.pv.models.Student;
import tfm.mvp.pv.models.StudentDto;
import tfm.mvp.pv.models.Subject;
import tfm.mvp.pv.models.SubjectDto;

public class StudentFormPresenter {

	private StudentDto studentDto;
	private SubjectDto subjectDto;
	private Student student;
	private List<Subject> subjectsCollection;

	public StudentFormPresenter() {
		studentDto = new StudentDto();
		subjectDto = new SubjectDto();
	}

	public void InsertNewStudent(String name, String surname, DefaultListModel<String> assignedSubjectModel) {

		Student student = new Student(0, name, surname);

		for (int i = 0; i < assignedSubjectModel.size(); i++) {
			String subjectAux = (String) assignedSubjectModel.getElementAt(i);
			int aux = Integer.parseInt(subjectAux.substring(0, subjectAux.indexOf("#")));
			student.getSubjectCollection().add(new Subject(aux));
		}
		studentDto.Insert(student);

	}

	public void UpdateStudent(String name, String surname, DefaultListModel<String> assignedSubjectModel, int id) {

		Student student = new Student(id, name, surname);

		for (int i = 0; i < assignedSubjectModel.size(); i++) {
			String subjectAux = (String) assignedSubjectModel.getElementAt(i);
			int aux = Integer.parseInt(subjectAux.substring(0, subjectAux.indexOf("#")));
			student.getSubjectCollection().add(new Subject(aux));
		}

		studentDto.Update(student);

	}

	public void loadStudent(int id) {
		student = studentDto.Get(id);
	}

	public String getStudentName() {
		return student.getName();
	}

	public String getStudentSurName() {
		return student.getSurname();
	}

	public int getStudentId() {
		return student.getId();
	}

	public int getStudentNumSubject() {
		return student.getSubjectCollection().size();
	}

	public String getStudentSubject(int id) {
		return student.getSubjectCollection().get(id).toString();
	}

	public int loadSubjects() {
		subjectsCollection = subjectDto.GetAll();
		return subjectsCollection.size();
	}

	public String getSubjectByPosition(int position) {
		return subjectsCollection.get(position).toString();
	}
}
