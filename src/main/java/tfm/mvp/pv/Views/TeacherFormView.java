package tfm.mvp.pv.Views;

import java.awt.event.ActionEvent;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import tfm.mvp.pv.Presenters.TeacherFormPresenter;
import tfm.mvp.pv.Presenters.TeachersCollectionPresenter;

public class TeacherFormView extends JPanel {

	private TeacherFormPresenter teacherFormPresenter;

	private DefaultListModel<String> UnassignedSubjectModel;
	private DefaultListModel<String> AssignedSubjectModel;

	private JLabel TeacherFormLabel;
	private JButton AddSubjectButton;
	private JButton RemoveSubjectButton;
	private JList<String> AssignSubjectCollection;
	private JList<String> UnassignSubjectCollection;
	private JTextField NameInput;
	private JTextField SurnameInput;
	private JButton SaveFormButton;
	private JLabel NameInputLabel;
	private JLabel SurnameInputLabel;
	private JLabel UnassignSubjectsInputLabel;
	private JLabel AssignSubjectInputLabel;
	private JScrollPane UnassignSubjectsScrollPane;
	private JScrollPane AssignSubjectPane;

	public TeacherFormView() {
		initComponents();
		teacherFormPresenter = new TeacherFormPresenter(this);
		UpdateSubjectList();
	}

	private void initComponents() {

		NameInput = new JTextField();
		NameInputLabel = new JLabel();
		SurnameInput = new JTextField();
		SurnameInputLabel = new JLabel();
		UnassignSubjectCollection = new JList<>();
		AssignSubjectCollection = new JList<>();
		AddSubjectButton = new JButton();
		RemoveSubjectButton = new JButton();
		UnassignSubjectsInputLabel = new JLabel();
		AssignSubjectInputLabel = new JLabel();
		SaveFormButton = new JButton();
		UnassignSubjectsScrollPane = new JScrollPane();
		AssignSubjectPane = new JScrollPane();
		TeacherFormLabel = new JLabel();

		NameInputLabel.setText("Nombre");

		SurnameInputLabel.setText("Apellidos");
		TeacherFormLabel.setText("Nuevo profesor");

		AddSubjectButton.setText("-->");
		AddSubjectButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				AddSubjectButtonActionPerformed(evt);
			}
		});

		RemoveSubjectButton.setText("<--");
		RemoveSubjectButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				RemoveSubjectButtonActionPerformed(evt);
			}
		});

		UnassignSubjectsInputLabel.setText("Asignaturas");

		AssignSubjectInputLabel.setText("Asignaturas seleccionadas");

		SaveFormButton.setText("Guardar");
		SaveFormButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				SaveFormButtonActionPerformed(evt);
			}
		});

		UnassignSubjectsScrollPane.setViewportView(UnassignSubjectCollection);

		AssignSubjectPane.setViewportView(AssignSubjectCollection);

		initComponentsPosition();
	}

	public void initComponentsPosition() {

		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);

		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout
								.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(TeacherFormLabel)
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(NameInput, GroupLayout.PREFERRED_SIZE, 72,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(NameInputLabel))
										.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(SurnameInputLabel).addComponent(SurnameInput,
														GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)))
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(UnassignSubjectsScrollPane, GroupLayout.PREFERRED_SIZE,
														101, GroupLayout.PREFERRED_SIZE)
												.addComponent(UnassignSubjectsInputLabel))
										.addGap(18, 18, 18)
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(AddSubjectButton).addComponent(RemoveSubjectButton))
										.addGap(18, 18, 18)
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(AssignSubjectPane, GroupLayout.PREFERRED_SIZE, 101,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(AssignSubjectInputLabel)))))
						.addGroup(layout.createSequentialGroup().addGap(117, 117, 117).addComponent(SaveFormButton)))
				.addContainerGap(114, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addGap(26, 26, 26).addComponent(TeacherFormLabel).addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(NameInputLabel)
						.addComponent(SurnameInputLabel))
				.addGap(4, 4, 4)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(NameInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(SurnameInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(56, 56, 56)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(UnassignSubjectsInputLabel).addComponent(AssignSubjectInputLabel))
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(UnassignSubjectsScrollPane, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(AssignSubjectPane, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(layout.createSequentialGroup().addGap(95, 95, 95).addComponent(AddSubjectButton)
								.addGap(33, 33, 33).addComponent(RemoveSubjectButton)))
				.addGap(51, 51, 51).addComponent(SaveFormButton).addContainerGap(96, Short.MAX_VALUE)));
	}

	public void NewTeacherMode() {
		teacherFormPresenter.NotifyNewTeacherMode();
	}
