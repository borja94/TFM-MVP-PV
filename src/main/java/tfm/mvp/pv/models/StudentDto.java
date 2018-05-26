/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

public class StudentDto extends Dto {

	public ResultSet Insert(Student student) {
		Connection conexion = null;
		ResultSet resultSet = null;
		PreparedStatement sentencia1 = null;
		try {
			conexion = basicDataSource.getConnection();

			String sql = "INSERT INTO STUDENT (NAME,SURNAME) VALUES(?,?)";
			sentencia1 = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia1.setString(1, student.getName());
			sentencia1.setString(2, student.getSurname());

			sentencia1.executeUpdate();
			resultSet = sentencia1.getGeneratedKeys();
			int id = -1;
			if (resultSet.next()) {
				id = resultSet.getInt(1);
			}

			for (Subject subject : student.getSubjectCollection()) {
				InsertSubjectStudent(id, subject.getId());
			}

			sentencia1.close();
		} catch (SQLException ex) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
		} finally {

			if (resultSet != null)
				CloseResultSet(resultSet);
			if (sentencia1 != null)
				ClosePreparedStatement(sentencia1);
			if (conexion != null)
				CloseConnection(conexion);
		}

		return resultSet;
	}

	public void Update(Student student) {
		Connection conexion = null;
		PreparedStatement sentencia1 = null;
		try {
			conexion = basicDataSource.getConnection();

			String sql = "UPDATE STUDENT  SET NAME = ?, SURNAME = ? WHERE ID= ?";
			sentencia1 = conexion.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE,
					Statement.RETURN_GENERATED_KEYS);
			sentencia1.setString(1, student.getName());
			sentencia1.setString(2, student.getSurname());
			sentencia1.setInt(3, student.getId());

			RemoveAllStudentSubjects(student.getId());
			for (Subject subject : student.getSubjectCollection()) {
				InsertSubjectStudent(student.getId(), subject.getId());
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

	public List<Student> GetAll() {

		List<Student> result = new ArrayList<>();
		Connection conexion = null;
		ResultSet rs = null;
		Statement sentencia = null;

		try {
			conexion = basicDataSource.getConnection();

			sentencia = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = sentencia.executeQuery("SELECT * FROM STUDENT ");

			rs.afterLast();
			while (rs.previous()) {

				Student student = new Student(rs.getInt("ID"), rs.getString(2), rs.getString(3));
				SubjectDto subjectDto = new SubjectDto();
				student.setSubjectCollection(subjectDto.GetByStudent(student.getId()));
				result.add(student);

			}
		} catch (SQLException ex) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);

		} finally {

			if (result != null)
				CloseResultSet(rs);
			if (sentencia != null)
				CloseStatement(sentencia);
			if (conexion != null)
				CloseConnection(conexion);
		}

		return result;
	}

	public Student Get(int id) {
		Connection conexion = null;
		Student student = null;
		PreparedStatement sentencia1 = null;
		ResultSet rs = null;
		try {
			conexion = basicDataSource.getConnection();

			String sql = "SELECT S.* , SUB.ID AS SUBJECT_ID , SUB.TITLE , SUB.COURSE " + "FROM STUDENT S "
					+ "LEFT JOIN STUDENT_SUBJECT SS " + "ON S.ID = SS.ID_STUDENT " + "LEFT JOIN SUBJECT SUB "
					+ "ON SS.ID_SUBJECT = SUB.ID " + " WHERE S.ID = ?";
			sentencia1 = conexion.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			sentencia1.setInt(1, id);

			rs = sentencia1.executeQuery();
			student = new Student();
			List<Subject> subjectCollection = new ArrayList<>();
			if (rs.next()) {
				student = new Student(rs.getInt("ID"), rs.getString("NAME"), rs.getString("SURNAME"));

				do {
					if (rs.getString("TITLE") != null) {
						subjectCollection
								.add(new Subject(rs.getInt("SUBJECT_ID"), rs.getString("TITLE"), rs.getInt("COURSE")));
					}
				} while (rs.next());
			}
			student.setSubjectCollection(subjectCollection);

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
		return student;
	}

	public void Remove(int id) {
		Connection conexion = null;
		PreparedStatement sentencia1 = null;
		try {
			conexion = basicDataSource.getConnection();

			String sql = "DELETE FROM  STUDENT WHERE ID = ?";
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

	public void RemoveAllStudentSubjects(int idStudent) {
		Connection conexion = null;
		PreparedStatement sentencia1 = null;
		try {
			conexion = basicDataSource.getConnection();
			String sql = "DELETE FROM  STUDENT_SUBJECT WHERE ID_STUDENT = ?";
			sentencia1 = conexion.prepareStatement(sql);
			sentencia1.setInt(1, idStudent);

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

	public void InsertSubjectStudent(int idStudent, int idSubject) {

		Connection conexion = null;
		PreparedStatement sentencia1 = null;
		try {
			conexion = basicDataSource.getConnection();
			String sql = "INSERT INTO STUDENT_SUBJECT (ID_SUBJECT,ID_STUDENT) VALUES(?,?)";
			sentencia1 = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia1.setInt(1, idSubject);
			sentencia1.setInt(2, idStudent);

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
}
