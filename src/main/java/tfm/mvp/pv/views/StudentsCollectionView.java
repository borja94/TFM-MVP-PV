package tfm.mvp.pv.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import tfm.mvp.pv.presenters.StudentsCollectionPresenter;

public class StudentsCollectionView extends JPanel {

	private JButton deleteStudentButton;
	private JButton editStudentButton;
	private JButton newStudentButton;
	private JTable studentsTable;
	private JScrollPane tableScrollPane;
	private StudentsCollectionPresenter studentCollectionPresenter;

	public StudentsCollectionView(StudentFormView studentFormView) {
		studentCollectionPresenter = new StudentsCollectionPresenter(this,studentFormView.getStudentFormPresenter());
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
		studentCollectionPresenter.notifyUpdateStudentTableData();
	}

	private void deleteStudentButtonActionPerformed() {
		studentCollectionPresenter.notifyDeleteStudent();
	}

	private void editStudentButtonActionPerformed() {
		studentCollectionPresenter.notifyEditStudent();
	}

	private void newStudentButtonActionPerformed() {
		studentCollectionPresenter.notifyNewStudent();
	}

	public StudentsCollectionPresenter getStudentCollectionPresenter() {
		return studentCollectionPresenter;
	}
	
	public void setTableModel(DefaultTableModel tableModel) {
		studentsTable.setModel(tableModel);
	}
	
	public int getTableSelectedRow() {
		return studentsTable.getSelectedRow();
	}
	
	public int getSelectedId() {
		return Integer.parseInt(studentsTable.getModel().getValueAt(getTableSelectedRow(), 0).toString());
	}
}