//!!!!!!!!!!!!!!!!!!!!!!!!!!!
	public void EditTeacherMode(int id) {
		teacherFormPresenter.NotifyEditTeacherMode(id);
	}

	private void UpdateSubjectList() {
		teacherFormPresenter.NotifyUpdateSubjectList();
	}

	private void AddSubjectButtonActionPerformed(ActionEvent evt) {
		teacherFormPresenter.NotifyAddSubject();
	}

	private void RemoveSubjectButtonActionPerformed(ActionEvent evt) {
		teacherFormPresenter.NotifyRemoveSubject();
	}

	private void SaveFormButtonActionPerformed(ActionEvent evt) {

		teacherFormPresenter.NotifySaveForm();
	}

	public TeacherFormPresenter getTeacherFormPresenter() {
		return teacherFormPresenter;
	}

	public void setTeacherFormPresenter(TeacherFormPresenter teacherFormPresenter) {
		this.teacherFormPresenter = teacherFormPresenter;
	}

	public DefaultListModel<String> getUnassignedSubjectModel() {
		return UnassignedSubjectModel;
	}

	public void setUnassignedSubjectModel(DefaultListModel<String> unassignedSubjectModel) {
		UnassignedSubjectModel = unassignedSubjectModel;
	}

	public DefaultListModel<String> getAssignedSubjectModel() {
		return AssignedSubjectModel;
	}

	public void setAssignedSubjectModel(DefaultListModel<String> assignedSubjectModel) {
		AssignedSubjectModel = assignedSubjectModel;
	}

	public JLabel getTeacherFormLabel() {
		return TeacherFormLabel;
	}

	public void setTeacherFormLabel(JLabel teacherFormLabel) {
		TeacherFormLabel = teacherFormLabel;
	}

	public JButton getAddSubjectButton() {
		return AddSubjectButton;
	}

	public void setAddSubjectButton(JButton addSubjectButton) {
		AddSubjectButton = addSubjectButton;
	}

	public JButton getRemoveSubjectButton() {
		return RemoveSubjectButton;
	}

	public void setRemoveSubjectButton(JButton removeSubjectButton) {
		RemoveSubjectButton = removeSubjectButton;
	}

	public JList<String> getAssignSubjectCollection() {
		return AssignSubjectCollection;
	}

	public void setAssignSubjectCollection(JList<String> assignSubjectCollection) {
		AssignSubjectCollection = assignSubjectCollection;
	}

	public JList<String> getUnassignSubjectCollection() {
		return UnassignSubjectCollection;
	}

	public void setUnassignSubjectCollection(JList<String> unassignSubjectCollection) {
		UnassignSubjectCollection = unassignSubjectCollection;
	}

	public JTextField getNameInput() {
		return NameInput;
	}

	public void setNameInput(JTextField nameInput) {
		NameInput = nameInput;
	}

	public JTextField getSurnameInput() {
		return SurnameInput;
	}

	public void setSurnameInput(JTextField surnameInput) {
		SurnameInput = surnameInput;
	}

	public JButton getSaveFormButton() {
		return SaveFormButton;
	}

	public void setSaveFormButton(JButton saveFormButton) {
		SaveFormButton = saveFormButton;
	}

	public JLabel getNameInputLabel() {
		return NameInputLabel;
	}

	public void setNameInputLabel(JLabel nameInputLabel) {
		NameInputLabel = nameInputLabel;
	}

	public JLabel getSurnameInputLabel() {
		return SurnameInputLabel;
	}

	public void setSurnameInputLabel(JLabel surnameInputLabel) {
		SurnameInputLabel = surnameInputLabel;
	}

	public JLabel getUnassignSubjectsInputLabel() {
		return UnassignSubjectsInputLabel;
	}

	public void setUnassignSubjectsInputLabel(JLabel unassignSubjectsInputLabel) {
		UnassignSubjectsInputLabel = unassignSubjectsInputLabel;
	}

	public JLabel getAssignSubjectInputLabel() {
		return AssignSubjectInputLabel;
	}

	public void setAssignSubjectInputLabel(JLabel assignSubjectInputLabel) {
		AssignSubjectInputLabel = assignSubjectInputLabel;
	}

	public JScrollPane getUnassignSubjectsScrollPane() {
		return UnassignSubjectsScrollPane;
	}

	public void setUnassignSubjectsScrollPane(JScrollPane unassignSubjectsScrollPane) {
		UnassignSubjectsScrollPane = unassignSubjectsScrollPane;
	}

	public JScrollPane getAssignSubjectPane() {
		return AssignSubjectPane;
	}

	public void setAssignSubjectPane(JScrollPane assignSubjectPane) {
		AssignSubjectPane = assignSubjectPane;
	}
	
	public void setTeacherCollectionPresenter(TeachersCollectionPresenter teacherCollectionPresenter) {
		this.teacherFormPresenter.setTeachetCollectionPresenter(teacherCollectionPresenter);
	}

}
