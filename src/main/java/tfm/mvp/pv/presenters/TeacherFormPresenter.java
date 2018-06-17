package tfm.mvp.pv.presenters;

import java.util.ArrayList;
import java.util.List;

import tfm.mvp.pv.models.Subject;
import tfm.mvp.pv.models.SubjectDao;
import tfm.mvp.pv.models.Teacher;
import tfm.mvp.pv.models.TeacherDao;
import tfm.mvp.pv.views.TeacherFormView;

public class TeacherFormPresenter implements ITeacherFormPresenter , ITeacherFormViewPresenter {

	private TeacherDao teacherDao;
	private SubjectDao subjectDao;
	private TeacherFormView teacherFormView;
	private int teacherSelectedId;
	private static final String NEW_TEACHER_LABEL_TEXT = "Nuevo profesor";
	private static final String EDIT_TEACHER_LABEL_TEXT = "Editar profesor";
	private TeachersCollectionPresenter teacherCollectionPresenter;
	private static final char ID_SUBJECT_SEPARATOR = '#';

	private static final int NO_TEACHER_SELECTED_ID = -1;

	public TeacherFormPresenter() {
		teacherSelectedId = NO_TEACHER_SELECTED_ID;
		teacherDao = new TeacherDao();
		subjectDao = new SubjectDao();
	}

	public void updateSubjectList() {

		updateSubjectList(null);
	}

	public void addSubject() {
		int[] selectedIndex = teacherFormView.getUnassignSubjectSelectedIndices();

		for (int i = selectedIndex.length - 1; i >= 0; i--) {
			int index = selectedIndex[i];
			String item = teacherFormView.getUnassignedSubjectElementAt(index);
			teacherFormView.addAssignedSubjectElement(item);
			teacherFormView.removeUnassignedSubjectElement(index);
		}
	}

	public void removeSubject() {
		int[] selectedIndex = teacherFormView.getAssignedSubjectIndices();

		for (int i = selectedIndex.length - 1; i >= 0; i--) {
			int index = selectedIndex[i];
			String item = teacherFormView.getAssignedSubjectElementAt(index);
			teacherFormView.addUnassignedSubjectElement(item);
			teacherFormView.removeAssignedSubjectElement(index);
		}
		cleanForm();
	}

	public void saveForm() {
		String name = teacherFormView.getNameInputValue();
		String surname = teacherFormView.getSurnameInputValue();

		if (!name.isEmpty() && !surname.isEmpty()) {
			if (teacherSelectedId != NO_TEACHER_SELECTED_ID)
				updateTeacher(name, surname, teacherSelectedId);
			else
				insertNewTeacher(name, surname);
			teacherCollectionPresenter.updateTeacherTableData();
		}
	}

	public void newTeacherMode() {
		cleanForm();
	}

	public void editTeacherMode(int id) {
		teacherFormView.setTeacherFormLabelText(EDIT_TEACHER_LABEL_TEXT);
		teacherSelectedId = id;
		Teacher teacher = teacherDao.get(id);
		teacherFormView.setNameInputValue(teacher.getName());
		teacherFormView.setSurnameInputValue(teacher.getSurname());
		List<String> subject = new ArrayList<>();
		for (int i = 0; i < teacher.getSubjectCollection().size(); i++) {
			subject.add(teacher.getSubjectCollection().get(i).toString());
		}
		updateSubjectList(subject);
	}

	public void setTeachetCollectionPresenter(TeachersCollectionPresenter teacherCollectionPresenter) {
		this.teacherCollectionPresenter = teacherCollectionPresenter;
	}
	
	public void setTeacherFormView(TeacherFormView teacherFormView) {
		this.teacherFormView = teacherFormView;
	}

	private void insertNewTeacher(String name, String surname) {

		teacherDao.insert(generateTeacher(0, name, surname));
		cleanForm();
	}

	private void updateTeacher(String name, String surname, int id) {

		teacherDao.update(generateTeacher(id, name, surname));
		cleanForm();
	}

	private Teacher generateTeacher(int id, String name, String surname) {

		Teacher teacherAux = new Teacher(id, name, surname);

		for (int i = 0; i < teacherFormView.getAssignedSubjectCollectionSize(); i++) {
			String subjectAux = teacherFormView.getAssignedSubjectElementAt(i);
			int aux = Integer.parseInt(subjectAux.substring(0, subjectAux.indexOf(ID_SUBJECT_SEPARATOR)));
			teacherAux.getSubjectCollection().add(new Subject(aux));
		}

		return teacherAux;

	}

	private void updateSubjectList(List<String> teacherSubjectCollection) {
		teacherFormView.clearSubjectModels();

		List<Subject> subjectsCollection = subjectDao.getAll();

		for (int i = 0; i < subjectsCollection.size(); i++) {
			String subject = subjectsCollection.get(i).toString();
			if (teacherSubjectCollection != null && teacherSubjectCollection.contains(subject)) {
				teacherFormView.addAssignedSubjectElement(subject);
			} else {
				teacherFormView.addUnassignedSubjectElement(subject);
			}
		}
	}

	private void cleanForm() {
		teacherFormView.setTeacherFormLabelText(NEW_TEACHER_LABEL_TEXT);
		teacherFormView.setNameInputValue("");
		teacherFormView.setSurnameInputValue("");
		updateSubjectList(null);
		teacherSelectedId = NO_TEACHER_SELECTED_ID;
	}

}
