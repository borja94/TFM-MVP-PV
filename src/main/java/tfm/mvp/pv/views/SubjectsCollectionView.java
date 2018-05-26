package tfm.mvp.pv.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import tfm.mvp.pv.presenters.SubjectsCollectionPresenter;

public class SubjectsCollectionView extends JPanel {

	private TableModel subjectsTableModel;

	private JButton deleteButton;
	private JButton editButton;
	private JButton newSubjectButton;
	private JTable subjectTable;
	private JScrollPane tableScrollPane;

	private SubjectFormView subjectFormView;
	private SubjectsCollectionPresenter subjectsCollectionPresenter;

	public SubjectsCollectionView(SubjectFormView subjectFormView) {

		subjectsCollectionPresenter = new SubjectsCollectionPresenter();
		this.subjectFormView = subjectFormView;
		initComponents();
		updateSubjectsTableData();

	}

	private void initComponents() {

		tableScrollPane = new JScrollPane();
		subjectTable = new JTable();
		deleteButton = new JButton();
		editButton = new JButton();
		newSubjectButton = new JButton();

		tableScrollPane.setViewportView(subjectTable);

		deleteButton.setText("Borrar");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				deleteButtonActionPerformed();
			}
		});

		editButton.setText("Modo edición");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				editButtonActionPerformed();
			}
		});

		newSubjectButton.setText("Nueva asignatura");
		newSubjectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				newSubjectButtonActionPerformed();
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
						.addComponent(tableScrollPane, GroupLayout.PREFERRED_SIZE, 425, GroupLayout.PREFERRED_SIZE)
						.addGroup(layout.createSequentialGroup().addComponent(deleteButton)
								.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(editButton)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(newSubjectButton)))
						.addGap(0, 19, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
				GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(tableScrollPane, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(deleteButton).addComponent(editButton).addComponent(newSubjectButton))
						.addGap(203, 203, 203)));

	}

	public void updateSubjectsTableData() {
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
		subjectsTableModel = new DefaultTableModel(tableData, columns);
		subjectTable.setModel(subjectsTableModel);
	}

	private void deleteButtonActionPerformed() {
		int selectedRow = subjectTable.getSelectedRow();
		if (selectedRow != -1) {
			int dialogResult = JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar al alumno");
			if (dialogResult == JOptionPane.YES_OPTION) {
				subjectsCollectionPresenter
						.removeSubject(Integer.parseInt(subjectsTableModel.getValueAt(selectedRow, 0).toString()));
				updateSubjectsTableData();
			}
		}
	}

	private void editButtonActionPerformed() {

		int selectedRow = subjectTable.getSelectedRow();
		if (selectedRow != -1)
			subjectFormView.editSubjectMode(Integer.parseInt(subjectsTableModel.getValueAt(selectedRow, 0).toString()));
	}

	private void newSubjectButtonActionPerformed() {
		subjectFormView.newSubjectMode();
	}

	public TableModel getSubjectsTableModel() {
		return subjectsTableModel;
	}

	public void setSubjectsTableModel(TableModel subjectsTableModel) {
		this.subjectsTableModel = subjectsTableModel;
	}

	public JButton getDeleteButton() {
		return deleteButton;
	}

	public void setDeleteButton(JButton deleteButton) {
		this.deleteButton = deleteButton;
	}

	public JButton getEditButton() {
		return editButton;
	}

	public void setEditButton(JButton editButton) {
		this.editButton = editButton;
	}

	public JButton getNewSubjectButton() {
		return newSubjectButton;
	}

	public void setNewSubjectButton(JButton newSubjectButton) {
		this.newSubjectButton = newSubjectButton;
	}

	public JTable getSubjectTable() {
		return subjectTable;
	}

	public void setSubjectTable(JTable subjectTable) {
		this.subjectTable = subjectTable;
	}

	public JScrollPane getTableScrollPane() {
		return tableScrollPane;
	}

	public void setTableScrollPane(JScrollPane tableScrollPane) {
		this.tableScrollPane = tableScrollPane;
	}
	
	

}
