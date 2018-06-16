package tfm.mvp.pv.presenters;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import tfm.mvp.pv.models.Subject;
import tfm.mvp.pv.models.Teacher;
import tfm.mvp.pv.models.TeacherDto;
import tfm.mvp.pv.views.TeacherCollectionView;

public class TeachersCollectionPresenter implements ITeacherCollectionViewPresenter {

	private TeacherDto teacherDto;
	private List<Teacher> teacherCollection;
	private static final String[] COLUMN_NAMES = { "ID", "Nombre", "Apellidos", "Asignaturas" };
	private TeacherCollectionView teacherCollectionView;
	private ITeacherFormPresenter iTeacherFormPresenter;

	public TeachersCollectionPresenter() {
		teacherDto = new TeacherDto();
	}

	public void loadTableData() {
		teacherCollection = teacherDto.getAll();
	}

	public int getNumColumns() {
		return COLUMN_NAMES.length;
	}

	public String getColumnName(int position) {
		return COLUMN_NAMES[position];
	}

	public int getNumRows() {
		return teacherCollection.size();
	}

	public String getTeacherAtribute(int column, int row) {

		switch (column) {
		case 0:
			return ((Integer) teacherCollection.get(row).getId()).toString();
		case 1:
			return teacherCollection.get(row).getName();
		case 2:
			return teacherCollection.get(row).getSurname();
		case 3:
			StringBuilder result = new StringBuilder();
			for (Subject subject : teacherCollection.get(row).getSubjectCollection()) {
				if (result.toString() == "")
					result.append(subject.getTitle());
				else
					result.append("," + subject.getTitle());
			}
			return result.toString();
		default:
			return null;
		}
	}

	public void removeTeacher(int id) {
		teacherDto.remove(id);
	}

	public void updateTeacherTableData() {

		loadTableData();
		String[] columns = new String[getNumColumns()];
		String[][] tableData = new String[getNumRows()][getNumColumns()];
		for (int i = 0; i < columns.length; i++) {
			columns[i] = getColumnName(i);
		}
		for (int i = 0; i < getNumRows(); i++) {
			for (int j = 0; j < getNumColumns(); j++) {
				tableData[i][j] = getTeacherAtribute(j, i);
			}
		}
		teacherCollectionView.setTableModel(new DefaultTableModel(tableData, columns));
	}

	public void setTeacherFormPresenter(ITeacherFormPresenter teacherFormPresenter) {
		iTeacherFormPresenter = teacherFormPresenter;
	}

	public void deleteTeacher() {
		int selectedRow = teacherCollectionView.getTableSelectedRow();
		if (selectedRow != -1) {
			int dialogResult = JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar al profesor");
			if (dialogResult == JOptionPane.YES_OPTION) {
				removeTeacher(teacherCollectionView.getSelectedId());
				updateTeacherTableData();
			}
		}
	}

	public void editTeacher() {
		int selectedRow = teacherCollectionView.getTableSelectedRow();
		if (selectedRow != -1)
			iTeacherFormPresenter.editTeacherMode(teacherCollectionView.getSelectedId());
	}

	public void newTeacher() {
		iTeacherFormPresenter.newTeacherMode();
	}
	
	public void setTeacherCollectionView(TeacherCollectionView teacherCollectionView) {
		this.teacherCollectionView = teacherCollectionView;
	}

}
