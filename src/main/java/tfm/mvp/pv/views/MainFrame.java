package tfm.mvp.pv.views;

import tfm.mvp.pv.presenters.MainPresenter;
import tfm.mvp.pv.views.MainView;

public class MainFrame extends javax.swing.JFrame {

	private SubjectsFrame subjectsFrame;
	private TeachersFrame teacherFrame;
	private StudentsFrame studentsFrame;

	
	public MainFrame() {
		MainPresenter mainPresenter = new MainPresenter();
		MainView menuPanel = new MainView(mainPresenter);
		this.setContentPane(menuPanel);
		initComponents();
		subjectsFrame = new SubjectsFrame(this);
		teacherFrame = new TeachersFrame(this);
		studentsFrame = new StudentsFrame(this);

		menuPanel.getStudentFrameButton().addActionListener(e->studentFrameButtonAction());

		menuPanel.getTeacherFrameButton().addActionListener(e->teacherFrameButtonAction());
		menuPanel.getSubjectFrameButton().addActionListener(e->subjectFrameButtonAction());

	}

	private void initComponents() {
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		this.setSize(500, 500);
	}

	private void teacherFrameButtonAction() {

		this.setVisible(false);
		teacherFrame.setVisible(true);
		dispose();
	}

	private void studentFrameButtonAction() {

		this.setVisible(false);
		studentsFrame.setVisible(true);
		dispose();
	}

	private void subjectFrameButtonAction() {

		this.setVisible(false);
		subjectsFrame.setVisible(true);
		dispose();
	}

}
