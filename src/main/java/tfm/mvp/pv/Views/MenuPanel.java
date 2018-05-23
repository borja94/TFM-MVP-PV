package tfm.mvp.pv.Views;

import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.GroupLayout;
import javax.swing.JButton;

public class MenuPanel extends JPanel {

	private javax.swing.JButton TeacherFrameButton;
	private javax.swing.JButton StudentFrameButton;
	private javax.swing.JButton SubjectFrameButton;
	private javax.swing.JLabel MenuLabel;

	public MenuPanel() {

		initComponents();
	}

	private void initComponents() {

		MenuLabel = new javax.swing.JLabel();
		TeacherFrameButton = new javax.swing.JButton();
		StudentFrameButton = new javax.swing.JButton();
		SubjectFrameButton = new javax.swing.JButton();

		MenuLabel.setText("Men�");
		TeacherFrameButton.setText("Profesores");
		StudentFrameButton.setText("Alumnos");
		SubjectFrameButton.setText("Asignaturas");
		initComponentsPosition();

	}

	public void initComponentsPosition() {
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);

		layout.setHorizontalGroup(layout.createSequentialGroup().addGap(23, 23, 23)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(MenuLabel, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
						.addComponent(TeacherFrameButton, javax.swing.GroupLayout.Alignment.LEADING,
								GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(StudentFrameButton, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
								GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(SubjectFrameButton, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
								GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGap(23, 23, 23));

		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(21, 21, 21).addComponent(MenuLabel)
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(TeacherFrameButton)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(StudentFrameButton)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(SubjectFrameButton)
						.addGap(21, 21, 21)));

	}

	public JButton getTeacherFrameButton() {
		return TeacherFrameButton;
	}

	public JButton getStudentFrameButton() {
		return StudentFrameButton;
	}

	public JButton getSubjectFrameButton() {
		return SubjectFrameButton;
	}

}
