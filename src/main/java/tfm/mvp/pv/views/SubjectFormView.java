package tfm.mvp.pv.views;



import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import tfm.mvp.pv.presenters.SubjectFormPresenter;

public class SubjectFormView extends JPanel {

	private static final String NewSubjectLabelText = "Nueva asignatura";
	private static final String EditSubjectLabelText = "Editar asignatura";
	private boolean EditMode;
	private int SubjectSelectedId;

	private JTextField CourseInput;
	private JButton SaveButton;
	private JLabel SubjectFormLabel;
	private JTextField TitleInput;
	private JLabel TitleInputLabel;
	private JLabel CourseInpitLabel;

	private SubjectFormPresenter subjectFormPresenter;
	private SubjectsCollectionView subjectCollectionView;

	public SubjectFormView() {
		subjectFormPresenter = new SubjectFormPresenter();
		initComponents();
	}

	private void initComponents() {

		SubjectFormLabel = new JLabel();
		TitleInput = new JTextField();
		CourseInput = new JTextField();
		TitleInputLabel = new JLabel();
		CourseInpitLabel = new JLabel();
		SaveButton = new JButton();

		SubjectFormLabel.setText("Nueva asignatura");

		TitleInputLabel.setText("T�tulo");

		CourseInpitLabel.setText("Curso");

		SaveButton.setText("Guardar");
		SaveButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				SaveButtonActionPerformed(evt);
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
								.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(SubjectFormLabel)
								.addGroup(jPanel1Layout.createSequentialGroup()
										.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(TitleInput, GroupLayout.PREFERRED_SIZE, 62,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(TitleInputLabel))
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(CourseInpitLabel).addComponent(CourseInput,
														GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)))
								.addComponent(SaveButton)).addContainerGap(225, Short.MAX_VALUE)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addGap(33, 33, 33).addComponent(SubjectFormLabel)
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(TitleInputLabel).addComponent(CourseInpitLabel))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(TitleInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(CourseInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(30, 30, 30).addComponent(SaveButton)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

	}

	private void SaveButtonActionPerformed(java.awt.event.ActionEvent evt) {

		String name = TitleInput.getText();
		String course = CourseInput.getText();

		if (!name.isEmpty() && !course.isEmpty()) {
			if (EditMode) {
				subjectFormPresenter.UpdateStudent(name, Integer.parseInt(course), SubjectSelectedId);
			} else {
				subjectFormPresenter.InsertNewStudent(name, Integer.parseInt(course));
			}
			
			 subjectCollectionView.UpdateSubjectsTableData();
			 
		}
	}
	
	public void NewSubjectMode() {
		SubjectFormLabel.setText(NewSubjectLabelText);
		TitleInput.setText("");
		CourseInput.setText("");
		SubjectSelectedId = 0;
		EditMode = false;
	}
	public void EditSubjectMode(int id) {
		SubjectFormLabel.setText(EditSubjectLabelText);
		SubjectSelectedId = id;
		EditMode = true;
		subjectFormPresenter.loadSubject(id);
		TitleInput.setText(subjectFormPresenter.getSubjectTitle());
		CourseInput.setText(subjectFormPresenter.getSubjectCourse());
	}

	public void setSubjectCollectionView(SubjectsCollectionView subjectCollectionView) {
		this.subjectCollectionView = subjectCollectionView;
	}

	public int getSubjectSelectedId() {
		return SubjectSelectedId;
	}

	public void setSubjectSelectedId(int subjectSelectedId) {
		SubjectSelectedId = subjectSelectedId;
	}

	public JTextField getCourseInput() {
		return CourseInput;
	}

	public void setCourseInput(JTextField courseInput) {
		CourseInput = courseInput;
	}

	public JButton getSaveButton() {
		return SaveButton;
	}

	public void setSaveButton(JButton saveButton) {
		SaveButton = saveButton;
	}

	public JLabel getSubjectFormLabel() {
		return SubjectFormLabel;
	}

	public void setSubjectFormLabel(JLabel subjectFormLabel) {
		SubjectFormLabel = subjectFormLabel;
	}

	public JTextField getTitleInput() {
		return TitleInput;
	}

	public void setTitleInput(JTextField titleInput) {
		TitleInput = titleInput;
	}

	public JLabel getTitleInputLabel() {
		return TitleInputLabel;
	}

	public void setTitleInputLabel(JLabel titleInputLabel) {
		TitleInputLabel = titleInputLabel;
	}

	public JLabel getCourseInpitLabel() {
		return CourseInpitLabel;
	}

	public void setCourseInpitLabel(JLabel courseInpitLabel) {
		CourseInpitLabel = courseInpitLabel;
	}

	public static String getNewsubjectlabeltext() {
		return NewSubjectLabelText;
	}

	public static String getEditsubjectlabeltext() {
		return EditSubjectLabelText;
	}
	
	
	
	
}
