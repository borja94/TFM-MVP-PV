package tfm.mvp.pv.Views;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import tfm.mvp.pv.Presenters.SubjectsCollectionPresenter;

public class SubjectsCollectionView extends JPanel {

	private TableModel SubjectsTableModel;

	private JButton DeleteButton;
	private JButton EditButton;
	private JButton NewSubjectButton;
	private JTable SubjectTable;
	private JScrollPane TableScrollPane;

	private SubjectFormView subjectFormView;
	private SubjectsCollectionPresenter subjectsCollectionPresenter;

	public SubjectsCollectionView(SubjectFormView subjectFormView) {

		subjectsCollectionPresenter = new SubjectsCollectionPresenter();
		this.subjectFormView = subjectFormView;
		initComponents();
		UpdateSubjectsTableData();

	}

	private void initComponents() {

		TableScrollPane = new JScrollPane();
		SubjectTable = new JTable();
		DeleteButton = new JButton();
		EditButton = new JButton();
		NewSubjectButton = new JButton();

		TableScrollPane.setViewportView(SubjectTable);

		DeleteButton.setText("Borrar");
		DeleteButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				DeleteButtonActionPerformed(evt);
			}
		});

		EditButton.setText("Modo edición");
		EditButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				EditButtonActionPerformed(evt);
			}
		});

		NewSubjectButton.setText("Nueva asignatura");
		NewSubjectButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				NewSubjectButtonActionPerformed(evt);
			}
		});

		initComponentsPosition();
	}

	private void initComponentsPosition() {

		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGroup(layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(TableScrollPane, GroupLayout.PREFERRED_SIZE, 425, GroupLayout.PREFERRED_SIZE)
						.addGroup(layout.createSequentialGroup().addComponent(DeleteButton)
								.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(EditButton)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(NewSubjectButton)))
						.addGap(0, 19, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
				GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(TableScrollPane, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(DeleteButton).addComponent(EditButton).addComponent(NewSubjectButton))
						.addGap(203, 203, 203)));

	}

	public void UpdateSubjectsTableData() {
		subjectsCollectionPresenter.loadTableData();
		String[] columns = new String[subjectsCollectionPresenter.getNumColumns()];
		String[][] tableData = new String[subjectsCollectionPresenter.getNumRows()][subjectsCollectionPresenter
				.getNumColumns()];
		for (int i = 0; i < columns.length; i++) {
			columns[i] = subjectsCollectionPresenter.getColumnName(i);
		}
		for (int i = 0; i < subjectsCollectionPresenter.getNumRows(); i++) {
			for (int j = 0; j < subjectsCollectionPresenter.getNumColumns(); j++) {
				tableData[i][j] = subjectsCollectionPresenter.getSubjectAtribute(j, i);
			}
		}
		SubjectsTableModel = new DefaultTableModel(tableData, columns);
		SubjectTable.setModel(SubjectsTableModel);
	}

	private void DeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {
		int selectedRow = SubjectTable.getSelectedRow();
		if (selectedRow != -1) {
			int dialogResult = JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar al alumno");
			if (dialogResult == JOptionPane.YES_OPTION) {
				subjectsCollectionPresenter
						.RemoveSubject(Integer.parseInt(SubjectsTableModel.getValueAt(selectedRow, 0).toString()));
				UpdateSubjectsTableData();
			}
		}
	}

	private void EditButtonActionPerformed(java.awt.event.ActionEvent evt) {

		int selectedRow = SubjectTable.getSelectedRow();
		if (selectedRow != -1)
			subjectFormView.EditSubjectMode(Integer.parseInt(SubjectsTableModel.getValueAt(selectedRow, 0).toString()));
	}

	private void NewSubjectButtonActionPerformed(java.awt.event.ActionEvent evt) {
		subjectFormView.NewSubjectMode();
	}

	public TableModel getSubjectsTableModel() {
		return SubjectsTableModel;
	}

	public void setSubjectsTableModel(TableModel subjectsTableModel) {
		SubjectsTableModel = subjectsTableModel;
	}

	public JButton getDeleteButton() {
		return DeleteButton;
	}

	public void setDeleteButton(JButton deleteButton) {
		DeleteButton = deleteButton;
	}

	public JButton getEditButton() {
		return EditButton;
	}

	public void setEditButton(JButton editButton) {
		EditButton = editButton;
	}

	public JButton getNewSubjectButton() {
		return NewSubjectButton;
	}

	public void setNewSubjectButton(JButton newSubjectButton) {
		NewSubjectButton = newSubjectButton;
	}

	public JTable getSubjectTable() {
		return SubjectTable;
	}

	public void setSubjectTable(JTable subjectTable) {
		SubjectTable = subjectTable;
	}

	public JScrollPane getTableScrollPane() {
		return TableScrollPane;
	}

	public void setTableScrollPane(JScrollPane tableScrollPane) {
		TableScrollPane = tableScrollPane;
	}
	
	

}
