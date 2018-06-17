package tfm.mvp.pv.presenters;

import tfm.mvp.pv.views.TeacherCollectionView;

public interface ITeacherCollectionViewPresenter {

	public void updateTeacherTableData();
	
	public void deleteTeacher();
	
	public void editTeacher();
	
	public void newTeacher();
	
	public void setTeacherCollectionView(TeacherCollectionView teacherCollectionView);

}
