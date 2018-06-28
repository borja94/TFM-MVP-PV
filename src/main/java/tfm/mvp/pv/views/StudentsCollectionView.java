package tfm.mvp.pv.views;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import tfm.mvp.pv.presenters.IStudentCollectionViewPresenter;

public class StudentsCollectionView extends JPanel {

	private JButton deleteStudentButton;
	private JButton editStudentButton;
	private JButton newStudentButton;
	private JTable studentsTable;
	private JScrollPane tableScrollPane;
	private IStudentCollectionViewPresenter iStudentCollectionPresenter;

	public StudentsCollectionView(IStudentCollectionViewPresenter studentCollectionViewPresenter) {
		iStudentCollectionPresenter = studentCollectionViewPresenter;
		iStudentCollectionPresenter.setStudentCollectionView(this);

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
		deleteStudentButton.addActionListener(e -> deleteStudentButtonActionPerformed());

		editStudentButton.setText("Modo edición");
		editStudentButton.addActionListener(e -> editStudentButtonActionPerformed());

		newStudentButton.setText("Nuevo alumno");
		newStudentButton.addActionListener(e -> newStudentButtonActionPerformed());

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
		iStudentCollectionPresenter.updateStudentTableData();
	}

	private void deleteStudentButtonActionPerformed() {
		iStudentCollectionPresenter.notifyDeleteStudent();
	}

	private void editStudentButtonActionPerformed() {
		iStudentCollectionPresenter.notifyEditStudent();
	}

	private void newStudentButtonActionPerformed() {
		iStudentCollectionPresenter.notifyNewStudent();
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
