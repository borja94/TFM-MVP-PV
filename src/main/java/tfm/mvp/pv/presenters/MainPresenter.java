package tfm.mvp.pv.presenters;

import tfm.mvp.pv.views.MainView;
import tfm.mvp.pv.models.StudentDao;
import tfm.mvp.pv.models.SubjectDao;
import tfm.mvp.pv.models.TeacherDao;

public class MainPresenter {

	private StudentDao studentDao;
	private SubjectDao subjectDao;
	private TeacherDao teacherDao;
	private MainView mainView;
	
	public MainPresenter() {
		studentDao = new StudentDao();
		subjectDao = new SubjectDao();
		teacherDao = new TeacherDao();		
	}
	
	private int getNumStudents() {
		return studentDao.getAll().size();
	}
	private int getNumTeachers() {
		return teacherDao.getAll().size();
	}
	
	private int getNumSubjects() {
		return subjectDao.getAll().size();
	}
	
	public void loadEntitiesCounters() {
		mainView.SetTeacherButtonText(getNumTeachers());
		mainView.SetStudentButtonText(getNumStudents());
		mainView.SetSubjectButtonText(getNumSubjects());
	}

	public void setMainView(MainView mainView) {
		this.mainView = mainView;
	}
}
