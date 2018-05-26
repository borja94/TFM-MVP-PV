package tfm.mvp.pv.presenters;


import tfm.mvp.pv.models.Subject;
import tfm.mvp.pv.models.SubjectDto;

public class SubjectFormPresenter {

	private SubjectDto subjectDto;
	private Subject subject;

	public SubjectFormPresenter() {
		subjectDto = new SubjectDto();
	}

	public void InsertNewStudent(String title, int course) {
		
			Subject subject = new Subject(0, title, course);
			subjectDto.Insert(subject);
		
	}

	public void UpdateStudent(String title, int course, int id) {
		
			Subject subject = new Subject(id, title, course);
			subjectDto.Update(subject);

		
	}

	public void loadSubject(int id) {
		
			subject = subjectDto.Get(id);
		
	}
	public String getSubjectTitle() {
		return subject.getTitle();
	}
	public String getSubjectCourse() {
		return subject.getCourse().toString();
	}
}
