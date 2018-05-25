package tfm.mvp.pv.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author borja
 */
public class SubjectDto extends Dto {

	public SubjectDto() {
		super();
	}

	public void Insert(Subject subject) {
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
				ClosePreparedStatement(sentencia1);
			if (connection != null)
				CloseConnection(connection);
		}

	}

	public List<Subject> GetAll() {

		List<Subject> result = new ArrayList<>();
		Connection connection = null;
		Statement sentencia = null;
		ResultSet rs = null;
		try {
			connection = basicDataSource.getConnection();

			sentencia = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = sentencia.executeQuery("SELECT * FROM SUBJECT ");

			while (rs.next()) {
				result.add(new Subject(rs.getInt("ID"), rs.getString("TITLE"), rs.getInt("COURSE")));
			}
		} catch (SQLException ex) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
		} finally {
			if (rs != null)
				CloseResultSet(rs);
			if (sentencia != null)
				CloseStatement(sentencia);
			if (connection != null)
				CloseConnection(connection);
		}

		return result;
	}

	public List<Subject> GetByTeacher(int idTeacher) {
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
				result.add(new Subject(rsSuebjects.getInt("ID"), rsSuebjects.getString("TITLE"),
						rsSuebjects.getInt("COURSE")));
			}

		} catch (SQLException ex) {

			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);

		} finally {
			if (rsSuebjects != null)
				CloseResultSet(rsSuebjects);
			if (statement != null)
				ClosePreparedStatement(statement);
			if (connection != null)
				CloseConnection(connection);
		}
		return result;
	}
	
	public List<Subject> GetByStudent(int idStudent) {
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
				result.add(new Subject(rsSuebjects.getInt("ID"), rsSuebjects.getString("TITLE"),
						rsSuebjects.getInt("COURSE")));
			}

		} catch (SQLException ex) {

			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);

		} finally {
			if (rsSuebjects != null)
				CloseResultSet(rsSuebjects);
			if (statement != null)
				ClosePreparedStatement(statement);
			if (connection != null)
				CloseConnection(connection);
		}
		return result;
	}

	public List<Subject> GetByStudent() {
		List<Subject> result = new ArrayList<>();

		return result;
	}

	public Subject Get(int id) {
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
				subject = new Subject(rs.getInt("ID"), rs.getString("TITLE"), rs.getInt("COURSE"));
			}
			sentencia1.close();
		} catch (SQLException ex) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
		} finally {
			if (rs != null)
				CloseResultSet(rs);
			if (sentencia1 != null)
				CloseStatement(sentencia1);
			if (connection != null)
				CloseConnection(connection);
		}
		return subject;
	}

	public void Remove(int id) {
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
				CloseStatement(sentencia1);
			if (connection != null)
				CloseConnection(connection);
		}
	}

	public void Update(Subject subject) {
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
				CloseStatement(sentencia1);
			if (connection != null)
				CloseConnection(connection);
		}
	}

}
