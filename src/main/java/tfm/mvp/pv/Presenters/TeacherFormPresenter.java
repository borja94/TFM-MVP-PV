package tfm.mvp.pv.Presenters;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;

import tfm.mvp.pv.Models.Subject;
import tfm.mvp.pv.Models.SubjectDto;
import tfm.mvp.pv.Models.Teacher;
import tfm.mvp.pv.Models.TeacherDto;
import tfm.mvp.pv.Views.TeacherFormView;

public class TeacherFormPresenter {

	private TeacherDto teacherDto;
	private SubjectDto subjectDto;
	private Teacher teacher;
	private List<Subject> subjectsCollection;
	private TeacherFormView teacherFormView;
	private boolean EditMode;
	private int TeacherSelectedId;
	private static final String NewTeacherLabelText = "Nuevo profesor";
	private static final String EditTeacherLabelText = "Editar profesor";
	private TeachersCollectionPresenter teacherCollectionPresenter;

	public TeacherFormPresenter(TeacherFormView teacherFormView) {
		this.teacherFormView = teacherFormView;
		teacherDto = new TeacherDto();
		subjectDto = new SubjectDto();
	}

	public void InsertNewTeacher(String name, String surname, DefaultListModel<String> assignedSubjectModel) {

		Teacher teacher = new Teacher(0, name, surname);

		for (int i = 0; i < assignedSubjectModel.size(); i++) {
			String subjectAux = (String) assignedSubjectModel.getElementAt(i);
			int aux = Integer.parseInt(subjectAux.substring(0, subjectAux.indexOf("#")));
			teacher.getSubjectCollection().add(new Subject(aux));
		}
		teacherDto.Insert(teacher);
		cleanForm();
	}

	public void UpdateTeacher(String name, String surname, DefaultListModel<String> assignedSubjectModel, int id) {

		Teacher teacher = new Teacher(id, name, surname);

		for (int i = 0; i < assignedSubjectModel.size(); i++) {
			String subjectAux = (String) assignedSubjectModel.getElementAt(i);
			int aux = Integer.parseInt(subjectAux.substring(0, subjectAux.indexOf("#")));
			teacher.getSubjectCollection().add(new Subject(aux));
		}

		teacherDto.Update(teacher);
		cleanForm();
	}

	public void NotifyUpdateSubjectList() {

		UpdateSubjectList(null);
	}

	private void UpdateSubjectList(List<String> teacherSubjectCollection) {
		teacherFormView.setUnassignedSubjectModel(new DefaultListModel<>());

		teacherFormView.setAssignedSubjectModel(new DefaultListModel<>());

		for (int i = 0; i < loadSubjects(); i++) {
			String subject = getSubjectByPosition(i);
			if (teacherSubjectCollection != null && teacherSubjectCollection.contains(subject.toString())) {
				teacherFormView.getAssignedSubjectModel().addElement(subject);
			} else {
				teacherFormView.getUnassignedSubjectModel().addElement(subject);
			}
		}
		teacherFormView.getUnassignSubjectCollection().setModel(teacherFormView.getUnassignedSubjectModel());
		teacherFormView.getAssignSubjectCollection().setModel(teacherFormView.getAssignedSubjectModel());
	}

	private void cleanForm() {
		teacherFormView.getTeacherFormLabel().setText(NewTeacherLabelText);
		teacherFormView.getNameInput().setText("");
		teacherFormView.getSurnameInput().setText("");
		UpdateSubjectList(null);
		TeacherSelectedId = 0;
		EditMode = false;
	}

	public void NotifyAddSubject() {
		int[] selectedIndex = teacherFormView.getUnassignSubjectCollection().getSelectedIndices();

		for (int i = selectedIndex.length - 1; i >= 0; i--) {
			int index = selectedIndex[i];
			String item = teacherFormView.getUnassignedSubjectModel().getElementAt(index).toString();
			teacherFormView.getAssignedSubjectModel().addElement(item);
			teacherFormView.getUnassignedSubjectModel().remove(index);
		}
	}

	public void NotifyRemoveSubject() {
		int[] selectedIndex = teacherFormView.getAssignSubjectCollection().getSelectedIndices();

		for (int i = selectedIndex.length - 1; i >= 0; i--) {
			int index = selectedIndex[i];
			String item = teacherFormView.getAssignedSubjectModel().getElementAt(index).toString();
			teacherFormView.getUnassignedSubjectModel().addElement(item);
			teacherFormView.getAssignedSubjectModel().remove(index);
		}
		cleanForm();
	}

	public void NotifySaveForm() {
		String name = teacherFormView.getNameInput().getText();
		String surname = teacherFormView.getSurnameInput().getText();

		if (!name.isEmpty() && !surname.isEmpty()) {
			if (EditMode)
				UpdateTeacher(name, surname, teacherFormView.getAssignedSubjectModel(), TeacherSelectedId);
			else
				InsertNewTeacher(name, surname, teacherFormView.getAssignedSubjectModel());
			teacherCollectionPresenter.NotifyUpdateTeacherTableData();
		}
	}

	public void NotifyNewTeacherMode() {
		cleanForm();
	}

	public void NotifyEditTeacherMode(int id) {
		teacherFormView.getTeacherFormLabel().setText(EditTeacherLabelText);
		TeacherSelectedId = id;
		EditMode = true;
		loadTeacher(id);
		teacherFormView.getNameInput().setText(getTeacherName());
		teacherFormView.getSurnameInput().setText(getTeacherSurName());
		List<String> subject = new ArrayList<>();
		for (int i = 0; i < getTeacherNumSubject(); i++) {
			subject.add(getTeacherSubject(i));
		}
		UpdateSubjectList(subject);
	}

	public void loadTeacher(int id) {
		teacher = teacherDto.Get(id);
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
		subjectsCollection = subjectDto.GetAll();
		return subjectsCollection.size();
	}

	public String getSubjectByPosition(int position) {
		return subjectsCollection.get(position).toString();
	}

	public void setTeachetCollectionPresenter(TeachersCollectionPresenter teacherCollectionPresenter) {
		this.teacherCollectionPresenter = teacherCollectionPresenter;
	}

}
