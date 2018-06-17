package tfm.mvp.pv.views;



import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle;
import javax.swing.table.DefaultTableModel;

import tfm.mvp.pv.presenters.ISubjectCollectionViewPresenter;

public class SubjectsCollectionView extends JPanel {

	private JButton deleteButton;
	private JButton editButton;
	private JButton newSubjectButton;
	private JTable subjectTable;
	private JScrollPane tableScrollPane;

	private ISubjectCollectionViewPresenter iSubjectsCollectionPresenter;

	public SubjectsCollectionView(ISubjectCollectionViewPresenter subjectCollectionViewPresenter) {

		iSubjectsCollectionPresenter = subjectCollectionViewPresenter;
		iSubjectsCollectionPresenter.setSubjectCollectionView(this);
		
		initComponents();
	}

	private void initComponents() {

		tableScrollPane = new JScrollPane();
		subjectTable = new JTable();
		deleteButton = new JButton();
		editButton = new JButton();
		newSubjectButton = new JButton();

		tableScrollPane.setViewportView(subjectTable);

		deleteButton.setText("Borrar");
		deleteButton.addActionListener(e->deleteButtonActionPerformed());

		editButton.setText("Modo edición");
		editButton.addActionListener(e->editButtonActionPerformed());

		newSubjectButton.setText("Nueva asignatura");
		newSubjectButton.addActionListener(e->newSubjectButtonActionPerformed());

		initComponentsPosition();
		updateSubjectsTableData();
	}

	private void initComponentsPosition() {

		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
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
						.addGap(18, 18, 18).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(deleteButton).addComponent(editButton).addComponent(newSubjectButton))
						.addGap(203, 203, 203)));

	}

	public void updateSubjectsTableData() {
		iSubjectsCollectionPresenter.notifyUpdateSubjectsTableData();
	}

	private void deleteButtonActionPerformed() {
		iSubjectsCollectionPresenter.notifyDeleteButtonActionPerformed();
	}

	private void editButtonActionPerformed() {

		iSubjectsCollectionPresenter.notifyEditMode();
	}

	private void newSubjectButtonActionPerformed() {
		iSubjectsCollectionPresenter.newSubjectMode();
	}

	public void setTableModel(DefaultTableModel tableModel) {
		subjectTable.setModel(tableModel);
	}

	public int getTableSelectedRow() {
		return subjectTable.getSelectedRow();
	}

	public int getSelectedId() {
		return Integer.parseInt(subjectTable.getModel().getValueAt(getTableSelectedRow(), 0).toString());
	}

}
