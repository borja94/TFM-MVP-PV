package tfm.mvp.pv.views;

import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import tfm.mvp.pv.presenters.StudentFormPresenter;

public class StudentFormView extends JPanel {

	private JButton AddSubjectButton;
	private JList<String> AssignSubjectCollection;
	private JTextField NameInput;
	private JButton RemoveSubjectButton;
	private JButton SaveFormButton;
	private JLabel StudentFormLabel;
	private JTextField SurnameInput;
	private JList<String> UnassignSubjectCollection;
	private JLabel NameInputLabel;
	private JLabel SurnameInputLabel;
	private JScrollPane UnassignSubjectPane;
	private JScrollPane AssignSubjectPane;
	private StudentsCollectionView studentCollectionView;

	private DefaultListModel<String> UnassignedSubjectModel2;
	private DefaultListModel<String> AssignedSubjectModel;
	private boolean EditMode;
	private int StudentSelectedId;
	private StudentFormPresenter studentFormPresenter;
	private static final String NewStudentLabelText = "Nuevo alumno";
	private static final String EditStudentLabelText = "Editar alumno";

	public StudentFormView() {
		studentFormPresenter = new StudentFormPresenter();
		initComponents();
		UpdateSubjectList(null);
	}

	private void initComponents() {

		StudentFormLabel = new JLabel();
		NameInput = new JTextField();
		SurnameInput = new JTextField();
		NameInputLabel = new JLabel();
		SurnameInputLabel = new JLabel();
		UnassignSubjectPane = new JScrollPane();
		UnassignSubjectCollection = new JList<>();
		AssignSubjectCollection = new JList<>();
		AddSubjectButton = new JButton();
		RemoveSubjectButton = new JButton();
		SaveFormButton = new JButton();
		AssignSubjectPane = new JScrollPane();

		StudentFormLabel.setText("Añadir alumno");

		NameInputLabel.setText("Nombre");

		SurnameInputLabel.setText("Apellidos");

		UnassignSubjectPane.setViewportView(UnassignSubjectCollection);

		AssignSubjectPane.setViewportView(AssignSubjectCollection);

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

		SaveFormButton.setText("Guardar");
		SaveFormButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				SaveFormButtonActionPerformed(evt);
			}
		});

		initComponentsPosition();
	}

	private void initComponentsPosition() {

		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGroup(layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout
								.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(StudentFormLabel)
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
										.addComponent(UnassignSubjectPane, GroupLayout.PREFERRED_SIZE, 101,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18)
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(AddSubjectButton).addComponent(RemoveSubjectButton))
										.addGap(18, 18, 18).addComponent(AssignSubjectPane, GroupLayout.PREFERRED_SIZE, 101,
												GroupLayout.PREFERRED_SIZE))))
						.addGroup(layout.createSequentialGroup().addGap(117, 117, 117)
								.addComponent(SaveFormButton)))
						.addContainerGap(114, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(26, 26, 26).addComponent(StudentFormLabel)
						.addGap(18, 18, 18)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(NameInputLabel).addComponent(SurnameInputLabel))
						.addGap(4, 4, 4)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(NameInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(SurnameInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addGap(56, 56, 56)
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(UnassignSubjectPane, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(AssignSubjectPane, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(layout.createSequentialGroup().addGap(95, 95, 95)
										.addComponent(AddSubjectButton).addGap(33, 33, 33)
										.addComponent(RemoveSubjectButton)))
						.addGap(51, 51, 51).addComponent(SaveFormButton).addContainerGap(96, Short.MAX_VALUE)));

	}

	public void NewTeacherMode() {
		StudentFormLabel.setText(NewStudentLabelText);
		NameInput.setText("");
		SurnameInput.setText("");
		UpdateSubjectList(null);
		StudentSelectedId = 0;
		EditMode = false;
	}

	public void EditTeacherMode(int id) {
		StudentFormLabel.setText(EditStudentLabelText);
		StudentSelectedId = id;
		EditMode = true;
		studentFormPresenter.loadStudent(id);
		NameInput.setText(studentFormPresenter.getStudentName());
		SurnameInput.setText(studentFormPresenter.getStudentSurName());
		List<String> subject = new ArrayList<>();
		for (int i = 0; i < studentFormPresenter.getStudentNumSubject(); i++) {
			subject.add(studentFormPresenter.getStudentSubject(i));
		}
		UpdateSubjectList(subject);
	}

	public void UpdateSubjectList(List<String> studentSubjectCollection) {
		UnassignedSubjectModel2 = new DefaultListModel<>();
		AssignedSubjectModel = new DefaultListModel<>();

		for (int i = 0; i < studentFormPresenter.loadSubjects(); i++) {
			String subject = studentFormPresenter.getSubjectByPosition(i);
			if (studentSubjectCollection != null && studentSubjectCollection.contains(subject.toString())) {
				AssignedSubjectModel.addElement(subject);
			} else {
				UnassignedSubjectModel2.addElement(subject);
			}
		}
		UnassignSubjectCollection.setModel(UnassignedSubjectModel2);
		AssignSubjectCollection.setModel(AssignedSubjectModel);
	}

	private void AddSubjectButtonActionPerformed(java.awt.event.ActionEvent evt) {
		int[] selectedIndex = UnassignSubjectCollection.getSelectedIndices();

		for (int i = selectedIndex.length - 1; i >= 0; i--) {
			int index = selectedIndex[i];
			String item = UnassignedSubjectModel2.getElementAt(index).toString();
			AssignedSubjectModel.addElement(item);
			UnassignedSubjectModel2.remove(index);
		}
	}

	private void RemoveSubjectButtonActionPerformed(java.awt.event.ActionEvent evt) {
		int[] selectedIndex = AssignSubjectCollection.getSelectedIndices();

		for (int i = selectedIndex.length - 1; i >= 0; i--) {
			int index = selectedIndex[i];
			String item = AssignedSubjectModel.getElementAt(index).toString();
			UnassignedSubjectModel2.addElement(item);
			AssignedSubjectModel.remove(index);
		}
	}

	private void SaveFormButtonActionPerformed(java.awt.event.ActionEvent evt) {

		String name = NameInput.getText();
		String surname = SurnameInput.getText();

		if (!name.isEmpty() && !surname.isEmpty()) {
			if (EditMode)
				studentFormPresenter.UpdateStudent(name, surname, AssignedSubjectModel, StudentSelectedId);
			else
				studentFormPresenter.InsertNewStudent(name, surname, AssignedSubjectModel);
			studentCollectionView.UpdateStudentTableData();
		}
	}

	public void setStudentCollectionView(StudentsCollectionView studentCollectionView) {
		this.studentCollectionView = studentCollectionView;
	}

	public JButton getAddSubjectButton() {
		return AddSubjectButton;
	}

	public void setAddSubjectButton(JButton addSubjectButton) {
		AddSubjectButton = addSubjectButton;
	}

	public JList<String> getAssignSubjectCollection() {
		return AssignSubjectCollection;
	}

	public void setAssignSubjectCollection(JList<String> assignSubjectCollection) {
		AssignSubjectCollection = assignSubjectCollection;
	}

	public JTextField getNameInput() {
		return NameInput;
	}

	public void setNameInput(JTextField nameInput) {
		NameInput = nameInput;
	}

	public JButton getRemoveSubjectButton() {
		return RemoveSubjectButton;
	}

	public void setRemoveSubjectButton(JButton removeSubjectButton) {
		RemoveSubjectButton = removeSubjectButton;
	}

	public JButton getSaveFormButton() {
		return SaveFormButton;
	}

	public void setSaveFormButton(JButton saveFormButton) {
		SaveFormButton = saveFormButton;
	}

	public JLabel getStudentFormLabel() {
		return StudentFormLabel;
	}

	public void setStudentFormLabel(JLabel studentFormLabel) {
		StudentFormLabel = studentFormLabel;
	}

	public JTextField getSurnameInput() {
		return SurnameInput;
	}

	public void setSurnameInput(JTextField surnameInput) {
		SurnameInput = surnameInput;
	}

	public JList<String> getUnassignSubjectCollection() {
		return UnassignSubjectCollection;
	}

	public void setUnassignSubjectCollection(JList<String> unassignSubjectCollection) {
		UnassignSubjectCollection = unassignSubjectCollection;
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

	public JScrollPane getUnassignSubjectPane() {
		return UnassignSubjectPane;
	}

	public void setUnassignSubjectPane(JScrollPane unassignSubjectPane) {
		UnassignSubjectPane = unassignSubjectPane;
	}

	public JScrollPane getAssignSubjectPane() {
		return AssignSubjectPane;
	}

	public void setAssignSubjectPane(JScrollPane assignSubjectPane) {
		AssignSubjectPane = assignSubjectPane;
	}

	public DefaultListModel<String> getUnassignedSubjectModel2() {
		return UnassignedSubjectModel2;
	}

	public void setUnassignedSubjectModel2(DefaultListModel<String> unassignedSubjectModel2) {
		UnassignedSubjectModel2 = unassignedSubjectModel2;
	}

	public DefaultListModel<String> getAssignedSubjectModel() {
		return AssignedSubjectModel;
	}

	public void setAssignedSubjectModel(DefaultListModel<String> assignedSubjectModel) {
		AssignedSubjectModel = assignedSubjectModel;
	}

	public static String getNewstudentlabeltext() {
		return NewStudentLabelText;
	}

	public static String getEditstudentlabeltext() {
		return EditStudentLabelText;
	}

	
	
}
