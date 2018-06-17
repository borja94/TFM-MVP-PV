package tfm.mvp.pv.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TeacherDao extends Dao {

	private static final String COURSE_COLUMN = "COURSE";
	private static final String ID_COLUMN = "ID";
	private static final String TITLE_COLUMN = "TITLE";
	private static final String SUBJECT_ID_COLUMN = "SUBJECT_ID";
	private static final String SURNAME_COLUMN = "SURNAME";
	private static final String NAME_COLUMN = "NAME";

	public TeacherDao() {
		super();
	}

	public int insert(Teacher teacher) {
		Connection conexion = null;
		ResultSet result = null;
		PreparedStatement sentencia1 = null;
		int id = -1;
		try {

			conexion = basicDataSource.getConnection();

			String sql = "INSERT INTO TEACHER (NAME,SURNAME) VALUES(?,?)";
			sentencia1 = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia1.setString(1, teacher.getName());
			sentencia1.setString(2, teacher.getSurname());

			sentencia1.executeUpdate();
			result = sentencia1.getGeneratedKeys();

			if (result.next()) {
				id = result.getInt(1);
			}

			for (Subject subject : teacher.getSubjectCollection()) {
				insertSubjectTeacher(id, subject.getId());
			}

		} catch (SQLException ex) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);

		} finally {

			if (result != null)
				closeResultSet(result);
			if (sentencia1 != null)
				closePreparedStatement(sentencia1);
			if (conexion != null)
				closeConnection(conexion);
		}

		return id;
	}

	public void update(Teacher teacher) {
		Connection conexion = null;
		PreparedStatement sentencia1 = null;
		try {

			conexion = basicDataSource.getConnection();

			String sql = "UPDATE TEACHER  SET NAME = ?, SURNAME = ? WHERE ID= ?";
			sentencia1 = conexion.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE,
					Statement.RETURN_GENERATED_KEYS);
			sentencia1.setString(1, teacher.getName());
			sentencia1.setString(2, teacher.getSurname());
			sentencia1.setInt(3, teacher.getId());

			removeAllTeacherSubjects(teacher.getId());
			for (Subject subject : teacher.getSubjectCollection()) {
				insertSubjectTeacher(teacher.getId(), subject.getId());
			}

			sentencia1.executeUpdate();

			sentencia1.close();
		} catch (SQLException ex) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
		} finally {
			if (sentencia1 != null)
				closePreparedStatement(sentencia1);
			if (conexion != null)
				closeConnection(conexion);
		}
	}

	public List<Teacher> getAll() {

		List<Teacher> result = new ArrayList<>();
		Connection conexion = null;
		Statement sentencia = null;
		ResultSet rs = null;

		try {
			conexion = basicDataSource.getConnection();

			sentencia = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = sentencia.executeQuery("SELECT * FROM TEACHER ");

			rs.afterLast();
			while (rs.previous()) {

				Teacher teacher = new Teacher(rs.getInt(ID_COLUMN), rs.getString(NAME_COLUMN),
						rs.getString(SURNAME_COLUMN));
				SubjectDao subjectDao = new SubjectDao();
				teacher.setSubjectCollection(subjectDao.getByTeacher(teacher.getId()));

				result.add(teacher);

			}
		} catch (SQLException ex) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
		} finally {
			if (rs != null)
				closeResultSet(rs);
			if (sentencia != null)
				closeStatement(sentencia);
			if (conexion != null)
				closeConnection(conexion);
		}

		return result;
	}

	public Teacher get(int id) {
		Connection conexion = null;
		Teacher teacher = null;
		PreparedStatement sentencia1 = null;
		ResultSet rs = null;
		try {
			conexion = basicDataSource.getConnection();

			String sql = "SELECT T.* , S.ID AS SUBJECT_ID , S.TITLE , S.COURSE FROM TEACHER T "
					+ "LEFT JOIN TEACHER_SUBJECT TS " + "ON T.ID = TS.ID_TEACHER " + "LEFT JOIN SUBJECT S "
					+ "ON TS.ID_SUBJECT = S.ID " + " WHERE T.ID = ?";
			sentencia1 = conexion.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			sentencia1.setInt(1, id);

			rs = sentencia1.executeQuery();
			teacher = new Teacher();
			List<Subject> subjectCollection = new ArrayList<>();
			if (rs.next()) {
				teacher = new Teacher(rs.getInt(ID_COLUMN), rs.getString(NAME_COLUMN), rs.getString(SURNAME_COLUMN));

				do {
					if (rs.getString(TITLE_COLUMN) != null) {
						subjectCollection.add(new Subject(rs.getInt(SUBJECT_ID_COLUMN), rs.getString(TITLE_COLUMN),
								rs.getInt(COURSE_COLUMN)));
					}
				} while (rs.next());
			}
			teacher.setSubjectCollection(subjectCollection);

			sentencia1.close();
		} catch (SQLException ex) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
		} finally {
			if (rs != null)
				closeResultSet(rs);
			if (sentencia1 != null)
				closePreparedStatement(sentencia1);
			if (conexion != null)
				closeConnection(conexion);
		}

		return teacher;
	}

	public void remove(int id) {
		Connection conexion = null;
		PreparedStatement sentencia1 = null;
		try {
			conexion = basicDataSource.getConnection();

			String sql = "DELETE FROM  TEACHER WHERE ID = ?";
			sentencia1 = conexion.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE,
					Statement.RETURN_GENERATED_KEYS);
			sentencia1.setInt(1, id);

			sentencia1.executeUpdate();
			sentencia1.close();

		} catch (SQLException ex) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
		} finally {
			if (sentencia1 != null)
				closePreparedStatement(sentencia1);
			if (conexion != null)
				closeConnection(conexion);
		}

	}

	public void removeAllTeacherSubjects(int idTeacher) {
		Connection conexion = null;
		PreparedStatement sentencia1 = null;
		try {
			conexion = basicDataSource.getConnection();
			String sql = "DELETE FROM  TEACHER_SUBJECT WHERE ID_TEACHER = ?";
			sentencia1 = conexion.prepareStatement(sql);
			sentencia1.setInt(1, idTeacher);

			sentencia1.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
		} finally {
			if (sentencia1 != null)
				closePreparedStatement(sentencia1);
			if (conexion != null)
				closeConnection(conexion);
		}

	}

	public void insertSubjectTeacher(int idTeacher, int idSubject) {

		Connection conexion = null;
		PreparedStatement sentencia1 = null;
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver");
			conexion = DriverManager.getConnection("jdbc:derby://localhost:1527/TFM", "APP", null);
			String sql = "INSERT INTO TEACHER_SUBJECT (ID_SUBJECT,ID_TEACHER) VALUES(?,?)";
			sentencia1 = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia1.setInt(1, idSubject);
			sentencia1.setInt(2, idTeacher);

			sentencia1.executeUpdate();
		} catch (ClassNotFoundException | SQLException ex) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
		} finally {
			if (sentencia1 != null)
				closePreparedStatement(sentencia1);
			if (conexion != null)
				closeConnection(conexion);
		}

	}
}
