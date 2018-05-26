package tfm.mvp.pv.views;

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

	private TableModel StudentsTableModel;

	private JButton DeleteStudentButton;
	private JButton EditStudentButton;
	private JButton NewStudentButton;
	private JTable StudentsTable;
	private JScrollPane TableScrollPane;
	private StudentFormView studentFormView;
	private StudentsCollectionPresenter studentCollectionPresenter;

	public StudentsCollectionView(StudentFormView studentFormView) {
		this.studentFormView = studentFormView;
		studentCollectionPresenter = new StudentsCollectionPresenter();
		initComponents();
	}

	private void initComponents() {

		TableScrollPane = new JScrollPane();
		StudentsTable = new JTable();
		DeleteStudentButton = new JButton();
		EditStudentButton = new JButton();
		NewStudentButton = new JButton();

		UpdateStudentTableData();

		TableScrollPane.setViewportView(StudentsTable);

		DeleteStudentButton.setText("Borrar");
		DeleteStudentButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				DeleteStudentButtonActionPerformed(evt);
			}
		});

		EditStudentButton.setText("Modo edici�n");
		EditStudentButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				EditStudentButtonActionPerformed(evt);
			}
		});

		NewStudentButton.setText("Nuevo alumno");
		NewStudentButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				NewStudentButtonActionPerformed(evt);
			}
		});

		initComponentsPosition();
	}

	private void initComponentsPosition() {

		GroupLayout jPanel2Layout = new GroupLayout(this);
		this.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup().addContainerGap()
						.addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(TableScrollPane, GroupLayout.PREFERRED_SIZE, 384, GroupLayout.PREFERRED_SIZE)
								.addGroup(jPanel2Layout.createSequentialGroup().addComponent(DeleteStudentButton)
										.addGap(18, 18, 18).addComponent(EditStudentButton).addGap(18, 18, 18)
										.addComponent(NewStudentButton)))
						.addContainerGap(17, Short.MAX_VALUE)));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup().addContainerGap()
						.addComponent(TableScrollPane, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(DeleteStudentButton).addComponent(EditStudentButton)
								.addComponent(NewStudentButton))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

	}

	public void UpdateStudentTableData() {
		studentCollectionPresenter.loadTableData();
		String[] columns = new String[studentCollectionPresenter.getNumColumns()];
		String[][] tableData = new String[studentCollectionPresenter.getNumRows()][studentCollectionPresenter
				.getNumColumns()];
		for (int i = 0; i < columns.length; i++) {
			columns[i]=studentCollectionPresenter.getColumnName(i);
		}
		for (int i = 0; i < studentCollectionPresenter.getNumRows(); i++) {
			for (int j = 0; j < studentCollectionPresenter.getNumColumns(); j++) {
				tableData[i][j] = studentCollectionPresenter.getStudentAtribute(j, i);
			}
		}
		StudentsTableModel = new DefaultTableModel(tableData, columns);
		StudentsTable.setModel(StudentsTableModel);
	}

	private void DeleteStudentButtonActionPerformed(java.awt.event.ActionEvent evt) {
		int selectedRow = StudentsTable.getSelectedRow();
		if (selectedRow != -1) {
			int dialogResult = JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar al alumno");
			if (dialogResult == JOptionPane.YES_OPTION) {
				studentCollectionPresenter.RemoveStudent((Integer.parseInt(StudentsTableModel.getValueAt(selectedRow, 0).toString())));
				UpdateStudentTableData();
			}
		}
	}

	private void EditStudentButtonActionPerformed(java.awt.event.ActionEvent evt) {

		int selectedRow = StudentsTable.getSelectedRow();
		if (selectedRow != -1) {
			studentFormView.EditTeacherMode(Integer.parseInt(StudentsTableModel.getValueAt(selectedRow, 0).toString()));

		}
	}

	private void NewStudentButtonActionPerformed(java.awt.event.ActionEvent evt) {
		studentFormView.NewTeacherMode();
	}

	public TableModel getStudentsTableModel() {
		return StudentsTableModel;
	}

	public void setStudentsTableModel(TableModel studentsTableModel) {
		StudentsTableModel = studentsTableModel;
	}

	public JButton getDeleteStudentButton() {
		return DeleteStudentButton;
	}

	public void setDeleteStudentButton(JButton deleteStudentButton) {
		DeleteStudentButton = deleteStudentButton;
	}

	public JButton getEditStudentButton() {
		return EditStudentButton;
	}

	public void setEditStudentButton(JButton editStudentButton) {
		EditStudentButton = editStudentButton;
	}

	public JButton getNewStudentButton() {
		return NewStudentButton;
	}

	public void setNewStudentButton(JButton newStudentButton) {
		NewStudentButton = newStudentButton;
	}

	public JTable getStudentsTable() {
		return StudentsTable;
	}

	public void setStudentsTable(JTable studentsTable) {
		StudentsTable = studentsTable;
	}

	public JScrollPane getTableScrollPane() {
		return TableScrollPane;
	}

	public void setTableScrollPane(JScrollPane tableScrollPane) {
		TableScrollPane = tableScrollPane;
	}
	
	
	
	

}
