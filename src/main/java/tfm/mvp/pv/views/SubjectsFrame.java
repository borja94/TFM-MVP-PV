/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tfm.mvp.pv.views;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

/**
 *
 * @author borja
 */
public class SubjectsFrame extends JFrame {

	private MenuFrame _menuFrame;

	private JButton ReturnMenuFrameButton;

	private SubjectFormView subjectFormView;
	private SubjectsCollectionView subjectCollectionView;

	/**
	 * Creates new form SubjectsFrame
	 *
	 * @param menuFrame
	 */
	public SubjectsFrame(MenuFrame menuFrame) {

		subjectFormView = new SubjectFormView();
		subjectCollectionView = new SubjectsCollectionView(subjectFormView);
		subjectFormView.setSubjectCollectionView(subjectCollectionView);
		
		initComponents();
		_menuFrame = menuFrame;
	}

	@Override
	public void setVisible(boolean b) {
		super.setVisible(b);
	}

	private void initComponents() {

		ReturnMenuFrameButton = new JButton();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		ReturnMenuFrameButton.setText("Volver");
		ReturnMenuFrameButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(GroupLayout.Alignment.TRAILING,
								layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE)
										.addComponent(ReturnMenuFrameButton))
						.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
								.addComponent(subjectFormView, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(subjectCollectionView, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(subjectFormView, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(subjectCollectionView, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
						.addGap(18, 18, 18).addComponent(ReturnMenuFrameButton).addContainerGap()));

		pack();
	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		this.setVisible(false);
		_menuFrame.setVisible(true);
		dispose();
	}

}
