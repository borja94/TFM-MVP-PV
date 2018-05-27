package tfm.mvp.pv.views;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle;
import javax.swing.table.DefaultTableModel;

import tfm.mvp.pv.presenters.TeachersCollectionPresenter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TeacherCollectionView extends JPanel {

	private JButton deleteTeacherButton;
	private JButton editTeacherButton;
	private JButton newTeacherButton;
	private JTable teachersTable;
	private JScrollPane tableScrollPane;

	private TeachersCollectionPresenter teacherCollectionPresenter;

	public TeacherCollectionView(TeacherFormView teacherFormView) {
		teacherCollectionPresenter = new TeachersCollectionPresenter(this, teacherFormView.getTeacherFormPresenter());
		initComponents();
	}

	private void initComponents() {

		tableScrollPane = new JScrollPane();
		teachersTable = new JTable();
		deleteTeacherButton = new JButton();
		editTeacherButton = new JButton();
		newTeacherButton = new JButton();

		updateTeacherTableData();

		tableScrollPane.setViewportView(teachersTable);

		deleteTeacherButton.setText("Borrar");
		deleteTeacherButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				deleteTeacherButtonActionPerformed();
			}
		});

		editTeacherButton.setText("Modo edición");
		editTeacherButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				editTeacherButtonActionPerformed();
			}
		});

		newTeacherButton.setText("Nuevo profesor");
		newTeacherButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				newTeacherButtonActionPerformed();
			}
		});

		initComponentPostions();
	}

	private void initComponentPostions() {

		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(tableScrollPane, GroupLayout.PREFERRED_SIZE, 425, GroupLayout.PREFERRED_SIZE)
						.addGroup(layout.createSequentialGroup().addComponent(deleteTeacherButton)
								.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(editTeacherButton).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(newTeacherButton)))
				.addGap(0, 19, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
				GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(tableScrollPane, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(deleteTeacherButton).addComponent(editTeacherButton)
								.addComponent(newTeacherButton))
						.addGap(203, 203, 203)));
	}

	public void updateTeacherTableData() {
		teacherCollectionPresenter.notifyUpdateTeacherTableData();
	}

	private void deleteTeacherButtonActionPerformed() {
		teacherCollectionPresenter.notifyDeleteTeacher();
	}

	private void editTeacherButtonActionPerformed() {
		teacherCollectionPresenter.notifyEditTeacher();
	}

	private void newTeacherButtonActionPerformed() {
		teacherCollectionPresenter.notifyNewTeacher();
	}

	public TeachersCollectionPresenter getTeacherCollectionPresenter() {
		return teacherCollectionPresenter;
	}

	public void setTableModel(DefaultTableModel tableModel) {
		teachersTable.setModel(tableModel);
	}

	public int getTableSelectedRow() {
		return teachersTable.getSelectedRow();
	}

	public int getSelectedId() {

		return Integer.parseInt(teachersTable.getModel().getValueAt(teachersTable.getSelectedRow(), 0).toString());
	}

}
