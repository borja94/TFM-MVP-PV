package tfm.mvp.pv.presenters;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import tfm.mvp.pv.models.Student;
import tfm.mvp.pv.models.StudentDto;
import tfm.mvp.pv.models.Subject;
import tfm.mvp.pv.views.StudentsCollectionView;

public class StudentsCollectionPresenter {

	private StudentDto studentDto;
	private List<Student> studentCollection;
	private static final String[] COLUMN_NAMES = { "ID", "Nombre", "Apellidos", "Asignaturas" };
	private StudentsCollectionView studentCollectionView;
	private StudentFormPresenter studentFormPresenter;

	public StudentsCollectionPresenter(StudentsCollectionView studentsCollectionView,
			StudentFormPresenter studentFormPresenter) {
		studentDto = new StudentDto();
		this.studentCollectionView = studentsCollectionView;
		this.studentFormPresenter = studentFormPresenter;
	}

	public void loadTableData() {
		studentCollection = studentDto.getAll();
	}

	public int getNumColumns() {
		return COLUMN_NAMES.length;
	}

	public String getColumnName(int position) {
		return COLUMN_NAMES[position];
	}

	public int getNumRows() {
		return studentCollection.size();
	}

	public String getStudentAtribute(int column, int row) {

		switch (column) {
		case 0:
			return ((Integer) studentCollection.get(row).getId()).toString();
		case 1:
			return studentCollection.get(row).getName();
		case 2:
			return studentCollection.get(row).getSurname();
		case 3:
			StringBuilder result = new StringBuilder();
			for (Subject subject : studentCollection.get(row).getSubjectCollection()) {
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

	public void notifyUpdateStudentTableData() {
		loadTableData();
		String[] columns = new String[getNumColumns()];
		String[][] tableData = new String[getNumRows()][getNumColumns()];
		for (int i = 0; i < columns.length; i++) {
			columns[i] = getColumnName(i);
		}
		for (int i = 0; i < getNumRows(); i++) {
			for (int j = 0; j < getNumColumns(); j++) {
				tableData[i][j] = getStudentAtribute(j, i);
			}
		}
		studentCollectionView.setTableModel(new DefaultTableModel(tableData, columns));
	}

	public void notifyEditStudent() {

		int selectedRow = studentCollectionView.getTableSelectedRow();
		if (selectedRow != -1) {
			studentFormPresenter.notifyEditTeacherMode(studentCollectionView.getSelectedId());
		}
	}

	public void notifyDeleteStudent() {
		int selectedRow = studentCollectionView.getTableSelectedRow();
		if (selectedRow != -1) {
			int dialogResult = JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar al alumno");
			if (dialogResult == JOptionPane.YES_OPTION) {
				removeStudent(studentCollectionView.getSelectedId());
				notifyUpdateStudentTableData();
			}
		}
	}

	public void notifyNewStudent() {
		studentFormPresenter.notifyNewTeacherMode();
	}

	public void removeStudent(int id) {
		studentDto.remove(id);
	}

}
