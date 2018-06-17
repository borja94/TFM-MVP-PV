package tfm.mvp.pv.presenters;

import java.util.ArrayList;
import java.util.List;

import tfm.mvp.pv.models.Student;
import tfm.mvp.pv.models.StudentDao;
import tfm.mvp.pv.models.Subject;
import tfm.mvp.pv.models.SubjectDao;
import tfm.mvp.pv.views.StudentFormView;

public class StudentFormPresenter implements IStudentFormPresenter, IStudentFormViewPresenter {

	private StudentDao studentDao;
	private SubjectDao subjectDao;
	private Student student;
	private List<Subject> subjectsCollection;
	private static final char ID_SUBJECT_SEPARATOR = '#';
	private int studentSelectedId;
	private static final String NEW_STUDENT_LABEL_TEXT = "Nuevo alumno";
	private static final String EDIT_STUDENT_LABEL_TEXT = "Editar alumno";
	private static final int NO_STUDENT_SELECTED = -1;
	private StudentFormView studentFormView;
	private StudentsCollectionPresenter studentCollectionPresenter;

	public StudentFormPresenter() {
		studentSelectedId = NO_STUDENT_SELECTED;
		studentDao = new StudentDao();
		subjectDao = new SubjectDao();

	}

	public void updateSubjectList(List<String> studentSubjectCollection) {
		studentFormView.cleanSubjecModels();

		for (int i = 0; i < loadSubjects(); i++) {
			String subject = getSubjectByPosition(i);
			if (studentSubjectCollection != null && studentSubjectCollection.contains(subject)) {
				studentFormView.addAssignedSubjectElement(subject);
			} else {
				studentFormView.addUnassignedSubjectElement(subject);
			}
		}
	}

	public void insertNewStudent(String name, String surname) {

		studentDao.insert(generateStudent(0, name, surname));
		cleanForm();
	}

	public void updateStudent(String name, String surname, int id) {

		studentDao.update(generateStudent(id, name, surname));
		cleanForm();
	}

	private Student generateStudent(int id , String name, String surname) {
		
		Student studentAux = new Student(id, name, surname);

		for (int i = 0; i < studentFormView.getAssignedSubjectCollectionSize(); i++) {
			String subjectAux = studentFormView.getAssignedSubjectElementAt(i);
			int aux = Integer.parseInt(subjectAux.substring(0, subjectAux.indexOf(ID_SUBJECT_SEPARATOR)));
			studentAux.getSubjectCollection().add(new Subject(aux));
		}
		
		return studentAux;
	}

	public void notifyNewMode() {
		cleanForm();
	}
	
	private void cleanForm() {
		studentFormView.setLabelFormText(NEW_STUDENT_LABEL_TEXT);
		studentFormView.setNameInputValue("");
		studentFormView.setSurnameInputValue("");
		updateSubjectList(null);
		studentSelectedId = NO_STUDENT_SELECTED;
	}

	public void notifyEditMode(int id) {
		studentFormView.setLabelFormText(EDIT_STUDENT_LABEL_TEXT);
		studentSelectedId = id;
		loadStudent(id);
		studentFormView.setNameInputValue(getStudentName());
		studentFormView.setSurnameInputValue(getStudentSurName());
		List<String> subject = new ArrayList<>();
		for (int i = 0; i < getStudentNumSubject(); i++) {
			subject.add(getStudentSubject(i));
		}
		updateSubjectList(subject);
	}

	public void saveForm() {

		String name = studentFormView.getNameInputValue();
		String surname = studentFormView.getSurnameInputValue();

		if (!name.isEmpty() && !surname.isEmpty()) {
			if (studentSelectedId != NO_STUDENT_SELECTED)
				updateStudent(name, surname, studentSelectedId);
			else
				insertNewStudent(name, surname);
			
			studentCollectionPresenter.notifyUpdateStudentTableData();
		}
	}
	
	

	public void setStudentCollectionPresenter(StudentsCollectionPresenter studentCollectionPresenter) {
		this.studentCollectionPresenter = studentCollectionPresenter;
	}

	public void loadStudent(int id) {
		student = studentDao.get(id);
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
		subjectsCollection = subjectDao.getAll();
		return subjectsCollection.size();
	}

	public String getSubjectByPosition(int position) {
		return subjectsCollection.get(position).toString();
	}

	@Override
	public void setStudentFormView(StudentFormView studentFormView) {
		this.studentFormView = studentFormView;
		
	}
}
