/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tfm.mvp.pv.Views;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;
import javax.swing.JButton;

/**
 *
 * @author borja
 */
public class TeachersFrame extends JFrame {

	private MenuFrame _menuFrame;
	private TeacherFormView teacherFormView;
	private TeacherCollectionView teacherCollectionView;
	private JButton ReturnMenuFrameButton;

	/**
	 * Creates new form TeachersFrame
	 * 
	 * @param menuFrame
	 */
	public TeachersFrame(MenuFrame menuFrame) {
		_menuFrame = menuFrame;
		teacherFormView = new TeacherFormView();
		teacherCollectionView = new TeacherCollectionView(teacherFormView);
		teacherFormView.setTeacherCollectionPresenter(teacherCollectionView.getTeacherCollectionPresenter());
		initComponents();
	}

	@Override
	public void setVisible(boolean b) {
		super.setVisible(b);
	}

	private void initComponents() {

		ReturnMenuFrameButton = new JButton();
		/* jPanel1 = new JPanel(); */

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		ReturnMenuFrameButton.setText("Volver");
		ReturnMenuFrameButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ReturnMenuFrameButtonActionPerformed(evt);
			}
		});

		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
		container.add(teacherFormView);
		container.add(teacherCollectionView);
		this.setContentPane(container);
		this.add(ReturnMenuFrameButton);

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(GroupLayout.Alignment.TRAILING,
								layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE)
										.addComponent(ReturnMenuFrameButton))
						.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
								.addComponent(teacherFormView, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(teacherCollectionView, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(teacherFormView, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(teacherCollectionView, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
						.addGap(18, 18, 18).addComponent(ReturnMenuFrameButton).addContainerGap()));

		pack();
	}

	private void ReturnMenuFrameButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_ReturnMenuFrameButtonActionPerformed
		this.setVisible(false);
		_menuFrame.setVisible(true);
		dispose();
	}

}
