package tfm.mvp.pv.presenters;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import tfm.mvp.pv.models.Subject;
import tfm.mvp.pv.models.SubjectDao;
import tfm.mvp.pv.views.SubjectsCollectionView;

public class SubjectsCollectionPresenter implements ISubjectCollectionViewPresenter {

	private SubjectDao subjectDao;
	private List<Subject> subjectsCollection;
	private static final String[] COLUMN_NAMES = { "ID", "Titulo", "Curso" };
	private SubjectsCollectionView subjectCollectionView;
	private ISubjectFormPresenter iSubjectFormPresenter;

	public SubjectsCollectionPresenter() {
		subjectDao = new SubjectDao();
	}

	public void loadTableData() {
		subjectsCollection = subjectDao.getAll();

	}

	public int getNumColumns() {
		return COLUMN_NAMES.length;
	}

	public String getColumnName(int position) {
		return COLUMN_NAMES[position];
	}

	public int getNumRows() {
		return subjectsCollection.size();
	}

	public String getSubjectAtribute(int column, int row) {

		switch (column) {
		case 0:
			return (subjectsCollection.get(row).getId()).toString();
		case 1:
			return subjectsCollection.get(row).getTitle();
		case 2:
			return subjectsCollection.get(row).getCourse().toString();
		default:
			return null;
		}
	}

	public void removeSubject(int id) {
		subjectDao.remove(id);
	}

	public void notifyUpdateSubjectsTableData() {
		loadTableData();
		String[] columns = new String[getNumColumns()];
		String[][] tableData = new String[getNumRows()][getNumColumns()];
		for (int i = 0; i < columns.length; i++) {
			columns[i] = getColumnName(i);
		}
		for (int i = 0; i < getNumRows(); i++) {
			for (int j = 0; j < getNumColumns(); j++) {
				tableData[i][j] = getSubjectAtribute(j, i);
			}
		}
		subjectCollectionView.setTableModel(new DefaultTableModel(tableData, columns));
	}

	public void notifyDeleteButtonActionPerformed() {
		int selectedRow = subjectCollectionView.getTableSelectedRow();
		if (selectedRow != -1) {
			int dialogResult = JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar al alumno");
			if (dialogResult == JOptionPane.YES_OPTION) {
				removeSubject(subjectCollectionView.getSelectedId());
				notifyUpdateSubjectsTableData();
			}
		}
	}

	public void notifyEditMode() {

		int selectedRow = subjectCollectionView.getTableSelectedRow();
		if (selectedRow != -1)
			iSubjectFormPresenter.editSubjectMode(subjectCollectionView.getSelectedId());
	}

	public void setSubjectCollectionView(SubjectsCollectionView subjectsCollectionView) {
		this.subjectCollectionView = subjectsCollectionView;
	}

	public void setTeacherFormPresenter(ISubjectFormPresenter subjectFormPresenter) {
		this.iSubjectFormPresenter = subjectFormPresenter;
	}

	
	public void newSubjectMode() {
		iSubjectFormPresenter.newSubjectMode();

	}

}
