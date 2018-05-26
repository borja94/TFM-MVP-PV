package tfm.mvp.pv.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import tfm.mvp.pv.presenters.StudentsCollectionPresenter;

public class StudentsCollectionView extends JPanel {

	private TableModel studentsTableModel;

	private JButton deleteStudentButton;
	private JButton editStudentButton;
	private JButton newStudentButton;
	private JTable studentsTable;
	private JScrollPane tableScrollPane;
	private StudentFormView studentFormView;
	private StudentsCollectionPresenter studentCollectionPresenter;

	public StudentsCollectionView(StudentFormView studentFormView) {
		this.studentFormView = studentFormView;
		studentCollectionPresenter = new StudentsCollectionPresenter();
		initComponents();
	}

	private void initComponents() {

		tableScrollPane = new JScrollPane();
		studentsTable = new JTable();
		deleteStudentButton = new JButton();
		editStudentButton = new JButton();
		newStudentButton = new JButton();

		updateStudentTableData();

		tableScrollPane.setViewportView(studentsTable);

		deleteStudentButton.setText("Borrar");
		deleteStudentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				deleteStudentButtonActionPerformed();
			}
		});

		editStudentButton.setText("Modo edición");
		editStudentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				editStudentButtonActionPerformed();
			}
		});

		newStudentButton.setText("Nuevo alumno");
		newStudentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				newStudentButtonActionPerformed();
			}
		});

		initComponentsPosition();
	}

	private void initComponentsPosition() {

		GroupLayout jPanel2Layout = new GroupLayout(this);
		this.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addGroup(jPanel2Layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(tableScrollPane, GroupLayout.PREFERRED_SIZE, 384, GroupLayout.PREFERRED_SIZE)
						.addGroup(jPanel2Layout.createSequentialGroup().addComponent(deleteStudentButton)
								.addGap(18, 18, 18).addComponent(editStudentButton).addGap(18, 18, 18)
								.addComponent(newStudentButton)))
						.addContainerGap(17, Short.MAX_VALUE)));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup().addContainerGap()
						.addComponent(tableScrollPane, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(deleteStudentButton).addComponent(editStudentButton)
								.addComponent(newStudentButton))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

	}

	public void updateStudentTableData() {
		studentCollectionPresenter.loadTableData();
		String[] columns = new String[studentCollectionPresenter.getNumColumns()];
		String[][] tableData = new String[studentCollectionPresenter.getNumRows()][studentCollectionPresenter
				.getNumColumns()];
		for (int i = 0; i < columns.length; i++) {
			columns[i] = studentCollectionPresenter.getColumnName(i);
		}
		for (int i = 0; i < studentCollectionPresenter.getNumRows(); i++) {
			for (int j = 0; j < studentCollectionPresenter.getNumColumns(); j++) {
				tableData[i][j] = studentCollectionPresenter.getStudentAtribute(j, i);
			}
		}
		studentsTableModel = new DefaultTableModel(tableData, columns);
		studentsTable.setModel(studentsTableModel);
	}

	private void deleteStudentButtonActionPerformed() {
		int selectedRow = studentsTable.getSelectedRow();
		if (selectedRow != -1) {
			int dialogResult = JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar al alumno");
			if (dialogResult == JOptionPane.YES_OPTION) {
				studentCollectionPresenter
						.removeStudent((Integer.parseInt(studentsTableModel.getValueAt(selectedRow, 0).toString())));
				updateStudentTableData();
			}
		}
	}

	private void editStudentButtonActionPerformed() {

		int selectedRow = studentsTable.getSelectedRow();
		if (selectedRow != -1) {
			studentFormView.editTeacherMode(Integer.parseInt(studentsTableModel.getValueAt(selectedRow, 0).toString()));

		}
	}

	private void newStudentButtonActionPerformed() {
		studentFormView.newTeacherMode();
	}

	public TableModel getStudentsTableModel() {
		return studentsTableModel;
	}

	public void setStudentsTableModel(TableModel studentsTableModel) {
		this.studentsTableModel = studentsTableModel;
	}

	public JButton getDeleteStudentButton() {
		return deleteStudentButton;
	}

	public void setDeleteStudentButton(JButton deleteStudentButton) {
		this.deleteStudentButton = deleteStudentButton;
	}

	public JButton getEditStudentButton() {
		return editStudentButton;
	}

	public void setEditStudentButton(JButton editStudentButton) {
		this.editStudentButton = editStudentButton;
	}

	public JButton getNewStudentButton() {
		return newStudentButton;
	}

	public void setNewStudentButton(JButton newStudentButton) {
		this.newStudentButton = newStudentButton;
	}

	public JTable getStudentsTable() {
		return studentsTable;
	}

	public void setStudentsTable(JTable studentsTable) {
		this.studentsTable = studentsTable;
	}

	public JScrollPane getTableScrollPane() {
		return tableScrollPane;
	}

	public void setTableScrollPane(JScrollPane tableScrollPane) {
		this.tableScrollPane = tableScrollPane;
	}

}
