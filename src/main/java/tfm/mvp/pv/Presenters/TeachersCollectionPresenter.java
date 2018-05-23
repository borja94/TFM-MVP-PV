package tfm.mvp.pv.Presenters;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import tfm.mvp.pv.Models.Subject;
import tfm.mvp.pv.Models.Teacher;
import tfm.mvp.pv.Models.TeacherDto;
import tfm.mvp.pv.Views.TeacherCollectionView;

public class TeachersCollectionPresenter {

	private TeacherDto teacherDto;
	private List<Teacher> teacherCollection;
	private final String columnNames[] = { "ID", "Nombre", "Apellidos", "Asignaturas" };
	private TeacherCollectionView teacherCollectionView;
	private TeacherFormPresenter teacherFormPresenter;

	public TeachersCollectionPresenter(TeacherCollectionView teacherCollectionView,
			TeacherFormPresenter teacherFormPresenter) {
		this.teacherCollectionView = teacherCollectionView;
		teacherDto = new TeacherDto();
		this.teacherFormPresenter = teacherFormPresenter;
	}

	public void loadTableData() {
		teacherCollection = teacherDto.GetAll();
	}

	public int getNumColumns() {
		return columnNames.length;
	}

	public String getColumnName(int position) {
		return columnNames[position];
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
			String result = "";
			for (Subject subject : teacherCollection.get(row).getSubjectCollection()) {
				if (result == "")
					result = subject.getTitle();
				else
					result += "," + subject.getTitle();
			}
			return result;
		default:
			return null;
		}
	}

	public void RemoveTeacher(int id) {
		teacherDto.Remove(id);
	}

	public void NotifyUpdateTeacherTableData() {

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
		teacherCollectionView.setTeachersTableModel(new DefaultTableModel(tableData, columns));
		teacherCollectionView.getTeachersTable().setModel(teacherCollectionView.getTeachersTableModel());
	}

	public void NotifyDeleteTeacher() {
		int selectedRow = teacherCollectionView.getTeachersTable().getSelectedRow();
		if (selectedRow != -1) {
			int dialogResult = JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar al profesor");
			if (dialogResult == JOptionPane.YES_OPTION) {
				RemoveTeacher((Integer.parseInt(
						teacherCollectionView.getTeachersTableModel().getValueAt(selectedRow, 0).toString())));
				NotifyUpdateTeacherTableData();
			}
		}
	}

	public void NotifyEditTeacher() {
		int selectedRow = teacherCollectionView.getTeachersTable().getSelectedRow();
		if (selectedRow != -1)
			teacherFormPresenter.NotifyEditTeacherMode(Integer
					.parseInt(teacherCollectionView.getTeachersTableModel().getValueAt(selectedRow, 0).toString()));
	}

	public void NotifyNewTeacher() {
		teacherFormPresenter.NotifyNewTeacherMode();
	}

}
