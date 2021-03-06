package tfm.mvp.pv.views;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

import tfm.mvp.pv.presenters.TeacherFormPresenter;
import tfm.mvp.pv.presenters.TeachersCollectionPresenter;

import javax.swing.JButton;


public class TeachersFrame extends JFrame {

	private MainFrame menuFrame;
	private TeacherFormView teacherFormView;
	private TeacherCollectionView teacherCollectionView;

	
	public TeachersFrame(MainFrame menuFrame) {
		this.menuFrame = menuFrame;
		
		TeacherFormPresenter teacherFormPresenter = new TeacherFormPresenter();
		TeachersCollectionPresenter teacherCollectionPresenter = new TeachersCollectionPresenter();
		
		teacherFormPresenter.setTeachetCollectionPresenter(teacherCollectionPresenter);
		teacherCollectionPresenter.setTeacherFormPresenter(teacherFormPresenter);
		
		teacherFormView = new TeacherFormView(teacherFormPresenter);
		teacherCollectionView = new TeacherCollectionView(teacherCollectionPresenter);
				
		
		initComponents();
	}

	private void initComponents() {

		JButton returnMenuFrameButton = new JButton();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		returnMenuFrameButton.setText("Volver");
		returnMenuFrameButton.addActionListener(e->returnMenuFrameButtonActionPerformed());


		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup()
				.addContainerGap()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(GroupLayout.Alignment.TRAILING,
								layout.createSequentialGroup()
								.addGap(0, 0, Short.MAX_VALUE)
										.addComponent(returnMenuFrameButton))
						.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
								.addComponent(teacherCollectionView , GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(teacherFormView, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(teacherCollectionView, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(teacherFormView , GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
						.addGap(18, 18, 18).addComponent(returnMenuFrameButton).addContainerGap()));

		pack();
	}

	private void returnMenuFrameButtonActionPerformed() {
		this.setVisible(false);
		menuFrame.setVisible(true);
		dispose();
	}

}
