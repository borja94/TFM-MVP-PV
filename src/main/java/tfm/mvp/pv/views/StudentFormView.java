package tfm.mvp.pv.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

	private JButton addSubjectButton;
	private JList<String> assignSubjectCollection;
	private JTextField nameInput;
	private JButton removeSubjectButton;
	private JButton saveFormButton;
	private JLabel studentFormLabel;
	private JTextField surnameInput;
	private JList<String> unassignSubjectCollection;
	private JLabel nameInputLabel;
	private JLabel surnameInputLabel;
	private JScrollPane unassignSubjectPane;
	private JScrollPane assignSubjectPane;
	private StudentsCollectionView studentCollectionView;

	private DefaultListModel<String> unassignedSubjectModel2;
	private DefaultListModel<String> assignedSubjectModel;
	private boolean editMode;
	private int studentSelectedId;
	private StudentFormPresenter studentFormPresenter;
	private static final String NEW_STUDENT_LABEL_TEXT = "Nuevo alumno";
	private static final String EDIT_STUDENT_LABEL_TEXT = "Editar alumno";

	public StudentFormView() {
		studentFormPresenter = new StudentFormPresenter();
		initComponents();
		updateSubjectList(null);
	}

	private void initComponents() {

		studentFormLabel = new JLabel();
		nameInput = new JTextField();
		surnameInput = new JTextField();
		nameInputLabel = new JLabel();
		surnameInputLabel = new JLabel();
		unassignSubjectPane = new JScrollPane();
		unassignSubjectCollection = new JList<>();
		assignSubjectCollection = new JList<>();
		addSubjectButton = new JButton();
		removeSubjectButton = new JButton();
		saveFormButton = new JButton();
		assignSubjectPane = new JScrollPane();

		studentFormLabel.setText(NEW_STUDENT_LABEL_TEXT);

		nameInputLabel.setText("Nombre");

		surnameInputLabel.setText("Apellidos");

		unassignSubjectPane.setViewportView(unassignSubjectCollection);

		assignSubjectPane.setViewportView(assignSubjectCollection);

		addSubjectButton.setText("-->");
		addSubjectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				addSubjectButtonActionPerformed();
			}
		});

		removeSubjectButton.setText("<--");
		removeSubjectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				removeSubjectButtonActionPerformed();
			}
		});

		saveFormButton.setText("Guardar");
		saveFormButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				saveFormButtonActionPerformed();
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
								.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(studentFormLabel)
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(nameInput, GroupLayout.PREFERRED_SIZE, 72,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(nameInputLabel))
										.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(surnameInputLabel).addComponent(surnameInput,
														GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)))
								.addGroup(layout.createSequentialGroup()
										.addComponent(unassignSubjectPane, GroupLayout.PREFERRED_SIZE, 101,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18)
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(addSubjectButton).addComponent(removeSubjectButton))
										.addGap(18, 18, 18).addComponent(assignSubjectPane, GroupLayout.PREFERRED_SIZE, 101,
												GroupLayout.PREFERRED_SIZE))))
						.addGroup(layout.createSequentialGroup().addGap(117, 117, 117)
								.addComponent(saveFormButton)))
						.addContainerGap(114, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(26, 26, 26).addComponent(studentFormLabel)
						.addGap(18, 18, 18)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(nameInputLabel).addComponent(surnameInputLabel))
						.addGap(4, 4, 4)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(nameInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(surnameInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addGap(56, 56, 56)
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(unassignSubjectPane, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(assignSubjectPane, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(layout.createSequentialGroup().addGap(95, 95, 95)
										.addComponent(addSubjectButton).addGap(33, 33, 33)
										.addComponent(removeSubjectButton)))
						.addGap(51, 51, 51).addComponent(saveFormButton).addContainerGap(96, Short.MAX_VALUE)));

	}

	public void newTeacherMode() {
		studentFormLabel.setText(NEW_STUDENT_LABEL_TEXT);
		nameInput.setText("");
		surnameInput.setText("");
		updateSubjectList(null);
		studentSelectedId = 0;
		editMode = false;
	}

	public void editTeacherMode(int id) {
		studentFormLabel.setText(EDIT_STUDENT_LABEL_TEXT);
		studentSelectedId = id;
		editMode = true;
		studentFormPresenter.loadStudent(id);
		nameInput.setText(studentFormPresenter.getStudentName());
		surnameInput.setText(studentFormPresenter.getStudentSurName());
		List<String> subject = new ArrayList<>();
		for (int i = 0; i < studentFormPresenter.getStudentNumSubject(); i++) {
			subject.add(studentFormPresenter.getStudentSubject(i));
		}
		updateSubjectList(subject);
	}

	public void updateSubjectList(List<String> studentSubjectCollection) {
		unassignedSubjectModel2 = new DefaultListModel<>();
		assignedSubjectModel = new DefaultListModel<>();

		for (int i = 0; i < studentFormPresenter.loadSubjects(); i++) {
			String subject = studentFormPresenter.getSubjectByPosition(i);
			if (studentSubjectCollection != null && studentSubjectCollection.contains(subject)) {
				assignedSubjectModel.addElement(subject);
			} else {
				unassignedSubjectModel2.addElement(subject);
			}
		}
		unassignSubjectCollection.setModel(unassignedSubjectModel2);
		assignSubjectCollection.setModel(assignedSubjectModel);
	}

	private void addSubjectButtonActionPerformed() {
		int[] selectedIndex = unassignSubjectCollection.getSelectedIndices();

		for (int i = selectedIndex.length - 1; i >= 0; i--) {
			int index = selectedIndex[i];
			String item = unassignedSubjectModel2.getElementAt(index);
			assignedSubjectModel.addElement(item);
			unassignedSubjectModel2.remove(index);
		}
	}

	private void removeSubjectButtonActionPerformed() {
		int[] selectedIndex = assignSubjectCollection.getSelectedIndices();

		for (int i = selectedIndex.length - 1; i >= 0; i--) {
			int index = selectedIndex[i];
			String item = assignedSubjectModel.getElementAt(index);
			unassignedSubjectModel2.addElement(item);
			assignedSubjectModel.remove(index);
		}
	}

	private void saveFormButtonActionPerformed() {

		String name = nameInput.getText();
		String surname = surnameInput.getText();

		if (!name.isEmpty() && !surname.isEmpty()) {
			if (editMode)
				studentFormPresenter.updateStudent(name, surname, assignedSubjectModel, studentSelectedId);
			else
				studentFormPresenter.insertNewStudent(name, surname, assignedSubjectModel);
			studentCollectionView.updateStudentTableData();
		}
	}

	public void setStudentCollectionView(StudentsCollectionView studentCollectionView) {
		this.studentCollectionView = studentCollectionView;
	}

	public JButton getAddSubjectButton() {
		return addSubjectButton;
	}

	public void setAddSubjectButton(JButton addSubjectButton) {
		this.addSubjectButton = addSubjectButton;
	}

	public JList<String> getAssignSubjectCollection() {
		return assignSubjectCollection;
	}

	public void setAssignSubjectCollection(JList<String> assignSubjectCollection) {
		this.assignSubjectCollection = assignSubjectCollection;
	}

	public JTextField getNameInput() {
		return nameInput;
	}

	public void setNameInput(JTextField nameInput) {
		this.nameInput = nameInput;
	}

	public JButton getRemoveSubjectButton() {
		return removeSubjectButton;
	}

	public void setRemoveSubjectButton(JButton removeSubjectButton) {
		this.removeSubjectButton = removeSubjectButton;
	}

	public JButton getSaveFormButton() {
		return saveFormButton;
	}

	public void setSaveFormButton(JButton saveFormButton) {
		this.saveFormButton = saveFormButton;
	}

	public JLabel getStudentFormLabel() {
		return studentFormLabel;
	}

	public void setStudentFormLabel(JLabel studentFormLabel) {
		this.studentFormLabel = studentFormLabel;
	}

	public JTextField getSurnameInput() {
		return surnameInput;
	}

	public void setSurnameInput(JTextField surnameInput) {
		this.surnameInput = surnameInput;
	}

	public JList<String> getUnassignSubjectCollection() {
		return unassignSubjectCollection;
	}

	public void setUnassignSubjectCollection(JList<String> unassignSubjectCollection) {
		this.unassignSubjectCollection = unassignSubjectCollection;
	}

	public JLabel getNameInputLabel() {
		return nameInputLabel;
	}

	public void setNameInputLabel(JLabel nameInputLabel) {
		this.nameInputLabel = nameInputLabel;
	}

	public JLabel getSurnameInputLabel() {
		return surnameInputLabel;
	}

	public void setSurnameInputLabel(JLabel surnameInputLabel) {
		this.surnameInputLabel = surnameInputLabel;
	}

	public JScrollPane getUnassignSubjectPane() {
		return unassignSubjectPane;
	}

	public void setUnassignSubjectPane(JScrollPane unassignSubjectPane) {
		this.unassignSubjectPane = unassignSubjectPane;
	}

	public JScrollPane getAssignSubjectPane() {
		return assignSubjectPane;
	}

	public void setAssignSubjectPane(JScrollPane assignSubjectPane) {
		this.assignSubjectPane = assignSubjectPane;
	}

	public DefaultListModel<String> getUnassignedSubjectModel2() {
		return unassignedSubjectModel2;
	}

	public void setUnassignedSubjectModel2(DefaultListModel<String> unassignedSubjectModel2) {
		this.unassignedSubjectModel2 = unassignedSubjectModel2;
	}

	public DefaultListModel<String> getAssignedSubjectModel() {
		return assignedSubjectModel;
	}

	public void setAssignedSubjectModel(DefaultListModel<String> assignedSubjectModel) {
		this.assignedSubjectModel = assignedSubjectModel;
	}

	public static String getNewstudentlabeltext() {
		return NEW_STUDENT_LABEL_TEXT;
	}

	public static String getEditstudentlabeltext() {
		return EDIT_STUDENT_LABEL_TEXT;
	}

	
	
}
