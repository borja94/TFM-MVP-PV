/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tfm.mvp.pv.views;


import javax.swing.GroupLayout;
import javax.swing.WindowConstants;
import javax.swing.JButton;

/**
 *
 * @author borja
 */
public class StudentsFrame extends javax.swing.JFrame {

	private MenuFrame _menuFrame;

	private StudentFormView studentFormView;
	private StudentsCollectionView studentsCollectionView;

	private JButton ReturnMenuFrameButton;

	/**
	 * Creates new form StudentsFrame
	 *
	 * @param menuFrame
	 */
	public StudentsFrame(MenuFrame menuFrame) {
		studentFormView = new StudentFormView();
		studentsCollectionView = new StudentsCollectionView(studentFormView);
		studentFormView.setStudentCollectionView(studentsCollectionView);
		initComponents();
		_menuFrame = menuFrame;
	}

	public void setVisible(boolean b) {
		super.setVisible(b);
	}

	private void initComponents() {

		ReturnMenuFrameButton = new JButton();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		ReturnMenuFrameButton.setText("Volver");
		ReturnMenuFrameButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ReturnMenuFrameButtonActionPerformed(evt);
			}
		});

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
				.addGroup(layout.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(ReturnMenuFrameButton).addContainerGap())
				.addGroup(layout.createSequentialGroup().addGap(22, 22, 22)
						.addComponent(studentFormView, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addGap(18, 18, 18).addComponent(studentsCollectionView, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(43, 43, 43)));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(studentFormView, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(studentsCollectionView, GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGap(18, 18, 18).addComponent(ReturnMenuFrameButton).addContainerGap()));

		pack();
	}

	private void ReturnMenuFrameButtonActionPerformed(java.awt.event.ActionEvent evt) {
		this.setVisible(false);
		_menuFrame.setVisible(true);
		dispose();
	}
}
