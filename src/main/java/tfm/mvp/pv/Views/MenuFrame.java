/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tfm.mvp.pv.Views;

/**
 *
 * @author borja
 */
public class MenuFrame extends javax.swing.JFrame {

	private SubjectsFrame subjectsFrame;
	private TeachersFrame teacherFrame;
	private StudentsFrame studentsFrame;

	
	public MenuFrame() {
		MenuPanel menuPanel = new MenuPanel();
		this.setContentPane(menuPanel);
		initComponents();
		subjectsFrame = new SubjectsFrame(this);
		teacherFrame = new TeachersFrame(this);
		studentsFrame = new StudentsFrame(this);

		menuPanel.getStudentFrameButton().addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				studentFrameButtonAction(evt);
			}
		});

		menuPanel.getTeacherFrameButton().addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				teacherFrameButtonAction(evt);
			}
		});
		menuPanel.getSubjectFrameButton().addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				subjectFrameButtonAction(evt);
			}
		});

	}

	private void initComponents() {
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		this.setSize(500, 500);
	}

	private void teacherFrameButtonAction(java.awt.event.ActionEvent evt) {

		this.setVisible(false);
		teacherFrame.setVisible(true);
		dispose();
	}

	private void studentFrameButtonAction(java.awt.event.ActionEvent evt) {

		this.setVisible(false);
		studentsFrame.setVisible(true);
		dispose();
	}

	private void subjectFrameButtonAction(java.awt.event.ActionEvent evt) {

		this.setVisible(false);
		subjectsFrame.setVisible(true);
		dispose();
	}

}
