package tfm.mvp.pv.views;


import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

import tfm.mvp.pv.presenters.SubjectFormPresenter;
import tfm.mvp.pv.presenters.SubjectsCollectionPresenter;

public class SubjectsFrame extends JFrame {

	private MenuFrame menuFrame;
	private SubjectFormView subjectFormView;
	private SubjectsCollectionView subjectCollectionView;


	public SubjectsFrame(MenuFrame menuFrame) {

		
		
		SubjectFormPresenter subjectFormPresenter = new SubjectFormPresenter();
		SubjectsCollectionPresenter subjectCollectionPresenter = new SubjectsCollectionPresenter();
		
		subjectFormPresenter.setSubjectsCollectionPresenter(subjectCollectionPresenter);
		subjectCollectionPresenter.setTeacherFormPresenter(subjectFormPresenter);
		
		subjectFormView = new SubjectFormView(subjectFormPresenter);
		subjectCollectionView = new SubjectsCollectionView(subjectCollectionPresenter);
		
		initComponents();
		this.menuFrame = menuFrame;
	}

	private void initComponents() {

		JButton returnMenuFrameButton = new JButton();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		returnMenuFrameButton.setText("Volver");
		returnMenuFrameButton.addActionListener(e->returnMenuFrameButtonActionPerformed());

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(GroupLayout.Alignment.TRAILING,
								layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE)
										.addComponent(returnMenuFrameButton))
						.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
								.addComponent(subjectCollectionView, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(subjectFormView, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(subjectCollectionView, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(subjectFormView, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
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
