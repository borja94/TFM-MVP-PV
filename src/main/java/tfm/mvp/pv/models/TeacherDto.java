/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author borja
 */
public class TeacherDto extends Dto {

	public TeacherDto() {
		super();
	}

	public int Insert(Teacher teacher) {
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
				InsertSubjectTeacher(id, subject.getId());
			}

		} catch (SQLException ex) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);

		} finally {

			if (result != null)
				CloseResultSet(result);
			if (sentencia1 != null)
				ClosePreparedStatement(sentencia1);
			if (conexion != null)
				CloseConnection(conexion);
		}

		return id;
	}

	public void Update(Teacher teacher) {
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

			RemoveAllTeacherSubjects(teacher.getId());
			for (Subject subject : teacher.getSubjectCollection()) {
				InsertSubjectTeacher(teacher.getId(), subject.getId());
			}

			sentencia1.executeUpdate();

			sentencia1.close();
		} catch (SQLException ex) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
		} finally {
			if (sentencia1 != null)
				ClosePreparedStatement(sentencia1);
			if (conexion != null)
				CloseConnection(conexion);
		}
	}

	public List<Teacher> GetAll() {

		List<Teacher> result = new ArrayList<Teacher>();
		Connection conexion = null;
		Statement sentencia = null;
		ResultSet rs = null;

		try {
			conexion = basicDataSource.getConnection();

			sentencia = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = sentencia.executeQuery("SELECT * FROM TEACHER ");

			rs.afterLast();
			while (rs.previous()) {

				Teacher teacher = new Teacher(rs.getInt("ID"), rs.getString(2), rs.getString(3));
				SubjectDto subjectDto = new SubjectDto();
				teacher.setSubjectCollection(subjectDto.GetByTeacher(teacher.getId()));

				result.add(teacher);

			}
		} catch (SQLException ex) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
		} finally {
			if (rs != null)
				CloseResultSet(rs);
			if (sentencia != null)
				CloseStatement(sentencia);
			if (conexion != null)
				CloseConnection(conexion);
		}

		return result;
	}

	public Teacher Get(int id) {
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
				teacher = new Teacher(rs.getInt("ID"), rs.getString("NAME"), rs.getString("SURNAME"));

				do {
					if (rs.getString("TITLE") != null) {
						subjectCollection
								.add(new Subject(rs.getInt("SUBJECT_ID"), rs.getString("TITLE"), rs.getInt("COURSE")));
					}
				} while (rs.next());
			}
			teacher.setSubjectCollection(subjectCollection);

			sentencia1.close();
		} catch (SQLException ex) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
		} finally {
			if (rs != null)
				CloseResultSet(rs);
			if (sentencia1 != null)
				ClosePreparedStatement(sentencia1);
			if (conexion != null)
				CloseConnection(conexion);
		}

		return teacher;
	}

	public void Remove(int id) {
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
				ClosePreparedStatement(sentencia1);
			if (conexion != null)
				CloseConnection(conexion);
		}

	}

	public void RemoveAllTeacherSubjects(int idTeacher) {
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
				ClosePreparedStatement(sentencia1);
			if (conexion != null)
				CloseConnection(conexion);
		}

	}

	public void InsertSubjectTeacher(int idTeacher, int idSubject) {

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
				ClosePreparedStatement(sentencia1);
			if (conexion != null)
				CloseConnection(conexion);
		}

	}
}
