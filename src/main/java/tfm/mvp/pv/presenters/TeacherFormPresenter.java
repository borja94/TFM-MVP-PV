package tfm.mvp.pv.presenters;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;

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
	private boolean editMode;
	private int teacherSelectedId;
	private static final String NEW_TEACHER_LABEL_TEXT = "Nuevo profesor";
	private static final String EDIT_TEACHER_LABEL_TEXT = "Editar profesor";
	private TeachersCollectionPresenter teacherCollectionPresenter;
	private static final char ID_SUBJECT_SEPARATOR = '#';


	public TeacherFormPresenter(TeacherFormView teacherFormView) {
		this.teacherFormView = teacherFormView;
		teacherDto = new TeacherDto();
		subjectDto = new SubjectDto();
	}

	public void insertNewTeacher(String name, String surname, DefaultListModel<String> assignedSubjectModel) {

		Teacher teacherAux = new Teacher(0, name, surname);

		for (int i = 0; i < assignedSubjectModel.size(); i++) {
			String subjectAux = assignedSubjectModel.getElementAt(i);
			int aux = Integer.parseInt(subjectAux.substring(0, subjectAux.indexOf(ID_SUBJECT_SEPARATOR)));
			teacherAux.getSubjectCollection().add(new Subject(aux));
		}
		teacherDto.insert(teacherAux);
		cleanForm();
	}

	public void updateTeacher(String name, String surname, DefaultListModel<String> assignedSubjectModel, int id) {

		Teacher teacherAux = new Teacher(id, name, surname);

		for (int i = 0; i < assignedSubjectModel.size(); i++) {
			String subjectAux = assignedSubjectModel.getElementAt(i);
			int aux = Integer.parseInt(subjectAux.substring(0, subjectAux.indexOf(ID_SUBJECT_SEPARATOR)));
			teacherAux.getSubjectCollection().add(new Subject(aux));
		}

		teacherDto.update(teacherAux);
		cleanForm();
	}

	public void notifyUpdateSubjectList() {

		updateSubjectList(null);
	}

	private void updateSubjectList(List<String> teacherSubjectCollection) {
		teacherFormView.setUnassignedSubjectModel(new DefaultListModel<>());

		teacherFormView.setAssignedSubjectModel(new DefaultListModel<>());

		for (int i = 0; i < loadSubjects(); i++) {
			String subject = getSubjectByPosition(i);
			if (teacherSubjectCollection != null && teacherSubjectCollection.contains(subject)) {
				teacherFormView.getAssignedSubjectModel().addElement(subject);
			} else {
				teacherFormView.getUnassignedSubjectModel().addElement(subject);
			}
		}
		teacherFormView.getUnassignSubjectCollection().setModel(teacherFormView.getUnassignedSubjectModel());
		teacherFormView.getAssignSubjectCollection().setModel(teacherFormView.getAssignedSubjectModel());
	}

	private void cleanForm() {
		teacherFormView.getTeacherFormLabel().setText(NEW_TEACHER_LABEL_TEXT);
		teacherFormView.getNameInput().setText("");
		teacherFormView.getSurnameInput().setText("");
		updateSubjectList(null);
		teacherSelectedId = 0;
		editMode = false;
	}

	public void notifyAddSubject() {
		int[] selectedIndex = teacherFormView.getUnassignSubjectCollection().getSelectedIndices();

		for (int i = selectedIndex.length - 1; i >= 0; i--) {
			int index = selectedIndex[i];
			String item = teacherFormView.getUnassignedSubjectModel().getElementAt(index);
			teacherFormView.getAssignedSubjectModel().addElement(item);
			teacherFormView.getUnassignedSubjectModel().remove(index);
		}
	}

	public void notifyRemoveSubject() {
		int[] selectedIndex = teacherFormView.getAssignSubjectCollection().getSelectedIndices();

		for (int i = selectedIndex.length - 1; i >= 0; i--) {
			int index = selectedIndex[i];
			String item = teacherFormView.getAssignedSubjectModel().getElementAt(index);
			teacherFormView.getUnassignedSubjectModel().addElement(item);
			teacherFormView.getAssignedSubjectModel().remove(index);
		}
		cleanForm();
	}

	public void notifySaveForm() {
		String name = teacherFormView.getNameInput().getText();
		String surname = teacherFormView.getSurnameInput().getText();

		if (!name.isEmpty() && !surname.isEmpty()) {
			if (editMode)
				updateTeacher(name, surname, teacherFormView.getAssignedSubjectModel(), teacherSelectedId);
			else
				insertNewTeacher(name, surname, teacherFormView.getAssignedSubjectModel());
			teacherCollectionPresenter.notifyUpdateTeacherTableData();
		}
	}

	public void notifyNewTeacherMode() {
		cleanForm();
	}

	public void notifyEditTeacherMode(int id) {
		teacherFormView.getTeacherFormLabel().setText(EDIT_TEACHER_LABEL_TEXT);
		teacherSelectedId = id;
		editMode = true;
		loadTeacher(id);
		teacherFormView.getNameInput().setText(getTeacherName());
		teacherFormView.getSurnameInput().setText(getTeacherSurName());
		List<String> subject = new ArrayList<>();
		for (int i = 0; i < getTeacherNumSubject(); i++) {
			subject.add(getTeacherSubject(i));
		}
		updateSubjectList(subject);
	}

	public void loadTeacher(int id) {
		teacher = teacherDto.get(id);
	}

	public String getTeacherName() {
		return teacher.getName();
	}

	public String getTeacherSurName() {
		return teacher.getSurname();
	}

	public int getTeacherId() {
		return teacher.getId();
	}

	public int getTeacherNumSubject() {
		return teacher.getSubjectCollection().size();
	}

	public String getTeacherSubject(int id) {
		return teacher.getSubjectCollection().get(id).toString();
	}

	public int loadSubjects() {
		subjectsCollection = subjectDto.getAll();
		return subjectsCollection.size();
	}

	public String getSubjectByPosition(int position) {
		return subjectsCollection.get(position).toString();
	}

	public void setTeachetCollectionPresenter(TeachersCollectionPresenter teacherCollectionPresenter) {
		this.teacherCollectionPresenter = teacherCollectionPresenter;
	}

}
