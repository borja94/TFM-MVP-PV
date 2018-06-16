package tfm.mvp.pv.presenters;

import tfm.mvp.pv.views.SubjectsCollectionView;

public interface ISubjectCollectionViewPresenter {

	public void notifyUpdateSubjectsTableData() ;
	
	public void notifyDeleteButtonActionPerformed();
	
	public void notifyEditMode();
	
	public void newSubjectMode();
	
	public void setSubjectCollectionView(SubjectsCollectionView subjectsCollectionView);

}
