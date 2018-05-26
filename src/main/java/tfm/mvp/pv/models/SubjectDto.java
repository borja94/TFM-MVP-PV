package tfm.mvp.pv.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SubjectDto extends Dto {

	private static final String COURSE_COLUMN = "COURSE";
	private static final String ID_COLUMN = "ID";
	private static final String TITLE_COLUMN = "TITLE";

	public SubjectDto() {
		super();
	}

	public void insert(Subject subject) {
		Connection connection = null;
		PreparedStatement sentencia1 = null;

		try {
			connection = basicDataSource.getConnection();

			String sql = "INSERT INTO SUBJECT (TITLE,COURSE) VALUES(?,?)";
			sentencia1 = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE,
					Statement.RETURN_GENERATED_KEYS);
			sentencia1.setString(1, subject.getTitle());
			sentencia1.setInt(2, subject.getCourse());

			sentencia1.executeUpdate();

			sentencia1.close();
		} catch (SQLException ex) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);

		} finally {

			if (sentencia1 != null)
				closePreparedStatement(sentencia1);
			if (connection != null)
				closeConnection(connection);
		}

	}

	public List<Subject> getAll() {

		List<Subject> result = new ArrayList<>();
		Connection connection = null;
		Statement sentencia = null;
		ResultSet rs = null;
		try {
			connection = basicDataSource.getConnection();

			sentencia = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = sentencia.executeQuery("SELECT * FROM SUBJECT ");

			while (rs.next()) {
				result.add(new Subject(rs.getInt(ID_COLUMN), rs.getString(TITLE_COLUMN), rs.getInt(COURSE_COLUMN)));
			}
		} catch (SQLException ex) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
		} finally {
			if (rs != null)
				closeResultSet(rs);
			if (sentencia != null)
				closeStatement(sentencia);
			if (connection != null)
				closeConnection(connection);
		}

		return result;
	}

	public List<Subject> getByTeacher(int idTeacher) {
		List<Subject> result = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rsSuebjects = null;

		try {
			connection = basicDataSource.getConnection();

			String sql = "SELECT * FROM SUBJECT S " + "INNER JOIN TEACHER_SUBJECT TS " + "ON S.ID = TS.ID_SUBJECT "
					+ "WHERE TS.ID_TEACHER = ?";

			statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			statement.setInt(1, idTeacher);

			rsSuebjects = statement.executeQuery();
			while (rsSuebjects.next()) {
				result.add(new Subject(rsSuebjects.getInt(ID_COLUMN), rsSuebjects.getString(TITLE_COLUMN),
						rsSuebjects.getInt(COURSE_COLUMN)));
			}

		} catch (SQLException ex) {

			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);

		} finally {
			if (rsSuebjects != null)
				closeResultSet(rsSuebjects);
			if (statement != null)
				closePreparedStatement(statement);
			if (connection != null)
				closeConnection(connection);
		}
		return result;
	}

	public List<Subject> getByStudent(int idStudent) {
		List<Subject> result = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rsSuebjects = null;

		try {
			connection = basicDataSource.getConnection();

			String sql = "SELECT * FROM SUBJECT S " + "INNER JOIN STUDENT_SUBJECT SS " + "ON S.ID = SS.ID_SUBJECT "
					+ "WHERE SS.ID_STUDENT = ?";

			statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			statement.setInt(1, idStudent);

			rsSuebjects = statement.executeQuery();
			while (rsSuebjects.next()) {
				result.add(new Subject(rsSuebjects.getInt(ID_COLUMN), rsSuebjects.getString(TITLE_COLUMN),
						rsSuebjects.getInt(COURSE_COLUMN)));
			}

		} catch (SQLException ex) {

			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);

		} finally {
			if (rsSuebjects != null)
				closeResultSet(rsSuebjects);
			if (statement != null)
				closePreparedStatement(statement);
			if (connection != null)
				closeConnection(connection);
		}
		return result;
	}

	public Subject get(int id) {
		Connection connection = null;
		Subject subject = null;
		ResultSet rs = null;
		PreparedStatement sentencia1 = null;
		try {
			connection = basicDataSource.getConnection();

			String sql = "SELECT * FROM SUBJECT WHERE ID = ?";
			sentencia1 = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			sentencia1.setInt(1, id);

			rs = sentencia1.executeQuery();
			subject = new Subject();
			if (rs.next()) {
				subject = new Subject(rs.getInt(ID_COLUMN), rs.getString(TITLE_COLUMN), rs.getInt(COURSE_COLUMN));
			}
			sentencia1.close();
		} catch (SQLException ex) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
		} finally {
			if (rs != null)
				closeResultSet(rs);
			if (sentencia1 != null)
				closeStatement(sentencia1);
			if (connection != null)
				closeConnection(connection);
		}
		return subject;
	}

	public void remove(int id) {
		Connection connection = null;
		PreparedStatement sentencia1 = null;
		try {
			connection = basicDataSource.getConnection();

			String sql = "DELETE FROM  SUBJECT WHERE ID = ?";
			sentencia1 = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE,
					Statement.RETURN_GENERATED_KEYS);
			sentencia1.setInt(1, id);

			sentencia1.executeUpdate();
			sentencia1.close();
		} catch (SQLException ex) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
		} finally {

			if (sentencia1 != null)
				closeStatement(sentencia1);
			if (connection != null)
				closeConnection(connection);
		}
	}

	public void update(Subject subject) {
		Connection connection = null;
		PreparedStatement sentencia1 = null;
		try {
			connection = basicDataSource.getConnection();

			String sql = "UPDATE SUBJECT  SET TITLE = ?, COURSE = ? WHERE ID= ?";
			sentencia1 = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE,
					Statement.RETURN_GENERATED_KEYS);
			sentencia1.setString(1, subject.getTitle());
			sentencia1.setInt(2, subject.getCourse());
			sentencia1.setInt(3, subject.getId());

			sentencia1.executeUpdate();

			sentencia1.close();
		} catch (SQLException ex) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
		} finally {

			if (sentencia1 != null)
				closeStatement(sentencia1);
			if (connection != null)
				closeConnection(connection);
		}
	}

}
