package tfm.mvp.pv.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import tfm.mvp.pv.presenters.IStudentFormPresenter;
import tfm.mvp.pv.presenters.IStudentFormViewPresenter;
import tfm.mvp.pv.presenters.StudentFormPresenter;
import tfm.mvp.pv.presenters.StudentsCollectionPresenter;

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

	private DefaultListModel<String> unassignedSubjectModel;
	private DefaultListModel<String> assignedSubjectModel;

	private IStudentFormViewPresenter iStudentFormPresenter;

	public StudentFormView(IStudentFormViewPresenter studentFormViewPresenter) {
		initComponents();
		iStudentFormPresenter = studentFormViewPresenter;
		iStudentFormPresenter.setStudentFormView(this);
		onUpdateSubjectList();
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
		unassignedSubjectModel = new DefaultListModel<>();
		assignedSubjectModel = new DefaultListModel<>();
		unassignSubjectCollection.setModel(unassignedSubjectModel);
		assignSubjectCollection.setModel(assignedSubjectModel);

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
				onSaveFormButtonActionPerformed();
			}
		});

		initComponentsPosition();
	}

	private void initComponentsPosition() {

		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
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
										.addGap(18, 18, 18).addComponent(assignSubjectPane, GroupLayout.PREFERRED_SIZE,
												101, GroupLayout.PREFERRED_SIZE))))
						.addGroup(layout.createSequentialGroup().addGap(117, 117, 117).addComponent(saveFormButton)))
				.addContainerGap(114, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addGap(26, 26, 26).addComponent(studentFormLabel).addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(nameInputLabel)
						.addComponent(surnameInputLabel))
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
						.addGroup(layout.createSequentialGroup().addGap(95, 95, 95).addComponent(addSubjectButton)
								.addGap(33, 33, 33).addComponent(removeSubjectButton)))
				.addGap(51, 51, 51).addComponent(saveFormButton).addContainerGap(96, Short.MAX_VALUE)));

	}

	public void onUpdateSubjectList() {
		iStudentFormPresenter.updateSubjectList(null);
	}

	private void addSubjectButtonActionPerformed() {
		int[] selectedIndex = unassignSubjectCollection.getSelectedIndices();

		for (int i = selectedIndex.length - 1; i >= 0; i--) {
			int index = selectedIndex[i];
			String item = unassignedSubjectModel.getElementAt(index);
			assignedSubjectModel.addElement(item);
			unassignedSubjectModel.remove(index);
		}
	}

	private void removeSubjectButtonActionPerformed() {
		int[] selectedIndex = assignSubjectCollection.getSelectedIndices();

		for (int i = selectedIndex.length - 1; i >= 0; i--) {
			int index = selectedIndex[i];
			String item = assignedSubjectModel.getElementAt(index);
			unassignedSubjectModel.addElement(item);
			assignedSubjectModel.remove(index);
		}
	}

	private void onSaveFormButtonActionPerformed() {

		iStudentFormPresenter.saveForm();
	}

	public void SetLabelFormText(String text) {
		studentFormLabel.setText(text);
	}

	public void cleanSubjecModels() {
		assignedSubjectModel.clear();
		unassignedSubjectModel.clear();
	}

	public int[] getUnassignSubjectSelectedIndices() {
		return unassignSubjectCollection.getSelectedIndices();
	}

	public String getUnassignedSubjectElementAt(int id) {
		return unassignedSubjectModel.getElementAt(id);
	}

	public void addAssignedSubjectElement(String element) {
		assignedSubjectModel.addElement(element);
	}

	public void removeAssignedSubjectElement(int id) {
		assignedSubjectModel.remove(id);
	}

	public int[] getAssignedSubjectIndices() {
		return assignSubjectCollection.getSelectedIndices();
	}

	public String getAssignedSubjectElementAt(int id) {
		return assignedSubjectModel.getElementAt(id);
	}

	public void addUnassignedSubjectElement(String element) {
		unassignedSubjectModel.addElement(element);
	}

	public void removeUnassignedSubjectElement(int id) {
		unassignedSubjectModel.remove(id);
	}

	public void setNameInputValue(String name) {
		nameInput.setText(name);
	}

	public void setSurnameInputValue(String surname) {
		surnameInput.setText(surname);
	}

	public String getNameInputValue() {
		return nameInput.getText();
	}

	public String getSurnameInputValue() {
		return surnameInput.getText();
	}

	public int getAssignedSubjectCollectionSize() {
		return assignedSubjectModel.size();
	}
}
