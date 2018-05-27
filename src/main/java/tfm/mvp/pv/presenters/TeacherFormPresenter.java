package tfm.mvp.pv.presenters;

import java.util.ArrayList;
import java.util.List;

import tfm.mvp.pv.models.Subject;
import tfm.mvp.pv.models.SubjectDto;
import tfm.mvp.pv.models.Teacher;
import tfm.mvp.pv.models.TeacherDto;
import tfm.mvp.pv.views.TeacherFormView;

public class TeacherFormPresenter {

	private TeacherDto teacherDto;
	private SubjectDto subjectDto;
	private Teacher teacher;
	private List<Subject> subjectsCollection;
	private TeacherFormView teacherFormView;
	private int teacherSelectedId;
	private static final String NEW_TEACHER_LABEL_TEXT = "Nuevo profesor";
	private static final String EDIT_TEACHER_LABEL_TEXT = "Editar profesor";
	private TeachersCollectionPresenter teacherCollectionPresenter;
	private static final char ID_SUBJECT_SEPARATOR = '#';

	private static final int NO_TEACHER_SELECTED_ID = -1;

	public TeacherFormPresenter(TeacherFormView teacherFormView) {
		teacherSelectedId = NO_TEACHER_SELECTED_ID;
		this.teacherFormView = teacherFormView;
		teacherDto = new TeacherDto();
		subjectDto = new SubjectDto();
	}

	public void notifyUpdateSubjectList() {

		updateSubjectList(null);
	}

	public void notifyAddSubject() {
		int[] selectedIndex = teacherFormView.getUnassignSubjectSelectedIndices();

		for (int i = selectedIndex.length - 1; i >= 0; i--) {
			int index = selectedIndex[i];
			String item = teacherFormView.getUnassignedSubjectElementAt(index);
			teacherFormView.addAssignedSubjectElement(item);
			teacherFormView.removeUnassignedSubjectElement(index);
		}
	}

	public void notifyRemoveSubject() {
		int[] selectedIndex = teacherFormView.getAssignedSubjectIndices();

		for (int i = selectedIndex.length - 1; i >= 0; i--) {
			int index = selectedIndex[i];
			String item = teacherFormView.getAssignedSubjectElementAt(index);
			teacherFormView.addUnassignedSubjectElement(item);
			teacherFormView.removeAssignedSubjectElement(index);
		}
		cleanForm();
	}

	public void notifySaveForm() {
		String name = teacherFormView.getNameInputValue();
		String surname = teacherFormView.getSurnameInputValue();

		if (!name.isEmpty() && !surname.isEmpty()) {
			if (teacherSelectedId != NO_TEACHER_SELECTED_ID)
				updateTeacher(name, surname, teacherSelectedId);
			else
				insertNewTeacher(name, surname);
			teacherCollectionPresenter.notifyUpdateTeacherTableData();
		}
	}

	public void notifyNewTeacherMode() {
		cleanForm();
	}

	public void notifyEditTeacherMode(int id) {
		teacherFormView.setTeacherFormLabelText(EDIT_TEACHER_LABEL_TEXT);
		teacherSelectedId = id;
		teacher = teacherDto.get(id);
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

	private void insertNewTeacher(String name, String surname) {

		teacherDto.insert(generateTeacher(0, name, surname));
		cleanForm();
	}

	private void updateTeacher(String name, String surname, int id) {

		teacherDto.update(generateTeacher(id, name, surname));
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

		subjectsCollection = subjectDto.getAll();

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
