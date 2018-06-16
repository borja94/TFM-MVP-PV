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

import tfm.mvp.pv.presenters.ITeacherFormViewPresenter;

public class TeacherFormView extends JPanel {

	private ITeacherFormViewPresenter iTeacherFormViewPresenter;

	private DefaultListModel<String> unassignedSubjectModel;
	private DefaultListModel<String> assignedSubjectModel;

	private JLabel teacherFormLabel;
	private JButton addSubjectButton;
	private JButton removeSubjectButton;
	private JList<String> assignSubjectCollection;
	private JList<String> unassignSubjectCollection;
	private JTextField nameInput;
	private JTextField surnameInput;
	private JButton saveFormButton;
	private JLabel nameInputLabel;
	private JLabel surnameInputLabel;
	private JLabel unassignSubjectsInputLabel;
	private JLabel assignSubjectInputLabel;
	private JScrollPane unassignSubjectsScrollPane;
	private JScrollPane assignSubjectPane;

	public TeacherFormView(ITeacherFormViewPresenter teacherFormViewPresenter) {
		iTeacherFormViewPresenter = teacherFormViewPresenter;
		iTeacherFormViewPresenter.setTeacherFormView(this);
		
		initComponents();
	}

	private void initComponents() {

		nameInput = new JTextField();
		nameInputLabel = new JLabel();
		surnameInput = new JTextField();
		surnameInputLabel = new JLabel();
		unassignSubjectCollection = new JList<>();
		assignSubjectCollection = new JList<>();
		addSubjectButton = new JButton();
		removeSubjectButton = new JButton();
		unassignSubjectsInputLabel = new JLabel();
		assignSubjectInputLabel = new JLabel();
		saveFormButton = new JButton();
		unassignSubjectsScrollPane = new JScrollPane();
		assignSubjectPane = new JScrollPane();
		teacherFormLabel = new JLabel();
		unassignedSubjectModel = new DefaultListModel<>();
		assignedSubjectModel = new DefaultListModel<>();
		unassignSubjectCollection.setModel(unassignedSubjectModel);
		assignSubjectCollection.setModel(assignedSubjectModel);

		nameInputLabel.setText("Nombre");

		surnameInputLabel.setText("Apellidos");
		teacherFormLabel.setText("Nuevo profesor");

		addSubjectButton.setText("-->");
		addSubjectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				onAddSubjectButtonActionPerformed();
			}
		});

		removeSubjectButton.setText("<--");
		removeSubjectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				onRemoveSubjectButtonActionPerformed();
			}
		});

		unassignSubjectsInputLabel.setText("Asignaturas");

		assignSubjectInputLabel.setText("Asignaturas seleccionadas");

		saveFormButton.setText("Guardar");
		saveFormButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				onSaveFormButtonActionPerformed();
			}
		});

		unassignSubjectsScrollPane.setViewportView(unassignSubjectCollection);

		assignSubjectPane.setViewportView(assignSubjectCollection);

		initComponentsPosition();
		onUpdateSubjectList();
	}

	private void initComponentsPosition() {

		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);

		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout
								.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(teacherFormLabel)
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
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(unassignSubjectsScrollPane, GroupLayout.PREFERRED_SIZE,
														101, GroupLayout.PREFERRED_SIZE)
												.addComponent(unassignSubjectsInputLabel))
										.addGap(18, 18, 18)
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(addSubjectButton).addComponent(removeSubjectButton))
										.addGap(18, 18, 18)
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(assignSubjectPane, GroupLayout.PREFERRED_SIZE, 101,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(assignSubjectInputLabel)))))
						.addGroup(layout.createSequentialGroup().addGap(117, 117, 117).addComponent(saveFormButton)))
				.addContainerGap(114, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addGap(26, 26, 26).addComponent(teacherFormLabel).addGap(18, 18, 18)
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
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(unassignSubjectsInputLabel).addComponent(assignSubjectInputLabel))
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(unassignSubjectsScrollPane, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(assignSubjectPane, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(layout.createSequentialGroup().addGap(95, 95, 95).addComponent(addSubjectButton)
								.addGap(33, 33, 33).addComponent(removeSubjectButton)))
				.addGap(51, 51, 51).addComponent(saveFormButton).addContainerGap(96, Short.MAX_VALUE)));
	}

	private void onUpdateSubjectList() {
		iTeacherFormViewPresenter.updateSubjectList();
	}

	private void onAddSubjectButtonActionPerformed() {
		iTeacherFormViewPresenter.addSubject();
	}

	private void onRemoveSubjectButtonActionPerformed() {
		iTeacherFormViewPresenter.removeSubject();
	}

	private void onSaveFormButtonActionPerformed() {
		iTeacherFormViewPresenter.saveForm();
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

	public String getNameInputValue() {
		return nameInput.getText();
	}

	public String getSurnameInputValue() {
		return surnameInput.getText();
	}

	public void setTeacherFormLabelText(String text) {
		teacherFormLabel.setText(text);
	}

	public void setNameInputValue(String text) {
		nameInput.setText(text);
	}

	public void setSurnameInputValue(String text) {
		surnameInput.setText(text);
	}

	public void clearSubjectModels() {
		assignedSubjectModel.clear();
		unassignedSubjectModel.clear();
	}

	public int getAssignedSubjectCollectionSize() {
		return assignedSubjectModel.size();
	}
}
