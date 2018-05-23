package tfm.mvp.pv.Presenters;

import java.util.List;

import tfm.mvp.pv.Models.Subject;
import tfm.mvp.pv.Models.SubjectDto;

public class SubjectsCollectionPresenter {

	private SubjectDto subjectDto;
	private List<Subject> subjectsCollection;
	private final String columnNames[] = { "ID", "Titulo", "Curso" };

	public SubjectsCollectionPresenter() {
		subjectDto = new SubjectDto();
	}

	public void loadTableData() {
		subjectsCollection = subjectDto.GetAll();

	}

	public int getNumColumns() {
		return columnNames.length;
	}

	public String getColumnName(int position) {
		return columnNames[position];
	}

	public int getNumRows() {
		return subjectsCollection.size();
	}

	public String getSubjectAtribute(int column, int row) {

		switch (column) {
		case 0:
			return ((Integer) subjectsCollection.get(row).getId()).toString();
		case 1:
			return subjectsCollection.get(row).getTitle();
		case 2:
			return subjectsCollection.get(row).getCourse().toString();
		default:
			return null;
		}
	}

	public void RemoveSubject(int id) {
		subjectDto.Remove(id);
	}

}
