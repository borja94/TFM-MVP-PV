package tfm.mvp.pv.views;


import javax.swing.GroupLayout;
import javax.swing.WindowConstants;

import tfm.mvp.pv.presenters.StudentFormPresenter;
import tfm.mvp.pv.presenters.StudentsCollectionPresenter;

import javax.swing.JButton;


public class StudentsFrame extends javax.swing.JFrame {

	private MainFrame menuFrame;
	private StudentFormView studentFormView;
	private StudentsCollectionView studentsCollectionView;



	public StudentsFrame(MainFrame menuFrame) {
		this.menuFrame = menuFrame;
	
		StudentFormPresenter studentFormPresenter = new StudentFormPresenter();
		StudentsCollectionPresenter studentCollectionPresenter = new StudentsCollectionPresenter();
		
		studentFormPresenter.setStudentCollectionPresenter(studentCollectionPresenter);
		studentCollectionPresenter.setStudentFormPresenter(studentFormPresenter);
		
		studentFormView = new StudentFormView(studentFormPresenter);
		studentsCollectionView = new StudentsCollectionView(studentCollectionPresenter);
		
		initComponents();
	}

	private void initComponents() {

		JButton returnMenuFrameButton = new JButton();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		returnMenuFrameButton.setText("Volver");
		returnMenuFrameButton.addActionListener(e->returnMenuFrameButtonActionPerformed());

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(returnMenuFrameButton)
						.addContainerGap())
				.addGroup(layout.createSequentialGroup()
						.addGap(22, 22, 22)
						.addComponent(studentsCollectionView, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addGap(18, 18, 18)
						.addComponent(studentFormView, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(43, 43, 43)));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(studentsCollectionView, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(studentFormView, GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGap(18, 18, 18).addComponent(returnMenuFrameButton).addContainerGap()));

		pack();
	}

	private void returnMenuFrameButtonActionPerformed() {
		this.setVisible(false);
		menuFrame.setVisible(true);
		dispose();
	}
}
