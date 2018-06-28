package tfm.mvp.pv.presenters;

import tfm.mvp.pv.views.StudentsCollectionView;

public interface IStudentCollectionViewPresenter {

	public void notifyNewStudent();

	public void notifyDeleteStudent();

	public void notifyEditStudent();

	public void updateStudentTableData();

	public void setStudentCollectionView(StudentsCollectionView studentsCollectionView);

}
