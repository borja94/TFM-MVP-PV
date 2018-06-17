package tfm.mvp.pv.presenters;

import tfm.mvp.pv.models.Subject;
import tfm.mvp.pv.models.SubjectDao;
import tfm.mvp.pv.views.SubjectFormView;

public class SubjectFormPresenter implements ISubjectFormPresenter, ISubjectFormViewPresenter {

	private static final String NEW_SUBJECT_LABEL_TEXT = "Nueva asignatura";
	private static final String EDIT_SUBJECT_LABEL_TEXT = "Editar asignatura";
	private int subjectSelectedId;
	private static final int NO_SUBJECT_SELECTED = -1;

	private SubjectDao subjectDao;

	private SubjectFormView subjectFormView;
	private SubjectsCollectionPresenter subjectsCollectionPresenter;

	public SubjectFormPresenter() {
		subjectSelectedId = NO_SUBJECT_SELECTED;

		subjectDao = new SubjectDao();
	}

	public void newSubjectMode() {
		subjectFormView.setSubjectFormLabelValue(NEW_SUBJECT_LABEL_TEXT);
		subjectFormView.setTitleInputValue("");
		subjectFormView.setCourseInputValue("");
		subjectSelectedId = NO_SUBJECT_SELECTED;
	}

	public void editSubjectMode(int id) {
		subjectFormView.setSubjectFormLabelValue(EDIT_SUBJECT_LABEL_TEXT);
		subjectSelectedId = id;
		
		Subject subject = subjectDao.get(id);
		subjectFormView.setTitleInputValue(subject.getTitle());
		subjectFormView.setCourseInputValue(subject.getCourse().toString());
	}

	public void insertNewStudent(String title, int course) {

		Subject subjectAux = new Subject(0, title, course);
		subjectDao.insert(subjectAux);

	}

	public void updateStudent(String title, int course, int id) {

		Subject subjectAux = new Subject(id, title, course);
		subjectDao.update(subjectAux);

	}

	public void saveForm() {

		String title = subjectFormView.getTitleInputValue();
		String course = subjectFormView.getCourseInputValue();

		if (!title.isEmpty() && !course.isEmpty()) {
			if (subjectSelectedId != NO_SUBJECT_SELECTED) {
				updateStudent(title, Integer.parseInt(course), subjectSelectedId);
			} else {
				insertNewStudent(title, Integer.parseInt(course));
			}

			subjectsCollectionPresenter.notifyUpdateSubjectsTableData();
		}
	}

	public void setSubjectsCollectionPresenter(SubjectsCollectionPresenter subjectsCollectionPresenter) {
		this.subjectsCollectionPresenter = subjectsCollectionPresenter;
	}

	public void setSubjectFormView(SubjectFormView subjectFormView) {
		this.subjectFormView = subjectFormView;
	}

}
