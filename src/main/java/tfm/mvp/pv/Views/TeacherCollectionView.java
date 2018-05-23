package tfm.mvp.pv.Views;

import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.table.TableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import tfm.mvp.pv.Presenters.TeachersCollectionPresenter;

public class TeacherCollectionView extends JPanel {

	private javax.swing.JButton DeleteTeacherButton;
	private javax.swing.JButton EditTeacherButton;
	private javax.swing.JButton NewTeacherButton;
	private javax.swing.JTable TeachersTable;
	private javax.swing.JScrollPane TableScrollPane;

	private TeachersCollectionPresenter teacherCollectionPresenter;
	private TableModel TeachersTableModel;

	public TeacherCollectionView(TeacherFormView teacherFormView) {
		teacherCollectionPresenter = new TeachersCollectionPresenter(this,teacherFormView.getTeacherFormPresenter());
		initComponents();
	}

	private void initComponents() {

		TableScrollPane = new javax.swing.JScrollPane();
		TeachersTable = new javax.swing.JTable();
		DeleteTeacherButton = new javax.swing.JButton();
		EditTeacherButton = new javax.swing.JButton();
		NewTeacherButton = new javax.swing.JButton();

		UpdateTeacherTableData();

		TableScrollPane.setViewportView(TeachersTable);

		DeleteTeacherButton.setText("Borrar");
		DeleteTeacherButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				DeleteTeacherButtonActionPerformed();
			}
		});

		EditTeacherButton.setText("Modo edición");
		EditTeacherButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				EditTeacherButtonActionPerformed();
			}
		});

		NewTeacherButton.setText("Nuevo profesor");
		NewTeacherButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				NewTeacherButtonActionPerformed();
			}
		});

		initComponentPostions();
	}

	private void initComponentPostions() {

		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGroup(layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(TableScrollPane, GroupLayout.PREFERRED_SIZE, 425, GroupLayout.PREFERRED_SIZE)
						.addGroup(layout.createSequentialGroup().addComponent(DeleteTeacherButton)
								.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(EditTeacherButton)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(NewTeacherButton)))
						.addGap(0, 19, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
				GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(TableScrollPane, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(DeleteTeacherButton).addComponent(EditTeacherButton).addComponent(NewTeacherButton))
						.addGap(203, 203, 203)));
	}

	public void UpdateTeacherTableData() {
		teacherCollectionPresenter.NotifyUpdateTeacherTableData();
	}

	private void DeleteTeacherButtonActionPerformed() {
		teacherCollectionPresenter.NotifyDeleteTeacher();
	}

	private void EditTeacherButtonActionPerformed() {
		teacherCollectionPresenter.NotifyEditTeacher();
	}

	private void NewTeacherButtonActionPerformed() {
		teacherCollectionPresenter.NotifyNewTeacher();
	}

	public javax.swing.JButton getDeleteTeacherButton() {
		return DeleteTeacherButton;
	}

	public void setDeleteTeacherButton(javax.swing.JButton deleteTeacherButton) {
		DeleteTeacherButton = deleteTeacherButton;
	}

	public javax.swing.JButton getEditTeacherButton() {
		return EditTeacherButton;
	}

	public void setEditTeacherButton(javax.swing.JButton editTeacherButton) {
		EditTeacherButton = editTeacherButton;
	}

	public javax.swing.JButton getNewTeacherButton() {
		return NewTeacherButton;
	}

	public void setNewTeacherButton(javax.swing.JButton newTeacherButton) {
		NewTeacherButton = newTeacherButton;
	}

	public javax.swing.JTable getTeachersTable() {
		return TeachersTable;
	}

	public void setTeachersTable(javax.swing.JTable teachersTable) {
		TeachersTable = teachersTable;
	}

	public javax.swing.JScrollPane getTableScrollPane() {
		return TableScrollPane;
	}

	public void setTableScrollPane(javax.swing.JScrollPane tableScrollPane) {
		TableScrollPane = tableScrollPane;
	}

	public TableModel getTeachersTableModel() {
		return TeachersTableModel;
	}

	public void setTeachersTableModel(TableModel teachersTableModel) {
		TeachersTableModel = teachersTableModel;
	}

	public TeachersCollectionPresenter getTeacherCollectionPresenter() {
		return teacherCollectionPresenter;
	}
	
	
	
	

}
