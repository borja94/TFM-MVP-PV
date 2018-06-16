package tfm.mvp.pv.presenters;

import tfm.mvp.pv.views.TeacherFormView;

public interface ITeacherFormViewPresenter {

	public void updateSubjectList();

	public void addSubject();

	public void removeSubject();

	public void saveForm();
	
	public void setTeacherFormView(TeacherFormView teacherFormView);

}
