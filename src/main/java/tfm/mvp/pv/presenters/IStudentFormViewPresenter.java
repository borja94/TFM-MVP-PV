package tfm.mvp.pv.presenters;

import java.util.List;

import tfm.mvp.pv.views.StudentFormView;

public interface IStudentFormViewPresenter {


	public void updateSubjectList(List<String> studentSubjectCollection) ;
	
	public void saveForm() ;
	
	public void setStudentFormView(StudentFormView studentFormView);

}
