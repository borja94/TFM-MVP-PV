package tfm.mvp.pv.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import tfm.mvp.pv.presenters.ISubjectFormViewPresenter;

public class SubjectFormView extends JPanel {

	private JTextField courseInput;
	private JButton saveButton;
	private JLabel subjectFormLabel;
	private JTextField titleInput;
	private JLabel titleInputLabel;
	private JLabel courseInpitLabel;

	private ISubjectFormViewPresenter iSubjectFormPresenter;

	public SubjectFormView(ISubjectFormViewPresenter subjectFormPresenter) {
		iSubjectFormPresenter = subjectFormPresenter;
		iSubjectFormPresenter.setSubjectFormView(this);
		
		initComponents();
	}

	private void initComponents() {

		subjectFormLabel = new JLabel();
		titleInput = new JTextField();
		courseInput = new JTextField();
		titleInputLabel = new JLabel();
		courseInpitLabel = new JLabel();
		saveButton = new JButton();


		titleInputLabel.setText("Título");

		courseInpitLabel.setText("Curso");

		saveButton.setText("Guardar");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				onSaveButtonActionPerformed();
			}
		});

		initComponentsPosition();
	}

	private void initComponentsPosition() {

		GroupLayout jPanel1Layout = new GroupLayout(this);
		this.setLayout(jPanel1Layout);
		jPanel1Layout
				.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout
								.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(subjectFormLabel)
								.addGroup(jPanel1Layout.createSequentialGroup()
										.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(titleInput, GroupLayout.PREFERRED_SIZE, 62,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(titleInputLabel))
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(courseInpitLabel).addComponent(courseInput,
														GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)))
								.addComponent(saveButton)).addContainerGap(225, Short.MAX_VALUE)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addGap(33, 33, 33).addComponent(subjectFormLabel)
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(titleInputLabel).addComponent(courseInpitLabel))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(titleInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(courseInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(30, 30, 30).addComponent(saveButton)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

	}

	private void onSaveButtonActionPerformed() {

		iSubjectFormPresenter.saveForm();
	}
	

	public void setCourseInputValue(String value) {
		courseInput.setText(value);
	}

	public void setTitleInputValue(String value) {
		titleInput.setText(value);
	}

	public void setSubjectFormLabelValue(String value) {
		subjectFormLabel.setText(value);
	}

	public String getTitleInputValue() {
		return titleInput.getText();
	}

	public String getCourseInputValue() {
		return courseInput.getText();
	}

}
