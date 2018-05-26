package tfm.mvp.pv.presenters;


import tfm.mvp.pv.models.Subject;
import tfm.mvp.pv.models.SubjectDto;

public class SubjectFormPresenter {

	private SubjectDto subjectDto;
	private Subject subject;

	public SubjectFormPresenter() {
		subjectDto = new SubjectDto();
	}

	public void insertNewStudent(String title, int course) {
		
			Subject subjectAux = new Subject(0, title, course);
			subjectDto.insert(subjectAux);
		
	}

	public void updateStudent(String title, int course, int id) {
		
			Subject subjectAux = new Subject(id, title, course);
			subjectDto.update(subjectAux);

		
	}

	public void loadSubject(int id) {
		
			subject = subjectDto.get(id);
		
	}
	public String getSubjectTitle() {
		return subject.getTitle();
	}
	public String getSubjectCourse() {
		return subject.getCourse().toString();
	}
}
